package common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.prefs.Preferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;

/**
 * 工具类.
 * 
 * @author wblishq
 * 
 */
public class CommonUtil {
	static Random r = new Random();

	/**
	 * 转换为utf8字符串. 
	 * @param s
	 * @return
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 返回一个随机字符串.
	 * 
	 * @return
	 */
	public static String getRandomStr() {
		return new BigInteger(165, r).toString(36).toUpperCase();
	}

	public static void main(String[] a) {
		CommonUtil.log("123");
		System.out.println(CommonUtil.readFileWithLine(
				"E:\\1616智能网盘\\renjie120\\java程序员认证的标准.txt", "\n"));
		CommonUtil util = new CommonUtil();
		System.out.println(util.getPropValueNew("/resource/db.properties",
				"test"));

		// 演示java的eval表达式的类
		CommonUtil.sumMoneyInMonths(120, 1000, 0.05);

		// CommonUtil.getLocaleLaguages();

		Locale currentLocale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle(
				"common.util.MyResource", currentLocale);
		System.out.println(bundle.getString("hello"));
	}

	/**
	 * 计算连续多月储蓄最后得到的本息和！
	 * 
	 * @param monthnum
	 *            月数
	 * @param money
	 *            每月存储的金额
	 * @param fee
	 *            利息
	 */
	public static void sumMoneyInMonths(int monthnum, double money, double fee) {
		FelEngine fel = new FelEngineImpl();
		BigDecimal sum = new BigDecimal(0);
		for (int i = 0; i < monthnum; i++) {
			String str = ""
					+ fel.eval(money + "+" + money + "*" + (monthnum - i) + "*"
							+ fee + "/12.0");
			BigDecimal bd = BigDecimal.valueOf(Double.parseDouble(str));
			sum = sum.add(bd);
		}
		System.out
				.println(money
						+ "连续储蓄"
						+ monthnum
						+ "个月，将得到"
						+ df.format(sum)
						+ "元！其中，利息是："
						+ df.format(sum.subtract(BigDecimal.valueOf(money
								* monthnum))));
	}

	// 用来对注册表进行操作
	private static Preferences p = Preferences
			.userNodeForPackage(CommonUtil.class);
	// 日期转换的格式
	private static DecimalFormat df = new DecimalFormat("#######.####");
	// 缓存属性文件的hashTable
	private Hashtable props;

	public CommonUtil() {
		props = new Hashtable();
	}

	/**
	 * 检查是否文件存在.
	 * 
	 * @param file
	 * @return
	 */
	public static boolean checkFileExist(String file) {
		File ff = new File(file);
		if (!ff.exists()) {
			log("输入的文件名不存在,请重新输入。");
			return false;
		}
		return true;
	}

	/**
	 * 检查目录是否存在.
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean checkDirExist(String dir) {
		File ff = new File(dir);
		if (!ff.exists()) {
			log("输入的目录名不存在,请重新输入。");
			return false;
		}
		if (!ff.isDirectory()) {
			log("输入的目录名不存在,请重新输入。");
			return false;
		}
		return true;
	}

	/**
	 * 写入注册表信息.
	 * 
	 * @param key
	 * @param val
	 */
	public static void register(String key, String val) {
		p.put(key, val);
	}

	/**
	 * 获取注册表信息.
	 * 
	 * @param key
	 * @param defaultStr
	 *            如果不存在,返回默认值.
	 * @return
	 */
	public static String getRegisterKey(String key, String defaultStr) {
		return (String) p.get(key, defaultStr);
	}

	/**
	 * 获取注册表信息.
	 * 
	 * @param key
	 * @return
	 */
	public static String getRegisterKey(String key) {
		return (String) p.get(key, null);
	}

	/**
	 * 返回对象的非空字符串.空则返回"".
	 * 
	 * @param obj
	 * @return
	 */
	public static String notBlank(Object obj) {
		if (obj == null)
			return "";
		return obj.toString();
	}

	/**
	 * 新的读取属性文件的方法.
	 * 
	 * @param fname
	 *            文件名为从class根目录下面算起的路径.
	 * @param key
	 * @return
	 */
	public String getPropValueNew(String fname, String key) {
		try {
			Properties p = null;
			if (props.get(fname) == null) {
				InputStream is = this.getClass().getResourceAsStream(fname);
				p = new Properties();
				p.load(is);
				is.close();
				props.put(fname, p);
			} else {
				p = (Properties) props.get(fname);
			}
			if (p != null && p.getProperty(key) != null) {
				String temp = p.getProperty(key).trim();
				return new String(temp.getBytes("iso-8859-1"), "GBK");
			} else {
				return "";
			}
		} catch (Exception e) {
			System.out.println("出现错误...");
			return null;
		}
	}

	/**
	 * 返回属性文件.
	 * 
	 * @param fname
	 * @param key
	 * @param defaultStr
	 * @return
	 */
	public String getPropValueNew(String fname, String key, String defaultStr) {
		return CommonUtil.notBlankStr(getPropValueNew(fname, key), defaultStr);
	}

	/**
	 * 写日志.
	 * 
	 * @param obj
	 */
	public void writeLog(Object obj) {
		writeLog(getClass().getName(), obj);
	}

	/**
	 * 使用Log4j写日志.
	 * 
	 * @param classname
	 * @param obj
	 */
	public void writeLog(String classname, Object obj) {
		Log log = LogFactory.getLog(classname);
		if (obj instanceof Exception)
			log.error(classname, (Exception) obj);
		else {
			log.error(obj);
		}
	}

	/**
	 * 将字符串写进文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param contant
	 *            要写入文件的字符串
	 */
	public static void writeFile(String fileName, String contant) {
		PrintWriter out;
		try {
			File file = new File(fileName);
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			out.print(contant);
			out.close();
		} catch (IOException e) {
			log("读写文件出现异常！");
		} catch (Exception e) {
			log("出现异常");
		}
	}

	/**
	 * 静态的输出错误日志.
	 * 
	 * @param obj
	 */
	public static void log(Object obj) {
		Log log = LogFactory.getLog("CommonUtil");
		if (obj instanceof Exception)
			log.error(null, (Exception) obj);
		else {
			log.error(obj);
		}
	}

	/**
	 * 将数组写入到文件中，数组中每个元素为一行
	 * 
	 * @param fileName
	 * @param rows
	 */
	public static void writeFile(String fileName, Object[] rows) {
		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			for (int temp = 0; temp < rows.length; temp++) {
				out.println(rows[temp]);
			}
			out.close();
		} catch (IOException e) {
			log("读写文件出现异常！");
		} catch (Exception e) {
			log("出现异常");
		}
	}

	/**
	 * 将指定文件中的内容已每行转换为字符串数组
	 * 
	 * @param fileName
	 * @return
	 */
	public static String[] readFileToStrArr(String fileName) {
		BufferedReader in;
		ArrayList list = new ArrayList();
		String[] result = null;
		try {
			// 定义文件读的数据流
			in = new BufferedReader(new FileReader(fileName));
			String s;
			while ((s = in.readLine()) != null) {
				list.add(s);
			}
			result = new String[list.size()];
			Iterator it = list.iterator();
			int index = 0;
			while (it.hasNext()) {
				result[index++] = it.next().toString();
			}
			return result;
		} catch (FileNotFoundException e) {
			log("找不到文件！");
			throw new Exception("文件找不到！");
		} catch (IOException e) {
			log("出现异常！");
			throw new Exception("文件找不到！");
		} finally {
			return result;
		}
	}

	/**
	 * 返回字符串的非空形式，如果空就返回""
	 * 
	 * @param oldStr
	 * @return
	 */
	public static String notBlank(String oldStr) {
		if (isBlank(oldStr)) {
			return "";
		}
		return oldStr;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean notNull(String str) {
		return str != null && !"".equals(str);
	}

	/**
	 * 将字符串转换为非空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String changeToNotNull(String str) {
		if (!notNull(str)) {
			return "";
		}
		return str;
	}

	/**
	 * 将字符串转换为非空字符串
	 * 
	 * @param str
	 * @param val
	 *            默认的值
	 * @return
	 */
	public static String changeToNotNull(String str, String val) {
		if (!notNull(str)) {
			return val;
		}
		return str;
	}

	/**
	 * 返回字符串的非空形式，如果是空就返回默认的字符串形式
	 * 
	 * @param oldStr
	 * @param defaultStr
	 * @return
	 */
	public static String notBlank(String oldStr, String defaultStr) {
		if (isBlank(oldStr)) {
			return defaultStr;
		}
		return oldStr;
	}

	/**
	 * 根据列表集合返回符合表格数据的json串,字段顺序是根据反射java形成,是不变的.
	 * 
	 * @param dataList
	 *            表格数据的集合
	 * @param total
	 *            数据的总行数
	 * @param page
	 *            当前的页数
	 * @return
	 */
	public static String getGridJsonStr(List dataList, long total, int page) {
		StringBuffer buf = new StringBuffer();
		if (total == 0)
			page = 0;
		buf.append("{page:").append(page).append("\n,total:").append(total)
				.append("\n,rows:[");
		if (dataList != null && dataList.size() > 0) {
			Iterator it = dataList.iterator();
			int rowNum = 1;
			while (it.hasNext()) {
				buf.append("{");
				Map allProperties = ReflectionUtil.getAllProperties(it.next());
				Set entrySet = allProperties.entrySet();
				Iterator iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					Object value = entry.getValue();
					Object key = entry.getKey();
					buf.append(key).append(":'").append(
							CommonUtil.notBlankStr(value)).append("',");
				}
				buf.deleteCharAt(buf.lastIndexOf(",")).append("},\n");
			}
			buf.deleteCharAt(buf.lastIndexOf(","));
		} else {
			buf.append("{'id':null,'name':null}");
		}
		buf.append("]}");
		return buf.toString();
	}

	/**
	 * 得到ext的表格的数据s
	 * 
	 * @param dataList
	 * @param total
	 * @param page
	 * @return
	 */
	public static String getExtGridJsonStr(List dataList, int total, int page) {
		StringBuffer buf = new StringBuffer();
		if (total == 0)
			page = 0;
		buf.append("{totalNum:").append(total).append("\n,root:[");
		if (dataList != null) {
			Iterator it = dataList.iterator();
			int rowNum = 1;
			while (it.hasNext()) {
				buf.append("{");
				Map allProperties = ReflectionUtil.getAllProperties(it.next());
				Set entrySet = allProperties.entrySet();
				Iterator iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					Object value = entry.getValue();
					Object key = entry.getKey();
					buf.append(key).append(":'").append(value).append("',");
				}
				buf.deleteCharAt(buf.lastIndexOf(",")).append("},\n");
			}
			buf.deleteCharAt(buf.lastIndexOf(","));
		} else {
			buf.append("{}");
		}
		buf.append("]}");
		return buf.toString();
	}

	/**
	 * 得到分页查询的sql语句
	 * 
	 * @param queryStr
	 *            原始sql语句
	 * @param start
	 *            起始行(最小为1)
	 * @param end
	 *            终止行
	 * @return
	 */
	public static String getRealQuerySql(String queryStr, int start, int end) {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT *																						");
		buf.append("  FROM (SELECT T_T_T.*, ROWNUM ROWCOUNT             ");
		buf.append("          FROM (").append(queryStr).append(") T_T_T  ");
		buf.append("         WHERE ROWNUM < ").append(end).append(")    ");
		buf.append(" WHERE ROWCOUNT >= ").append(start);
		return buf.toString();
	}

	/**
	 * 在形成ext表格的时候得到分页的相关数组信息.
	 * 
	 * @param start
	 *            起始位置
	 * @param limit
	 *            每页的行数
	 * @param total
	 *            总行数
	 * @return [起始位置，终止位置，当前页数]
	 */
	public static int[] getStartAndEnd(int start, int limit, int total) {
		return new int[] { start, start + limit, start / limit + 1 };
	}

	/**
	 * 判断是否为空字符串
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果不为空返回true
	 */
	public static boolean isNotBlank(String str) {
		return (str != null && !"".equals(str)) ? true : false;
	}

	/**
	 * 判断是否为空字符串
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果为空返回true
	 */
	public static boolean isBlank(String str) {
		return !isNotBlank(str);
	}

	/**
	 * 判断对象是否为空，空就返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isBlank(Object obj) {
		return !isNotBlank(obj);
	}

	/**
	 * 判断对象是否为非空，非空就返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotBlank(Object obj) {
		return obj != null;
	}

	/**
	 * 返回对象的非空字符串形式
	 * 
	 * @param obj
	 * @param defaultStr
	 * @return
	 */
	public static String notBlankStr(Object obj, String defaultStr) {
		if (isBlank(obj) || "".equals(obj))
			return defaultStr;
		return obj.toString();
	}

	/**
	 * 返回非空字符串，是空就返回‘’
	 * 
	 * @param obj
	 * @return
	 */
	public static String notBlankStr(Object obj) {
		if (isBlank(obj))
			return "";
		return obj.toString();
	}

	/**
	 * 判断是否为空字符串(包括空格)
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果不为空返回true
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && !"".equals(str.trim())) ? true : false;
	}

	/**
	 * 判断是否为空字符串(包括空格)
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果为空返回true
	 */
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * 字符串比较
	 * 
	 * @param src
	 * @param des
	 * @return
	 */
	public static boolean equals(String src, String des) {
		if (src == null)
			return (des == null ? true : false);
		if (des == null)
			return (src == null ? true : false);
		return src.equals(des);
	}

	/**
	 * 将String数组变成","号间隔的字符串
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果为空返回true
	 */
	public static String StringArrayToString(String[] str) {
		StringBuilder sb = new StringBuilder();
		if (str != null && str.length > 0) {
			for (String s : str) {
				if (s != null) {
					sb.append(s + ",");
				}
			}
			if (sb.length() == 0)
				return "";
			return sb.substring(0, sb.length() - 1).toString();
		}
		return str[0];
	}

	/**
	 * 判断URL后缀是否为.action,如果是的话，提取actionName
	 * 
	 * @param servletPath
	 *            request.getServletPath()
	 * @return actionName
	 */
	public static String parseServletPath(String servletPath, String fiexdStr) {
		fiexdStr = "." + fiexdStr;
		if (null != servletPath && !"".equals(servletPath)) {
			if (servletPath.contains(fiexdStr)) {
				String actionName = servletPath.substring(servletPath
						.lastIndexOf("/") + 1, servletPath.indexOf(fiexdStr));
				return actionName;
			}
		}
		return "";
	}

	/**
	 * 根据文件名得到所属的目录名.
	 * 
	 * @param filename
	 * @return
	 */
	public static String getDirName(String filename) {
		String ans = null;
		if (null != filename && !"".equals(filename)) {
			ans = filename.substring(0, filename.lastIndexOf("\\"));
		}
		return ans;
	}

	/**
	 * 读取指定文件的内容，返回文本字符串.
	 * 
	 * @param fileName
	 *            文件名
	 * @param linkChar
	 *            换行符号
	 * @return
	 */
	public static String readFile(String fileName, String linkChar) {
		StringBuffer sb = new StringBuffer();
		BufferedReader in;
		String result = "";
		try {
			// 定义文件读的数据流
			in = new BufferedReader(new FileReader(fileName));
			String s;
			while ((s = in.readLine()) != null) {
				sb.append(s);
				// 定义每一行的数据读取之后采用美元连接
				sb.append(linkChar);
			}
			in.close();
			int i = linkChar.length();
			result = sb.toString();
			result = result.subSequence(0, sb.length() - i).toString();
		} catch (FileNotFoundException e) {
			log("找不到文件！");
			throw new Exception("文件找不到！");
		} catch (IOException e) {
			log("出现异常！");
			throw new Exception("文件找不到！");
		} catch (Exception e) {
			e.printStackTrace();
			log("出现异常！");
			throw new Exception("文件找不到！");
		} finally {
			return result;
		}
	}

	public static String readFileWithLine(String fileName, String linkChar) {
		StringBuffer sb = new StringBuffer();
		BufferedReader in;
		LineableReader rl;
		String result = "";
		try {
			// 定义文件读的数据流
			in = new BufferedReader(new FileReader(fileName));
			rl = new LineableReader(in);
			String s;
			while ((s = rl.readLine()) != null) {
				sb.append(rl.getLineNumber() + ":" + s);
				// 定义每一行的数据读取之后采用美元连接
				sb.append(linkChar);
			}
			rl.close();
			int i = linkChar.length();
			result = sb.toString();
			result = result.subSequence(0, sb.length() - i).toString();
		} catch (FileNotFoundException e) {
			log("找不到文件！");
			throw new Exception("文件找不到！");
		} catch (IOException e) {
			log("出现异常！");
			throw new Exception("文件找不到！");
		} catch (Exception e) {
			e.printStackTrace();
			log("出现异常！");
			throw new Exception("文件找不到！");
		} finally {
			return result;
		}
	}

	/**
	 * 返回一个map的字符串形式.
	 * 
	 * @param map
	 * @return
	 */
	public static String getStrFromMap(Map map) {
		StringBuffer buf = new StringBuffer();
		if (map != null) {
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String v = it.next().toString();
				buf.append("['").append(v).append("',").append(map.get(v))
						.append("]").append(",");
			}
			buf.deleteCharAt(buf.length() - 1);
			return buf.toString();
		}
		return "";
	}

	/**
	 * 返回一个目录下面的全部的jsp页面地址.
	 * 
	 * @param filename
	 * @return
	 */
	public static ArrayList seeAllJspFile(String filename) {
		File tempFile = new File(filename);
		ArrayList ans = new ArrayList();
		if (tempFile.isFile() && tempFile.getName().endsWith(".jsp")) {
			ans.add(tempFile.getAbsolutePath());
		} else {
			String[] list;
			list = tempFile.list();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					Iterator it = seeAllJspFile(filename + "\\" + list[i])
							.iterator();
					while (it.hasNext()) {
						ans.add(it.next());
					}
				}
			}
		}
		return ans;
	}

	/**
	 * 根据规则生成自己的sql字符串.
	 * 
	 * @param str
	 *            例如：select * from moneys
	 * @param start
	 * @param end
	 * @param whereStr
	 *            例如：mid<100 and desc = "支出"
	 * @param sortStr
	 *            例如：order by mid,mtime desc
	 * @return
	 */
	public static String getQueryStr(String str, int start, int end,
			String whereStr, String sortStr) {
		StringBuffer sqlbf = new StringBuffer();
		sqlbf.append("select *									");
		sqlbf
				.append("  from (select t2_t2_t2.*, ROWNUM ROWCOUNT                                ");
		sqlbf
				.append("          from (select t1_t1_t1.*                                         ");
		sqlbf.append("                  from (" + str
				+ ")t1_t1_t1 where 1=1        ");
		if (notNull(whereStr)) {
			sqlbf.append(" and " + whereStr);
		}
		if (notNull(sortStr)) {
			sqlbf.append(sortStr);
		}
		sqlbf.append("  ) t2_t2_t2                                 ");
		sqlbf.append("         where ROWNUM < " + end
				+ ")                                               ");
		sqlbf.append(" where ROWCOUNT >= " + start);
		return sqlbf.toString();
	}

	/**
	 * 判断参数2字符串是否与参数1字符串相等
	 * 
	 * @param str1
	 *            被比较的字符串
	 * @param str2
	 *            比较的字符串
	 * @return boolean
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1.toLowerCase().equals(str2.toLowerCase());
	}

	/**
	 * 统计总和函数。
	 * 
	 * @return
	 */
	public static double getSum(List l) {
		double ans = 0;
		Iterator mit = l.iterator();
		while (mit.hasNext()) {
			ans = CommonUtil
					.add(Double.parseDouble(mit.next().toString()), ans);
		}
		return ans;
	}

	/**
	 * 计算平均数函数。
	 * 
	 * @param l
	 * @return
	 */
	public static double getAvg(List l) {
		double ans = 0;
		ans = CommonUtil.divide(getSum(l), l.size(), 2, RoundingMode.HALF_UP);
		return ans;
	}

	/**
	 * 转换日期为指定日期格式的字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToStr(Date date, String formatStr) {
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		return df.format(date);
	}

	/**
	 * 转换日期字符串到日期。
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param formatStr
	 *            格式化字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String dateString, String formatStr)
			throws ParseException {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat(formatStr);
		Date timeDate = dateFormat.parse(dateString);// util类型
		return timeDate;
	}

	/**
	 * 减法
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double subtract(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return Double.parseDouble(df.format(b1.subtract(b2).doubleValue()));
	}

	/**
	 * 乘法
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double multiply(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return Double.parseDouble(df.format(b1.multiply(b2).doubleValue()));
	}

	/**
	 * 加法
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return Double.parseDouble(df.format(b1.add(b2).doubleValue()));
	}

	/**
	 * 除法
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 *            精确度
	 * @param mode
	 *            舍入方式
	 * @return
	 */
	public static double divide(double d1, double d2, int scale,
			RoundingMode mode) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return Double.parseDouble(df.format(b1.divide(b2, scale, mode)
				.doubleValue()));
	}

	/**
	 * 除法
	 * 
	 * @param d1
	 *            被除数
	 * @param d2
	 *            除数
	 * @param scale
	 *            小数点精度
	 * @return
	 */
	public static double divide(double d1, double d2, int scale) {
		return divide(d1, d2, scale, RoundingMode.HALF_UP);
	}

	/**
	 * 得到支持的本地语言。
	 */
	public static void getLocaleLaguages() {
		Locale[] localList = Locale.getAvailableLocales();
		for (int i = 0; i < localList.length; i++) {
			System.out.println(localList[i].getDisplayCountry() + "="
					+ localList[i].getCountry() + " "
					+ localList[i].getLanguage() + " 展示语言："
					+ localList[i].getDisplayName());
		}

		Locale lc = Locale.getDefault();
		System.out.println(lc.getDisplayCountry() + "=" + lc.getCountry() + " "
				+ lc.getLanguage() + " 展示语言：" + lc.getDisplayName());

		System.out
				.println(MessageFormat.format("{0}，现在时间是：{1}", "ahha", "aaa"));
	}
}

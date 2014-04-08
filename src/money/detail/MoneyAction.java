package money.detail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import common.base.AllSelect;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportStrGenerate;
import common.util.CommonUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 理财管理.
 * 
 * @author lsq
 * 
 */
public class MoneyAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MoneyManager mMgr = bf.getManager(BeanManagerKey.moneyManager);
	private Money moneyVo;
	private int moneySno;
	private String year;
	private String month;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	private double money;
	private Date moneyTime;
	private String moneyType;
	private String moneyDesc;
	private int shopCard;
	private String bookType;
	private File upload;
	private String arg;
	// 下面的两个属性是用来接收依赖注入的属性----如果定义了file上传的属性为xxx，还要再定义两个属性用来封装类型和文件名！（如果不写怎么样？）
	private String uploadContentType;
	private String uploadFileName;
	// 设置允许上传的文件类型
	private String allowTypes;
	// 下面的属性可以通过配置文件来配置，允许动态设置---典型的依赖注入---见这个action的配置文件。
	private String savePath;

	public double getMoney() {
		return money;
	}

	public String beforeAdd() {
		return "detail";
	}

	/**
	 * 指向下载界面.
	 * 
	 * @return
	 */
	public String initImport() {
		return "import";
	}

	public String technology() {
		return "technology";
	}

	public String newindex() {
		return "newindex";
	}

	public String about() {
		return "about";
	}

	/**
	 * 模板下载.
	 * 
	 * @return
	 */
	public String model() {
		response.setContentType("Application/excel");
		String fileNameString = CommonUtil.toUtf8String("result.xls");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileNameString);

		XlsExport e = new XlsExport();
		e.createRow(0);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.toString());
		}
		e.exportXls(response);
		return null;
	}

	public String importExcel() throws Exception {
		if (CommonUtil.isEmpty(uploadFileName)
				|| !(uploadFileName.endsWith(".xls") || uploadFileName
						.endsWith(".xlsx"))) {
			writeToPage(response, "请上传excel文件!");
			return null;
		}
		// 得到文件后缀名
		String fileType = uploadFileName.substring(uploadFileName.indexOf("."),
				uploadFileName.length());
		// 得到新的文件名..防止重名.
		String newfile = uploadFileName.substring(0,
				uploadFileName.indexOf("."))
				+ getContextUser().getUserName()
				+ System.currentTimeMillis()
				+ "." + fileType;
		// 下面的文件上传路径先要保证在服务器上面已经存在！
		String desFileString = getSavePath() + "\\" + newfile;
		FileOutputStream fos = new FileOutputStream(desFileString);
		FileInputStream fis = new FileInputStream(upload);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		File f = new File(desFileString);
		if (f.exists()) {
			// 导入excel中的数据到数据库.
			mMgr.importFromExcel(f);
		}
		writeToPage(response, "导入成功!");
		return null;
	}

	private static int DEFAULT_PAGE_SIZE = 10;

	class MoneyReport {
		private double money;
		private String moneyType;
		private String parentType;
		private String year;
		private String month;
	}

	public String gridTree() {
		return "gridTree";
	}

	public String moneyGridTree() {
		Collection<Object[]> c = mMgr.reportMoneyGroupByYear();
		LinkedHashSet<Integer> yearSet = new LinkedHashSet<Integer>();
		LinkedHashSet<String> typeSet = new LinkedHashSet<String>();
		HashMap<String, Double> yearAndTypeToMoney = new HashMap<String, Double>();
		HashMap<String, Double> yearMonthAndTypeToMoney = new HashMap<String, Double>();
		HashMap<String, String> typeToDesc = new HashMap<String, String>();
		HashMap<Integer, LinkedHashSet> yearToMonth = new HashMap<Integer, LinkedHashSet>();
		StringBuffer buf = new StringBuffer();
		try {
			Iterator<Object[]> yearIt = c.iterator();

			if (yearIt != null) {
				while (yearIt.hasNext()) {
					Object[] obj = yearIt.next();
					yearSet.add(Integer.parseInt("" + obj[0]));
					typeSet.add("" + obj[2]);
					typeToDesc.put(obj[2] + "", obj[3] + "");
					yearAndTypeToMoney.put(obj[0] + "-" + obj[2],
							Double.parseDouble("" + obj[1]));
				}

				Iterator<Integer> allYearIt = yearSet.iterator();
				while (allYearIt.hasNext()) {
					int year = allYearIt.next();
					Collection<Object[]> c2 = mMgr
							.reportMoneyGroupByMonth(year);
					if (c2 != null) {
						Iterator<Object[]> allMonthIt = c2.iterator();
						while (allMonthIt.hasNext()) {
							Object[] monthObject = allMonthIt.next();
							LinkedHashSet<String> thisYearMonth = (LinkedHashSet<String>) yearToMonth
									.get(year);
							if (thisYearMonth == null) {
								thisYearMonth = new LinkedHashSet<String>();
								thisYearMonth.add(monthObject[1].toString());
								yearToMonth.put(year, thisYearMonth);
							} else {
								thisYearMonth.add(monthObject[1].toString());
							}
							yearMonthAndTypeToMoney.put(monthObject[0] + "-"
									+ monthObject[1] + "-" + monthObject[3],
									Double.parseDouble("" + monthObject[2]));
						}
					}
				}

				buf.append("{total:" + yearSet.size() + ",page:1,data[");
				allYearIt = yearSet.iterator();
				while (allYearIt.hasNext()) {
					int year = allYearIt.next();
					buf.append("{");
					buf.append("\"time\":" + year + ",\"parentTime\":null,");
					for (String tp : typeSet) {
						Double d = yearAndTypeToMoney.get(year + "-" + tp);
						buf.append("\"type_" + tp + "\":\""
								+ ("null".equals(d) || d == null ? "0" : d)
								+ "\",");

					}
					buf.deleteCharAt(buf.lastIndexOf(","));
					buf.append("},");
					LinkedHashSet<String> thisYearMonth = (LinkedHashSet<String>) yearToMonth
							.get(year);
					for (String m : thisYearMonth) {
						buf.append("{");
						buf.append("\"time\":" + m + ",\"parentTime\":" + year
								+ ",");
						for (String tp : typeSet) {
							Double d = yearMonthAndTypeToMoney.get(year + "-"
									+ m + "-" + tp);
							buf.append("\"type_"
									+ tp
									+ "\":\""
									+ (("null".equals(d) || d == null) ? "0"
											: d) + "\",");
						}
						buf.deleteCharAt(buf.lastIndexOf(","));
						buf.append("},");
					}
				}
				buf.deleteCharAt(buf.lastIndexOf(","));
				buf.append("]}");
			}
			String jsonStr = buf.toString();
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("json串:" + jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static enum ExportFiled {
		MONEY_TIME, MONEY, MONEY_TYPE, MONEY_DESC;
		@Override
		public String toString() {
			switch (this.ordinal()) {
			case 0:
				return "时间";
			case 1:
				return "金额";
			case 2:
				return "类型";
			case 3:
				return "描述";
			default:
				return "";
			}
		}
	}

	public String export() {
		String fileNameString = CommonUtil.toUtf8String("金额列表导出结果.xls");
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileNameString);

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<MoneySearchFields, Object> criterias = getCriterias();

		Collection<Money> moneyList = mMgr.searchMoney(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.toString());
		}

		for (Money money : moneyList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case MONEY_TIME:
					e.setCell(filed.ordinal(), money.getMoneyTime());
					break;
				case MONEY:
					e.setCell(filed.ordinal(), money.getMoney());
					break;
				case MONEY_TYPE:
					e.setCell(filed.ordinal(), money.getMoneyType());
					break;
				case MONEY_DESC:
					e.setCell(filed.ordinal(), money.getMoneyDesc());
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	/**
	 * 添加信息.
	 * 
	 * @return
	 */
	public String doAdd() {
		try {
			MoneyImpl moneyImpl = new MoneyImpl(moneyTime, money, moneyType,
					moneyDesc, shopCard, bookType);
			mMgr.createMoney(moneyImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String report() {
		return "report";
	}

	/**
	 * 删除信息.
	 * 
	 * @return
	 */
	public String doDelete() {
		String ids = request.getParameter("ids");
		mMgr.removeMoney(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	/**
	 * 得到详细信息.
	 * 
	 * @return
	 */
	public String beforeUpdate() {
		moneyVo = mMgr.getMoney(moneySno);
		return "editdetail";
	}

	/**
	 * 更新信息.
	 * 
	 * @return
	 */
	public String doUpdate() {
		try {
			MoneyImpl moneyImpl = new MoneyImpl(moneyTime, money, moneyType,
					moneyDesc, shopCard, bookType, moneySno);
			mMgr.updateMoney(moneyImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	private int page = 1;
	private int pageSize = 50;
	private long count;
	private String moneyTypeName;

	public String getMoneyTypeName() {
		return moneyTypeName;
	}

	public void setMoneyTypeName(String moneyTypeName) {
		this.moneyTypeName = moneyTypeName;
	}

	public String queryByType() {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(new Date());
		int iMonth = ca.get(Calendar.MONTH) + 1;
		int iYear = ca.get(Calendar.YEAR);
		if (!CommonUtil.isNotEmpty(month))
			month = iMonth + "";
		if (!CommonUtil.isNotEmpty(year))
			year = iYear + "";

		Map<MoneySearchFields, Object> criterias = getCriterias();

		Collection<Money> ans = mMgr.searchMoneyByType(criterias);
		StringBuilder build = new StringBuilder();
		build.append("{");
		if (ans != null && ans.size() > 0) {
			for (Money v : ans) {
				build.append("type:'" + v.getMoneyType() + "',");
				build.append("money:'" + v.getMoney() + "',");
			}
			build = build.deleteCharAt(build.lastIndexOf(","));
		}
		build.append("}");
		writeToPage(response, build.toString());
		return null;
	}

	/**
	 * 查询信息.
	 * 
	 * @return
	 */
	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(new Date());
		int iMonth = ca.get(Calendar.MONTH) + 1;
		int iYear = ca.get(Calendar.YEAR);
		if (!CommonUtil.isNotEmpty(month))
			month = iMonth + "";
		if (!CommonUtil.isNotEmpty(year))
			year = iYear + "";

		Map<MoneySearchFields, Object> criterias = getCriterias();

		Collection<Money> moneyList = mMgr.searchMoney(criterias,
				realOrderField(), startIndex, numPerPage);

		moneyGridTree();
		Collection<Money> ans = mMgr.searchMoneyByType(criterias);
		double shouru = 0;
		double zhichu = 0;
		if (ans != null && ans.size() > 0) {
			for (Money v : ans) {
				if ("1".equals(v.getMoneyType()))
					shouru = v.getMoney();
				else if ("2".equals(v.getMoneyType()))
					zhichu = v.getMoney();
			}
		}
		request.setAttribute("zhichu", zhichu);
		request.setAttribute("shouru", shouru);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("moneyTypeName", moneyTypeName);
		request.setAttribute("moneyType", moneyType);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", mMgr.searchMoneyNum(criterias));
		ActionContext.getContext().put("list", moneyList);
		return "list";
	}

	public String reQuery() {
		return "list";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private Map<MoneySearchFields, Object> getCriterias() {
		Map<MoneySearchFields, Object> criterias = new HashMap<MoneySearchFields, Object>();
		criterias.put(MoneySearchFields.YEAR, year);
		criterias.put(MoneySearchFields.MONTH, month);
		if (CommonUtil.isNotEmpty(moneyType))
			criterias.put(MoneySearchFields.MONEY_TYPE, moneyType);
		if (moneyVo == null)
			return criterias;
		if (moneyVo.getId() > 0)
			criterias.put(MoneySearchFields.MONEY_SNO, moneyVo.getId());
		if (CommonUtil.isNotEmpty(moneyVo.getBookType()))
			criterias.put(MoneySearchFields.BOOK_TYPE, moneyVo.getBookType());
		if (CommonUtil.isNotEmpty(moneyVo.getMoneyDesc()))
			criterias.put(MoneySearchFields.MONEY_DESC,
					"%" + moneyVo.getMoneyDesc() + "%");
		if (!CommonUtil.isBlank(moneyVo.getMoneyTime()))
			criterias.put(MoneySearchFields.MONEY_TIME, moneyVo.getMoneyTime());
		if (moneyVo.getMoney() > 0)
			criterias.put(MoneySearchFields.MONEY, moneyVo.getMoney());
		if (moneyVo.getShopCard() > 0)
			criterias.put(MoneySearchFields.SHOP_CARD, moneyVo.getShopCard());
		return criterias;
	}

	/**
	 * 按照类别统计数量.
	 * 
	 * @return
	 */
	public String reportCountByType() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy("bigtype").count().colomns(new String[] { "bigtype" })
				.build().generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + " ]";
			}

		});
		writeToPage(response, ans);
		return null;
	}
	
	/**
	 * 统计功过记录的数据报表图.
	 * @return
	 */
	public String reportGongguoStatis() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("gongguo_view")
				.groupBy(new String[] { "g_value"  })
				.colomns(new String[] {  "pname"  }).sum("flag").count().order("g_value").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[2] + "'," + (Double.parseDouble(objs[0]+"")-Double.parseDouble(objs[1]+""))+","+ (Double.parseDouble(objs[0]+""))+ "]";
			}
		});
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 按照类别，年份统计金额总数.
	 * 
	 * @return
	 */
	public String reportSumByTypeAndYear() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "year", "bigtype" }).sum("money")
				.colomns(new String[] { "year", "bigtype" })
				.where("big_money_type='2'").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "','" + objs[2] + "'," + objs[0] + " ]";
			}
		});
		System.out.println("reportSumByTypeAndYear==" + ans);
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 按照类别，年份，月份统计金额总数.
	 * 
	 * @return
	 */
	public String reportSumByTypeAndYearAndMonth() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil"); 
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "month", "bigtype" }).sum("money")
				.colomns(new String[] { "month", "bigtype" })
				.where("year='"+year+"' and big_money_type='2' ").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "','" + objs[2] + "'," + objs[0] + " ]";
			}
		});
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 按照类别统计各个的总金额.
	 * 
	 * @return
	 */
	public String reportSumByType() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy("bigtype").sum("money").where(" big_money_type='2' ")
				.colomns(new String[] { "bigtype" }).build().generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + "]";
			}

		});
		writeToPage(response, ans);
		return null;
	}

	public Money getMoneyVo() {
		return moneyVo;
	}

	public void setMoneyVo(Money moneyVo) {
		this.moneyVo = moneyVo;
	}

	public int getMoneySno() {
		return moneySno;
	}

	public void setMoneySno(int moneySno) {
		this.moneySno = moneySno;
	}

	public Date getMoneyTime() {
		return moneyTime;
	}

	public void setMoneyTime(Date moneyTime) {
		this.moneyTime = moneyTime;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getMoneyDesc() {
		return moneyDesc;
	}

	public void setMoneyDesc(String moneyDesc) {
		this.moneyDesc = moneyDesc;
	}

	public int getShopCard() {
		return shopCard;
	}

	public void setShopCard(int shopCard) {
		this.shopCard = shopCard;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}

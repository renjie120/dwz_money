package money.report;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.util.LinkedCaseInsensitiveMap;

import common.base.SpringContextUtil;
import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportDataGenerate2;
import common.report.ReportStrGenerate2;
import common.util.CommonUtil;

import dwz.present.BaseAction;

/**
 * 收支报表.
 * 
 * @author Administrator
 * 
 */
public class MoneyReport extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String report() {
		return "report";
	}
	
	/**
	 * 返回大类的报表图.
	 * @return
	 */
	public String bigReport() {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");
		List typeList = jdbcTemplate.queryForList("select distinct tally_type_desc from tally_type_t where (parent_code is null or parent_code = '') and money_type = '2'");
		List ansList = new ArrayList();
		if (typeList != null) {
			Iterator it = typeList.iterator();
			while (it.hasNext()) {
				LinkedCaseInsensitiveMap map = (LinkedCaseInsensitiveMap) it
						.next();
				ansList.add(map.get("tally_type_desc"));
			}
		}
		request.setAttribute("typeList", ansList);
		return "bigReport";
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
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("year") + "','" + objs.get("bigtype")
						+ "'," + objs.get("sum_MONEY") + " ]";
			}
		});
		System.out.println("reportSumByTypeAndYear==" + ans);
		writeToPage(response, ans);
		return null;
	}
	
	/**
	 * 按照大类别返回下面对应的小类的年度对比报表.
	 * @return
	 */
	public String reportSumByBigTypeAndYear() {
		String bigtype = request.getParameter("bigtype");
		if(bigtype==null)
			bigtype=""; 
		try {
			 bigtype = new String(bigtype.getBytes("ISO-8859-1"), "UTF-8"); 
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "year", "tallytype" }).sum("money")
				.colomns(new String[] { "year", "tallytype" })
				.where("big_money_type='2' and bigtype='"+bigtype+"'").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("year") + "','" + objs.get("tallytype")
						+ "'," + objs.get("sum_MONEY") + " ]";
			}
		});
		System.out.println("reportSumByBigTypeAndYear=="+bigtype+"---" + ans);
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 大类别的报表就显示一个饼图，和表格图.
	 * @return
	 */
	public String reportBigtableByBigType() { 
		String bigtype = request.getParameter("bigtype"); 
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if(bigtype==null)
			bigtype=""; 
		try {
			 bigtype = new String(bigtype.getBytes("ISO-8859-1"), "UTF-8"); 
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		
		MyReport.Builder builder =  new MyReport.Builder("money_detail_view")
		.groupBy(new String[] {   "tallytype" }).sum("money") 
		.colomns(new String[] {  "tallytype" }); 
		
		String sql  = builder.where("year="+year+" and month= "+month+" and bigtype='"+bigtype+"'").build().generateSql();
		
		ReportDaoUtil<MoneyReportVo> util = (ReportDaoUtil<MoneyReportVo>)SpringContextUtil
				.getBean("reportUtil");
		
		List<MoneyReportVo> result = util.getReportData(sql,
				new ReportDataGenerate2<MoneyReportVo>() {
					@Override
					public MoneyReportVo change(Map objs) {
						MoneyReportVo result = new MoneyReportVo();
						if (objs != null) {
							result.setMoney(objs.get("SUM_money") + ""); 
							result.setType(objs.get("tallytype") + ""); 
						}
						return result;
					}

				});
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("bigtype", bigtype);
		request.setAttribute("dataList", result); 
		return "bigReport2";
	}
	
	/**
	 * 小的类别报表就显示全部的具体记录.
	 * @return
	 */
	public String reportBigtableBySmallType() {  
		String smalltype = request.getParameter("tallytype");
		String year = request.getParameter("year");
		String month = request.getParameter("month"); 
		if(smalltype==null)
			smalltype="";
		try { 
			 smalltype = new String(smalltype.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");
		String sql = " select money_sno,money_time,money,tallytype,year,month,money_desc from money_detail_view where year = ? and month = ? and tallytype = ? ";
		final List<MoneyReportVo> ans = new ArrayList<MoneyReportVo>();
		jdbcTemplate.query(sql, new Object[]{year,month,smalltype},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				 MoneyReportVo result = new MoneyReportVo();
				 result.setDesc(rs.getString("money_desc"));
				 result.setMoney(rs.getString("money"));
				 result.setTime(rs.getString("money_time"));
				 result.setMonth(rs.getString("month"));
				 result.setYear(rs.getString("year"));
				 result.setType(rs.getString("tallytype"));
				 result.setSno(rs.getString("money_sno"));
				 ans.add(result);
			}
			
		});
		request.setAttribute("dataList", ans);
		return "bigReport3";
	}
	/**
	 * 
	 * @return
	 */
	public String reportBigtable() { 
		String bigtype = request.getParameter("bigtype");
		String smalltype = request.getParameter("smalltype");
		
		if(bigtype==null)
			bigtype="";
		if(smalltype==null)
			smalltype="";
		try {
			 bigtype = new String(bigtype.getBytes("ISO-8859-1"), "UTF-8");
			 smalltype = new String(smalltype.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		ReportDaoUtil<MoneyReportVo> util = (ReportDaoUtil<MoneyReportVo>)SpringContextUtil
				.getBean("reportUtil");
		String sql = "";
		//查询大类报表 
		if("".equals(bigtype)&&"".equals(smalltype)){ 
			sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "year","month",  "bigtype" }).sum("money")
				.colomns(new String[] { "year","month",  "bigtype" })
				.where("big_money_type='2'").build().generateSql();
		}
		//根据大类查询下面的小类.
		else if(!"".equals(bigtype)){
			MyReport.Builder builder =  new MyReport.Builder("money_detail_view")
			.groupBy(new String[] { "year","month",  "tallytype" }).sum("money") 
			.colomns(new String[] { "year","month",  "tallytype" }); 
			
			sql  = builder.where("big_money_type='2' and bigtype='"+bigtype+"'").build().generateSql();
		}
		System.out.println("查询sql:" + sql); 

		final String bt = bigtype;
		List<MoneyReportVo> result = util.getReportData(sql,
				new ReportDataGenerate2<MoneyReportVo>() {
					@Override
					public MoneyReportVo change(Map objs) {
						MoneyReportVo result = new MoneyReportVo();
						if (objs != null) {
							result.setMoney(objs.get("SUM_money") + "");
							result.setMonth(objs.get("month") + "");
							if("".equals(bt))
								result.setType(objs.get("bigtype") + "");
							else
								result.setType(objs.get("tallytype") + "");
							result.setYear(objs.get("year") + "");
						}
						return result;
					}

				});
		TreeSet yearList = new TreeSet();
		TreeSet typeList = new TreeSet();
		Map monthToMoney = new HashMap();
		Map monthToSum = new HashMap();
		Map yearToSum = new HashMap();
		Map yearTypeToSum = new HashMap();
		Map yearToAllSum = new HashMap();
		TreeSet allMonthList = new TreeSet();
		
		for(MoneyReportVo v:result){
			yearList.add(v.getYear());
			typeList.add(v.getType());
			allMonthList.add(v.getYear()+","+v.getMonth());
			//按月计算统计月度开支总数
			if(monthToSum.get(v.getYear()+","+v.getMonth())==null)
				monthToSum.put(v.getYear()+","+v.getMonth(), v.getMoney());
			else{
				Double s = Double.parseDouble(monthToSum.get(v.getYear()+","+v.getMonth())+"");
				double d = CommonUtil.add(s, Double.parseDouble(v.getMoney()+""));
				monthToSum.put(v.getYear()+","+v.getMonth(), d+"");
			}
			
			//按年计算统计年度度开支总数
			if(yearToSum.get(v.getYear())==null)
				yearToSum.put(v.getYear(), v.getMoney());
			else{
				Double s = Double.parseDouble(yearToSum.get(v.getYear())+"");
				double d = CommonUtil.add(s, Double.parseDouble(v.getMoney()+""));
				yearToSum.put(v.getYear(), d+"");
			}
			
			//按年+类别计算统计年度度开支总数
			if(yearTypeToSum.get(v.getYear()+","+v.getType())==null)
				yearTypeToSum.put(v.getYear()+","+v.getType(), v.getMoney());
			else{
				Double s = Double.parseDouble(yearTypeToSum.get(v.getYear()+","+v.getType())+"");
				double d = CommonUtil.add(s, Double.parseDouble(v.getMoney()+""));
				yearTypeToSum.put(v.getYear()+","+v.getType(), d+"");
			}
			
			//按年+类别计算统计年度度开支总数
			if(yearToAllSum.get(v.getYear())==null)
				yearToAllSum.put(v.getYear(), v.getMoney());
			else{
				Double s = Double.parseDouble(yearToAllSum.get(v.getYear())+"");
				double d = CommonUtil.add(s, Double.parseDouble(v.getMoney()+""));
				yearToAllSum.put(v.getYear(), d+"");
			}
			monthToMoney.put(v.getYear()+","+v.getMonth()+","+v.getType(), v.getMoney());
		}
		request.setAttribute("yearList", yearList.toArray(new String[yearList.size()]));
		request.setAttribute("allMonthList", allMonthList);
		request.setAttribute("yearToAllSum", yearToAllSum);
		request.setAttribute("monthToSum", monthToSum);
		request.setAttribute("yearTypeToSum", yearTypeToSum);
		request.setAttribute("yearToSum", yearToSum);
		request.setAttribute("typeList", typeList.toArray(new String[typeList.size()]));
		request.setAttribute("monthToMoney", monthToMoney);
		request.setAttribute("bigtype", bigtype);
		return "bigtable";
	} 

	/**
	 * 按照类别统计数量.
	 * 
	 * @return
	 */
	public String reportCountByType() {
		String bigtype = request.getParameter("bigtype");
		String smalltype = request.getParameter("smalltype");
		
		if(bigtype==null)
			bigtype="";
		if(smalltype==null)
			smalltype="";
		try {
			 bigtype = new String(bigtype.getBytes("ISO-8859-1"), "UTF-8");
			 smalltype = new String(smalltype.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		
		String where = "big_money_type='2'";
		if(year!=null&&!"".equals(year)){
			where+=" and year="+year;
		}
		if(bigtype!=null&&!"".equals(bigtype)){
			where+=" and bigtype='"+bigtype+"'";		
		}
		if(month!=null&&!"".equals(month)){
			where+=" and month="+month;
		}
		if(smalltype!=null&&!"".equals(smalltype)){
			where+=" and tallytype='"+smalltype+"'";
		}
		final boolean isSmallType = (smalltype!=null&&!"".equals(smalltype));
		
		MyReport.Builder build = new MyReport.Builder("money_detail_view")
		.groupBy(isSmallType?"tallytype":"bigtype").count().colomns(new String[] { isSmallType?"tallytype":"bigtype" });
		
		String sql = build.where(where).build().generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map strs) { 
				return "['" + strs.get(isSmallType?"tallytype":"bigtype") + "'," + strs.get("count1")
						+ " ]";
			}

		});
		writeToPage(response, ans);
		return null;
	}

	public String reportYingli() { 
		String ans = "[['盈利','08/01',10000],['盈利','08/02',14000],['盈利','08/03',20000],['盈利','08/04',15900]"
				+ ",['盈利','08/05',10000],['盈利','08/06',10000],['盈利','08/07',10000],['盈利','08/08',20000]"
				+ ",['盈利','08/09',10900],['盈利','08/10',40000],['亏损','08/01',-10500],['亏损','08/02',-12000]"
				+ ",['亏损','08/03',-14000],['亏损','08/04',-24000],['亏损','08/05',-10900],['亏损','08/06',-19000],"
				+ "['亏损','08/07',-16000],['亏损','08/08',-19000],['亏损','08/09',-20000],['亏损','08/10',-9000]]";
		System.out.println("reportSumByTypeAndYear==" + ans);
		writeToPage(response, ans);
		return null;
	}

	private String year;
	private String month;

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
				.where("year='" + year + "' and big_money_type='2' ").build()
				.generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("month") + "'," + objs.get("tallytype")
						+ "'," + objs.get("SUM_money") + "]";
			}
		});
		writeToPage(response, ans);
		return null;
	}

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

	/**
	 * 按照类别统计各个的总金额.
	 * 
	 * @return
	 */
	public String reportSumByType() {
		String bigtype = request.getParameter("bigtype");
		String smalltype = request.getParameter("smalltype");
		
		if(bigtype==null)
			bigtype="";
		if(smalltype==null)
			smalltype="";
		try {
			 bigtype = new String(bigtype.getBytes("ISO-8859-1"), "UTF-8");
			 smalltype = new String(smalltype.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		String where = "big_money_type='2' ";
		if(year!=null&&!"".equals(year)){
			where+=" and year="+year;
		}
		if(bigtype!=null&&!"".equals(bigtype)){
			where+=" and bigtype='"+bigtype+"'";		
		}
		if(month!=null&&!"".equals(month)){
			where+=" and month="+month;
		}
		if(smalltype!=null&&!"".equals(smalltype)){
			where+=" and tallytype='"+smalltype+"'";
		}
		final boolean isSmallType = (smalltype!=null&&!"".equals(smalltype)); 
		 
		MyReport.Builder build = new MyReport.Builder("money_detail_view")
		.groupBy(isSmallType?"tallytype":"bigtype").sum("money").where(where) ;
		
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil"); 
		
		String sql = build.colomns(new String[] { isSmallType?"tallytype":"bigtype" }).build().generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get(isSmallType?"tallytype":"bigtype") + "',"
						+ objs.get("SUM_money") + "]";
			}

		});
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 统计功过记录的数据报表图.
	 * 
	 * @return
	 */
	public String reportGongguoStatis() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("gongguo_view")
				.groupBy(new String[] { "g_value" })
				.colomns(new String[] { "pname" }).sum("flag").count()
				.order("g_value").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['"
						+ objs.get("pname")
						+ "',"
						+ (Double.parseDouble(objs.get("SUM_flag") + "") - Double
								.parseDouble(objs.get("COUNT1") + "")) + ","
						+ (Double.parseDouble(objs.get("SUM_flag") + "")) + "]";
			}
		});
		writeToPage(response, ans);
		return null;
	}
}

package com.renjie120.reportUtil;

import java.util.Map;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.base.SpringContextUtil;
import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportStrGenerate2;

@SuppressWarnings("deprecation")
public class MyJdbcActionTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}

	public void atestGetReportOut() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").groupBy("year")
				.sum("money").where(" big_money_type=2")
				.colomns(new String[] { "year" }).build().generateSql();
		
		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("year") + "'," + objs.get("SUM_money") + "]";
			}
		});
		System.out.println(ans); 
	}
	
	public void atestGetReportOutByYear() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").groupBy("month")
				.sum("money")
				.where(" big_money_type=2 and year='2013'")
				.colomns(new String[] { "month" }).build().generateSql();
		
		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("month") + "'," + objs.get("SUM_money") + "]";
			}
		});
		System.out.println(ans); 
	}
	
	public void atestGetReportOutByMonth() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
		.groupBy("money_time")
		.sum("money")
		.where(" big_money_type=2 and year='2014' and month=10 ").colomns(new String[] { "money_time" })
		.build().generateSql();
		
		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("money_time") + "'," + objs.get("SUM_money") + "]";
			}
		});
		System.out.println(ans); 
	}
	
	public void atestGetReportOutInDay() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
		.sum("money")
		.where("money_time='2014-10-10'")
		.groupBy("money_sno")
		.colomns(
				new String[] { "money_time", "money", "tallytype",
						"money_sno", "money_desc" }).build()
		.generateSql();
		
		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
//				return "['" + objs.get("money_time") + "'," + objs.get("SUM_money") + "]";
				return "['" + objs.get("money_time") + "','" + objs.get("tallytype") + "','" + objs.get("money")
						+ "','" + objs.get("money_desc")+ "']";
			}
		});
		System.out.println(ans); 
	}
	
	public void atestGetReportSumByBigType() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").sum("money")
				.groupBy("bigtype")
				.colomns(new String[] { "bigtype", "bigtypesno" }).build()
				.generateSql();
		
		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
					return "['" + objs.get("bigtype") + "','" + objs.get("SUM_money")  + "','" + objs.get("bigtypesno")+ "']";
			}
		});
		System.out.println(ans); 
	}
	
	public void atestGetReportSumBySmallType() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").sum("money")
				.where(" bigtypesno='1'").groupBy("tallytype")
				.colomns(new String[] { "tallytype", "money_type" }).build()
				.generateSql();
		
		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("tallytype") + "','" + objs.get("SUM_money")  + "','" + objs.get("money_type")+ "']";
			}
		});
		System.out.println(ans); 
	}
	
	public void atestGetReportSumBySmallTypeInMonth() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
		.sum("money")
		.where(" money_type='AD' and year='2014' ").groupBy("month")
		.colomns(new String[] { "month" }).build().generateSql();
		
		
		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("month") + "','" + objs.get("SUM_money")  + "']";
			}
		});
		System.out.println(ans); 
	}
	
	public void atestGetReportSumBySmallTypeInYear() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").sum("money")
				.where(" money_type='A'  ").groupBy("year")
				.colomns(new String[] { "year" }).build().generateSql();
		
		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("year") + "','" + objs.get("SUM_money")  + "']";
			}
		});
		System.out.println(ans); 
	}
	
	public void testGetReportSumBySmallTypeInDay() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
		.sum("money")
		.where(" money_type='AB' and year='2014' and month='05'").groupBy("money_time")
		.colomns(new String[] { "money_time" }).build().generateSql();

		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("money_time") + "','" + objs.get("SUM_money")  + "']";
			}
		});
		System.out.println(ans); 
	}
	
	public void testGetReportSumBySmallTypeInSomeDay() throws Exception {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
		.sum("money")
		.where(" money_type='AB' and money_time='2014-10-10' ").groupBy("money_sno")
		.colomns(new String[] {"money_time", "money", "tallytype",
				"money_sno", "money_desc"  }).build().generateSql();

		System.out.println("查询sql:" + sql);

		
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("money_time") + "','" + objs.get("tallytype")+ "','" + objs.get("money") + "','" + objs.get("money_desc")   + "']";
			}
		});
		System.out.println(ans); 
	}
}

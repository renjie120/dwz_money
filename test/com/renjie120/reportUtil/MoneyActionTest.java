package com.renjie120.reportUtil;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportStrGenerate;

@SuppressWarnings("deprecation")
public class MoneyActionTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}
 
	public void atestReportCountByType() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_type_v")
				.groupBy("tallytype").sum("money")
				.colomns(new String[] { "tallytype" }).build().generateSql();
		System.out.println("查询sql:" + sql);

		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + " ]";
			}

		});
		System.out.println(ans);

	}

	public void atestReportSumByTypeAndYear() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "year", "tallytype" }).sum("money")
				.colomns(new String[] { "year", "tallytype" })
				.where("big_money_type='2'").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "','" + objs[2] + "'," + objs[0] + " ]";
			}
		});
		System.out.println(ans);
	}

	public void atestReportSumByTypeAndYearAndMonth() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "month", "tallytype" }).sum("money")
				.colomns(new String[] { "month", "tallytype" })
				.where("year='2013'").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "','" + objs[2] + "'," + objs[0] + " ]";
			}
		});
		System.out.println(ans);
	}

	public void testReportSumByType() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy("bigtype").sum("money")
				.colomns(new String[] { "bigtype" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + "]";
			}
		});
		System.out.println(ans);
	}
}

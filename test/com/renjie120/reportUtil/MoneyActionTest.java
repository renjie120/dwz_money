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

	// select g_value,sum(flag),sum(flag)-count(1) from gongguo_detail group by
	// g_value order by g_value;
	public void testReportSumByTypeAndYear() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
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

	public void qtestReportSumByType() {
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

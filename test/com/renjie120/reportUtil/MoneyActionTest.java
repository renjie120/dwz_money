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

	public void aatestReportSumByTypeAndYear() {
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
	public void atestReportSumByTypeAndYear() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("gongguo_view")
				.groupBy(new String[] { "g_value" })
				.colomns(new String[] { "pname" }).sum("flag").count()
				.order("g_value").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['"
						+ objs[2]
						+ "',"
						+ (Double.parseDouble(objs[0] + "") - Double
								.parseDouble(objs[1] + "")) + ","
						+ (Double.parseDouble(objs[0] + "")) + "]";
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

	public void aatestReportOutSumByYear() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").groupBy("year")
				.sum("money").where(" big_money_type=2")
				.colomns(new String[] { "year" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + "]";
			}
		});
		System.out.println(ans);
	}

	public void atestReportOutSumByMonth() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").groupBy("month")
				.sum("money").where(" big_money_type=2 and year='2013'")
				.colomns(new String[] { "month" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + "]";
			}
		});
		System.out.println(ans);
	}

	public void atestReportOutSumInDay() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.sum("money")
				.where(" money_time='2014-03-03'")
				.groupBy("money_sno")
				.colomns(
						new String[] { "money_time", "money", "tallytype",
								"money_sno" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[3] + "," + objs[2] + "]";
			}
		});
		System.out.println(ans);
	}

	public void atestReportOutSumByDay() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.sum("money")
				.where(" money_time='2014-03-03'")
				.groupBy("money_sno")
				.colomns(
						new String[] { "money_time", "money", "tallytype",
								"money_sno" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[3] + "," + objs[2] + "]";
			}
		});
		System.out.println(ans);
	}

	public void testReportSumByBigType() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.sum("money") 
				.groupBy("bigtype")
				.colomns(
						new String[] { "bigtype" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + "]";
			}
		});
		System.out.println(ans);
	}

	public void testReportSumBySmallType() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.sum("money")
				.where(" bigtype='饮食'")
				.groupBy("tallytype")
				.colomns(
						new String[] { "tallytype"  }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + "]";
			}
		});
		System.out.println(ans);
	}

	public void testReportSumBySmallTypeInYear() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.sum("money")
				.where(" tallytype='晚饭'  ")
				.groupBy("year")
				.colomns(
						new String[] { "year"  }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + "]";
			}
		});
		System.out.println(ans);
	}

	public void testReportSumBySmallTypeInMonth() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.sum("money")
				.where(" tallytype='晚饭' and year='2013' ")
				.groupBy("month")
				.colomns(
						new String[] { "month" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + "]";
			}
		});
		System.out.println(ans);
	}

	public void testReportSumBySmallTypeInDay() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.sum("money")
				.where(" tallytype='晚饭' and year='2013' and month='1'")
				.groupBy("money_time")
				.colomns(
						new String[] { "money_time" }).build().generateSql();
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

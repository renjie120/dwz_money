package com.renjie120.reportUtil;

import java.util.List;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportModels;
import common.report.ReportMultiSeriesSet;
import common.report.ReportMultiSeriesSetGenerate;
import common.report.ReportMultiSeriesSetStrGenerate;
import common.report.ReportSet;
import common.report.ReportSetGenerate;
import common.report.ReportSetStrGenerate;
import common.report.ReportStringTool;

@SuppressWarnings("deprecation")
public class MoneyActionTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}

	public void atestReportSumByType() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_type_v")
				.groupBy("tallytype").sum("money")
				.colomns(new String[] { "tallytype" }).build().generateSql();
		System.out.println("查询sql:" + sql);

		List<ReportSet> ans = util.getTwoColumnReport(sql,
				new ReportSetGenerate() {
					@Override
					public ReportSet change(Object[] objs) {
						ReportSet set = new ReportSet();
						set.setSum(objs[0].toString());
						set.setName((String) objs[1]);
						return set;
					}

				});
		String str = ReportStringTool.getReportSetStr(ans,
				new ReportSetStrGenerate() {
					@Override
					public String change(ReportSet s) {
						return "['" + s.getName() + "'," + s.getSum() + "]";
					}
				});
		System.out.println(str);

	}

	@Test
	public void testReportSumByTypeAndYear() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "year", "tallytype" }).sum("money")
				.colomns(new String[] { "year", "tallytype" }).build()
				.generateSql();
		System.out.println("查询sql:" + sql);

		List<ReportMultiSeriesSet> ans = util.getThreeColumnReport(sql,
				new ReportMultiSeriesSetGenerate() {
					@Override
					public ReportMultiSeriesSet change(Object[] objs) {
						ReportMultiSeriesSet set = new ReportMultiSeriesSet();
						set.setSumColumn(objs[0].toString());
						set.setGroupColumn(objs[1].toString());
						set.setThirdColumn(objs[2].toString());
						return set;
					}

				});
		String str = ReportStringTool.getThreeColumnReportStr(ans,
				new ReportMultiSeriesSetStrGenerate() {
					@Override
					public String change(ReportMultiSeriesSet s) {
						return "['" + s.getGroupColumn() + "',"
								+ s.getSumColumn() + "," + s.getThirdColumn()
								+ "]";
					}
				});
		System.out.println(str);

	}

	public void atestReportCountByType() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy("bigtype").count().colomns(new String[] { "bigtype" })
				.build().generateSql();
		System.out.println("查询sql:" + sql);

		List<ReportSet> ans = util.getTwoColumnReport(sql,
				ReportModels.CountReportSetModel);
		String str = ReportStringTool.getReportSetStr(ans,
				ReportModels.CountReportRStrModel);
		System.out.println(str);

	}
}

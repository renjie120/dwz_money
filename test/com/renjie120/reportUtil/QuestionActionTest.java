package com.renjie120.reportUtil;

import java.util.List;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportSet;
import common.report.ReportSetGenerate;
import common.report.ReportSetStrGenerate;
import common.report.ReportStringTool;

@SuppressWarnings("deprecation")
public class QuestionActionTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}

	@Test
	public void testReportQuestionCount() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("question_v").groupBy("statusname")
				.count().colomns(new String[] { "statusname" }).build()
				.generateSql();
		System.out.println("查询sql:" + sql);
		List<ReportSet> ans = util.getTwoColumnReport(sql,
				new ReportSetGenerate() {
					@Override
					public ReportSet change(Object[] objs) {
						ReportSet set = new ReportSet();
						set.setCount(objs[0].toString());
						set.setName((String) objs[1]);
						return set;
					}

				});
		String str = ReportStringTool.getReportSetStr(ans,
				new ReportSetStrGenerate() { 
					@Override
					public String change(ReportSet s) {
						return "['" + s.getName() + "'," + s.getCount() + "]";
					}
				});
		System.out.println(str);
	}
}

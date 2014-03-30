package com.renjie120.reportUtil;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportStrGenerate;

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
		String str = util.getReportStr(sql,
				new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" +objs[1] + "'," + objs[0] + " ]";
			} 
		}); 
		System.out.println(str);
	}
}

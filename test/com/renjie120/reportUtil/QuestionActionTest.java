package com.renjie120.reportUtil;

import java.util.List;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.report.ReportBuilderFactory;
import common.report.ReportDaoUtil;
import common.report.ReportSet;

@SuppressWarnings("deprecation")
public class QuestionActionTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml"};
		return config;
	}

	@Test
	public void testReportQuestionCount() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = ReportBuilderFactory.getInstance()
				.countByColumn("question_v", "statusname").generateSql(); 
		List<ReportSet> ans = util.getTwoColumnReport(sql);
		StringBuilder buil = new StringBuilder();
		if(ans!=null){
			buil.append("[");
			for(ReportSet s:ans){
				buil.append("['"+s.getName()+"',"+s.getCount()+"],"); 
			}
			buil.deleteCharAt(buil.lastIndexOf(","));
			buil.append("]");
		}
		System.out.println(buil.toString());
	}
}

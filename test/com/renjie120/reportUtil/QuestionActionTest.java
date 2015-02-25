package com.renjie120.reportUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportDataGenerate2;
import common.report.ReportStrGenerate;

@SuppressWarnings("deprecation")
public class QuestionActionTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}

	//@Test
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
	
	//@Test
	public void testReportQuestionCount2() {
		ReportDaoUtil util =  (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").groupBy("year")
				.sum("money").where(" big_money_type='2' ")
				.colomns(new String[] { "year" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		List s =  util.getReportData(sql, new ReportDataGenerate2(){

			@Override
			public List<String> change(Map objs) {
				List<String> result = new ArrayList<String>();
				if(objs!=null){
					for(Object o:objs.keySet()){
						result.add(objs.get(o)+"");
					}
				}
				return result;
			}
			
		});
		System.out.println(s);
	}
	
	public void testReportQuestionCount3() {
		ReportDaoUtil util =  (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").groupBy("bigtypesno")
				.sum("money").where(" big_money_type='2' and year= 2008")
				.colomns(new String[] { "bigtypesno" }).build().generateSql();
		System.out.println("查询sql:" + sql);
		List s =  util.getReportData(sql, new ReportDataGenerate2(){

			@Override
			public List<String> change(Map objs) {
				List<String> result = new ArrayList<String>();
				if(objs!=null){
					for(Object o:objs.keySet()){
						result.add(objs.get(o)+"");
					}
				}
				return result;
			}
			
		});
		System.out.println(s);
	}
}

package com.renjie120.reportUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import money.report.MoneyReportVo;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.alibaba.fastjson.JSON;

import common.base.SpringContextUtil;
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
	public void atestReportQuestionCount() {
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
	public void atestReportQuestionCount2() {
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
	
	@Test
	public void testReportBigtable() {
		ReportDaoUtil<MoneyReportVo> util = (ReportDaoUtil<MoneyReportVo>)applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "year","month",  "bigtype" }).sum("money")
				.colomns(new String[] { "year","month",  "bigtype" })
				.where("big_money_type='2'").build().generateSql();
		System.out.println("查询sql:" + sql);
		List<MoneyReportVo> result = util.getReportData(sql,
				new ReportDataGenerate2<MoneyReportVo>() {
					@Override
					public MoneyReportVo change(Map objs) {
						MoneyReportVo result = new MoneyReportVo();
						if (objs != null) {
							result.setMoney(objs.get("SUM_money") + "");
							result.setMonth(objs.get("month") + "");
							result.setType(objs.get("bigtype") + "");
							result.setYear(objs.get("year") + "");
						}
						return result;
					}

				});
		System.out.println(JSON.toJSONString(result));
	}
	
	//@Test
	public void atestReportQuestionCount3() {
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

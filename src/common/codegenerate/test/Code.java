package common.codegenerate.test;

import java.util.HashMap;
import java.util.Map;

import common.codegenerate.Generate;
import common.codegenerate.ModelParse;

public class Code {
	public static void main(String[] agrgs) {
//		 final String file =
//		 "D:\\My Documents\\GitHub\\dwz_money\\code_template\\datamodle.xml";
//		final String file = "F:\\github\\dwz_money\\code_template\\datamodle.xml";
		final String file = "D:\\github\\dwz_money\\code_template\\datamodle.xml";
		String beanName = "UserMenuRight"; 
		new Generate("action_code.ftl", beanName, "Action.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("dao_code.ftl", beanName, "Dao.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("beaninter_code.ftl", beanName, ".java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("manager_code.ftl", beanName, "Manager.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("vo_code.ftl", beanName, "VO.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("searchfield_code.ftl", beanName, "SearchFields.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("orderfield_code.ftl", beanName, "OrderByFields.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("bean_impl_code.ftl", beanName, "Impl.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("manager_impl_code.ftl", beanName, "ManagerImpl.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("edit_jsp.ftl", beanName, "edit.jsp") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("info_jsp.ftl", beanName, "info.jsp") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("list_jsp.ftl", beanName ,"list.jsp") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("hbm_code.ftl", beanName.toLowerCase(), ".hbm.xml") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();
		
		new Generate("config.ftl", beanName.toLowerCase(), "-config.txt") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		System.out.println("生成完毕:d:\\"+beanName+"\\");
	}
}

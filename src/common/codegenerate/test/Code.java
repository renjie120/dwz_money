package common.codegenerate.test;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import common.codegenerate.Generate;
import common.codegenerate.ModelParse;

public class Code {
	public static void main(String[] agrgs) {
		// final String file =
		// "D:\\My Documents\\GitHub\\dwz_money\\code_template\\datamodle.xml";
		final String file = "F:\\github\\dwz_money\\code_template\\datamodle.xml";
		String beanName = "Org";
		// Writer out = null;
		// try {
		// out = new FileWriter("d:\\out.txt");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		Writer out = new OutputStreamWriter(System.out);
		new Generate("action_code.ftl", beanName + "Action.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("dao_code.ftl", beanName + "Dao.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("manager_code.ftl", beanName + "Manager.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("vo_code.ftl", beanName + "VO.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("searchfield_code.ftl", beanName + "SearchFields.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("orderfield_code.ftl", beanName + "OrderByFields.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("bean_impl_code.ftl", beanName + "Impl.java") {
			public Object setData() {
				ModelParse p = new ModelParse();
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		new Generate("manager_impl_code.ftl", beanName + "ManagerImpl.java") {
			public Object setData() {
				ModelParse p = new ModelParse(); 
				p.setFileName(file);
				Map m = new HashMap();
				m.put("model", p.parse());
				return m;
			}
		}.make();

		System.out.println("生成完毕");
	}
}

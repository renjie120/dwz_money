package common.codegenerate.test;

import java.util.HashMap;
import java.util.Map;

import common.codegenerate.ClassModel;
import common.codegenerate.Generate;
import common.codegenerate.ModelParse;

public class Code {
	public static void main(String[] agrgs) {
		// final String file =
		// "E:\\github\\dwz_money\\code_template\\datamodle.xml";
		//String Root = "D:\\github\\dwz_money";
		String Root = "E:\\github\\dwz_money";
		final String file = Root + "\\code_template\\datamodle.xml";
		String javaRoot = Root + "\\src";
		String jspRoot = Root + "\\WebRoot\\WEB-INF\\jsp";
		String hbmRoot = Root + "\\src\\hbm";

		ModelParse p = new ModelParse();
		p.setFileName(file);
		Map mm = new HashMap();
		mm.put("model", p.parse());
		final Map m = mm;
		ClassModel model = (ClassModel) mm.get("model");
		String beanName = model.getClassName();
		String packageName = model.getPackageName();

		new Generate("action_code.ftl", beanName, "Action.java", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("dao_code.ftl", beanName, "Dao.java", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("beaninter_code.ftl", beanName, ".java", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("manager_code.ftl", beanName, "Manager.java", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("vo_code.ftl", beanName, "VO.java", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("searchfield_code.ftl", beanName, "SearchFields.java",
				packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("orderfield_code.ftl", beanName, "OrderByFields.java",
				packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("bean_impl_code.ftl", beanName, "Impl.java", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("manager_impl_code.ftl", beanName, "ManagerImpl.java",
				packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(javaRoot, 1).make();

		new Generate("edit_jsp.ftl", beanName, "edit.jsp", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).make();

		new Generate("info_jsp.ftl", beanName, "info.jsp", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).make();

		new Generate("list_jsp.ftl", beanName, "list.jsp", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).make();

		new Generate("hbm_code.ftl", beanName.toLowerCase(), ".hbm.xml",
				packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(hbmRoot, 3).make();

		new Generate("config.ftl", beanName.toLowerCase(), "-config.txt",
				packageName) {
			public Object setData() {
				return m;
			}
		}.make();

		System.out.println("生成完毕:d:\\" + beanName + "\\");
	}
}

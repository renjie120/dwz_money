package common.codegenerate.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.codegenerate.ClassModel;
import common.codegenerate.Generate;
import common.codegenerate.ModelParse;

public class EcoCode { 
	static String Root = "E:\\dwz_money\\";
	final static String file = Root + "\\code_template\\datamodle.xml";
	static String javaRoot = Root + "\\src";
	static String jspRoot = Root + "\\WebRoot\\WEB-INF\\jsp";
	static String hbmRoot = Root + "\\src\\hbm";
	/**
	 * 根据map生成对应的全部的文件.
	 * @param m
	 */
	public static void generateFiles(Map mm){
		final Map m = mm;
		ClassModel model = (ClassModel) mm.get("model");
		String beanName = model.getClassName();
		String packageName = model.getPackageName();
		System.out.println(beanName+",,"+packageName);
		boolean java = true;//是否生成java文件
		boolean jsp = true;//是否生成jsp文件
		boolean hbm = false;//是否生成hbm文件
		boolean other = false;//是否生成其他文件

		if (java) {
			new Generate("action_code.ftl", beanName, "Action.java",
					packageName) {
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
			new Generate("manager_code.ftl", beanName, "Manager.java",
					packageName) {
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
			new Generate("bean_impl_code.ftl", beanName, "Impl.java",
					packageName) {
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
		}

		if (jsp) {

			new Generate("import_jsp.ftl", beanName, "import.jsp", packageName) {
				public Object setData() {
					return m;
				}
			}.setDirName(jspRoot, 2).make();
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
			new Generate("query_jsp.ftl", beanName, "query.jsp", packageName) {
				public Object setData() {
					return m;
				}
			}.setDirName(jspRoot, 2).make();

		}

		if (hbm) {
			new Generate("dwz_database.ftl", beanName, "db.txt", packageName) {
				public Object setData() {
					return m;
				}
			}.make();

			new Generate("hbm_code.ftl", beanName.toLowerCase(), ".hbm.xml",
					packageName) {
				public Object setData() {
					return m;
				}
			}.setDirName(hbmRoot, 3).make();

		}

		if (other)
			new Generate("config.ftl", beanName.toLowerCase(), "-config.txt",
					packageName) {
				public Object setData() {
					return m;
				}
			}.make();

		System.out.println("生成完毕:d:\\" + beanName + "\\");
	} 
	
	public static void main(String[] agrgs) {  
		ModelParse p = new ModelParse();
		p.setFileName(file);
		List<ClassModel> list = p.parseClasses();
		if(list!=null&&list.size()>0){
			for(ClassModel cm:list){
				Map mm = new HashMap();
				mm.put("model", cm);
				generateFiles(mm);
			}
		}
	}
}

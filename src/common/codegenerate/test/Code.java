package common.codegenerate.test;

import java.util.HashMap;
import java.util.Map;

import common.codegenerate.ClassModel;
import common.codegenerate.Generate;
import common.codegenerate.ModelParse;

class MyThread implements Runnable {
	private String file;

	public MyThread(String f) {
		this.file = f;
	}

	@Override
	public void run() {
		String Root = "E:";
		// final String file =
		// "E:\\dwz_money-master\\dwz_money-master\\code_template\\datamodle.xml";
		// String javaRoot = Root + "\\src";
		// String jspRoot = Root + "\\WebRoot\\WEB-INF\\jsp";
		String jspRoot = Root + "\\jsp";
		// String hbmRoot = Root + "\\src\\hbm";

		ModelParse p = new ModelParse();
		p.setFileName(file);
		Map mm = new HashMap();
		mm.put("model", p.parse());
		final Map m = mm;
		ClassModel model = (ClassModel) mm.get("model");
		String beanName = model.getClassName();
		String packageName = model.getPackageName();

		new Generate("eco_List_jsp.ftl", beanName, "_list.jsp", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).setSplitByBeanName(false).make(true);

		new Generate("eco_Add_jsp.ftl", beanName, "_add.jsp", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).setSplitByBeanName(false).make(true);

		new Generate("eco_operation_jsp.ftl", beanName, "_operation.jsp",
				packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).setSplitByBeanName(false).make(true);

		new Generate("eco_view_jsp.ftl", beanName, "_view.jsp", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).setSplitByBeanName(false).make(true);

		new Generate("eco_edit_jsp.ftl", beanName, "_edit.jsp", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).setSplitByBeanName(false).make(true);

		new Generate("sqlserver_proceduce.ftl", beanName, ".sql", packageName) {
			public Object setData() {
				return m;
			}
		}.setDirName(jspRoot, 2).setSplitByBeanName(false).make(true);

		System.out.println("生成完毕:" + jspRoot + "\\" + beanName);
	}
}

public class Code {
	public static void main(String[] agrgs) {
		String file2 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_contract.xml";
		String file3 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_huifang.xml";
		String file4 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_lianxi.xml";
		String file5 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_peixun.xml";
		String file6 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_contacter.xml";
		String file7 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_kaohe.xml";
		String file8 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_jiyao.xml";
		String file9 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_neixun_hetong.xml";
		String file11 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_mainTest.xml"; 
		String file10 = "E:\\github\\dwz_money\\code_template\\datamodle_eco_binggou_hetong.xml";

		// String file2 = "E:\\github\\dwz_money\\code_template\\datamodle.xml";
		new Thread(new MyThread(file2)).start();
		new Thread(new MyThread(file3)).start();
		new Thread(new MyThread(file4)).start();
		new Thread(new MyThread(file5)).start();
		new Thread(new MyThread(file6)).start();
		new Thread(new MyThread(file7)).start();
		new Thread(new MyThread(file8)).start();
		new Thread(new MyThread(file9)).start();
		new Thread(new MyThread(file10)).start();
		new Thread(new MyThread(file11)).start();
		// new Generate("dao_code.ftl", beanName, "Dao.java", packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(javaRoot, 1).make();
		//
		// new Generate("beaninter_code.ftl", beanName, ".java", packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(javaRoot, 1).make();
		//
		// new Generate("manager_code.ftl", beanName, "Manager.java",
		// packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(javaRoot, 1).make();
		//
		// new Generate("vo_code.ftl", beanName, "VO.java", packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(javaRoot, 1).make();
		//
		// new Generate("searchfield_code.ftl", beanName, "SearchFields.java",
		// packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(javaRoot, 1).make();
		//
		// new Generate("orderfield_code.ftl", beanName, "OrderByFields.java",
		// packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(javaRoot, 1).make();
		//
		// new Generate("bean_impl_code.ftl", beanName, "Impl.java",
		// packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(javaRoot, 1).make();
		//
		// new Generate("manager_impl_code.ftl", beanName, "ManagerImpl.java",
		// packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(javaRoot, 1).make();
		//
		// new Generate("edit_jsp.ftl", beanName, "edit.jsp", packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(jspRoot, 2).make();
		//
		// new Generate("info_jsp.ftl", beanName, "info.jsp", packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(jspRoot, 2).make();
		//
		// new Generate("list_jsp.ftl", beanName, "list.jsp", packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(jspRoot, 2).make();
		//
		// new Generate("hbm_code.ftl", beanName.toLowerCase(), ".hbm.xml",
		// packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.setDirName(hbmRoot, 3).make();
		//
		// new Generate("config.ftl", beanName.toLowerCase(), "-config.txt",
		// packageName) {
		// public Object setData() {
		// return m;
		// }
		// }.make();

	}
}

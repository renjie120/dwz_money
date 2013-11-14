package common.struts2;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 测试返回文本的action。
 * 输入：http://127.0.0.1:1988/source/ajaxDone!jsp.do   返回ajaxDone。jsp
 * http://127.0.0.1:1988/source/ColumnTag!java.do   返回ColumnTag。java
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class PlainTextResultAction implements Action ,ModelDriven<JavaBeanTest>{
	private String par1; 
	public String getPar1() {
		return par1;
	}
	public void setPar1(String par1) {
		this.par1 = par1;
	}
	public String execute() throws Exception {  
		return SUCCESS;
	} 
	public String seeJava() throws Exception {  
		return "java";
	}
	public String seeJsp() throws Exception {  
		return "jsp";
	} 
	public String testGloablAction() throws Exception {   
		return "test";
	}
	public String testException() throws Exception {   
		throw new Exception("123");
	}
	
	JavaBeanTest test = new JavaBeanTest();
	public JavaBeanTest getModel() {  
		return test;
	} 
}

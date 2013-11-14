package common.struts2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 测试.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class UploadAction2 extends ActionSupport {
	private File[] upload; 
	private String arg;
	private List ognlList;
	//下面的两个属性是用来接收依赖注入的属性----如果定义了file上传的属性为xxx，还要再定义两个属性用来封装类型和文件名！（如果不写怎么样？）
	private String[] uploadContentType;  
	private String[] uploadFileName;
	//设置允许上传的文件类型
	private String allowTypes;
	//下面的属性可以通过配置文件来配置，允许动态设置---典型的依赖注入---见这个action的配置文件。
	private String savePath;  
	 
	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	public String testOgnl(){
		ognlList = new ArrayList();
		JavaBeanTest test1 = new JavaBeanTest("test1","1");
		JavaBeanTest test2 = new JavaBeanTest("test2","2");
		JavaBeanTest test3 = new JavaBeanTest("test3","3");
		JavaBeanTest test4 = new JavaBeanTest("test4","4");
		JavaBeanTest test5 = new JavaBeanTest("test5","5");
		ognlList.add(test1);
		ognlList.add(test2);
		ognlList.add(test3);
		ognlList.add(test4);
		ognlList.add(test5);
		return "ognl";
	}

	private String changeStr(String name) throws UnsupportedEncodingException{
		System.out.println(new String(name.getBytes("GBK")));
		System.out.println(new String(name.getBytes("UTF-8")));
		System.out.println(new String(name.getBytes("GBK"),"ISO-8859-1"));
		System.out.println(new String(name.getBytes("UTF-8"),"GBK"));
		
		return new String(name.getBytes("ISO-8859-1"));
	}
	public String saveFile() throws Exception { 
		File[] f = getUpload();
		System.out.println("上传的文件数目为："+f.length);
		for(int i=0;i<f.length;i++){
			System.out.println("上传的文件名："+changeStr(getUploadFileName()[i]));
			FileOutputStream fos = new FileOutputStream(getSavePath()+"\\"+getUploadFileName()[i]); 
			FileInputStream fis = new FileInputStream(f[i]);
			byte[] buffer= new byte[1024];
			int len = 0;
			while((len=fis.read(buffer))>0){
				fos.write(buffer,0,len);
			}
		}
		return "success";
	}

	public String init() throws Exception {
		return "init";
	} 

	public String execute() throws Exception { 
		
		return "success";
	}   
	public File[] getUpload() {
		return upload;
	}
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}
 
	public String[] getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String[] getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getAllowTypes() {
		return allowTypes;
	}
	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public List getOgnlList() {
		return ognlList;
	}

	public void setOgnlList(List ognlList) {
		this.ognlList = ognlList;
	} 
}

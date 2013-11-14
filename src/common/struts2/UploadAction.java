package common.struts2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import common.util.NPOIReader;
import common.util.ZipCompressor;

import dwz.present.BaseAction;

/**
 * 测试.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class UploadAction extends BaseAction {
	private File upload; 
	private String arg;
	//下面的两个属性是用来接收依赖注入的属性----如果定义了file上传的属性为xxx，还要再定义两个属性用来封装类型和文件名！（如果不写怎么样？）
	private String uploadContentType;  
	private String uploadFileName;
	//设置允许上传的文件类型
	private String allowTypes;
	//下面的属性可以通过配置文件来配置，允许动态设置---典型的依赖注入---见这个action的配置文件。
	private String savePath;  
	
	/**
	 * 对上传文件进行过滤。
	 * 如果失败，就返回INPUT视图！
	 * @param types
	 * @return
	 */
	private String filterTypes(String[] types){
		 String fileType  = getUploadContentType();
		 System.out.println("上传的文件类型是："+fileType);
		 for(String type:types){
			 if(type.equals(fileType)){
				 return null;
			 }
		 }
		return INPUT;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String saveFile() throws Exception {
		//下面的文件上传类型的是通过Action配置文件里面的属性注入的！param  
		String destFileName = getSavePath()+"\\"+uploadFileName;
		//下面的文件上传路径先要保证在服务器上面已经存在！
		FileOutputStream fos = new FileOutputStream(destFileName); 
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer= new byte[1024];
		int len = 0;
		while((len=fis.read(buffer))>0){
			fos.write(buffer,0,len);
		}  
		//将上传的文件压缩之后，删除原文件.
		File destFile = new File(destFileName);
		if(destFile.exists()){
			//如果是excel文件，进行读取.
			if(uploadFileName.endsWith(".xls")||uploadFileName.endsWith(".xlsx")){
				 
			} 
			ZipCompressor zipCompressor = new ZipCompressor(getSavePath()+"\\"+System.currentTimeMillis()+".zip");
			zipCompressor.compress(destFileName);
			
			
		}
		destFile.delete();
		return "success";
	}

	public String init() throws Exception {
		return "init";
	} 
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getAllowTypes() {
		return allowTypes;
	}
	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	} 
}

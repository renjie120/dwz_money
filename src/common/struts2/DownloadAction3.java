package common.struts2;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class DownloadAction3 implements Action{
	//指定要下载的文件保存在服务器上面的路径.
	private String inputPath; 
	//用来进行对下载的要保存的文件名命名的属性。在action配置文件中的contentPosition中使用了.
	private String downloadFileName;
	//下面的文件名属性是在action配置里面注入的。
	private String fileName;
	
	/**
	 * 将可能出现的中文乱码进行转换
	 * @return
	 */
	public String getDownloadFileName() {
		String downFileName =fileName;
		 
		try {
			downloadFileName = new String(downFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
	public InputStream getTargetFile() throws Exception{ 
		 return ServletActionContext.getServletContext().getResourceAsStream(inputPath); 
	}
	
	public String execute() throws Exception {
		//得到download文件夹的在服务器上面的实际位置
		String downloadDir  =ServletActionContext.getServletContext().getRealPath("/WEB-INF/download");
		//得到要下载的文件的物理地址
		String downLoadFile =  ServletActionContext.getServletContext().getRealPath(inputPath);
		File file = new File(downLoadFile);
		System.out.println("downLoadFile="+downLoadFile);
		downLoadFile = file.getCanonicalPath();
		System.out.println("downLoadFile="+downLoadFile);
		//如果不在允许下载的文件夹里面，就不允许下载！
		if(!downLoadFile.startsWith(downloadDir)){
			ActionContext.getContext().put("typeError", "你没有权限下载！"); 
			return INPUT;
		}
		return SUCCESS;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

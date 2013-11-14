package common.struts2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.Action;

public class DownloadAction2 implements Action{ 
	//用来进行对下载的文件名命名的属性。在action配置文件中的contentPosition中使用了.
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
	
	public InputStream getTargetFile() throws Exception{
		//return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
		return new ByteArrayInputStream("struts 下载示例".getBytes());
	}
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

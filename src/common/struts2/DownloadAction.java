package common.struts2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.Action;

public class DownloadAction implements Action{  
	public InputStream getTargetFile() throws Exception{
		//return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
		return new ByteArrayInputStream("struts 下载示例".getBytes());
	}
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

}

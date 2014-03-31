package common.struts2;

import java.io.File;
import java.sql.Blob;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public class MyFile {
	String fileId;
	String fileName;
	int fileLen;
//	public File getContent2() {
//		return content2;
//	}
//
//	public void setContent2(File content2) {
//		this.content2 = content2;
//	}

//	public void setContent(Blob content) {
//		this.content = content;
//	}

//	File content2;
	File content;

	public File getContent() {
		return content;
	}

	public void setContent(File content) {
		this.content = content;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileLen() {
		return fileLen;
	}

	public void setFileLen(int fileLen) {
		this.fileLen = fileLen;
	}
 
}


package money.filemanage;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于文件管理的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class FileManagerVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FileManagerVO() {

	}
	
	public FileManagerVO( int sno , String fileId , String fileName , int fileLen ) {
		 this.sno = sno;
		 this.fileId = fileId;
		 this.fileName = fileName;
		 this.fileLen = fileLen;
	}
	
	public FileManagerVO(String fileId ,String fileName ,int fileLen ) {
			 this.fileId = fileId;
			 this.fileName = fileName;
			 this.fileLen = fileLen;
	}
	 
	private Integer sno; 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public Integer getSno(){
 		return sno;
 	}
 	
 	/**
 	 * 设置流水号的属性值.
 	 */
 	public void setSno(Integer sno){
 		this.sno = sno;
 	}
	private String fileId; 
 	/**
 	 * 获取文件id的属性值.
 	 */
 	public String getFileId(){
 		return fileId;
 	}
 	
 	/**
 	 * 设置文件id的属性值.
 	 */
 	public void setFileId(String fileid){
 		this.fileId = fileid;
 	}
	private String fileName; 
 	/**
 	 * 获取文件名的属性值.
 	 */
 	public String getFileName(){
 		return fileName;
 	}
 	
 	/**
 	 * 设置文件名的属性值.
 	 */
 	public void setFileName(String filename){
 		this.fileName = filename;
 	}
	private int fileLen; 
 	/**
 	 * 获取文件长度的属性值.
 	 */
 	public int getFileLen(){
 		return fileLen;
 	}
 	
 	/**
 	 * 设置文件长度的属性值.
 	 */
 	public void setFileLen(int filelen){
 		this.fileLen = filelen;
 	}
}

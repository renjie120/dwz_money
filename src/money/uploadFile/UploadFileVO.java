
package money.uploadFile;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于上传文件的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UploadFileVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public UploadFileVO() {

	}
	
	private String createUserName;
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public UploadFileVO( int sno , String businessId , String fileType , String isExist , String fileName , String realFileName , int fileSize , int createUser , String createTime ) {
		 this.sno = sno;
		 this.businessId = businessId;
		 this.fileType = fileType;
		 this.isExist = isExist;
		 this.fileName = fileName;
		 this.realFileName = realFileName;
		 this.fileSize = fileSize;
		 this.createUser = createUser;
		 this.createTime = createTime;
	}
	
	public UploadFileVO(String businessId ,String fileType ,String isExist ,String fileName ,String realFileName ,int fileSize ,int createUser ,String createTime ) {
			 this.businessId = businessId;
			 this.fileType = fileType;
			 this.isExist = isExist;
			 this.fileName = fileName;
			 this.realFileName = realFileName;
			 this.fileSize = fileSize;
			 this.createUser = createUser;
			 this.createTime = createTime;
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
	private String businessId; 
 	/**
 	 * 获取业务关联id 的属性值.
 	 */
 	public String getBusinessId(){
 		return businessId;
 	}
 	
 	/**
 	 * 设置业务关联id 的属性值.
 	 */
 	public void setBusinessId(String businessid){
 		this.businessId = businessid;
 	}
	private String fileType; 
 	/**
 	 * 获取文件类型的属性值.
 	 */
 	public String getFileType(){
 		return fileType;
 	}
 	
 	/**
 	 * 设置文件类型的属性值.
 	 */
 	public void setFileType(String filetype){
 		this.fileType = filetype;
 	}
	private String isExist; 
 	/**
 	 * 获取是否有效的属性值.
 	 */
 	public String getIsExist(){
 		return isExist;
 	}
 	
 	/**
 	 * 设置是否有效的属性值.
 	 */
 	public void setIsExist(String isexist){
 		this.isExist = isexist;
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
	private String realFileName; 
 	/**
 	 * 获取实际文件名的属性值.
 	 */
 	public String getRealFileName(){
 		return realFileName;
 	}
 	
 	/**
 	 * 设置实际文件名的属性值.
 	 */
 	public void setRealFileName(String realfilename){
 		this.realFileName = realfilename;
 	}
	private int fileSize; 
 	/**
 	 * 获取文件大小的属性值.
 	 */
 	public int getFileSize(){
 		return fileSize;
 	}
 	
 	/**
 	 * 设置文件大小的属性值.
 	 */
 	public void setFileSize(int filesize){
 		this.fileSize = filesize;
 	}
	private int createUser; 
 	/**
 	 * 获取上传用户的属性值.
 	 */
 	public int getCreateUser(){
 		return createUser;
 	}
 	
 	/**
 	 * 设置上传用户的属性值.
 	 */
 	public void setCreateUser(int createuser){
 		this.createUser = createuser;
 	}
	private String createTime; 
 	/**
 	 * 获取上传时间的属性值.
 	 */
 	public String getCreateTime(){
 		return createTime;
 	}
 	
 	/**
 	 * 设置上传时间的属性值.
 	 */
 	public void setCreateTime(String createtime){
 		this.createTime = createtime;
 	}

}

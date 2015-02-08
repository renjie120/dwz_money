
package money.uploadFile;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于上传文件的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface UploadFile extends BusinessObject {  
	String FILETYPE_COMAPNY = "119";
	String FILETYPE_COMMON = "120"; 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取业务关联id 的属性值.
 	 */
 	public  String   getBusinessId();
 	/**
 	 * 获取文件类型的属性值.
 	 */
 	public  String   getFileType();
 	/**
 	 * 获取是否有效的属性值.
 	 */
 	public  String   getIsExist();
 	/**
 	 * 获取文件名的属性值.
 	 */
 	public  String   getFileName();
 	/**
 	 * 获取实际文件名的属性值.
 	 */
 	public  String   getRealFileName();
 	/**
 	 * 获取文件大小的属性值.
 	 */
 	public  int   getFileSize();
 	/**
 	 * 获取上传用户的属性值.
 	 */
 	public  int   getCreateUser();
 	
 	public  String   getCreateUserName();
 	/**
 	 * 获取上传时间的属性值.
 	 */
 	public  String   getCreateTime();
}

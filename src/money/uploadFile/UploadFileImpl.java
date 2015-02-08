
package money.uploadFile;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于上传文件的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public  class UploadFileImpl implements UploadFile {
	private UploadFileVO uploadfileVO = null;
	private static final long serialVersionUID = 1L;

	public UploadFileImpl(UploadFileVO uploadfileVO) {
		this.uploadfileVO = uploadfileVO;
	}

	public UploadFileImpl( int sno , String businessId , String fileType , String isExist , String fileName , String realFileName , int fileSize , int createUser , String createTime ) {
		this.uploadfileVO = new UploadFileVO( sno , businessId , fileType , isExist , fileName , realFileName , fileSize , createUser , createTime );
	} 
	
	public UploadFileImpl(String businessId ,String fileType ,String isExist ,String fileName ,String realFileName ,int fileSize ,int createUser ,String createTime ) {
		this.uploadfileVO = new UploadFileVO(businessId ,fileType ,isExist ,fileName ,realFileName ,fileSize ,createUser ,createTime );
	} 

	public UploadFileVO getUploadFileVO() {
		return this.uploadfileVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.uploadfileVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.uploadfileVO.getSno();
 	}
 	/**
 	 * 获取业务关联id 的属性值.
 	 */
 	public  String   getBusinessId(){
 		return this.uploadfileVO.getBusinessId();
 	}
 	/**
 	 * 获取文件类型的属性值.
 	 */
 	public  String   getFileType(){
 		return this.uploadfileVO.getFileType();
 	}
 	/**
 	 * 获取是否有效的属性值.
 	 */
 	public  String   getIsExist(){
 		return this.uploadfileVO.getIsExist();
 	}
 	/**
 	 * 获取文件名的属性值.
 	 */
 	public  String   getFileName(){
 		return this.uploadfileVO.getFileName();
 	}
 	/**
 	 * 获取实际文件名的属性值.
 	 */
 	public  String   getRealFileName(){
 		return this.uploadfileVO.getRealFileName();
 	}
 	/**
 	 * 获取文件大小的属性值.
 	 */
 	public  int   getFileSize(){
 		return this.uploadfileVO.getFileSize();
 	}
 	/**
 	 * 获取上传用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.uploadfileVO.getCreateUser();
 	}
 	/**
 	 * 获取上传时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.uploadfileVO.getCreateTime();
 	}

	@Override
	public String getCreateUserName() {
		// TODO Auto-generated method stub
		return this.uploadfileVO.getCreateUserName();
	}
 
}
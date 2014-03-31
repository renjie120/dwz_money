
package money.filemanage;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于文件管理的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class FileManagerImpl implements FileManager {
	private FileManagerVO filemanagerVO = null;
	private static final long serialVersionUID = 1L;

	public FileManagerImpl(FileManagerVO filemanagerVO) {
		this.filemanagerVO = filemanagerVO;
	}

	public FileManagerImpl( int sno , String fileId , String fileName , int fileLen ) {
		this.filemanagerVO = new FileManagerVO( sno , fileId , fileName , fileLen );
	} 
	
	public FileManagerImpl(String fileId ,String fileName ,int fileLen ) {
		this.filemanagerVO = new FileManagerVO(fileId ,fileName ,fileLen );
	} 

	public FileManagerVO getFileManagerVO() {
		return this.filemanagerVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.filemanagerVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.filemanagerVO.getSno();
 	}
 	/**
 	 * 获取文件id的属性值.
 	 */
 	public  String   getFileId(){
 		return this.filemanagerVO.getFileId();
 	}
 	/**
 	 * 获取文件名的属性值.
 	 */
 	public  String   getFileName(){
 		return this.filemanagerVO.getFileName();
 	}
 	/**
 	 * 获取文件长度的属性值.
 	 */
 	public  int   getFileLen(){
 		return this.filemanagerVO.getFileLen();
 	}
 
}
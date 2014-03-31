
package money.filemanage;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于文件管理的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface FileManager extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取文件id的属性值.
 	 */
 	public  String   getFileId();
 	/**
 	 * 获取文件名的属性值.
 	 */
 	public  String   getFileName();
 	/**
 	 * 获取文件长度的属性值.
 	 */
 	public  int   getFileLen();
}

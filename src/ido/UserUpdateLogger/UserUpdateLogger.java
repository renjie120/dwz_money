
package ido.UserUpdateLogger;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于用户状态修改记录的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface UserUpdateLogger extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取用户 的属性值.
 	 */
 	public  String   getUserId();
 	
 	/**
 	 * 
 	 * @return
 	 */
	public  String   getUseName();
	 	/**
 	 * 获取用户状态的属性值.
 	 */
 	public  String   getState();
 	/**
 	 * 获取操作原因的属性值.
 	 */
 	public  String   getLogDetail();
 	/**
 	 * 获取备用字段1的属性值.
 	 */
 	public  int   getArg1();
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser();
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime();
}

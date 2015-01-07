
package ido.loginfo;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于操作日志的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface LogInfo extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public  int   getOperUser();
 	/**
 	 * 获取用户的属性值.
 	 */
 	public  String   getOperUserName();
 	/**
 	 * 获取时间 的属性值.
 	 */
 	public  String   getOperTime();
 	/**
 	 * 获取操作类型的属性值.
 	 */
 	public  String   getOperType();
 	/**
 	 * 获取ip地址的属性值.
 	 */
 	public  String   getOperIp();
 	/**
 	 * 获取操作地址的属性值.
 	 */
 	public  String   getOperUrl();
 	/**
 	 * 获取修改前的属性值.
 	 */
 	public  String   getOperBefore();
 	/**
 	 * 获取修改后的属性值.
 	 */
 	public  String   getOperAfter();
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getOperDesc();
}

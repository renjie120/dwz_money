
package money.myuser;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于用户信息表的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface MyUser extends BusinessObject {  
 	/**
 	 * 获取用户流水号的属性值.
 	 */
 	public  Integer   getUseId();
 	
 	public String getOrgName();
 	/**
 	 * 获取用户名的属性值.
 	 */
 	public  String   getUserName();
 	/**
 	 * 获取密码的属性值.
 	 */
 	public  String   getPassword();
 	/**
 	 * 获取登陆号的属性值.
 	 */
 	public  String   getLoginId();
 	/**
 	 * 获取组织机构的属性值.
 	 */
 	public  int   getOrgId();
 	/**
 	 * 获取邮件的属性值.
 	 */
 	public  String   getEmail();
 	/**
 	 * 获取座机的属性值.
 	 */
 	public  String   getPhone();
 	/**
 	 * 获取手机的属性值.
 	 */
 	public  String   getMobile();
 	/**
 	 * 获取用户类型的属性值.
 	 */
 	public  String   getUserType();
 	
 	public  String   getUserTypeName();
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getAddress();
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public  int   getOrderId();
}

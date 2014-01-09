
package money.rolemanager;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于角色信息的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface Role extends BusinessObject {  
 	/**
 	 * 获取角色id的属性值.
 	 */
 	public  Integer   getRoleId();
 	/**
 	 * 获取角色描述 的属性值.
 	 */
 	public  String   getRoleDesc();
 	/**
 	 * 获取角色名称的属性值.
 	 */
 	public  String   getRoleName();
}

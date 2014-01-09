
package money.rolemanager;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于角色拥有的菜单权限的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface RoleWithMenu extends BusinessObject {  
 	/**
 	 * 获取角色id的属性值.
 	 */
 	public  Integer   getId();
 	/**
 	 * 获取角色id 的属性值.
 	 */
 	public  int   getRoleId();
 	/**
 	 * 获取菜单id的属性值.
 	 */
 	public  int   getMenuId();
}

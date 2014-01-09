package money.role;

import dwz.framework.core.business.BusinessObject;

/**
 * 关于用户菜单权限信息的业务类接口
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public interface UserMenuRight extends BusinessObject {
	/**
	 * 获取菜单id的属性值.
	 */
	public int getMenuId();

	public Integer getId(); 
	/**
	 * 获取用户id的属性值.
	 */
	public int getUserId();
}

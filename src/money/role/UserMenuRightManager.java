package money.role;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;

/**
 * 关于用户菜单权限信息的业务操作操作接口.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public interface UserMenuRightManager extends BusinessObjectManager {

	/**
	 * 删除操作
	 * 
	 * @param menuId
	 */
	@Transactional
	public void addUserMenuRights(String menuId, String userId);

	/**
	 * 根据主键取值.
	 * 
	 * @param menuId
	 * @return
	 */
	public Set<Integer> getMenuIdsByUserId(int menuId);
}

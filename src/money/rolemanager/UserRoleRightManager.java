package money.rolemanager;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于用户角色权限的业务操作操作接口.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public interface UserRoleRightManager extends BusinessObjectManager { 

	/**
	 * 保存实体到数据库.
	 * 
	 * @param userroleright
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createUserRoleRight(UserRoleRight userroleright)
			throws ValidateFieldsException;

	/**
	 * 删除操作
	 * 
	 * @param id
	 */
	@Transactional
	public void removeUserRoleRights(String id); 
}

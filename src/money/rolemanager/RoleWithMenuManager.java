package money.rolemanager;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于角色拥有的菜单权限的业务操作操作接口.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public interface RoleWithMenuManager extends BusinessObjectManager {

	/**
	 * 保存实体到数据库.
	 * 
	 * @param rolewithmenu
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createRoleWithMenu(RoleWithMenu rolewithmenu)
			throws ValidateFieldsException;

	/**
	 * 删除操作
	 * 
	 * @param id
	 */
	@Transactional
	public void removeRoleWithMenus(String id);

	/**
	 * 查询用户有权限的角色.
	 * @param userId
	 * @return
	 */
	public Collection<Role> searchRolesByUserId(int userId,int startIndex, int count);
	
	/**
	 * 查询用户有权限的角色数量.
	 * @param userId
	 * @return
	 */
	public Integer searchRoleNumByUserId(int userId);
}

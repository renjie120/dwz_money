package money.rolemanager;

import java.util.Collection;

import dwz.dal.BaseDao;

/**
 * 关于角色拥有的菜单权限的数据库操作接口
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public interface RoleWithMenuDao extends BaseDao<RoleWithMenuVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<RoleWithMenuVO> findRecordById(int id);

	/**
	 * 根据角色id查询有权限的全部集合.
	 * 
	 * @param id
	 * @return
	 */
	public Collection<RoleWithMenuVO> findRecordByRoleId(int id);

	/**
	 * 查询全部集合.
	 */
	public Collection<RoleWithMenuVO> findAll();

	/**
	 * 删除角色下面的全部权限.
	 * 
	 * @param roleId
	 */
	public void deleteAllByRoleId(int roleId);

	/**
	 * 查询用户有权限的角色.
	 * @param userId
	 * @param startIndex
	 * @param count
	 * @return
	 */
	public Collection<Role> searchRolesByUserId(int userId, int startIndex,
			int count);

	/**
	 * 查询用户有权限的角色数量.
	 * 
	 * @param userId
	 * @return
	 */
	public Integer searchRoleNumByUserId(int userId);
}

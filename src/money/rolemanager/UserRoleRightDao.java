package money.rolemanager;

import java.util.Collection;

import dwz.dal.BaseDao; 

/**
 * 关于用户角色权限的数据库操作接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface UserRoleRightDao extends BaseDao<UserRoleRightVO, Integer> {
	/**
	 * 根据用户id查询全部集合.
	 */
	public Collection<UserRoleRightVO> findRecordByUserId(int userId); 
	
	/**
	 * 查询全部集合.
	 */
	public Collection<UserRoleRightVO> findAll(); 
	 

	/**
	 * 根据用户id删除数据.
	 */
	public void deleteAllByUserId(int userId);
	
	/**
	 * 查询总数.
	 * @param userId
	 * @return
	 */
	public Integer searchRoleNumByUserId(int userId);
	
	/**
	 * 根据流水号删除数据.
	 * @param id
	 */
	public void deleteRecordById(int id);

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccessUserRoleRightVO(UserRoleRightVO vo, int id);
}

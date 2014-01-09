
package money.role;

import java.util.Collection;

import dwz.dal.BaseDao; 

/**
 * 关于用户菜单权限信息的数据库操作接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface UserMenuRightDao extends BaseDao<UserMenuRightVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<UserMenuRightVO> findRecordByUserId(int uiserId); 
	
	 	/**
	 * 根据用户删除下面的全部权限..
	 */
	public void deleteAllByUserId(int userId); 
 
}

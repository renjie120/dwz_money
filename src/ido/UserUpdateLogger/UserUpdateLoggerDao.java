
package ido.UserUpdateLogger;

import java.util.Collection;

import dwz.dal.BaseDao; 

/**
 * 关于用户状态修改记录的数据库操作接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface UserUpdateLoggerDao extends BaseDao<UserUpdateLoggerVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<UserUpdateLoggerVO> findRecordById(int sno); 
	
	/**
	 * 查询全部集合.
	 */
	public Collection<UserUpdateLoggerVO> findAll(); 
	
	/**
	 * 根据条件查询全部集合.
	 */
	public Collection<UserUpdateLoggerVO> findParmByType(int paramType);  

	/**
	 * 根据主键删除数据.
	 */
	public void deleteAllById(int sno);

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccessUserUpdateLoggerVO(UserUpdateLoggerVO vo, int sno);
}

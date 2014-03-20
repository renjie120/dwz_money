package money.myuser;

import java.util.Collection;

import dwz.dal.BaseDao;

/**
 * 关于用户信息表的数据库操作接口
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public interface MyUserDao extends BaseDao<MyUserVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<MyUserVO> findRecordById(int useId);
	
	public Collection<MyUserVO> findRecordById2(int useId);

	/**
	 * 查询全部集合.
	 */
	public Collection<MyUserVO> findAll();

	/**
	 * 根据条件查询全部集合.
	 */
	public Collection<MyUserVO> findParmByType(int paramType);

	/**
	 * 根据主键删除数据.
	 */
	public void deleteAllById(int useId);

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccessMyUserVO(MyUserVO vo, int useId);

	public void updateMyUser(MyUserVO vo);
	
	public Collection<Integer> findByLoginId(String loginId, String pass);

	public Collection<MyUserVO> findByLoginId2(String loginId);

	public void updateAllPasswd(String pass, String id);  
	
	public void updateAllPasswdById(String pass, int id);
}

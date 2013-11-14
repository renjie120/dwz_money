
package money.user;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface MyUserDao extends BaseDao<MyUserVO, Integer> {

	public Collection<MyUserVO> findRecordById(int moneySno); 
	
	public Collection<MyUserVO> findAll(); 
	
	public Collection<Integer> findByLoginId(String loginId,String pass); 
	
	public Collection<MyUserVO> findByLoginId2(String loginId); 

	public void deleteAllById(String id); 

	public void updateAllaccessUserVO(MyUserVO vo, String id);
	
	public void updateAllPasswd(String pass, String id);
}


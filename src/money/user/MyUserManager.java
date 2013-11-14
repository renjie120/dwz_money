
package money.user;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface MyUserManager extends BusinessObjectManager {  

	public Collection<IUser> searchUser(Map<MyUserSearchFields, Object> criterias,
			String orderField, int startIndex, int count); 
	
	public Integer searchUserNum(Map<MyUserSearchFields, Object> criterias); 

	@Transactional
	public void createUser(IUser user)
			throws ValidateFieldsException;

	@Transactional
	public void updateUser(IUser user) throws ValidateFieldsException;
	
	@Transactional
	public void changePwd(String loginId,String pass) throws ValidateFieldsException;

	@Transactional
	public void removeUser(String userId);
	
	public IUser getUser(Integer id);
}


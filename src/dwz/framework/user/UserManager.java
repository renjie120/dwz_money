package dwz.framework.user;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.constants.user.UserSearchFields;
import dwz.framework.constants.user.UserStatus;
import dwz.framework.constants.user.UserType;
import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface UserManager extends BusinessObjectManager {

	User newUser(String userName, UserType userType);

	@Transactional
	public void createUser(User user)
			throws ValidateFieldsException;

	@Transactional
	public void updateUser(User user) throws ValidateFieldsException;

	@Transactional
	public void removeUser(String userId);

	public User getUser(String id);

	public User getUserByName(String userName);

	public boolean isUniqueUser(String userName, String id);

	@Transactional
	public void changeUserStatus(User user, UserStatus userStatus);

	@Transactional
	public void resetPassword(User user);

	@Transactional
	public void changePassword(User user, String oldpwd, String newpwd)
			throws ValidateFieldsException;

	public Collection<User> searchUser(Map<UserSearchFields, String> criterias,
			String orderField, int startIndex, int count);

	public Integer searchUserNum(Map<UserSearchFields, String> criterias);

}

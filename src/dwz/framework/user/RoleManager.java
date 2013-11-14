package dwz.framework.user;

import java.util.Collection;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;


@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface RoleManager extends BusinessObjectManager{

//	public void createRole(Role role);
//	public void saveRole(Role role);
//	public void removeRole(Role role);

	public Role getRole(int id);

	public Collection<Role> listAllRoles();
//	public Collection<Role> listAllRoles(User user);
	
	public Collection<Role> listRoles(User user);

	public void saveRoles(User user, int[] roleIds) throws ValidateFieldsException;
}

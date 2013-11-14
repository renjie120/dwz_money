package money.user;

import java.util.Collection;

import dwz.framework.core.business.BusinessObject;
import dwz.framework.user.Role;

public interface IUser extends BusinessObject {
	public Integer getId();

	public int getUserId();

	public String getUserName();

	public String getPass();
	
	public void setPass(String pass);

	public String getLoginId();

	public int getOrgId();

	public String getEmail();

	public String getPhone();

	public String getMobile();
	
	public Collection<Role> getRoles();

	public String getAddress();

	public int getOrderId();
}

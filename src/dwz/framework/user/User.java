package dwz.framework.user;

import java.util.Collection;
import java.util.Date;

import dwz.framework.constants.user.UserStatus;
import dwz.framework.constants.user.UserType;
import dwz.framework.core.business.BusinessObject;

public interface User extends BusinessObject {

	/**
	 * 返回登陆id.例如“admin”
	 */
	public String getId();
	
	/**
	 * 返回用户流水号，是数字
	 * @return
	 */
	public String getUserId();

	/**
	 * 用户名.
	 * @return
	 */
	public String getUserName();

//	public void setUserName(String name);

	/**
	 * 邮箱
	 */
	public String getEmail();

	public void setEmail(String email);
	
	public String getPostcode();
	
	public void setPostcode(String postcode);

	public String getPhone();

	public void setPhone(String phone);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public UserType getUserType();

	public Collection<Role> getRoles();

	public String getTitle();

	public void setTitle(String title);

	public String getPassword();

	public void setPassword(String password);

	public Date getBirthDate();

	public void setBirthDate(Date birthDate);
	
	public User getInsertBy();
	
	public Date getInsertDate();
	
	public Date getUpdateDate();
	
	public UserStatus getStatus();
	
	public void setStatus(UserStatus status);


}

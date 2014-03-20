package dwz.framework.user.impl;

import java.util.Collection;
import java.util.Date;

import money.param.Param;
import money.param.ParamManager;

import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.constants.user.UserStatus;
import dwz.framework.constants.user.UserType;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.Role;
import dwz.framework.user.RoleManager;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.framework.utils.EnumUtils;
import dwz.persistence.beans.SysUser;

public class UserImpl extends AbstractBusinessObject implements User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7832441082521547711L;

	private SysUser sysUser = null;

	UserImpl(String userName, String userType, String insertBy) {
		Date now = new Date();
		sysUser = new SysUser(userName, userType, UserStatus.ACTIVE.toString(),
				now, now, insertBy);
	}

	public UserImpl(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getEmail() {
		return this.sysUser.getEmail();
	}

	public String getId() {
		return this.sysUser.getId();
	}

	public String getUserName() {
		return this.sysUser.getUserName();
	}

	public String getPhone() {
		return this.sysUser.getPhone();
	}

	public UserType getUserType() {
		ParamManager pm = (ParamManager) SpringContextUtil
				.getBean(BeanManagerKey.paramManager.toString());
		try {
			Param p = pm.getParam(Integer.parseInt(this.sysUser.getUserType()));
			// 如果是1，就是超级用户
			if ("1".equals(p.getUsevalue())) {
				return UserType.SUPER;
			}
			// 2就是管理员.
			else if ("2".equals(p.getUsevalue()))
				return UserType.ADMIN;
			// 其他都是普通用户.
			else
				return UserType.PERSON;
		} catch (Exception e) {
			return UserType.PERSON;
		}
	}

	public void setEmail(String email) {
		this.sysUser.setEmail(email);
	}

	// public void setUserName(String name) {
	// this.sysUser.setUserName(name);
	// }

	public void setPhone(String phone) {
		this.sysUser.setPhone(phone);
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public UserStatus getStatus() {
		if (EnumUtils.isDefined(UserStatus.values(), sysUser.getStatus())) {
			return UserStatus.valueOf(sysUser.getStatus());
		}

		return UserStatus.INACTIVE;
	}

	public void setStatus(UserStatus status) {
		this.sysUser.setStatus(status.toString());
	}

	public String getPassword() {
		return this.sysUser.getPassword();
	}

	public void setPassword(String password) {
		this.sysUser.setPassword(password);
	}

	public String getTitle() {
		return this.sysUser.getTitle();
	}

	public void setTitle(String title) {
		this.sysUser.setTitle(title);
	}

	public String getFirstName() {
		return this.sysUser.getFirstName();
	}

	public String getLastName() {
		return this.sysUser.getLastName();
	}

	public void setFirstName(String firstName) {
		this.sysUser.setFirstName(firstName);
	}

	public void setLastName(String lastName) {
		this.sysUser.setLastName(lastName);
	}

	public Date getBirthDate() {
		return sysUser.getBirthDate();
	}

	public void setBirthDate(Date birthDate) {
		sysUser.setBirthDate(birthDate);
	}

	public Collection<Role> getRoles() {
		RoleManager rm = BusinessFactory.getFactory().getManager(
				BeanManagerKey.roleManager);
		return rm.listRoles(this);
	}

	public Date getInsertDate() {
		return sysUser.getInsertDate();
	}

	public Date getUpdateDate() {
		return sysUser.getUpdateDate();
	}

	public User getInsertBy() {
		UserManager uMgr = BusinessFactory.getFactory().getManager(
				BeanManagerKey.userManager);
		return uMgr.getUser(sysUser.getInsertBy());
	}

	public void setInsertBy(String userId) {
		sysUser.setInsertBy(userId);

	}

	public String getPostcode() {
		return sysUser.getPostcode();
	}

	public void setPostcode(String postcode) {
		sysUser.setPostcode(postcode);

	}

	public String getUserId() {
		return sysUser.getUserId();
	}

}

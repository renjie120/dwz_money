package dwz.framework.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import dwz.framework.constants.user.UserOrderByFields;
import dwz.framework.constants.user.UserSearchFields;
import dwz.framework.constants.user.UserStatus;
import dwz.framework.constants.user.UserType;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.framework.utils.StringUtils;
import dwz.framework.utils.Utils;
import dwz.persistence.beans.SysUser;
import dwz.persistence.daos.SysUserDao;

public class UserManagerImpl extends AbstractBusinessObjectManager implements
		UserManager {

	private SysUserDao	sysUserDao	= null;

	public UserManagerImpl(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public void changeUserStatus(User user, UserStatus status) {
		UserImpl userImpl = (UserImpl) user;
		userImpl.setStatus(status);

		SysUser po = userImpl.getSysUser();
		po.setUpdateDate(new Date());
		this.sysUserDao.update(po);
	}

	public User newUser(String userName, UserType userType) {

		return new UserImpl(userName, userType.toString(), getContextUser()
				.getId());
	}

	public void removeUser(String userId) {

		SysUser sysUser = this.sysUserDao.findByPrimaryKey(userId);
		sysUser.setStatus("DELETED");
		sysUserDao.update(sysUser);
		
	}

	public void createUser(User user)
			throws ValidateFieldsException {
		String userName = user.getUserName();
		if (user.getUserType() == null){
			throw new ValidateFieldsException("Usertype is required.");
		}
		if (userName == null || userName.length() < 1){
			throw new ValidateFieldsException("Username is required.");
		}
		if (!isUniqueUser(user.getUserName(),  null)) {
			throw new ValidateFieldsException(
					"Username exists. Please re-enter another username. ");
		}
		UserImpl userImpl = (UserImpl) user;
		userImpl.setPassword(StringUtils.randomStr(6));
		SysUser po = userImpl.getSysUser();
		po.setInsertBy(getContextUser().getId());
		
		this.sysUserDao.insert(po);
	}

	public User getUser(String userId) {
		SysUser sysUser = this.sysUserDao.findByPrimaryKey(userId);

		return new UserImpl(sysUser);
	}

	private Collection<User> listUsers(UserType userType, int startIndex,
			int count) {
		ArrayList<User> users = new ArrayList<User>();
		Collection<SysUser> sysUsers = this.sysUserDao.findAllUserPageBreak(
				userType.toString(), startIndex, count);

		if (sysUsers == null) {
			return new ArrayList<User>(0);
		}

		for (SysUser po : sysUsers) {
			users.add(new UserImpl(po));
		}
		return users;
	}

	public void resetPassword(User user) {
		String password = Utils.createInitPassword();

		this.changePassword(user, password);
	}

	public void updateUser(User user) throws ValidateFieldsException {
		String userName = user.getUserName();
		if (userName == null || userName.length() < 1)
			throw new ValidateFieldsException("Username is required.");
		if (!isUniqueUser(user.getUserName(), user.getId())) {
			throw new ValidateFieldsException(
					"Username exists. Please re-enter another username. ");
		}

		UserImpl userImpl = (UserImpl) user;

		SysUser po = userImpl.getSysUser();

		po.setUpdateDate(new Date());
		this.sysUserDao.update(po);
	}

	public void changePassword(User user, String oldpwd, String newpwd)
			throws ValidateFieldsException {

		if (newpwd == null || "".equals(newpwd.trim())) {
			throw new ValidateFieldsException("Password is required.");
		}
		if (!oldpwd.equals(user.getPassword())) {
			throw new ValidateFieldsException(
					"The old password is incorrect.");
		}

		this.changePassword(user, newpwd);

	}

	private void changePassword(User user, String password) {
		UserImpl userImpl = (UserImpl) user;

		userImpl.setPassword(password);

		SysUser po = userImpl.getSysUser();

		po.setUpdateDate(new Date());
		this.sysUserDao.update(po);
	}

	public boolean isUniqueUser(String userName, String id) {

		Number count = null;
		Collection<Number> ids = this.sysUserDao.findUsernameUnique(
				id != null ? id : "", userName);

		if (ids == null || ids.size() < 1)
			return false;

		count = ids.toArray(new Number[ids.size()])[0];

		if (count == null || count.intValue() > 0)
			return false;

		return true;
	}

	public User getUserByName(String userName) {

		Collection<SysUser> sUsers = this.sysUserDao.findUserByName(userName);

		if (sUsers == null || sUsers.size() < 1)
			return null;

		SysUser suser = sUsers.toArray(new SysUser[sUsers.size()])[0];

		return new UserImpl(suser);
	}


	public Collection<User> searchUser(Map<UserSearchFields, String> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<User> eaList = new ArrayList<User>();
		if (criterias == null)
			return eaList;
		
		String hql = this.createQueryString(false, criterias, orderField);
		System.out.println(hql);
		Collection<SysUser> sysUsers = this.sysUserDao.findByQuery(this
				.createQueryString(false, criterias, orderField), startIndex,
				count);

		if (sysUsers == null || sysUsers.size() == 0)
			return eaList;

		for (SysUser po : sysUsers) {
			eaList.add(new UserImpl(po));
		}

		return eaList;
	}

	public Integer searchUserNum(Map<UserSearchFields, String> criterias) {

		if (criterias == null) {
			return 0;
		}

		Number totalCount = this.sysUserDao.countByQuery(createQueryString(
				true, criterias, null));

		return totalCount.intValue();
	}

	private String createQueryString(boolean useCount,
			Map<UserSearchFields, String> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();

		sb
				.append(
						useCount ? "select count(distinct sysUser) "
								: "select distinct sysUser ")
				.append(
						"from SysUser as sysUser left join sysUser.sysUserRoles as sysUserRole");

		int count = 0;
		for (UserSearchFields field : UserSearchFields.values()) {
			switch (field) {

			case USER_NAME:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUser.userName='").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			case USER_TYPE:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUser.userType='").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			case FIRST_NAME:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUser.firstName like '").append(
							criterias.get(field)).append("%'");
					count++;
				}
				break;

			case LAST_NAME:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUser.lastName like '")
							.append(criterias.get(field)).append("%'");
					count++;
				}
				break;
			case EMAIL:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUser.email = '").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			case POSTCODE:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUser.postcode like '").append(
							criterias.get(field)).append(
							"%'");
					count++;
				}
				break;

			case ROLE:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUserRole.id.sysRole.id = '").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			case PHONE:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUser.phone = '").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			case STATUS:
				if (criterias.containsKey(field)) {
					sb.append(count == 0 ? " where" : " and").append(" sysUser.status = '").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			
			case KEYWORDS:
				if (criterias.containsKey(field)) {
					String keywords = criterias.get(field);
					sb.append(count == 0 ? " where" : " and").append(" (sysUser.firstName like '%")
							.append(keywords).append("'").append(
									" or sysUser.lastName like '%").append(
									keywords).append("'").append(
									" or sysUser.userName like '%").append(
									keywords).append("'").append(
									" or sysUser.email like '%").append(
									keywords).append("'").append(
									" or sysUser.postcode like '%").append(
									keywords).append("'").append(
									" or sysUser.phone = '").append(keywords)
							.append("')");
					count++;
				}
				break;
			default:
				break;
			}
		}

		if (sb.indexOf("sysUser.status=") == -1) {
			sb.append(count == 0 ? " where" : " and").append(" sysUser.status<>'DELETED'");
		}

		if (useCount)
			return sb.toString();

		UserOrderByFields orderBy = UserOrderByFields.INSERT_DATE_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = UserOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case USER_NAME:
			sb.append(" order by sysUser.userName");
			break;
		case USER_NAME_DESC:
			sb.append(" order by sysUser.userName desc");
			break;
		case EMAIL:
			sb.append(" order by sysUser.email");
			break;
		case EMAIL_DESC:
			sb.append(" order by sysUser.email desc");
			break;
		case FIRST_NAME:
			sb.append(" order by sysUser.firstName");
			break;
		case FIRST_NAME_DESC:
			sb.append(" order by sysUser.firstName desc");
			break;
		case INSERT_DATE:
			sb.append(" order by sysUser.insertDate");
			break;
		case INSERT_DATE_DESC:
			sb.append(" order by sysUser.insertDate desc");
			break;
		case STATUS:
			sb.append(" order by sysUser.status");
			break;
		case STATUS_DESC:
			sb.append(" order by sysUser.status desc");
			break;
		}
		// System.out.println("hql=" + sb);
		return sb.toString();
	}
	
}

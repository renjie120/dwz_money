package dwz.present.app.management;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dwz.constants.BeanManagerKey;
import dwz.framework.constants.user.Title;
import dwz.framework.constants.user.UserSearchFields;
import dwz.framework.constants.user.UserStatus;
import dwz.framework.constants.user.UserType;
import dwz.framework.context.AppContextHolder;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.Role;
import dwz.framework.user.RoleManager;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637525620541964172L;
	private int[] roleIds;
	private int roleId;
	private String uid;

	private String userName;
	private UserStatus userStatus;
	private UserType userType = UserType.PERSON;
	UserManager uMgr = bf.getManager(BeanManagerKey.userManager);
	private User user = uMgr.newUser("", userType);

	private Map<UserSearchFields, String> getCriterias() {
		Map<UserSearchFields, String> criterias = new HashMap<UserSearchFields, String>();
		if (getKeywords() != null && !getKeywords().equals(""))
			criterias.put(UserSearchFields.KEYWORDS, getKeywords());
		if (getUserName() != null && getUserName().length() > 0)
			criterias.put(UserSearchFields.USER_NAME, getUserName());
		if (user.getFirstName() != null && user.getFirstName().length() > 0)
			criterias.put(UserSearchFields.FIRST_NAME, user.getFirstName());
		if (user.getLastName() != null && user.getLastName().length() > 0)
			criterias.put(UserSearchFields.LAST_NAME, user.getLastName());
		if (user.getEmail() != null && user.getEmail().length() > 0)
			criterias.put(UserSearchFields.EMAIL, user.getEmail());
		if (user.getPostcode() != null && user.getPostcode().length() > 0)
			criterias.put(UserSearchFields.POSTCODE, user.getPostcode());
		if (roleId > 0)
			criterias.put(UserSearchFields.ROLE, roleId + "");
		if (getUserStatus() != null)
			criterias.put(UserSearchFields.STATUS, getUserStatus().toString());
		if (userType != null)
			criterias.put(UserSearchFields.USER_TYPE, userType.toString());

		return criterias;
	}

	private static enum ExportFiled {
		USER_NAME, FIRST_NAME, LAST_NAME, TITLE, EMAIL, PHONE, POSTCODE, STATUS, BIRTH_DATE, INSERT_DATE;
	}

	public String list() {
		int pageNum = this.getPageNum() > 0 ? this.getPageNum() - 1 : 0;
		int startIndex = pageNum * getNumPerPage();

		Map<UserSearchFields, String> criterias = getCriterias();

		Collection<User> userList = uMgr.searchUser(criterias,
				realOrderField(), startIndex, getNumPerPage());
		ActionContext.getContext().put("userList", userList);

		this.setTotalCount(uMgr.searchUserNum(criterias));

		return INPUT;
	}
	
	public String gridTree() {
		int pageNum = this.getPageNum() > 0 ? this.getPageNum() - 1 : 0;
		int startIndex = pageNum * getNumPerPage();

		Map<UserSearchFields, String> criterias = getCriterias();

		Collection<User> userList = uMgr.searchUser(criterias,
				realOrderField(), startIndex, getNumPerPage());
		ActionContext.getContext().put("userList", userList);

		this.setTotalCount(uMgr.searchUserNum(criterias));

		return INPUT;
	}

	public String export() {
		UserManager manager = BusinessFactory.getFactory().getUserManager();

		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=userList.xls");

		Map<UserSearchFields, String> criterias = getCriterias();
		Collection<User> userList = uMgr.searchUser(criterias,
				realOrderField(), 0, 2000);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		// header
		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.toString());
		}

		// ea info
		for (User user : userList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case USER_NAME:
					e.setCell(filed.ordinal(), user.getUserName());
					break;
				case FIRST_NAME:
					e.setCell(filed.ordinal(), user.getFirstName());
					break;
				case LAST_NAME:
					e.setCell(filed.ordinal(), user.getLastName());
					break;
				case TITLE:
					e.setCell(filed.ordinal(), user.getTitle());
					break;
				case PHONE:
					e.setCell(filed.ordinal(), user.getPhone());
					break;
				case EMAIL:
					e.setCell(filed.ordinal(), user.getEmail());
					break;
				case BIRTH_DATE:
					e.setCell(filed.ordinal(), user.getBirthDate());
					break;
				case INSERT_DATE:
					e.setCell(filed.ordinal(), user.getInsertDate());
					break;
				case POSTCODE:
					e.setCell(filed.ordinal(), user.getPostcode());
					break;
				case STATUS:
					e.setCell(filed.ordinal(), user.getStatus().toString());
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	public String edit() {
		user = uMgr.getUser(uid);
		return INPUT;
	}

	public String insert() {
		User m = uMgr.newUser(userName, userType);
		m.copyProperties(user);
		try {
			uMgr.createUser(m);

			if (roleIds != null) {
				RoleManager rMgr = bf.getManager(BeanManagerKey.roleManager);
				rMgr.saveRoles(m, roleIds);
			}
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String update() {
		User m = uMgr.getUser(uid);
		m.copyProperties(user);
		try {
			uMgr.updateUser(m);
			if (roleIds != null) {
				RoleManager rMgr = bf.getManager(BeanManagerKey.roleManager);
				rMgr.saveRoles(m, roleIds);
			}
		} catch (ValidateFieldsException e) {
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String delete() {
		User m = uMgr.getUser(uid);
		m.setStatus(UserStatus.DELETED);
		try {
			uMgr.updateUser(m);
		} catch (ValidateFieldsException e) {
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Title[] getTitles() {
		return Title.values();
	}

	public Collection<Role> getRolesByUser() {
		RoleManager rMgr = bf.getManager(BeanManagerKey.roleManager);

		return rMgr.listRoles(AppContextHolder.getContext().getUser());
	}

	public Collection<Role> getAllRoles() {
		RoleManager rMgr = bf.getManager(BeanManagerKey.roleManager);
		return rMgr.listAllRoles();
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}

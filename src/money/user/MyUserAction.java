package money.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import common.util.Cdd2;
import common.util.Coder;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.user.User;
import dwz.present.BaseAction;

/**
 * 用户
 * 
 * @author lsq
 * 
 */
public class MyUserAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyUserManager pMgr = bf.getManager(BeanManagerKey.myUserManager);
	private IUser userVo;
	private int userId;
	private String userName;
	private String pass;
	private String loginId;
	private int orgId;
	private int orderId;
	private String email;
	private String userType;
	private String phone;
	private String mobile;
	private String address;

	public MyUserManager getPMgr() {
		return pMgr;
	}

	public void setPMgr(MyUserManager mgr) {
		pMgr = mgr;
	}

	public IUser getParamVo() {
		return userVo;
	}

	public void setParamVo(IUser userVo) {
		this.userVo = userVo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String beforeAdd() {
		return "detail";
	}

	/**
	 * 添加信息.
	 * 
	 * @return
	 */
	public String doAdd() {
		try {  
			pass = Coder.toMyCoder(pass);
			MyUserImpl userImpl = new MyUserImpl(userName, pass, loginId,
					orgId, email, phone, mobile, address, orderId,UserType.USER.getType());
			pMgr.createUser(userImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		} catch (Exception e) { 
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	/**
	 * 删除信息.
	 * 
	 * @return
	 */
	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeUser(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	/**
	 * 得到详细信息.
	 * 
	 * @return
	 */
	public String beforeUpdate() {
		Cdd2 cdd2 = new Cdd2();
		userVo = pMgr.getUser(userId); 
		return "editdetail";
	}

	/**
	 * 更新信息.
	 * 
	 * @return
	 */
	public String doUpdate() {
		try { 
			MyUserImpl userImpl = new MyUserImpl(userId, userName, pass,
					loginId, orgId, email, phone, mobile, address, orderId,UserType.USER.getType());
			pMgr.updateUser(userImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}
	
	public String changePwd() {
		try {  
			User user = getAppContext().getUser(); 
			String newPass = (String)request.getParameter("newPassword");
			String oldPassword = (String)request.getParameter("oldPassword"); 
			if (!oldPassword.equals(user.getPassword())) {
				return ajaxForwardError(getText("msg.password.incorrect"));
			}  
			pMgr.changePwd(user.getId(),Coder.toMyCoder(newPass));
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	private int page = 1;
	private int pageSize = 50;
	private long count;

	/**
	 * 查询信息.
	 * 
	 * @return
	 */
	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<MyUserSearchFields, Object> criterias = getCriterias();

		Collection<IUser> moneyList = pMgr.searchUser(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", pMgr.searchUserNum(criterias));
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",
				pMgr.searchUserNum(criterias));
		return "list";
	}

	public String reQuery() {
		return "list";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private Map<MyUserSearchFields, Object> getCriterias() {
		Map<MyUserSearchFields, Object> criterias = new HashMap<MyUserSearchFields, Object>();
		return criterias;
	}

	public IUser getUserVo() {
		return userVo;
	}

	public void setUserVo(IUser userVo) {
		this.userVo = userVo;
	}

	public IUser getMoneyVo() {
		return userVo;
	}

	public void setMoneyVo(IUser userVo) {
		this.userVo = userVo;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}

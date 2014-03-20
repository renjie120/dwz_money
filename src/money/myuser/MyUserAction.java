package money.myuser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import common.util.Coder;

import dwz.constants.BeanManagerKey;
import dwz.framework.constants.Constants;
import dwz.framework.core.exception.BusinessException;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.user.impl.UserImpl;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于用户信息表的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class MyUserAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	MyUserManager pMgr = bf.getManager(BeanManagerKey.myuserManager);
	// 业务实体对象
	private MyUser vo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
	private long count;

	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			password = Coder.toMyCoder(password);
			MyUserImpl myuserImpl = new MyUserImpl(userName, password, loginId,
					orgId, email, phone, mobile, userType, address, orderId);
			pMgr.createMyUser(myuserImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		} catch (Exception e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}
	
	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeMyUsers(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}
	
	public String initPass() {
		String ids = request.getParameter("ids");
		pMgr.initPassword(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getMyUser(useId);
		return "editdetail";
	}

	/**
	 * 首页的我的资料.
	 * 
	 * @return
	 */
	public String myContact() {
		HttpSession session = request.getSession();
		int userid = Integer.parseInt(((UserImpl) session
				.getAttribute(Constants.AUTHENTICATION_KEY)).getUserId());
		vo = pMgr.getMyUser(userid);
		return "myContact";
	}

	private String oldPassword;
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String changePwd() {
		try {
			UserImpl user = (UserImpl) ActionContext.getContext().getSession()
					.get(Constants.AUTHENTICATION_KEY);
			if (!oldPassword.equals(user.getPassword())) {
				throw new BusinessException("密码不正确！");
			}
			newPassword = Coder.toMyCoder(newPassword);
			pMgr.updatePassword(newPassword, user.getUserName());
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		} catch (Exception e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String doUpdate() {
		try {
			MyUser u = pMgr.getMyUser(useId);
			if (password != null && !"".equals(password))
				password = Coder.toMyCoder(password);
			else {
				password = u.getPassword();
			}
			MyUserVO myuserVO = new MyUserVO();
			myuserVO.setUseId(useId);
			myuserVO.setUserName(userName);
			myuserVO.setPassword(password);
			myuserVO.setLoginId(loginId);
			if (orgId == null) {
				orgId = u.getOrgId();
			}
			if (userType == null) {
				userType = u.getUserType();
			}
			if (orderId == null) {
				orderId = u.getOrderId();
			}
			myuserVO.setOrgId(orgId);
			myuserVO.setEmail(email);
			myuserVO.setPhone(phone);
			myuserVO.setMobile(mobile);
			myuserVO.setUserType(userType);
			myuserVO.setAddress(address);
			myuserVO.setOrderId(orderId);
			MyUserImpl myuserImpl = new MyUserImpl(myuserVO);
			pMgr.updateMyUser(myuserImpl);
			// 从session中取得当前登录人
			UserImpl user = (UserImpl) ActionContext.getContext().getSession()
					.get(Constants.AUTHENTICATION_KEY);
			if (user.getUserId().equals(useId)) {
				user.setPassword(Coder.fromMyCoder(password));
			}
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
			return ajaxForwardError(e.getLocalizedMessage());
		} catch (Exception e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		USEID("用户流水号"), USERNAME("用户名"), PASSWORD("密码"), LOGINID("登陆号"), ORGID(
				"组织机构"), EMAIL("邮件"), PHONE("座机"), MOBILE("手机"), USERTYPE(
				"用户类型"), ADDRESS("地址"), ORDERID("排序号");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public String beforeQuery() {
		return "query";
	}

	public String MyGridTree() {
		return "gridtree";
	}

	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=MyUserList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<MyUserSearchFields, Object> criterias = getCriterias();

		Collection<MyUser> myuserList = pMgr.searchMyUser(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (MyUser myuser : myuserList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case USEID:
					e.setCell(filed.ordinal(), myuser.getUseId());
					break;
				case USERNAME:
					e.setCell(filed.ordinal(), myuser.getUserName());
					break;
				case PASSWORD:
					e.setCell(filed.ordinal(), myuser.getPassword());
					break;
				case LOGINID:
					e.setCell(filed.ordinal(), myuser.getLoginId());
					break;
				case ORGID:
					e.setCell(filed.ordinal(), myuser.getOrgId());
					break;
				case EMAIL:
					e.setCell(filed.ordinal(), myuser.getEmail());
					break;
				case PHONE:
					e.setCell(filed.ordinal(), myuser.getPhone());
					break;
				case MOBILE:
					e.setCell(filed.ordinal(), myuser.getMobile());
					break;
				case USERTYPE:
					e.setCell(filed.ordinal(), myuser.getUserType());
					break;
				case ADDRESS:
					e.setCell(filed.ordinal(), myuser.getAddress());
					break;
				case ORDERID:
					e.setCell(filed.ordinal(), myuser.getOrderId());
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<MyUserSearchFields, Object> criterias = getCriterias();

		Collection<MyUser> moneyList = pMgr.searchMyUser(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchMyUserNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
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
		if (getUseId() != null && getUseId() != 0)
			criterias.put(MyUserSearchFields.USEID, getUseId());
		if (getUserName() != null && !"".equals(getUserName()))
			criterias.put(MyUserSearchFields.USERNAME, getUserName());
		if (getLoginId() != null && !"".equals(getLoginId()))
			criterias.put(MyUserSearchFields.LOGINID, getLoginId());
		if (getOrgId() != null && getOrgId() != 0)
			criterias.put(MyUserSearchFields.ORGID, getOrgId());
		return criterias;
	}

	public MyUser getVo() {
		return vo;
	}

	public void setVo(MyUser vo) {
		this.vo = vo;
	}

	private Integer useId;

	/**
	 * 获取用户流水号的属性值.
	 */
	public Integer getUseId() {
		return useId;
	}

	/**
	 * 设置用户流水号的属性值.
	 */
	public void setUseId(Integer useid) {
		this.useId = useid;
	}

	private String userName;

	/**
	 * 获取用户名的属性值.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名的属性值.
	 */
	public void setUserName(String username) {
		this.userName = username;
	}

	private String password;

	/**
	 * 获取密码的属性值.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码的属性值.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	private String loginId;

	/**
	 * 获取登陆号的属性值.
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * 设置登陆号的属性值.
	 */
	public void setLoginId(String loginid) {
		this.loginId = loginid;
	}

	private Integer orgId;

	/**
	 * 获取组织机构的属性值.
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * 设置组织机构的属性值.
	 */
	public void setOrgId(Integer orgid) {
		this.orgId = orgid;
	}

	private String email;

	/**
	 * 获取邮件的属性值.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮件的属性值.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	private String phone;

	/**
	 * 获取座机的属性值.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置座机的属性值.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String mobile;

	/**
	 * 获取手机的属性值.
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机的属性值.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String userType;

	/**
	 * 获取用户类型的属性值.
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * 设置用户类型的属性值.
	 */
	public void setUserType(String usertype) {
		this.userType = usertype;
	}

	private String address;

	/**
	 * 获取地址的属性值.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置地址的属性值.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	private Integer orderId;

	/**
	 * 获取排序号的属性值.
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置排序号的属性值.
	 */
	public void setOrderId(Integer orderid) {
		this.orderId = orderid;
	}
}

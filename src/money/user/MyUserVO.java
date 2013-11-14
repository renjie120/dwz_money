package money.user;

import java.io.Serializable;

/**
 * 理财管理类.
 * 
 * @author lsq
 * 
 */
public class MyUserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String userType;
	private String pass;
	private String loginId;
	private int orgId;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private int orderId;

	public MyUserVO() {

	}

	public MyUserVO(int userId, String userName, String pass, String loginId,
			int orgId, String email, String phone, String mobile, String address,int orderId,String userType) {
		this.userId = userId;
		this.userName = userName;
		this.pass = pass;
		this.loginId = loginId;
		this.userType = userType;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.orderId = orderId;
	}

	public MyUserVO(String userName, String pass, String loginId, int orgId,
			String email, String phone, String mobile, String address,int orderId,String userType) {
		this.userName = userName;
		this.pass = pass;
		this.loginId = loginId;
		this.userType = userType;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.orderId = orderId;
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}

package money.user;

import java.util.Collection;

import dwz.framework.core.business.BusinessObject;
import dwz.framework.user.Role;

/**
 * 理财管理类.
 * @author lsq
 * 
 */
public class MyUserImpl implements IUser {
	private MyUserVO userVO = null;
	private static final long serialVersionUID = 1L;

	public MyUserImpl(MyUserVO userVO) {
		this.userVO = userVO;
	}

	public MyUserImpl(int userId, String userName, String pass, String longinId,
			int orgId, String email, String phone, String mobile, String address,int orderId,String userType) {
		this.userVO = new MyUserVO(userId, userName, pass, longinId, orgId,
				email, phone, mobile, address,orderId,userType);
	}

	public MyUserImpl(String userName, String pass, String longinId, int orgId,
			String email, String phone, String mobile, String address,int orderId,String userType) {
		this.userVO = new MyUserVO(userName, pass, longinId, orgId, email, phone,
				mobile, address,orderId,userType);
	}

	public MyUserVO getUserVO() {
		return this.userVO;
	}

	public void copyProperties(BusinessObject orig) {
		// TODO Auto-generated method stub

	}

	public Integer getId() {
		return null;
	}

	public String getAddress() {
		return this.userVO.getAddress();
	}

	public String getEmail() {
		return this.userVO.getEmail();
	}

	public String getLoginId() {
		return this.userVO.getLoginId();
	}

	public String getMobile() {
		return this.userVO.getMobile();
	}

	public int getOrderId() {
		return this.userVO.getOrderId();
	}

	public int getOrgId() {
		return this.userVO.getOrderId();
	}

	public String getPass() {
		return this.userVO.getPass();
	}

	public String getPhone() {
		return this.userVO.getPhone();
	}

	public int getUserId() {
		return this.userVO.getUserId();
	}

	public String getUserName() {
		return this.userVO.getUserName();
	}

	public Collection<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPass(String pass) {
		this.userVO.setPass(pass);
	}

}

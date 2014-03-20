
package money.myuser;

import java.io.Serializable;
/**
 * 关于用户信息表的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class MyUserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MyUserVO() {

	}
	
	private String orgName;
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	private String userTypeName;
	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public MyUserVO( int useId , String userName , String password , String loginId , int orgId , String email , String phone , String mobile , String userType , String address , int orderId ) {
		 this.useId = useId;
		 this.userName = userName;
		 this.password = password;
		 this.loginId = loginId;
		 this.orgId = orgId;
		 this.email = email;
		 this.phone = phone;
		 this.mobile = mobile;
		 this.userType = userType;
		 this.address = address;
		 this.orderId = orderId;
	}
	
	public MyUserVO( int useId , String userName , String password , String loginId , int orgId , String email , String phone , String mobile , String userType , String address , int orderId ,String orgName) {
		 this.useId = useId;
		 this.userName = userName;
		 this.password = password;
		 this.loginId = loginId;
		 this.orgId = orgId;
		 this.email = email;
		 this.phone = phone;
		 this.mobile = mobile;
		 this.userType = userType;
		 this.address = address;
		 this.orderId = orderId;
		 this.orgName = orgName;
	}
	
	public MyUserVO(String userName ,String password ,String loginId ,int orgId ,String email ,String phone ,String mobile ,String userType ,String address ,int orderId ) {
			 this.userName = userName;
			 this.password = password;
			 this.loginId = loginId;
			 this.orgId = orgId;
			 this.email = email;
			 this.phone = phone;
			 this.mobile = mobile;
			 this.userType = userType;
			 this.address = address;
			 this.orderId = orderId;
	}
	 
	private Integer useId; 
 	/**
 	 * 获取用户流水号的属性值.
 	 */
 	public Integer getUseId(){
 		return useId;
 	}
 	
 	/**
 	 * 设置用户流水号的属性值.
 	 */
 	public void setUseId(Integer useid){
 		this.useId = useid;
 	}
	private String userName; 
 	/**
 	 * 获取用户名的属性值.
 	 */
 	public String getUserName(){
 		return userName;
 	}
 	
 	/**
 	 * 设置用户名的属性值.
 	 */
 	public void setUserName(String username){
 		this.userName = username;
 	}
	private String password; 
 	/**
 	 * 获取密码的属性值.
 	 */
 	public String getPassword(){
 		return password;
 	}
 	
 	/**
 	 * 设置密码的属性值.
 	 */
 	public void setPassword(String password){
 		this.password = password;
 	}
	private String loginId; 
 	/**
 	 * 获取登陆号的属性值.
 	 */
 	public String getLoginId(){
 		return loginId;
 	}
 	
 	/**
 	 * 设置登陆号的属性值.
 	 */
 	public void setLoginId(String loginid){
 		this.loginId = loginid;
 	}
	private Integer orgId; 
 	/**
 	 * 获取组织机构的属性值.
 	 */
 	public Integer getOrgId(){
 		return orgId;
 	}
 	
 	/**
 	 * 设置组织机构的属性值.
 	 */
 	public void setOrgId(Integer orgid){
 		this.orgId = orgid;
 	}
	private String email; 
 	/**
 	 * 获取邮件的属性值.
 	 */
 	public String getEmail(){
 		return email;
 	}
 	
 	/**
 	 * 设置邮件的属性值.
 	 */
 	public void setEmail(String email){
 		this.email = email;
 	}
	private String phone; 
 	/**
 	 * 获取座机的属性值.
 	 */
 	public String getPhone(){
 		return phone;
 	}
 	
 	/**
 	 * 设置座机的属性值.
 	 */
 	public void setPhone(String phone){
 		this.phone = phone;
 	}
	private String mobile; 
 	/**
 	 * 获取手机的属性值.
 	 */
 	public String getMobile(){
 		return mobile;
 	}
 	
 	/**
 	 * 设置手机的属性值.
 	 */
 	public void setMobile(String mobile){
 		this.mobile = mobile;
 	}
	private String userType; 
 	/**
 	 * 获取用户类型的属性值.
 	 */
 	public String getUserType(){
 		return userType;
 	}
 	
 	/**
 	 * 设置用户类型的属性值.
 	 */
 	public void setUserType(String usertype){
 		this.userType = usertype;
 	}
	private String address; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getAddress(){
 		return address;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setAddress(String address){
 		this.address = address;
 	}
	private Integer orderId; 
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public Integer getOrderId(){
 		return orderId;
 	}
 	
 	/**
 	 * 设置排序号的属性值.
 	 */
 	public void setOrderId(Integer orderid){
 		this.orderId = orderid;
 	}
}

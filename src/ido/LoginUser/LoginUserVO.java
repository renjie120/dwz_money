
package ido.LoginUser;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于系统用户的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LoginUserVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	
	public LoginUserVO() {

	}
	
	private String createUserName;
	private String updateUserName;
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}


	
	public LoginUserVO( int sno , String userName , String userId , String userType , String userUnit , String userPass , String userStatus , String userPhone , String userEmail , String userAddress , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.userName = userName;
		 this.userId = userId;
		 this.userType = userType;
		 this.userUnit = userUnit;
		 this.userPass = userPass;
		 this.userStatus = userStatus;
		 this.userPhone = userPhone;
		 this.userEmail = userEmail;
		 this.userAddress = userAddress;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public LoginUserVO(String userName ,String userId ,String userType ,String userUnit ,String userPass ,String userStatus ,String userPhone ,String userEmail ,String userAddress ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.userName = userName;
			 this.userId = userId;
			 this.userType = userType;
			 this.userUnit = userUnit;
			 this.userPass = userPass;
			 this.userStatus = userStatus;
			 this.userPhone = userPhone;
			 this.userEmail = userEmail;
			 this.userAddress = userAddress;
			 this.createUser = createUser;
			 this.createTime = createTime;
			 this.updateUser = updateUser;
			 this.updateTime = updateTime;
	}
	 
	private Integer sno; 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public Integer getSno(){
 		return sno;
 	}
 	
 	/**
 	 * 设置流水号的属性值.
 	 */
 	public void setSno(Integer sno){
 		this.sno = sno;
 	}
	private String userName; 
 	/**
 	 * 获取用户姓名的属性值.
 	 */
 	public String getUserName(){
 		return userName;
 	}
 	
 	/**
 	 * 设置用户姓名的属性值.
 	 */
 	public void setUserName(String username){
 		this.userName = username;
 	}
	private String userId; 
 	/**
 	 * 获取登录名称 的属性值.
 	 */
 	public String getUserId(){
 		return userId;
 	}
 	
 	/**
 	 * 设置登录名称 的属性值.
 	 */
 	public void setUserId(String userid){
 		this.userId = userid;
 	}
	private String userType; 
 	/**
 	 * 获取所属类别 的属性值.
 	 */
 	public String getUserType(){
 		return userType;
 	}
 	
 	/**
 	 * 设置所属类别 的属性值.
 	 */
 	public void setUserType(String usertype){
 		this.userType = usertype;
 	}
	private String userUnit; 
 	/**
 	 * 获取所属单位 的属性值.
 	 */
 	public String getUserUnit(){
 		return userUnit;
 	}
 	
 	/**
 	 * 设置所属单位 的属性值.
 	 */
 	public void setUserUnit(String userunit){
 		this.userUnit = userunit;
 	}
	private String userPass; 
 	/**
 	 * 获取用户密码的属性值.
 	 */
 	public String getUserPass(){
 		return userPass;
 	}
 	
 	/**
 	 * 设置用户密码的属性值.
 	 */
 	public void setUserPass(String userpass){
 		this.userPass = userpass;
 	}
	private String userStatus; 
 	/**
 	 * 获取用户状态 的属性值.
 	 */
 	public String getUserStatus(){
 		return userStatus;
 	}
 	
 	/**
 	 * 设置用户状态 的属性值.
 	 */
 	public void setUserStatus(String userstatus){
 		this.userStatus = userstatus;
 	}
	private String userPhone; 
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public String getUserPhone(){
 		return userPhone;
 	}
 	
 	/**
 	 * 设置联系电话的属性值.
 	 */
 	public void setUserPhone(String userphone){
 		this.userPhone = userphone;
 	}
	private String userEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getUserEmail(){
 		return userEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setUserEmail(String useremail){
 		this.userEmail = useremail;
 	}
	private String userAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getUserAddress(){
 		return userAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setUserAddress(String useraddress){
 		this.userAddress = useraddress;
 	}
	private int createUser; 
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public int getCreateUser(){
 		return createUser;
 	}
 	
 	/**
 	 * 设置创建用户的属性值.
 	 */
 	public void setCreateUser(int createuser){
 		this.createUser = createuser;
 	}
	private String createTime; 
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public String getCreateTime(){
 		return createTime;
 	}
 	
 	/**
 	 * 设置创建时间的属性值.
 	 */
 	public void setCreateTime(String createtime){
 		this.createTime = createtime;
 	}
	private int updateUser; 
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public int getUpdateUser(){
 		return updateUser;
 	}
 	
 	/**
 	 * 设置更新用户的属性值.
 	 */
 	public void setUpdateUser(int updateuser){
 		this.updateUser = updateuser;
 	}
	private String updateTime; 
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public String getUpdateTime(){
 		return updateTime;
 	}
 	
 	/**
 	 * 设置更新时间的属性值.
 	 */
 	public void setUpdateTime(String updatetime){
 		this.updateTime = updatetime;
 	}

	@Override
	public String getOptionId() {
		return this.getSno()+"";
	}

	@Override
	public String getOptionName() {
		return this.getUserName()+"";
	}
}


package ido.BusinessGroup;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于商家集团的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessGroupVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	
	public BusinessGroupVO() {

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

	public BusinessGroupVO( int sno , String groupSno , String groupName , String groupEmail , String groupContact , String groupContactPhone , String groupContactMobile , String groupStatus , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.groupSno = groupSno;
		 this.groupName = groupName;
		 this.groupEmail = groupEmail;
		 this.groupContact = groupContact;
		 this.groupContactPhone = groupContactPhone;
		 this.groupContactMobile = groupContactMobile;
		 this.groupStatus = groupStatus;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public BusinessGroupVO(String groupSno ,String groupName ,String groupEmail ,String groupContact ,String groupContactPhone ,String groupContactMobile ,String groupStatus ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.groupSno = groupSno;
			 this.groupName = groupName;
			 this.groupEmail = groupEmail;
			 this.groupContact = groupContact;
			 this.groupContactPhone = groupContactPhone;
			 this.groupContactMobile = groupContactMobile;
			 this.groupStatus = groupStatus;
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
	private String groupSno; 
 	/**
 	 * 获取集团编号的属性值.
 	 */
 	public String getGroupSno(){
 		return groupSno;
 	}
 	
 	/**
 	 * 设置集团编号的属性值.
 	 */
 	public void setGroupSno(String groupsno){
 		this.groupSno = groupsno;
 	}
	private String groupName; 
 	/**
 	 * 获取集团名称 的属性值.
 	 */
 	public String getGroupName(){
 		return groupName;
 	}
 	
 	/**
 	 * 设置集团名称 的属性值.
 	 */
 	public void setGroupName(String groupname){
 		this.groupName = groupname;
 	}
	private String groupEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getGroupEmail(){
 		return groupEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setGroupEmail(String groupemail){
 		this.groupEmail = groupemail;
 	}
	private String groupContact; 
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public String getGroupContact(){
 		return groupContact;
 	}
 	
 	/**
 	 * 设置联系人名称的属性值.
 	 */
 	public void setGroupContact(String groupcontact){
 		this.groupContact = groupcontact;
 	}
	private String groupContactPhone; 
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public String getGroupContactPhone(){
 		return groupContactPhone;
 	}
 	
 	/**
 	 * 设置联系电话的属性值.
 	 */
 	public void setGroupContactPhone(String groupcontactphone){
 		this.groupContactPhone = groupcontactphone;
 	}
	private String groupContactMobile; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getGroupContactMobile(){
 		return groupContactMobile;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setGroupContactMobile(String groupcontactmobile){
 		this.groupContactMobile = groupcontactmobile;
 	}
	private String groupStatus; 
 	/**
 	 * 获取状态的属性值.
 	 */
 	public String getGroupStatus(){
 		return groupStatus;
 	}
 	
 	/**
 	 * 设置状态的属性值.
 	 */
 	public void setGroupStatus(String groupstatus){
 		this.groupStatus = groupstatus;
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
		return this.getGroupName()+"";
	}
}

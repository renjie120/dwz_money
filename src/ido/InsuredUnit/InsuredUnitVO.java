
package ido.InsuredUnit;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于投保单位的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredUnitVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public InsuredUnitVO() {

	}
	
	public InsuredUnitVO( int sno , String unitCode , String unitName , String contactName , String contactMobile , String contactEmail , int unitParentId , String unitState , String unitAddress , String unitRemark , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.unitCode = unitCode;
		 this.unitName = unitName;
		 this.contactName = contactName;
		 this.contactMobile = contactMobile;
		 this.contactEmail = contactEmail;
		 this.unitParentId = unitParentId;
		 this.unitState = unitState;
		 this.unitAddress = unitAddress;
		 this.unitRemark = unitRemark;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public InsuredUnitVO(String unitCode ,String unitName ,String contactName ,String contactMobile ,String contactEmail ,int unitParentId ,String unitState ,String unitAddress ,String unitRemark ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.unitCode = unitCode;
			 this.unitName = unitName;
			 this.contactName = contactName;
			 this.contactMobile = contactMobile;
			 this.contactEmail = contactEmail;
			 this.unitParentId = unitParentId;
			 this.unitState = unitState;
			 this.unitAddress = unitAddress;
			 this.unitRemark = unitRemark;
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
	private String unitCode; 
 	/**
 	 * 获取编号的属性值.
 	 */
 	public String getUnitCode(){
 		return unitCode;
 	}
 	
 	/**
 	 * 设置编号的属性值.
 	 */
 	public void setUnitCode(String unitcode){
 		this.unitCode = unitcode;
 	}
	private String unitName; 
 	/**
 	 * 获取投保单位 的属性值.
 	 */
 	public String getUnitName(){
 		return unitName;
 	}
 	
 	/**
 	 * 设置投保单位 的属性值.
 	 */
 	public void setUnitName(String unitname){
 		this.unitName = unitname;
 	}
	private String contactName; 
 	/**
 	 * 获取联系人的属性值.
 	 */
 	public String getContactName(){
 		return contactName;
 	}
 	
 	/**
 	 * 设置联系人的属性值.
 	 */
 	public void setContactName(String contactname){
 		this.contactName = contactname;
 	}
	private String contactMobile; 
 	/**
 	 * 获取手机的属性值.
 	 */
 	public String getContactMobile(){
 		return contactMobile;
 	}
 	
 	/**
 	 * 设置手机的属性值.
 	 */
 	public void setContactMobile(String contactmobile){
 		this.contactMobile = contactmobile;
 	}
	private String contactEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getContactEmail(){
 		return contactEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setContactEmail(String contactemail){
 		this.contactEmail = contactemail;
 	}
	private int unitParentId; 
 	/**
 	 * 获取上级单位的属性值.
 	 */
 	public int getUnitParentId(){
 		return unitParentId;
 	}
 	
 	/**
 	 * 设置上级单位的属性值.
 	 */
 	public void setUnitParentId(int unitparentid){
 		this.unitParentId = unitparentid;
 	}
	private String unitState; 
 	/**
 	 * 获取是否显示的属性值.
 	 */
 	public String getUnitState(){
 		return unitState;
 	}
 	
 	/**
 	 * 设置是否显示的属性值.
 	 */
 	public void setUnitState(String unitstate){
 		this.unitState = unitstate;
 	}
	private String unitAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getUnitAddress(){
 		return unitAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setUnitAddress(String unitaddress){
 		this.unitAddress = unitaddress;
 	}
	private String unitRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getUnitRemark(){
 		return unitRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setUnitRemark(String unitremark){
 		this.unitRemark = unitremark;
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
}

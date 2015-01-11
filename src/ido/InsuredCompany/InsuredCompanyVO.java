
package ido.InsuredCompany;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于保险公司的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredCompanyVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public InsuredCompanyVO() {

	}
	
	public InsuredCompanyVO( int sno , String comName , String comNo , String comShortName , String comPhone , String comContactName , String comContactPhone , String ownerCompany , String comEmail , String comAddress , String comRemark , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.comName = comName;
		 this.comNo = comNo;
		 this.comShortName = comShortName;
		 this.comPhone = comPhone;
		 this.comContactName = comContactName;
		 this.comContactPhone = comContactPhone;
		 this.ownerCompany = ownerCompany;
		 this.comEmail = comEmail;
		 this.comAddress = comAddress;
		 this.comRemark = comRemark;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public InsuredCompanyVO(String comName ,String comNo ,String comShortName ,String comPhone ,String comContactName ,String comContactPhone ,String ownerCompany ,String comEmail ,String comAddress ,String comRemark ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.comName = comName;
			 this.comNo = comNo;
			 this.comShortName = comShortName;
			 this.comPhone = comPhone;
			 this.comContactName = comContactName;
			 this.comContactPhone = comContactPhone;
			 this.ownerCompany = ownerCompany;
			 this.comEmail = comEmail;
			 this.comAddress = comAddress;
			 this.comRemark = comRemark;
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
	private String comName; 
 	/**
 	 * 获取保险公司名称的属性值.
 	 */
 	public String getComName(){
 		return comName;
 	}
 	
 	/**
 	 * 设置保险公司名称的属性值.
 	 */
 	public void setComName(String comname){
 		this.comName = comname;
 	}
	private String comNo; 
 	/**
 	 * 获取保险公司编号 的属性值.
 	 */
 	public String getComNo(){
 		return comNo;
 	}
 	
 	/**
 	 * 设置保险公司编号 的属性值.
 	 */
 	public void setComNo(String comno){
 		this.comNo = comno;
 	}
	private String comShortName; 
 	/**
 	 * 获取简称的属性值.
 	 */
 	public String getComShortName(){
 		return comShortName;
 	}
 	
 	/**
 	 * 设置简称的属性值.
 	 */
 	public void setComShortName(String comshortname){
 		this.comShortName = comshortname;
 	}
	private String comPhone; 
 	/**
 	 * 获取电话的属性值.
 	 */
 	public String getComPhone(){
 		return comPhone;
 	}
 	
 	/**
 	 * 设置电话的属性值.
 	 */
 	public void setComPhone(String comphone){
 		this.comPhone = comphone;
 	}
	private String comContactName; 
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public String getComContactName(){
 		return comContactName;
 	}
 	
 	/**
 	 * 设置联系人名称的属性值.
 	 */
 	public void setComContactName(String comcontactname){
 		this.comContactName = comcontactname;
 	}
	private String comContactPhone; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getComContactPhone(){
 		return comContactPhone;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setComContactPhone(String comcontactphone){
 		this.comContactPhone = comcontactphone;
 	}
	private String ownerCompany; 
 	/**
 	 * 获取所属保险公司的属性值.
 	 */
 	public String getOwnerCompany(){
 		return ownerCompany;
 	}
 	
 	/**
 	 * 设置所属保险公司的属性值.
 	 */
 	public void setOwnerCompany(String ownercompany){
 		this.ownerCompany = ownercompany;
 	}
	private String comEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getComEmail(){
 		return comEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setComEmail(String comemail){
 		this.comEmail = comemail;
 	}
	private String comAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getComAddress(){
 		return comAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setComAddress(String comaddress){
 		this.comAddress = comaddress;
 	}
	private String comRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getComRemark(){
 		return comRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setComRemark(String comremark){
 		this.comRemark = comremark;
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

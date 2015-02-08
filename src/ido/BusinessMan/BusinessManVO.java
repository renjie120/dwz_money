
package ido.BusinessMan;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于商家的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessManVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	
	public BusinessManVO() {

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

	
	
	public BusinessManVO( int sno , String shopmSno , String shopmName , String shopmShortName , String shopmType , String shopmEmail , String shopmConPhone , String shopmContactName , String shopmAddress , String openBank , String openBankName , String openBankNo , String openTicketUnit , String OpenBankProvince , String OpenBankCity , String compensationDay , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.shopmSno = shopmSno;
		 this.shopmName = shopmName;
		 this.shopmShortName = shopmShortName;
		 this.shopmType = shopmType;
		 this.shopmEmail = shopmEmail;
		 this.shopmConPhone = shopmConPhone;
		 this.shopmContactName = shopmContactName;
		 this.shopmAddress = shopmAddress;
		 this.openBank = openBank;
		 this.openBankName = openBankName;
		 this.openBankNo = openBankNo;
		 this.openTicketUnit = openTicketUnit;
		 this.OpenBankProvince = OpenBankProvince;
		 this.OpenBankCity = OpenBankCity;
		 this.compensationDay = compensationDay;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public BusinessManVO(String shopmSno ,String shopmName ,String shopmShortName ,String shopmType ,String shopmEmail ,String shopmConPhone ,String shopmContactName ,String shopmAddress ,String openBank ,String openBankName ,String openBankNo ,String openTicketUnit ,String OpenBankProvince ,String OpenBankCity ,String compensationDay ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.shopmSno = shopmSno;
			 this.shopmName = shopmName;
			 this.shopmShortName = shopmShortName;
			 this.shopmType = shopmType;
			 this.shopmEmail = shopmEmail;
			 this.shopmConPhone = shopmConPhone;
			 this.shopmContactName = shopmContactName;
			 this.shopmAddress = shopmAddress;
			 this.openBank = openBank;
			 this.openBankName = openBankName;
			 this.openBankNo = openBankNo;
			 this.openTicketUnit = openTicketUnit;
			 this.OpenBankProvince = OpenBankProvince;
			 this.OpenBankCity = OpenBankCity;
			 this.compensationDay = compensationDay;
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
	private String shopmSno; 
 	/**
 	 * 获取商家编号的属性值.
 	 */
 	public String getShopmSno(){
 		return shopmSno;
 	}
 	
 	/**
 	 * 设置商家编号的属性值.
 	 */
 	public void setShopmSno(String shopmsno){
 		this.shopmSno = shopmsno;
 	}
	private String shopmName; 
 	/**
 	 * 获取商家名称 的属性值.
 	 */
 	public String getShopmName(){
 		return shopmName;
 	}
 	
 	/**
 	 * 设置商家名称 的属性值.
 	 */
 	public void setShopmName(String shopmname){
 		this.shopmName = shopmname;
 	}
	private String shopmShortName; 
 	/**
 	 * 获取商家简称 的属性值.
 	 */
 	public String getShopmShortName(){
 		return shopmShortName;
 	}
 	
 	/**
 	 * 设置商家简称 的属性值.
 	 */
 	public void setShopmShortName(String shopmshortname){
 		this.shopmShortName = shopmshortname;
 	}
	private String shopmType; 
 	/**
 	 * 获取商家类型 的属性值.
 	 */
 	public String getShopmType(){
 		return shopmType;
 	}
 	
 	/**
 	 * 设置商家类型 的属性值.
 	 */
 	public void setShopmType(String shopmtype){
 		this.shopmType = shopmtype;
 	}
	private String shopmEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getShopmEmail(){
 		return shopmEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setShopmEmail(String shopmemail){
 		this.shopmEmail = shopmemail;
 	}
	private String shopmConPhone; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getShopmConPhone(){
 		return shopmConPhone;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setShopmConPhone(String shopmconphone){
 		this.shopmConPhone = shopmconphone;
 	}
	private String shopmContactName; 
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public String getShopmContactName(){
 		return shopmContactName;
 	}
 	
 	/**
 	 * 设置联系人名称的属性值.
 	 */
 	public void setShopmContactName(String shopmcontactname){
 		this.shopmContactName = shopmcontactname;
 	}
	private String shopmAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getShopmAddress(){
 		return shopmAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setShopmAddress(String shopmaddress){
 		this.shopmAddress = shopmaddress;
 	}
	private String openBank; 
 	/**
 	 * 获取开户行的属性值.
 	 */
 	public String getOpenBank(){
 		return openBank;
 	}
 	
 	/**
 	 * 设置开户行的属性值.
 	 */
 	public void setOpenBank(String openbank){
 		this.openBank = openbank;
 	}
	private String openBankName; 
 	/**
 	 * 获取户名的属性值.
 	 */
 	public String getOpenBankName(){
 		return openBankName;
 	}
 	
 	/**
 	 * 设置户名的属性值.
 	 */
 	public void setOpenBankName(String openbankname){
 		this.openBankName = openbankname;
 	}
	private String openBankNo; 
 	/**
 	 * 获取银行帐号的属性值.
 	 */
 	public String getOpenBankNo(){
 		return openBankNo;
 	}
 	
 	/**
 	 * 设置银行帐号的属性值.
 	 */
 	public void setOpenBankNo(String openbankno){
 		this.openBankNo = openbankno;
 	}
	private String openTicketUnit; 
 	/**
 	 * 获取开票单位的属性值.
 	 */
 	public String getOpenTicketUnit(){
 		return openTicketUnit;
 	}
 	
 	/**
 	 * 设置开票单位的属性值.
 	 */
 	public void setOpenTicketUnit(String openticketunit){
 		this.openTicketUnit = openticketunit;
 	}
	private String OpenBankProvince; 
 	/**
 	 * 获取开户所在省的属性值.
 	 */
 	public String getOpenBankProvince(){
 		return OpenBankProvince;
 	}
 	
 	/**
 	 * 设置开户所在省的属性值.
 	 */
 	public void setOpenBankProvince(String openbankprovince){
 		this.OpenBankProvince = openbankprovince;
 	}
	private String OpenBankCity; 
 	/**
 	 * 获取开户所在市的属性值.
 	 */
 	public String getOpenBankCity(){
 		return OpenBankCity;
 	}
 	
 	/**
 	 * 设置开户所在市的属性值.
 	 */
 	public void setOpenBankCity(String openbankcity){
 		this.OpenBankCity = openbankcity;
 	}
	private String compensationDay; 
 	/**
 	 * 获取理赔截止日期的属性值.
 	 */
 	public String getCompensationDay(){
 		return compensationDay;
 	}
 	
 	/**
 	 * 设置理赔截止日期的属性值.
 	 */
 	public void setCompensationDay(String compensationday){
 		this.compensationDay = compensationday;
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
		return this.getShopmName()+"";
	}
}

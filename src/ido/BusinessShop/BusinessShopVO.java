
package ido.BusinessShop;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于商铺的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessShopVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BusinessShopVO() {

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
	public BusinessShopVO( int sno , String shopmId , String shopName , String shopSno , String shopStatus , String shopContactName , String shopConPhone , String shopEmail , String shopAddress , String shopDate , String shopJingdu , String shopWeidu , String shopProvince , String shopCity , String shopxian , String shopRemark , String shopMain , String shopIntroduce , String shopSpecial , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.shopmId = shopmId;
		 this.shopName = shopName;
		 this.shopSno = shopSno;
		 this.shopStatus = shopStatus;
		 this.shopContactName = shopContactName;
		 this.shopConPhone = shopConPhone;
		 this.shopEmail = shopEmail;
		 this.shopAddress = shopAddress;
		 this.shopDate = shopDate;
		 this.shopJingdu = shopJingdu;
		 this.shopWeidu = shopWeidu;
		 this.shopProvince = shopProvince;
		 this.shopCity = shopCity;
		 this.shopxian = shopxian;
		 this.shopRemark = shopRemark;
		 this.shopMain = shopMain;
		 this.shopIntroduce = shopIntroduce;
		 this.shopSpecial = shopSpecial;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public BusinessShopVO(String shopmId ,String shopName ,String shopSno ,String shopStatus ,String shopContactName ,String shopConPhone ,String shopEmail ,String shopAddress ,String shopDate ,String shopJingdu ,String shopWeidu ,String shopProvince ,String shopCity ,String shopxian ,String shopRemark ,String shopMain ,String shopIntroduce ,String shopSpecial ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.shopmId = shopmId;
			 this.shopName = shopName;
			 this.shopSno = shopSno;
			 this.shopStatus = shopStatus;
			 this.shopContactName = shopContactName;
			 this.shopConPhone = shopConPhone;
			 this.shopEmail = shopEmail;
			 this.shopAddress = shopAddress;
			 this.shopDate = shopDate;
			 this.shopJingdu = shopJingdu;
			 this.shopWeidu = shopWeidu;
			 this.shopProvince = shopProvince;
			 this.shopCity = shopCity;
			 this.shopxian = shopxian;
			 this.shopRemark = shopRemark;
			 this.shopMain = shopMain;
			 this.shopIntroduce = shopIntroduce;
			 this.shopSpecial = shopSpecial;
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
	private String shopmId; 
 	/**
 	 * 获取商家编号的属性值.
 	 */
 	public String getShopmId(){
 		return shopmId;
 	}
 	
 	/**
 	 * 设置商家编号的属性值.
 	 */
 	public void setShopmId(String shopmid){
 		this.shopmId = shopmid;
 	}
	private String shopName; 
 	/**
 	 * 获取商铺名称 的属性值.
 	 */
 	public String getShopName(){
 		return shopName;
 	}
 	
 	/**
 	 * 设置商铺名称 的属性值.
 	 */
 	public void setShopName(String shopname){
 		this.shopName = shopname;
 	}
	private String shopSno; 
 	/**
 	 * 获取商铺编号 的属性值.
 	 */
 	public String getShopSno(){
 		return shopSno;
 	}
 	
 	/**
 	 * 设置商铺编号 的属性值.
 	 */
 	public void setShopSno(String shopsno){
 		this.shopSno = shopsno;
 	}
	private String shopStatus; 
 	/**
 	 * 获取商铺状态 的属性值.
 	 */
 	public String getShopStatus(){
 		return shopStatus;
 	}
 	
 	/**
 	 * 设置商铺状态 的属性值.
 	 */
 	public void setShopStatus(String shopstatus){
 		this.shopStatus = shopstatus;
 	}
	private String shopContactName; 
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public String getShopContactName(){
 		return shopContactName;
 	}
 	
 	/**
 	 * 设置联系人名称的属性值.
 	 */
 	public void setShopContactName(String shopcontactname){
 		this.shopContactName = shopcontactname;
 	}
	private String shopConPhone; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getShopConPhone(){
 		return shopConPhone;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setShopConPhone(String shopconphone){
 		this.shopConPhone = shopconphone;
 	}
	private String shopEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getShopEmail(){
 		return shopEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setShopEmail(String shopemail){
 		this.shopEmail = shopemail;
 	}
	private String shopAddress; 
 	/**
 	 * 获取地址的属性值.
 	 */
 	public String getShopAddress(){
 		return shopAddress;
 	}
 	
 	/**
 	 * 设置地址的属性值.
 	 */
 	public void setShopAddress(String shopaddress){
 		this.shopAddress = shopaddress;
 	}
	private String shopDate; 
 	/**
 	 * 获取签约日期的属性值.
 	 */
 	public String getShopDate(){
 		return shopDate;
 	}
 	
 	/**
 	 * 设置签约日期的属性值.
 	 */
 	public void setShopDate(String shopdate){
 		this.shopDate = shopdate;
 	}
	private String shopJingdu; 
 	/**
 	 * 获取经度的属性值.
 	 */
 	public String getShopJingdu(){
 		return shopJingdu;
 	}
 	
 	/**
 	 * 设置经度的属性值.
 	 */
 	public void setShopJingdu(String shopjingdu){
 		this.shopJingdu = shopjingdu;
 	}
	private String shopWeidu; 
 	/**
 	 * 获取纬度的属性值.
 	 */
 	public String getShopWeidu(){
 		return shopWeidu;
 	}
 	
 	/**
 	 * 设置纬度的属性值.
 	 */
 	public void setShopWeidu(String shopweidu){
 		this.shopWeidu = shopweidu;
 	}
	private String shopProvince; 
 	/**
 	 * 获取省份的属性值.
 	 */
 	public String getShopProvince(){
 		return shopProvince;
 	}
 	
 	/**
 	 * 设置省份的属性值.
 	 */
 	public void setShopProvince(String shopprovince){
 		this.shopProvince = shopprovince;
 	}
	private String shopCity; 
 	/**
 	 * 获取市的属性值.
 	 */
 	public String getShopCity(){
 		return shopCity;
 	}
 	
 	/**
 	 * 设置市的属性值.
 	 */
 	public void setShopCity(String shopcity){
 		this.shopCity = shopcity;
 	}
	private String shopxian; 
 	/**
 	 * 获取区县的属性值.
 	 */
 	public String getShopxian(){
 		return shopxian;
 	}
 	
 	/**
 	 * 设置区县的属性值.
 	 */
 	public void setShopxian(String shopxian){
 		this.shopxian = shopxian;
 	}
	private String shopRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getShopRemark(){
 		return shopRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setShopRemark(String shopremark){
 		this.shopRemark = shopremark;
 	}
	private String shopMain; 
 	/**
 	 * 获取主营的属性值.
 	 */
 	public String getShopMain(){
 		return shopMain;
 	}
 	
 	/**
 	 * 设置主营的属性值.
 	 */
 	public void setShopMain(String shopmain){
 		this.shopMain = shopmain;
 	}
	private String shopIntroduce; 
 	/**
 	 * 获取简介的属性值.
 	 */
 	public String getShopIntroduce(){
 		return shopIntroduce;
 	}
 	
 	/**
 	 * 设置简介的属性值.
 	 */
 	public void setShopIntroduce(String shopintroduce){
 		this.shopIntroduce = shopintroduce;
 	}
	private String shopSpecial; 
 	/**
 	 * 获取特色的属性值.
 	 */
 	public String getShopSpecial(){
 		return shopSpecial;
 	}
 	
 	/**
 	 * 设置特色的属性值.
 	 */
 	public void setShopSpecial(String shopspecial){
 		this.shopSpecial = shopspecial;
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

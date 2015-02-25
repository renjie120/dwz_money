
package ido.ConsumeInfo;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于消费记录的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ConsumeInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ConsumeInfoVO() {

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
	
	public ConsumeInfoVO( int sno , String iuserId , String comId , String shopmId , String shopId , String ownerCom , String consumeStatus , double consumeMoney , double cardMoney , double cashMoney , String consumeTime , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.iuserId = iuserId;
		 this.comId = comId;
		 this.shopmId = shopmId;
		 this.shopId = shopId;
		 this.ownerCom = ownerCom;
		 this.consumeStatus = consumeStatus;
		 this.consumeMoney = consumeMoney;
		 this.cardMoney = cardMoney;
		 this.cashMoney = cashMoney;
		 this.consumeTime = consumeTime;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public ConsumeInfoVO(String iuserId ,String comId ,String shopmId ,String shopId ,String ownerCom ,String consumeStatus ,double consumeMoney ,double cardMoney ,double cashMoney ,String consumeTime ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.iuserId = iuserId;
			 this.comId = comId;
			 this.shopmId = shopmId;
			 this.shopId = shopId;
			 this.ownerCom = ownerCom;
			 this.consumeStatus = consumeStatus;
			 this.consumeMoney = consumeMoney;
			 this.cardMoney = cardMoney;
			 this.cashMoney = cashMoney;
			 this.consumeTime = consumeTime;
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
	private String iuserId; 
 	/**
 	 * 获取投保用户号的属性值.
 	 */
 	public String getIuserId(){
 		return iuserId;
 	}
 	
 	/**
 	 * 设置投保用户号的属性值.
 	 */
 	public void setIuserId(String iuserid){
 		this.iuserId = iuserid;
 	}
	private String comId; 
 	/**
 	 * 获取投保公司的属性值.
 	 */
 	public String getComId(){
 		return comId;
 	}
 	
 	/**
 	 * 设置投保公司的属性值.
 	 */
 	public void setComId(String comid){
 		this.comId = comid;
 	}
	private String shopmId; 
 	/**
 	 * 获取所属商家 的属性值.
 	 */
 	public String getShopmId(){
 		return shopmId;
 	}
 	
 	/**
 	 * 设置所属商家 的属性值.
 	 */
 	public void setShopmId(String shopmid){
 		this.shopmId = shopmid;
 	}
	private String shopId; 
 	/**
 	 * 获取所属商铺 的属性值.
 	 */
 	public String getShopId(){
 		return shopId;
 	}
 	
 	/**
 	 * 设置所属商铺 的属性值.
 	 */
 	public void setShopId(String shopid){
 		this.shopId = shopid;
 	}
	private String ownerCom; 
 	/**
 	 * 获取所属分公司 的属性值.
 	 */
 	public String getOwnerCom(){
 		return ownerCom;
 	}
 	
 	/**
 	 * 设置所属分公司 的属性值.
 	 */
 	public void setOwnerCom(String ownercom){
 		this.ownerCom = ownercom;
 	}
	private String consumeStatus; 
 	/**
 	 * 获取支付状态的属性值.
 	 */
 	public String getConsumeStatus(){
 		return consumeStatus;
 	}
 	
 	/**
 	 * 设置支付状态的属性值.
 	 */
 	public void setConsumeStatus(String consumestatus){
 		this.consumeStatus = consumestatus;
 	}
	private double consumeMoney; 
 	/**
 	 * 获取消费金额的属性值.
 	 */
 	public double getConsumeMoney(){
 		return consumeMoney;
 	}
 	
 	/**
 	 * 设置消费金额的属性值.
 	 */
 	public void setConsumeMoney(double consumemoney){
 		this.consumeMoney = consumemoney;
 	}
	private double cardMoney; 
 	/**
 	 * 获取刷卡消费的属性值.
 	 */
 	public double getCardMoney(){
 		return cardMoney;
 	}
 	
 	/**
 	 * 设置刷卡消费的属性值.
 	 */
 	public void setCardMoney(double cardmoney){
 		this.cardMoney = cardmoney;
 	}
	private double cashMoney; 
 	/**
 	 * 获取现金支付的属性值.
 	 */
 	public double getCashMoney(){
 		return cashMoney;
 	}
 	
 	/**
 	 * 设置现金支付的属性值.
 	 */
 	public void setCashMoney(double cashmoney){
 		this.cashMoney = cashmoney;
 	}
	private String consumeTime; 
 	/**
 	 * 获取消费时间的属性值.
 	 */
 	public String getConsumeTime(){
 		return consumeTime;
 	}
 	
 	/**
 	 * 设置消费时间的属性值.
 	 */
 	public void setConsumeTime(String consumetime){
 		this.consumeTime = consumetime;
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

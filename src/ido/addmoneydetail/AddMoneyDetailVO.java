
package ido.addmoneydetail;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于充值记录明细的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddMoneyDetailVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String createUserName;
	 
	 
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public AddMoneyDetailVO() {

	}
	
	public AddMoneyDetailVO( int sno , String iuserId , String addType , String addMoney , String insuredFileId , String addTime , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.iuserId = iuserId;
		 this.addType = addType;
		 this.addMoney = addMoney;
		 this.insuredFileId = insuredFileId;
		 this.addTime = addTime;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public AddMoneyDetailVO(String iuserId ,String addType ,String addMoney ,String insuredFileId ,String addTime ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.iuserId = iuserId;
			 this.addType = addType;
			 this.addMoney = addMoney;
			 this.insuredFileId = insuredFileId;
			 this.addTime = addTime;
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
	private String addType; 
 	/**
 	 * 获取充值字段的属性值.
 	 */
 	public String getAddType(){
 		return addType;
 	}
 	
 	/**
 	 * 设置充值字段的属性值.
 	 */
 	public void setAddType(String addtype){
 		this.addType = addtype;
 	}
	private String addMoney; 
 	/**
 	 * 获取充值金额 的属性值.
 	 */
 	public String getAddMoney(){
 		return addMoney;
 	}
 	
 	/**
 	 * 设置充值金额 的属性值.
 	 */
 	public void setAddMoney(String addmoney){
 		this.addMoney = addmoney;
 	}
	private String insuredFileId; 
 	/**
 	 * 获取投保单号 的属性值.
 	 */
 	public String getInsuredFileId(){
 		return insuredFileId;
 	}
 	
 	/**
 	 * 设置投保单号 的属性值.
 	 */
 	public void setInsuredFileId(String insuredfileid){
 		this.insuredFileId = insuredfileid;
 	}
	private String addTime; 
 	/**
 	 * 获取充值时间的属性值.
 	 */
 	public String getAddTime(){
 		return addTime;
 	}
 	
 	/**
 	 * 设置充值时间的属性值.
 	 */
 	public void setAddTime(String addtime){
 		this.addTime = addtime;
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

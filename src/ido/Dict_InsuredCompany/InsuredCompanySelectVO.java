
package ido.Dict_InsuredCompany;

import java.io.Serializable;

import common.base.SelectAble;
/**
 * 关于保险公司字典表的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredCompanySelectVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	
	public InsuredCompanySelectVO() {

	}
	
	public InsuredCompanySelectVO( int sno , String comName , String comStatus , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.comName = comName;
		 this.comStatus = comStatus;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public InsuredCompanySelectVO(String comName ,String comStatus ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.comName = comName;
			 this.comStatus = comStatus;
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
	private String comStatus; 
 	/**
 	 * 获取是否显示 的属性值.
 	 */
 	public String getComStatus(){
 		return comStatus;
 	}
 	
 	/**
 	 * 设置是否显示 的属性值.
 	 */
 	public void setComStatus(String comstatus){
 		this.comStatus = comstatus;
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
		return this.getComName()+"";
	}
}

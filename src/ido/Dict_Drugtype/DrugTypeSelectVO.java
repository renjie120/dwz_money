
package ido.Dict_Drugtype;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于药品大类字典表的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class DrugTypeSelectVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	
	public DrugTypeSelectVO() {

	}
	
	public DrugTypeSelectVO( int sno , String drugType , String typeStatus , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.drugType = drugType;
		 this.typeStatus = typeStatus;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public DrugTypeSelectVO(String drugType ,String typeStatus ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.drugType = drugType;
			 this.typeStatus = typeStatus;
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
	private String drugType; 
 	/**
 	 * 获取药品类别名称的属性值.
 	 */
 	public String getDrugType(){
 		return drugType;
 	}
 	
 	/**
 	 * 设置药品类别名称的属性值.
 	 */
 	public void setDrugType(String drugtype){
 		this.drugType = drugtype;
 	}
	private String typeStatus; 
 	/**
 	 * 获取是否有效的属性值.
 	 */
 	public String getTypeStatus(){
 		return typeStatus;
 	}
 	
 	/**
 	 * 设置是否有效的属性值.
 	 */
 	public void setTypeStatus(String typestatus){
 		this.typeStatus = typestatus;
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
		return this.getDrugType()+"";
	}
}

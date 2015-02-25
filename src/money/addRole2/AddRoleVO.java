
package money.addRole2;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于新角色授权的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddRoleVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AddRoleVO() {

	}
	
	public AddRoleVO( int sno , String roleType , String roleKey , String roleIds , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.roleType = roleType;
		 this.roleKey = roleKey;
		 this.roleIds = roleIds;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public AddRoleVO(String roleType ,String roleKey ,String roleIds ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.roleType = roleType;
			 this.roleKey = roleKey;
			 this.roleIds = roleIds;
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
	private String roleType; 
 	/**
 	 * 获取角色类型的属性值.
 	 */
 	public String getRoleType(){
 		return roleType;
 	}
 	
 	/**
 	 * 设置角色类型的属性值.
 	 */
 	public void setRoleType(String roletype){
 		this.roleType = roletype;
 	}
	private String roleKey; 
 	/**
 	 * 获取授权对象 的属性值.
 	 */
 	public String getRoleKey(){
 		return roleKey;
 	}
 	
 	/**
 	 * 设置授权对象 的属性值.
 	 */
 	public void setRoleKey(String rolekey){
 		this.roleKey = rolekey;
 	}
	private String roleIds; 
 	/**
 	 * 获取角色 的属性值.
 	 */
 	public String getRoleIds(){
 		return roleIds;
 	}
 	
 	/**
 	 * 设置角色 的属性值.
 	 */
 	public void setRoleIds(String roleids){
 		this.roleIds = roleids;
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


package money.rolemanager;

import java.io.Serializable;
/**
 * 关于用户角色权限的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UserRoleRightVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public UserRoleRightVO() {

	}
	
	public UserRoleRightVO( int id , int userId , int roleId ) {
		 this.id = id;
		 this.userId = userId;
		 this.roleId = roleId;
	}
	
	public UserRoleRightVO(int userId ,int roleId ) {
			 this.userId = userId;
			 this.roleId = roleId;
	}
	 
	private Integer id; 
 	/**
 	 * 获取首页流水号id的属性值.
 	 */
 	public Integer getId(){
 		return id;
 	}
 	
 	/**
 	 * 设置首页流水号id的属性值.
 	 */
 	public void setId(Integer id){
 		this.id = id;
 	}
	private int userId; 
 	/**
 	 * 获取用户id 的属性值.
 	 */
 	public int getUserId(){
 		return userId;
 	}
 	
 	/**
 	 * 设置用户id 的属性值.
 	 */
 	public void setUserId(int userid){
 		this.userId = userid;
 	}
	private int roleId; 
 	/**
 	 * 获取角色id的属性值.
 	 */
 	public int getRoleId(){
 		return roleId;
 	}
 	
 	/**
 	 * 设置角色id的属性值.
 	 */
 	public void setRoleId(int roleid){
 		this.roleId = roleid;
 	}
}

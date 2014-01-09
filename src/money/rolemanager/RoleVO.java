
package money.rolemanager;

import java.io.Serializable;
/**
 * 关于角色信息的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RoleVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public RoleVO() {

	}
	
	public RoleVO( int roleId , String roleDesc , String roleName ) {
		 this.roleId = roleId;
		 this.roleDesc = roleDesc;
		 this.roleName = roleName;
	}
	
	public RoleVO(String roleDesc ,String roleName ) {
			 this.roleDesc = roleDesc;
			 this.roleName = roleName;
	}
	 
	private Integer roleId; 
 	/**
 	 * 获取角色id的属性值.
 	 */
 	public Integer getRoleId(){
 		return roleId;
 	}
 	
 	/**
 	 * 设置角色id的属性值.
 	 */
 	public void setRoleId(Integer roleid){
 		this.roleId = roleid;
 	}
	private String roleDesc; 
 	/**
 	 * 获取角色描述 的属性值.
 	 */
 	public String getRoleDesc(){
 		return roleDesc;
 	}
 	
 	/**
 	 * 设置角色描述 的属性值.
 	 */
 	public void setRoleDesc(String roledesc){
 		this.roleDesc = roledesc;
 	}
	private String roleName; 
 	/**
 	 * 获取角色名称的属性值.
 	 */
 	public String getRoleName(){
 		return roleName;
 	}
 	
 	/**
 	 * 设置角色名称的属性值.
 	 */
 	public void setRoleName(String rolename){
 		this.roleName = rolename;
 	}
}

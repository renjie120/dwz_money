
package money.rolemanager;

import java.io.Serializable;
/**
 * 关于角色拥有的菜单权限的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RoleWithMenuVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public RoleWithMenuVO() {

	}
	 
	public RoleWithMenuVO( int id , int roleId , int menuId ) {
		 this.id = id;
		 this.roleId = roleId;
		 this.menuId = menuId;
	}
	
	public RoleWithMenuVO(int roleId ,int menuId ) {
			 this.roleId = roleId;
			 this.menuId = menuId;
	}
	 
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	private Integer id; 
 	/**
 	 * 获取角色id的属性值.
 	 */
 	public Integer getId(){
 		return id;
 	}
 	
 	/**
 	 * 设置角色id的属性值.
 	 */
 	public void setId(Integer id){
 		this.id = id;
 	}
	private int roleId; 
 	 
	private int menuId; 
 	 
}

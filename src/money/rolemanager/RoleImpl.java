
package money.rolemanager;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于角色信息的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RoleImpl implements Role {
	private RoleVO roleVO = null;
	private static final long serialVersionUID = 1L;

	public RoleImpl(RoleVO roleVO) {
		this.roleVO = roleVO;
	}

	public RoleImpl( int roleId , String roleDesc , String roleName ) {
		this.roleVO = new RoleVO( roleId , roleDesc , roleName );
	} 
	
	public RoleImpl(String roleDesc ,String roleName ) {
		this.roleVO = new RoleVO(roleDesc ,roleName );
	} 

	public RoleVO getRoleVO() {
		return this.roleVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.roleVO.getRoleId();
	} 
	
 	/**
 	 * 获取角色id的属性值.
 	 */
 	public  Integer   getRoleId(){
 		return this.roleVO.getRoleId();
 	}
 	/**
 	 * 获取角色描述 的属性值.
 	 */
 	public  String   getRoleDesc(){
 		return this.roleVO.getRoleDesc();
 	}
 	/**
 	 * 获取角色名称的属性值.
 	 */
 	public  String   getRoleName(){
 		return this.roleVO.getRoleName();
 	}
 
}
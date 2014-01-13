
package money.rolemanager;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于用户角色权限的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UserRoleRightImpl implements UserRoleRight {
	private UserRoleRightVO userrolerightVO = null;
	private static final long serialVersionUID = 1L;

	public UserRoleRightImpl(UserRoleRightVO userrolerightVO) {
		this.userrolerightVO = userrolerightVO;
	}

	public UserRoleRightImpl( int id , int userId , int roleId ) {
		this.userrolerightVO = new UserRoleRightVO( id , userId , roleId );
	} 
	
	public UserRoleRightImpl(int userId ,int roleId ) {
		this.userrolerightVO = new UserRoleRightVO(userId ,roleId );
	} 

	public UserRoleRightVO getUserRoleRightVO() {
		return this.userrolerightVO;
	}

	public void copyProperties(BusinessObject orig) {

	}
 
 	/**
 	 * 获取首页流水号id的属性值.
 	 */
 	public  Integer   getId(){
 		return this.userrolerightVO.getId();
 	}
 	/**
 	 * 获取用户id 的属性值.
 	 */
 	public  int   getUserId(){
 		return this.userrolerightVO.getUserId();
 	}
 	/**
 	 * 获取角色id的属性值.
 	 */
 	public  int   getRoleId(){
 		return this.userrolerightVO.getRoleId();
 	}
 
}
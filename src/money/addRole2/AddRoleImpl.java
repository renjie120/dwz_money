
package money.addRole2;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于新角色授权的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddRoleImpl implements AddRole {
	private AddRoleVO addroleVO = null;
	private static final long serialVersionUID = 1L;

	public AddRoleImpl(AddRoleVO addroleVO) {
		this.addroleVO = addroleVO;
	}

	public AddRoleImpl( int sno , String roleType , String roleKey , String roleIds , int createUser , String createTime , int updateUser , String updateTime ) {
		this.addroleVO = new AddRoleVO( sno , roleType , roleKey , roleIds , createUser , createTime , updateUser , updateTime );
	} 
	
	public AddRoleImpl(String roleType ,String roleKey ,String roleIds ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.addroleVO = new AddRoleVO(roleType ,roleKey ,roleIds ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public AddRoleVO getAddRoleVO() {
		return this.addroleVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.addroleVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.addroleVO.getSno();
 	}
 	/**
 	 * 获取角色类型的属性值.
 	 */
 	public  String   getRoleType(){
 		return this.addroleVO.getRoleType();
 	}
 	/**
 	 * 获取授权对象 的属性值.
 	 */
 	public  String   getRoleKey(){
 		return this.addroleVO.getRoleKey();
 	}
 	/**
 	 * 获取角色 的属性值.
 	 */
 	public  String   getRoleIds(){
 		return this.addroleVO.getRoleIds();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.addroleVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.addroleVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.addroleVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.addroleVO.getUpdateTime();
 	}
 
}
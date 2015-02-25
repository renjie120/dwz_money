
package money.addRoleDetail;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于新角色授权明细的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddRoleDetailVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AddRoleDetailVO() {

	}
	
	public AddRoleDetailVO( int sno , String roleType , String roleKey , String roleId ) {
		 this.sno = sno;
		 this.roleType = roleType;
		 this.roleKey = roleKey;
		 this.roleId = roleId;
	}
	
	public AddRoleDetailVO(String roleType ,String roleKey ,String roleId ) {
			 this.roleType = roleType;
			 this.roleKey = roleKey;
			 this.roleId = roleId;
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
	private String roleId; 
 	/**
 	 * 获取角色 的属性值.
 	 */
 	public String getRoleId(){
 		return roleId;
 	}
 	
 	/**
 	 * 设置角色 的属性值.
 	 */
 	public void setRoleId(String roleid){
 		this.roleId = roleid;
 	}

}


package money.addRoleDetail;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于新角色授权明细的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddRoleDetailImpl implements AddRoleDetail {
	private AddRoleDetailVO addroledetailVO = null;
	private static final long serialVersionUID = 1L;

	public AddRoleDetailImpl(AddRoleDetailVO addroledetailVO) {
		this.addroledetailVO = addroledetailVO;
	}

	public AddRoleDetailImpl( int sno , String roleType , String roleKey , String roleId ) {
		this.addroledetailVO = new AddRoleDetailVO( sno , roleType , roleKey , roleId );
	} 
	
	public AddRoleDetailImpl(String roleType ,String roleKey ,String roleId ) {
		this.addroledetailVO = new AddRoleDetailVO(roleType ,roleKey ,roleId );
	} 

	public AddRoleDetailVO getAddRoleDetailVO() {
		return this.addroledetailVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.addroledetailVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.addroledetailVO.getSno();
 	}
 	/**
 	 * 获取角色类型的属性值.
 	 */
 	public  String   getRoleType(){
 		return this.addroledetailVO.getRoleType();
 	}
 	/**
 	 * 获取授权对象 的属性值.
 	 */
 	public  String   getRoleKey(){
 		return this.addroledetailVO.getRoleKey();
 	}
 	/**
 	 * 获取角色 的属性值.
 	 */
 	public  String   getRoleId(){
 		return this.addroledetailVO.getRoleId();
 	}
 
}
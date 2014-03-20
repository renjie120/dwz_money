
package money.myuser;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于用户信息表的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class MyUserImpl implements MyUser {
	private MyUserVO myuserVO = null;
	private static final long serialVersionUID = 1L;

	public MyUserImpl(MyUserVO myuserVO) {
		this.myuserVO = myuserVO;
	}

	public MyUserImpl( int useId , String userName , String password , String loginId , int orgId , String email , String phone , String mobile , String userType , String address , int orderId ) {
		this.myuserVO = new MyUserVO( useId , userName , password , loginId , orgId , email , phone , mobile , userType , address , orderId );
	} 
	
	public MyUserImpl(String userName ,String password ,String loginId ,int orgId ,String email ,String phone ,String mobile ,String userType ,String address ,int orderId ) {
		this.myuserVO = new MyUserVO(userName ,password ,loginId ,orgId ,email ,phone ,mobile ,userType ,address ,orderId );
	} 

	public MyUserVO getMyUserVO() {
		return this.myuserVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.myuserVO.getUseId();
	} 
	
 	/**
 	 * 获取用户流水号的属性值.
 	 */
 	public  Integer   getUseId(){
 		return this.myuserVO.getUseId();
 	}
 	/**
 	 * 获取用户名的属性值.
 	 */
 	public  String   getUserName(){
 		return this.myuserVO.getUserName();
 	}
 	/**
 	 * 获取密码的属性值.
 	 */
 	public  String   getPassword(){
 		return this.myuserVO.getPassword();
 	}
 	/**
 	 * 获取登陆号的属性值.
 	 */
 	public  String   getLoginId(){
 		return this.myuserVO.getLoginId();
 	}
 	/**
 	 * 获取组织机构的属性值.
 	 */
 	public  int   getOrgId(){
 		return this.myuserVO.getOrgId();
 	}
 	/**
 	 * 获取邮件的属性值.
 	 */
 	public  String   getEmail(){
 		return this.myuserVO.getEmail();
 	}
 	/**
 	 * 获取座机的属性值.
 	 */
 	public  String   getPhone(){
 		return this.myuserVO.getPhone();
 	}
 	/**
 	 * 获取手机的属性值.
 	 */
 	public  String   getMobile(){
 		return this.myuserVO.getMobile();
 	}
 	/**
 	 * 获取用户类型的属性值.
 	 */
 	public  String   getUserType(){
 		return this.myuserVO.getUserType();
 	}
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getAddress(){
 		return this.myuserVO.getAddress();
 	}
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public  int   getOrderId(){
 		return this.myuserVO.getOrderId();
 	}

	@Override
	public String getOrgName() {
		return this.myuserVO.getOrgName();
	}

	@Override
	public String getUserTypeName() {
		return this.myuserVO.getUserTypeName();
	}
 
}
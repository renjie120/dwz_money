
package ido.LoginUser;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于系统用户的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LoginUserImpl implements LoginUser {
	private LoginUserVO loginuserVO = null;
	private static final long serialVersionUID = 1L;

	public LoginUserImpl(LoginUserVO loginuserVO) {
		this.loginuserVO = loginuserVO;
	}

	public LoginUserImpl( int sno , String userName , String userId , String userType , String userUnit , String userPass , String userStatus , String userPhone , String userEmail , String userAddress , int createUser , String createTime , int updateUser , String updateTime ) {
		this.loginuserVO = new LoginUserVO( sno , userName , userId , userType , userUnit , userPass , userStatus , userPhone , userEmail , userAddress , createUser , createTime , updateUser , updateTime );
	} 
	
	public LoginUserImpl(String userName ,String userId ,String userType ,String userUnit ,String userPass ,String userStatus ,String userPhone ,String userEmail ,String userAddress ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.loginuserVO = new LoginUserVO(userName ,userId ,userType ,userUnit ,userPass ,userStatus ,userPhone ,userEmail ,userAddress ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public LoginUserVO getLoginUserVO() {
		return this.loginuserVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.loginuserVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.loginuserVO.getSno();
 	}
 	/**
 	 * 获取用户姓名的属性值.
 	 */
 	public  String   getUserName(){
 		return this.loginuserVO.getUserName();
 	}
 	/**
 	 * 获取登录名称 的属性值.
 	 */
 	public  String   getUserId(){
 		return this.loginuserVO.getUserId();
 	}
 	/**
 	 * 获取所属类别 的属性值.
 	 */
 	public  String   getUserType(){
 		return this.loginuserVO.getUserType();
 	}
 	/**
 	 * 获取所属单位 的属性值.
 	 */
 	public  String   getUserUnit(){
 		return this.loginuserVO.getUserUnit();
 	}
 	/**
 	 * 获取用户密码的属性值.
 	 */
 	public  String   getUserPass(){
 		return this.loginuserVO.getUserPass();
 	}
 	/**
 	 * 获取用户状态 的属性值.
 	 */
 	public  String   getUserStatus(){
 		return this.loginuserVO.getUserStatus();
 	}
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public  String   getUserPhone(){
 		return this.loginuserVO.getUserPhone();
 	}
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getUserEmail(){
 		return this.loginuserVO.getUserEmail();
 	}
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getUserAddress(){
 		return this.loginuserVO.getUserAddress();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.loginuserVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.loginuserVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.loginuserVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.loginuserVO.getUpdateTime();
 	}
 
}
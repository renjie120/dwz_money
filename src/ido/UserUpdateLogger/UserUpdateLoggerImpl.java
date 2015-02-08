
package ido.UserUpdateLogger;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于用户状态修改记录的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UserUpdateLoggerImpl implements UserUpdateLogger {
	private UserUpdateLoggerVO userupdateloggerVO = null;
	private static final long serialVersionUID = 1L;

	public UserUpdateLoggerImpl(UserUpdateLoggerVO userupdateloggerVO) {
		this.userupdateloggerVO = userupdateloggerVO;
	}

	public UserUpdateLoggerImpl( int sno , String userId , String state , String logDetail , int arg1 , int createUser , String createTime ) {
		this.userupdateloggerVO = new UserUpdateLoggerVO( sno , userId , state , logDetail , arg1 , createUser , createTime );
	} 
	
	public UserUpdateLoggerImpl(String userId ,String state ,String logDetail ,int arg1 ,int createUser ,String createTime ) {
		this.userupdateloggerVO = new UserUpdateLoggerVO(userId ,state ,logDetail ,arg1 ,createUser ,createTime );
	} 

	public UserUpdateLoggerVO getUserUpdateLoggerVO() {
		return this.userupdateloggerVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.userupdateloggerVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.userupdateloggerVO.getSno();
 	}
 	/**
 	 * 获取用户 的属性值.
 	 */
 	public  String   getUserId(){
 		return this.userupdateloggerVO.getUserId();
 	}
 	/**
 	 * 获取用户状态的属性值.
 	 */
 	public  String   getState(){
 		return this.userupdateloggerVO.getState();
 	}
 	/**
 	 * 获取操作原因的属性值.
 	 */
 	public  String   getLogDetail(){
 		return this.userupdateloggerVO.getLogDetail();
 	}
 	/**
 	 * 获取备用字段1的属性值.
 	 */
 	public  int   getArg1(){
 		return this.userupdateloggerVO.getArg1();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.userupdateloggerVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.userupdateloggerVO.getCreateTime();
 	}

	@Override
	public String getUseName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCreateUserName() {
		// TODO Auto-generated method stub
		return this.userupdateloggerVO.getCreateUserName();
	}
 
}
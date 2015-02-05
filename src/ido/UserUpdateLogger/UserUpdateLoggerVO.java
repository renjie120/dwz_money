
package ido.UserUpdateLogger;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于用户状态修改记录的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UserUpdateLoggerVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public UserUpdateLoggerVO() {

	}
	
	public UserUpdateLoggerVO( int sno , String userId , String state , String logDetail , int arg1 , int createUser , String createTime ) {
		 this.sno = sno;
		 this.userId = userId;
		 this.state = state;
		 this.logDetail = logDetail;
		 this.arg1 = arg1;
		 this.createUser = createUser;
		 this.createTime = createTime;
	}
	
	public UserUpdateLoggerVO(String userId ,String state ,String logDetail ,int arg1 ,int createUser ,String createTime ) {
			 this.userId = userId;
			 this.state = state;
			 this.logDetail = logDetail;
			 this.arg1 = arg1;
			 this.createUser = createUser;
			 this.createTime = createTime;
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
	private String userId; 
 	/**
 	 * 获取用户 的属性值.
 	 */
 	public String getUserId(){
 		return userId;
 	}
 	
 	/**
 	 * 设置用户 的属性值.
 	 */
 	public void setUserId(String userid){
 		this.userId = userid;
 	}
	private String state; 
 	/**
 	 * 获取用户状态的属性值.
 	 */
 	public String getState(){
 		return state;
 	}
 	
 	/**
 	 * 设置用户状态的属性值.
 	 */
 	public void setState(String state){
 		this.state = state;
 	}
	private String logDetail; 
 	/**
 	 * 获取操作原因的属性值.
 	 */
 	public String getLogDetail(){
 		return logDetail;
 	}
 	
 	/**
 	 * 设置操作原因的属性值.
 	 */
 	public void setLogDetail(String logdetail){
 		this.logDetail = logdetail;
 	}
	private int arg1; 
 	/**
 	 * 获取备用字段1的属性值.
 	 */
 	public int getArg1(){
 		return arg1;
 	}
 	
 	/**
 	 * 设置备用字段1的属性值.
 	 */
 	public void setArg1(int arg1){
 		this.arg1 = arg1;
 	}
	private int createUser; 
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public int getCreateUser(){
 		return createUser;
 	}
 	
 	/**
 	 * 设置创建用户的属性值.
 	 */
 	public void setCreateUser(int createuser){
 		this.createUser = createuser;
 	}
	private String createTime; 
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public String getCreateTime(){
 		return createTime;
 	}
 	
 	/**
 	 * 设置创建时间的属性值.
 	 */
 	public void setCreateTime(String createtime){
 		this.createTime = createtime;
 	}

}

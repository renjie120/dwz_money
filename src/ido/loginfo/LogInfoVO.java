
package ido.loginfo;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于操作日志的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LogInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public LogInfoVO() {

	}
	
	public LogInfoVO( int sno , int operUser , String operUserName , String operTime , String operType , String operIp , String operUrl , String operBefore , String operAfter , String operDesc ) {
		 this.sno = sno;
		 this.operUser = operUser;
		 this.operUserName = operUserName;
		 this.operTime = operTime;
		 this.operType = operType;
		 this.operIp = operIp;
		 this.operUrl = operUrl;
		 this.operBefore = operBefore;
		 this.operAfter = operAfter;
		 this.operDesc = operDesc;
	}
	
	public LogInfoVO(int operUser ,String operUserName ,String operTime ,String operType ,String operIp ,String operUrl ,String operBefore ,String operAfter ,String operDesc ) {
			 this.operUser = operUser;
			 this.operUserName = operUserName;
			 this.operTime = operTime;
			 this.operType = operType;
			 this.operIp = operIp;
			 this.operUrl = operUrl;
			 this.operBefore = operBefore;
			 this.operAfter = operAfter;
			 this.operDesc = operDesc;
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
	private int operUser; 
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public int getOperUser(){
 		return operUser;
 	}
 	
 	/**
 	 * 设置用户id的属性值.
 	 */
 	public void setOperUser(int operuser){
 		this.operUser = operuser;
 	}
	private String operUserName; 
 	/**
 	 * 获取用户的属性值.
 	 */
 	public String getOperUserName(){
 		return operUserName;
 	}
 	
 	/**
 	 * 设置用户的属性值.
 	 */
 	public void setOperUserName(String operusername){
 		this.operUserName = operusername;
 	}
	private String operTime; 
 	/**
 	 * 获取时间 的属性值.
 	 */
 	public String getOperTime(){
 		return operTime;
 	}
 	
 	/**
 	 * 设置时间 的属性值.
 	 */
 	public void setOperTime(String opertime){
 		this.operTime = opertime;
 	}
	private String operType; 
 	/**
 	 * 获取操作类型的属性值.
 	 */
 	public String getOperType(){
 		return operType;
 	}
 	
 	/**
 	 * 设置操作类型的属性值.
 	 */
 	public void setOperType(String opertype){
 		this.operType = opertype;
 	}
	private String operIp; 
 	/**
 	 * 获取ip地址的属性值.
 	 */
 	public String getOperIp(){
 		return operIp;
 	}
 	
 	/**
 	 * 设置ip地址的属性值.
 	 */
 	public void setOperIp(String operip){
 		this.operIp = operip;
 	}
	private String operUrl; 
 	/**
 	 * 获取操作地址的属性值.
 	 */
 	public String getOperUrl(){
 		return operUrl;
 	}
 	
 	/**
 	 * 设置操作地址的属性值.
 	 */
 	public void setOperUrl(String operurl){
 		this.operUrl = operurl;
 	}
	private String operBefore; 
 	/**
 	 * 获取修改前的属性值.
 	 */
 	public String getOperBefore(){
 		return operBefore;
 	}
 	
 	/**
 	 * 设置修改前的属性值.
 	 */
 	public void setOperBefore(String operbefore){
 		this.operBefore = operbefore;
 	}
	private String operAfter; 
 	/**
 	 * 获取修改后的属性值.
 	 */
 	public String getOperAfter(){
 		return operAfter;
 	}
 	
 	/**
 	 * 设置修改后的属性值.
 	 */
 	public void setOperAfter(String operafter){
 		this.operAfter = operafter;
 	}
	private String operDesc; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getOperDesc(){
 		return operDesc;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setOperDesc(String operdesc){
 		this.operDesc = operdesc;
 	}

}


package money.bugList;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于问题清单的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BugListVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BugListVO() {

	}
	
	public BugListVO( int sno , String bugType , String bugDesc , int createUser , String createTime , String needTime , String consolePeople , String consoleTile , String remark ) {
		 this.sno = sno;
		 this.bugType = bugType;
		 this.bugDesc = bugDesc;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.needTime = needTime;
		 this.consolePeople = consolePeople;
		 this.consoleTile = consoleTile;
		 this.remark = remark;
	}
	
	public BugListVO(String bugType ,String bugDesc ,int createUser ,String createTime ,String needTime ,String consolePeople ,String consoleTile ,String remark ) {
			 this.bugType = bugType;
			 this.bugDesc = bugDesc;
			 this.createUser = createUser;
			 this.createTime = createTime;
			 this.needTime = needTime;
			 this.consolePeople = consolePeople;
			 this.consoleTile = consoleTile;
			 this.remark = remark;
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
	private String bugType; 
 	/**
 	 * 获取问题类型的属性值.
 	 */
 	public String getBugType(){
 		return bugType;
 	}
 	
 	/**
 	 * 设置问题类型的属性值.
 	 */
 	public void setBugType(String bugtype){
 		this.bugType = bugtype;
 	}
	private String bugDesc; 
 	/**
 	 * 获取问题描述的属性值.
 	 */
 	public String getBugDesc(){
 		return bugDesc;
 	}
 	
 	/**
 	 * 设置问题描述的属性值.
 	 */
 	public void setBugDesc(String bugdesc){
 		this.bugDesc = bugdesc;
 	}
	private int createUser; 
 	/**
 	 * 获取问题发现人的属性值.
 	 */
 	public int getCreateUser(){
 		return createUser;
 	}
 	
 	/**
 	 * 设置问题发现人的属性值.
 	 */
 	public void setCreateUser(int createuser){
 		this.createUser = createuser;
 	}
	private String createTime; 
 	/**
 	 * 获取发现时间的属性值.
 	 */
 	public String getCreateTime(){
 		return createTime;
 	}
 	
 	/**
 	 * 设置发现时间的属性值.
 	 */
 	public void setCreateTime(String createtime){
 		this.createTime = createtime;
 	}
	private String needTime; 
 	/**
 	 * 获取待解决时间的属性值.
 	 */
 	public String getNeedTime(){
 		return needTime;
 	}
 	
 	/**
 	 * 设置待解决时间的属性值.
 	 */
 	public void setNeedTime(String needtime){
 		this.needTime = needtime;
 	}
	private String consolePeople; 
 	/**
 	 * 获取解决人的属性值.
 	 */
 	public String getConsolePeople(){
 		return consolePeople;
 	}
 	
 	/**
 	 * 设置解决人的属性值.
 	 */
 	public void setConsolePeople(String consolepeople){
 		this.consolePeople = consolepeople;
 	}
	private String consoleTile; 
 	/**
 	 * 获取解决时间的属性值.
 	 */
 	public String getConsoleTile(){
 		return consoleTile;
 	}
 	
 	/**
 	 * 设置解决时间的属性值.
 	 */
 	public void setConsoleTile(String consoletile){
 		this.consoleTile = consoletile;
 	}
	private String remark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getRemark(){
 		return remark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setRemark(String remark){
 		this.remark = remark;
 	}

}


package ido.bindFamily;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于投保用户的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BindFamilyVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BindFamilyVO() {

	}
	
	public BindFamilyVO( int sno , String iuserNo , String bindName , String relation , String cardNo , String phone , int createUser , String createTime ) {
		 this.sno = sno;
		 this.iuserNo = iuserNo;
		 this.bindName = bindName;
		 this.relation = relation;
		 this.cardNo = cardNo;
		 this.phone = phone;
		 this.createUser = createUser;
		 this.createTime = createTime;
	}
	
	public BindFamilyVO(String iuserNo ,String bindName ,String relation ,String cardNo ,String phone ,int createUser ,String createTime ) {
			 this.iuserNo = iuserNo;
			 this.bindName = bindName;
			 this.relation = relation;
			 this.cardNo = cardNo;
			 this.phone = phone;
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
	private String iuserNo; 
 	/**
 	 * 获取主用户号的属性值.
 	 */
 	public String getIuserNo(){
 		return iuserNo;
 	}
 	
 	/**
 	 * 设置主用户号的属性值.
 	 */
 	public void setIuserNo(String iuserno){
 		this.iuserNo = iuserno;
 	}
	private String bindName; 
 	/**
 	 * 获取绑定人的属性值.
 	 */
 	public String getBindName(){
 		return bindName;
 	}
 	
 	/**
 	 * 设置绑定人的属性值.
 	 */
 	public void setBindName(String bindname){
 		this.bindName = bindname;
 	}
	private String relation; 
 	/**
 	 * 获取关系的属性值.
 	 */
 	public String getRelation(){
 		return relation;
 	}
 	
 	/**
 	 * 设置关系的属性值.
 	 */
 	public void setRelation(String relation){
 		this.relation = relation;
 	}
	private String cardNo; 
 	/**
 	 * 获取身份证的属性值.
 	 */
 	public String getCardNo(){
 		return cardNo;
 	}
 	
 	/**
 	 * 设置身份证的属性值.
 	 */
 	public void setCardNo(String cardno){
 		this.cardNo = cardno;
 	}
	private String phone; 
 	/**
 	 * 获取手机号的属性值.
 	 */
 	public String getPhone(){
 		return phone;
 	}
 	
 	/**
 	 * 设置手机号的属性值.
 	 */
 	public void setPhone(String phone){
 		this.phone = phone;
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

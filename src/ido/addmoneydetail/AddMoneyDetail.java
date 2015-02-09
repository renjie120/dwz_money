
package ido.addmoneydetail;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于充值记录明细的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface AddMoneyDetail extends BusinessObject {   
	public  String   getCreateUserName(); 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取投保用户号的属性值.
 	 */
 	public  String   getIuserId();
 	/**
 	 * 获取充值字段的属性值.
 	 */
 	public  String   getAddType();
 	/**
 	 * 获取充值金额 的属性值.
 	 */
 	public  String   getAddMoney();
 	/**
 	 * 获取投保单号 的属性值.
 	 */
 	public  String   getInsuredFileId();
 	/**
 	 * 获取充值时间的属性值.
 	 */
 	public  String   getAddTime();
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser();
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime();
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser();
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime();
}

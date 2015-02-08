
package ido.BusinessMan;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于商家的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface BusinessMan extends BusinessObject {  
	public  String   getCreateUserName();
 	public  String   getUpdateUserName();
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取商家编号的属性值.
 	 */
 	public  String   getShopmSno();
 	/**
 	 * 获取商家名称 的属性值.
 	 */
 	public  String   getShopmName();
 	/**
 	 * 获取商家简称 的属性值.
 	 */
 	public  String   getShopmShortName();
 	/**
 	 * 获取商家类型 的属性值.
 	 */
 	public  String   getShopmType();
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getShopmEmail();
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getShopmConPhone();
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public  String   getShopmContactName();
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getShopmAddress();
 	/**
 	 * 获取开户行的属性值.
 	 */
 	public  String   getOpenBank();
 	/**
 	 * 获取户名的属性值.
 	 */
 	public  String   getOpenBankName();
 	/**
 	 * 获取银行帐号的属性值.
 	 */
 	public  String   getOpenBankNo();
 	/**
 	 * 获取开票单位的属性值.
 	 */
 	public  String   getOpenTicketUnit();
 	/**
 	 * 获取开户所在省的属性值.
 	 */
 	public  String   getOpenBankProvince();
 	/**
 	 * 获取开户所在市的属性值.
 	 */
 	public  String   getOpenBankCity();
 	/**
 	 * 获取理赔截止日期的属性值.
 	 */
 	public  String   getCompensationDay();
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


package ido.InsuredCompany;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于保险公司的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface InsuredCompany extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取保险公司名称的属性值.
 	 */
 	public  String   getComName();
 	/**
 	 * 获取保险公司编号 的属性值.
 	 */
 	public  String   getComNo();
 	/**
 	 * 获取简称的属性值.
 	 */
 	public  String   getComShortName();
 	/**
 	 * 获取电话的属性值.
 	 */
 	public  String   getComPhone();
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public  String   getComContactName();
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getComContactPhone();
 	/**
 	 * 获取所属保险公司的属性值.
 	 */
 	public  String   getOwnerCompany();
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getComEmail();
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getComAddress();
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getComRemark();
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

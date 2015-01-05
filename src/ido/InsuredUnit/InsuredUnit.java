
package ido.InsuredUnit;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于投保单位的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface InsuredUnit extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取编号的属性值.
 	 */
 	public  String   getUnitCode();
 	/**
 	 * 获取投保单位 的属性值.
 	 */
 	public  String   getUnitName();
 	/**
 	 * 获取联系人的属性值.
 	 */
 	public  String   getContactName();
 	/**
 	 * 获取手机的属性值.
 	 */
 	public  String   getContactMobile();
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getContactEmail();
 	/**
 	 * 获取上级单位的属性值.
 	 */
 	public  int   getUnitParentId();
 	/**
 	 * 获取状态的属性值.
 	 */
 	public  String   getUnitState();
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getUnitAddress();
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getUnitRemark();
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

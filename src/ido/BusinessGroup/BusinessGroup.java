
package ido.BusinessGroup;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于商家集团的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface BusinessGroup extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取集团编号的属性值.
 	 */
 	public  String   getGroupSno();
 	/**
 	 * 获取集团名称 的属性值.
 	 */
 	public  String   getGroupName();
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getGroupEmail();
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public  String   getGroupContact();
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public  String   getGroupContactPhone();
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getGroupContactMobile();
 	/**
 	 * 获取状态的属性值.
 	 */
 	public  String   getGroupStatus();
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

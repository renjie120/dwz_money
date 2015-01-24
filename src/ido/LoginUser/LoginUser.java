
package ido.LoginUser;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于系统用户的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface LoginUser extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取用户姓名的属性值.
 	 */
 	public  String   getUserName();
 	/**
 	 * 获取登录名称 的属性值.
 	 */
 	public  String   getUserId();
 	/**
 	 * 获取所属类别 的属性值.
 	 */
 	public  String   getUserType();
 	/**
 	 * 获取所属单位 的属性值.
 	 */
 	public  String   getUserUnit();
 	/**
 	 * 获取用户密码的属性值.
 	 */
 	public  String   getUserPass();
 	/**
 	 * 获取用户状态 的属性值.
 	 */
 	public  String   getUserStatus();
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public  String   getUserPhone();
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getUserEmail();
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getUserAddress();
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

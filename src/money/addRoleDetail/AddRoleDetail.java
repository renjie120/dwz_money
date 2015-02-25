
package money.addRoleDetail;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于新角色授权明细的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface AddRoleDetail extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取角色类型的属性值.
 	 */
 	public  String   getRoleType();
 	/**
 	 * 获取授权对象 的属性值.
 	 */
 	public  String   getRoleKey();
 	/**
 	 * 获取角色 的属性值.
 	 */
 	public  String   getRoleId();
}

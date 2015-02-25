
package ido.Dict_Drugtype;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于药品大类字典表的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface DrugTypeSelect extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取药品类别名称的属性值.
 	 */
 	public  String   getDrugType();
 	/**
 	 * 获取是否有效的属性值.
 	 */
 	public  String   getTypeStatus();
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

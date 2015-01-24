
package ido.province;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于省份字典表的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface ProvinceDict extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取省份的属性值.
 	 */
 	public  String   getProvName();
 	/**
 	 * 获取类型的属性值.
 	 */
 	public  String   getProvType();
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public  String   getProvState();
}

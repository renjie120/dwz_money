
package ido.city;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于城市字典表的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface CityDict extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取名称的属性值.
 	 */
 	public  String   getCityName();
 	/**
 	 * 获取省份的属性值.
 	 */
 	public  String   getProvId();
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public  String   getCityState();
}

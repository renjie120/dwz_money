
package money.param;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于参数的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface Param extends BusinessObject {  
 	/**
 	 * 获取参数流水号的属性值.
 	 */
 	public  Integer   getParamId();
 	/**
 	 * 获取参数类型的属性值.
 	 */
 	public  int   getParamType();
 	/**
 	 * 获取参数描述的属性值.
 	 */
 	public  String   getParamName();
 	/**
 	 * 获取参数值的属性值.
 	 */
 	public  int   getParamValue();
 	/**
 	 * 获取用户自定义值的属性值.
 	 */
 	public  String   getUsevalue();
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public  int   getOrderId();
}

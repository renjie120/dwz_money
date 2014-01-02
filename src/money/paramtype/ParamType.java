
package money.paramtype;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于参数类型的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface ParamType extends BusinessObject {  
 	/**
 	 * 获取参数类型流水号的属性值.
 	 */
 	public  Integer   getParamTypeId();
 	/**
 	 * 获取参数类型的属性值.
 	 */
 	public  String   getParamTypeName();
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public  int   getOrderId();
 	/**
 	 * 获取参数类型编码的属性值.
 	 */
 	public  String   getCode();
}


package money.homepage;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于首页url的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface HomePageUrl extends BusinessObject {  
 	/**
 	 * 获取首页流水号id的属性值.
 	 */
 	public  Integer   getUrlId();
 	/**
 	 * 获取链接描述 的属性值.
 	 */
 	public  String   getUrlDesc();
 	/**
 	 * 获取链接的属性值.
 	 */
 	public  String   getUrl();
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public  int   getOrderId();
 	/**
 	 * 获取类型的属性值.
 	 */
 	public  int   getTypeId();
}


package money.stockmanage;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于股票交易的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface StockManager extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取股票号码的属性值.
 	 */
 	public  String   getStockNo();
 	/**
 	 * 获取股票名称 的属性值.
 	 */
 	public  String   getStockName();
 	/**
 	 * 获取交易时间的属性值.
 	 */
 	public  Date   getDealDate();
 	/**
 	 * 获取交易价格的属性值.
 	 */
 	public  double   getPrice();
 	/**
 	 * 获取交易份额的属性值.
 	 */
 	public  double   getDealNumber();
 	/**
 	 * 获取交易费率的属性值.
 	 */
 	public  double   getFee();
 	/**
 	 * 获取交易类型的属性值.
 	 */
 	public  String   getDealType();
}


package money.yaopin;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于药品销售信息的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface YaopinInfo extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取业务实体的属性值.
 	 */
 	public  String   getCompanyName();
 	/**
 	 * 获取物料名称（英文）的属性值.
 	 */
 	public  String   getEngName();
 	/**
 	 * 获取物料名称（中文）的属性值.
 	 */
 	public  String   getChnName();
 	/**
 	 * 获取结构式的属性值.
 	 */
 	public  String   getChemStruct();
 	/**
 	 * 获取生产厂家的属性值.
 	 */
 	public  String   getProductName();
 	/**
 	 * 获取厂家销售客户的属性值.
 	 */
 	public  String   getCustomer();
 	/**
 	 * 获取数量的属性值.
 	 */
 	public  Double   getNum();
 	/**
 	 * 获取销售价格的属性值.
 	 */
 	public  Double   getPrice();
 	/**
 	 * 获取纯度的属性值.
 	 */
 	public  String   getChundu();
 	/**
 	 * 获取销售时间的属性值.
 	 */
 	public  Date   getSaleTime();
 	/**
 	 * 获取CAS的属性值.
 	 */
 	public  String   getCas();
 	/**
 	 * 获取厂家联系人的属性值.
 	 */
 	public  String   getConnect();
}

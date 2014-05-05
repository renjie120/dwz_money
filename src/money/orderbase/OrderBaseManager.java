
package money.orderbase;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于订单基本信息的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface OrderBaseManager extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取订单的属性值.
 	 */
 	public  String   getOrderNo();
 	/**
 	 * 获取客户名称的属性值.
 	 */
 	public  String   getCustomerNo();
 	/**
 	 * 获取功率的属性值.
 	 */
 	public  String   getGongLv();
 	/**
 	 * 获取电压的属性值.
 	 */
 	public  String   getDianYa();
 	/**
 	 * 获取世代的属性值.
 	 */
 	public  String   getShiDai();
 	/**
 	 * 获取整流变压器厂家的属性值.
 	 */
 	public  String   getBianyaChangjia();
 	/**
 	 * 获取整流变压器型号的属性值.
 	 */
 	public  String   getBianyaXinghao();
 	/**
 	 * 获取是否重点客户的属性值.
 	 */
 	public  String   getIsImport();
 	/**
 	 * 获取计划开工时间的属性值.
 	 */
 	public  Date   getStartDate();
 	/**
 	 * 获取计划结束时间的属性值.
 	 */
 	public  Date   getEndDate();
 	/**
 	 * 获取当前状态的属性值.
 	 */
 	public  String   getCurrentState();
}

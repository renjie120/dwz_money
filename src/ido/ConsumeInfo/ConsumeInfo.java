
package ido.ConsumeInfo;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于消费记录的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface ConsumeInfo extends BusinessObject {  
	public  String   getCreateUserName();
 	public  String   getUpdateUserName();
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取投保用户号的属性值.
 	 */
 	public  String   getIuserId();
 	/**
 	 * 获取投保公司的属性值.
 	 */
 	public  String   getComId();
 	/**
 	 * 获取所属商家 的属性值.
 	 */
 	public  String   getShopmId();
 	/**
 	 * 获取所属商铺 的属性值.
 	 */
 	public  String   getShopId();
 	/**
 	 * 获取所属分公司 的属性值.
 	 */
 	public  String   getOwnerCom();
 	/**
 	 * 获取支付状态的属性值.
 	 */
 	public  String   getConsumeStatus();
 	/**
 	 * 获取消费金额的属性值.
 	 */
 	public  double   getConsumeMoney();
 	/**
 	 * 获取刷卡消费的属性值.
 	 */
 	public  double   getCardMoney();
 	/**
 	 * 获取现金支付的属性值.
 	 */
 	public  double   getCashMoney();
 	/**
 	 * 获取消费时间的属性值.
 	 */
 	public  String   getConsumeTime();
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

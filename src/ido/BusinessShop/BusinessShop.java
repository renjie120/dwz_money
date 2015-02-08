
package ido.BusinessShop;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于商铺的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface BusinessShop extends BusinessObject {  
	public  String   getCreateUserName();
 	public  String   getUpdateUserName();

 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取商家编号的属性值.
 	 */
 	public  String   getShopmId();
 	/**
 	 * 获取商铺名称 的属性值.
 	 */
 	public  String   getShopName();
 	/**
 	 * 获取商铺编号 的属性值.
 	 */
 	public  String   getShopSno();
 	/**
 	 * 获取商铺状态 的属性值.
 	 */
 	public  String   getShopStatus();
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public  String   getShopContactName();
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getShopConPhone();
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getShopEmail();
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getShopAddress();
 	/**
 	 * 获取签约日期的属性值.
 	 */
 	public  String   getShopDate();
 	/**
 	 * 获取经度的属性值.
 	 */
 	public  String   getShopJingdu();
 	/**
 	 * 获取纬度的属性值.
 	 */
 	public  String   getShopWeidu();
 	/**
 	 * 获取省份的属性值.
 	 */
 	public  String   getShopProvince();
 	/**
 	 * 获取市的属性值.
 	 */
 	public  String   getShopCity();
 	/**
 	 * 获取区县的属性值.
 	 */
 	public  String   getShopxian();
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getShopRemark();
 	/**
 	 * 获取主营的属性值.
 	 */
 	public  String   getShopMain();
 	/**
 	 * 获取简介的属性值.
 	 */
 	public  String   getShopIntroduce();
 	/**
 	 * 获取特色的属性值.
 	 */
 	public  String   getShopSpecial();
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

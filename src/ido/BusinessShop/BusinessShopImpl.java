
package ido.BusinessShop;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于商铺的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessShopImpl implements BusinessShop {
	private BusinessShopVO businessshopVO = null;
	private static final long serialVersionUID = 1L;

	public BusinessShopImpl(BusinessShopVO businessshopVO) {
		this.businessshopVO = businessshopVO;
	}

	public BusinessShopImpl( int sno , String shopmId , String shopName , String shopSno , String shopStatus , String shopContactName , String shopConPhone , String shopEmail , String shopAddress , String shopDate , String shopJingdu , String shopWeidu , String shopProvince , String shopCity , String shopxian , String shopRemark , String shopMain , String shopIntroduce , String shopSpecial , int createUser , String createTime , int updateUser , String updateTime ) {
		this.businessshopVO = new BusinessShopVO( sno , shopmId , shopName , shopSno , shopStatus , shopContactName , shopConPhone , shopEmail , shopAddress , shopDate , shopJingdu , shopWeidu , shopProvince , shopCity , shopxian , shopRemark , shopMain , shopIntroduce , shopSpecial , createUser , createTime , updateUser , updateTime );
	} 
	
	public BusinessShopImpl(String shopmId ,String shopName ,String shopSno ,String shopStatus ,String shopContactName ,String shopConPhone ,String shopEmail ,String shopAddress ,String shopDate ,String shopJingdu ,String shopWeidu ,String shopProvince ,String shopCity ,String shopxian ,String shopRemark ,String shopMain ,String shopIntroduce ,String shopSpecial ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.businessshopVO = new BusinessShopVO(shopmId ,shopName ,shopSno ,shopStatus ,shopContactName ,shopConPhone ,shopEmail ,shopAddress ,shopDate ,shopJingdu ,shopWeidu ,shopProvince ,shopCity ,shopxian ,shopRemark ,shopMain ,shopIntroduce ,shopSpecial ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public BusinessShopVO getBusinessShopVO() {
		return this.businessshopVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.businessshopVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.businessshopVO.getSno();
 	}
 	/**
 	 * 获取商家编号的属性值.
 	 */
 	public  String   getShopmId(){
 		return this.businessshopVO.getShopmId();
 	}
 	/**
 	 * 获取商铺名称 的属性值.
 	 */
 	public  String   getShopName(){
 		return this.businessshopVO.getShopName();
 	}
 	/**
 	 * 获取商铺编号 的属性值.
 	 */
 	public  String   getShopSno(){
 		return this.businessshopVO.getShopSno();
 	}
 	/**
 	 * 获取商铺状态 的属性值.
 	 */
 	public  String   getShopStatus(){
 		return this.businessshopVO.getShopStatus();
 	}
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public  String   getShopContactName(){
 		return this.businessshopVO.getShopContactName();
 	}
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getShopConPhone(){
 		return this.businessshopVO.getShopConPhone();
 	}
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getShopEmail(){
 		return this.businessshopVO.getShopEmail();
 	}
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getShopAddress(){
 		return this.businessshopVO.getShopAddress();
 	}
 	/**
 	 * 获取签约日期的属性值.
 	 */
 	public  String   getShopDate(){
 		return this.businessshopVO.getShopDate();
 	}
 	/**
 	 * 获取经度的属性值.
 	 */
 	public  String   getShopJingdu(){
 		return this.businessshopVO.getShopJingdu();
 	}
 	/**
 	 * 获取纬度的属性值.
 	 */
 	public  String   getShopWeidu(){
 		return this.businessshopVO.getShopWeidu();
 	}
 	/**
 	 * 获取省份的属性值.
 	 */
 	public  String   getShopProvince(){
 		return this.businessshopVO.getShopProvince();
 	}
 	/**
 	 * 获取市的属性值.
 	 */
 	public  String   getShopCity(){
 		return this.businessshopVO.getShopCity();
 	}
 	/**
 	 * 获取区县的属性值.
 	 */
 	public  String   getShopxian(){
 		return this.businessshopVO.getShopxian();
 	}
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getShopRemark(){
 		return this.businessshopVO.getShopRemark();
 	}
 	/**
 	 * 获取主营的属性值.
 	 */
 	public  String   getShopMain(){
 		return this.businessshopVO.getShopMain();
 	}
 	/**
 	 * 获取简介的属性值.
 	 */
 	public  String   getShopIntroduce(){
 		return this.businessshopVO.getShopIntroduce();
 	}
 	/**
 	 * 获取特色的属性值.
 	 */
 	public  String   getShopSpecial(){
 		return this.businessshopVO.getShopSpecial();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.businessshopVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.businessshopVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.businessshopVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.businessshopVO.getUpdateTime();
 	}

	@Override
	public String getCreateUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateUserName() {
		// TODO Auto-generated method stub
		return null;
	}
 
}

package ido.ConsumeInfo;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于消费记录的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ConsumeInfoImpl implements ConsumeInfo {
	private ConsumeInfoVO consumeinfoVO = null;
	private static final long serialVersionUID = 1L;

	public ConsumeInfoImpl(ConsumeInfoVO consumeinfoVO) {
		this.consumeinfoVO = consumeinfoVO;
	}

	public ConsumeInfoImpl( int sno , String iuserId , String comId , String shopmId , String shopId , String ownerCom , String consumeStatus , double consumeMoney , double cardMoney , double cashMoney , String consumeTime , int createUser , String createTime , int updateUser , String updateTime ) {
		this.consumeinfoVO = new ConsumeInfoVO( sno , iuserId , comId , shopmId , shopId , ownerCom , consumeStatus , consumeMoney , cardMoney , cashMoney , consumeTime , createUser , createTime , updateUser , updateTime );
	} 
	
	public ConsumeInfoImpl(String iuserId ,String comId ,String shopmId ,String shopId ,String ownerCom ,String consumeStatus ,double consumeMoney ,double cardMoney ,double cashMoney ,String consumeTime ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.consumeinfoVO = new ConsumeInfoVO(iuserId ,comId ,shopmId ,shopId ,ownerCom ,consumeStatus ,consumeMoney ,cardMoney ,cashMoney ,consumeTime ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public ConsumeInfoVO getConsumeInfoVO() {
		return this.consumeinfoVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.consumeinfoVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.consumeinfoVO.getSno();
 	}
 	/**
 	 * 获取投保用户号的属性值.
 	 */
 	public  String   getIuserId(){
 		return this.consumeinfoVO.getIuserId();
 	}
 	/**
 	 * 获取投保公司的属性值.
 	 */
 	public  String   getComId(){
 		return this.consumeinfoVO.getComId();
 	}
 	/**
 	 * 获取所属商家 的属性值.
 	 */
 	public  String   getShopmId(){
 		return this.consumeinfoVO.getShopmId();
 	}
 	/**
 	 * 获取所属商铺 的属性值.
 	 */
 	public  String   getShopId(){
 		return this.consumeinfoVO.getShopId();
 	}
 	/**
 	 * 获取所属分公司 的属性值.
 	 */
 	public  String   getOwnerCom(){
 		return this.consumeinfoVO.getOwnerCom();
 	}
 	/**
 	 * 获取支付状态的属性值.
 	 */
 	public  String   getConsumeStatus(){
 		return this.consumeinfoVO.getConsumeStatus();
 	}
 	/**
 	 * 获取消费金额的属性值.
 	 */
 	public  double   getConsumeMoney(){
 		return this.consumeinfoVO.getConsumeMoney();
 	}
 	/**
 	 * 获取刷卡消费的属性值.
 	 */
 	public  double   getCardMoney(){
 		return this.consumeinfoVO.getCardMoney();
 	}
 	/**
 	 * 获取现金支付的属性值.
 	 */
 	public  double   getCashMoney(){
 		return this.consumeinfoVO.getCashMoney();
 	}
 	/**
 	 * 获取消费时间的属性值.
 	 */
 	public  String   getConsumeTime(){
 		return this.consumeinfoVO.getConsumeTime();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.consumeinfoVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.consumeinfoVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.consumeinfoVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.consumeinfoVO.getUpdateTime();
 	}

	@Override
	public String getCreateUserName() {
		// TODO Auto-generated method stub
		return this.consumeinfoVO.getCreateUserName();
	}

	@Override
	public String getUpdateUserName() {
		// TODO Auto-generated method stub
		return this.consumeinfoVO.getUpdateUserName();
	}
 
}
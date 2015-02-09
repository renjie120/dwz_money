
package ido.addmoneydetail;

import dwz.framework.core.business.BusinessObject;
/**
 * 关于充值记录明细的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class AddMoneyDetailImpl implements AddMoneyDetail {
	private AddMoneyDetailVO addmoneydetailVO = null;
	private static final long serialVersionUID = 1L;

	public AddMoneyDetailImpl(AddMoneyDetailVO addmoneydetailVO) {
		this.addmoneydetailVO = addmoneydetailVO;
	}

	public AddMoneyDetailImpl( int sno , String iuserId , String addType , String addMoney , String insuredFileId , String addTime , int createUser , String createTime , int updateUser , String updateTime ) {
		this.addmoneydetailVO = new AddMoneyDetailVO( sno , iuserId , addType , addMoney , insuredFileId , addTime , createUser , createTime , updateUser , updateTime );
	} 
	
	public AddMoneyDetailImpl(String iuserId ,String addType ,String addMoney ,String insuredFileId ,String addTime ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.addmoneydetailVO = new AddMoneyDetailVO(iuserId ,addType ,addMoney ,insuredFileId ,addTime ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public AddMoneyDetailVO getAddMoneyDetailVO() {
		return this.addmoneydetailVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.addmoneydetailVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.addmoneydetailVO.getSno();
 	}
 	/**
 	 * 获取投保用户号的属性值.
 	 */
 	public  String   getIuserId(){
 		return this.addmoneydetailVO.getIuserId();
 	}
 	/**
 	 * 获取充值字段的属性值.
 	 */
 	public  String   getAddType(){
 		return this.addmoneydetailVO.getAddType();
 	}
 	/**
 	 * 获取充值金额 的属性值.
 	 */
 	public  String   getAddMoney(){
 		return this.addmoneydetailVO.getAddMoney();
 	}
 	/**
 	 * 获取投保单号 的属性值.
 	 */
 	public  String   getInsuredFileId(){
 		return this.addmoneydetailVO.getInsuredFileId();
 	}
 	/**
 	 * 获取充值时间的属性值.
 	 */
 	public  String   getAddTime(){
 		return this.addmoneydetailVO.getAddTime();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.addmoneydetailVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.addmoneydetailVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.addmoneydetailVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.addmoneydetailVO.getUpdateTime();
 	}

	@Override
	public String getCreateUserName() {
		// TODO Auto-generated method stub
		return this.addmoneydetailVO.getCreateUserName();
	}
 
}
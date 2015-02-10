
package ido.BusinessMan;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于商家的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BusinessManImpl implements BusinessMan {
	private BusinessManVO businessmanVO = null;
	private static final long serialVersionUID = 1L;

	public BusinessManImpl(BusinessManVO businessmanVO) {
		this.businessmanVO = businessmanVO;
	}

	public BusinessManImpl( int sno , String shopmSno , String shopmName , String shopmShortName , String shopmType , String shopmEmail , String shopmConPhone , String shopmContactName , String shopmAddress , String openBank , String openBankName , String openBankNo , String openTicketUnit , String OpenBankProvince , String OpenBankCity , String compensationDay , int createUser , String createTime , int updateUser , String updateTime ) {
		this.businessmanVO = new BusinessManVO( sno , shopmSno , shopmName , shopmShortName , shopmType , shopmEmail , shopmConPhone , shopmContactName , shopmAddress , openBank , openBankName , openBankNo , openTicketUnit , OpenBankProvince , OpenBankCity , compensationDay , createUser , createTime , updateUser , updateTime );
	} 
	
	public BusinessManImpl(String shopmSno ,String shopmName ,String shopmShortName ,String shopmType ,String shopmEmail ,String shopmConPhone ,String shopmContactName ,String shopmAddress ,String openBank ,String openBankName ,String openBankNo ,String openTicketUnit ,String OpenBankProvince ,String OpenBankCity ,String compensationDay ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.businessmanVO = new BusinessManVO(shopmSno ,shopmName ,shopmShortName ,shopmType ,shopmEmail ,shopmConPhone ,shopmContactName ,shopmAddress ,openBank ,openBankName ,openBankNo ,openTicketUnit ,OpenBankProvince ,OpenBankCity ,compensationDay ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public BusinessManVO getBusinessManVO() {
		return this.businessmanVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.businessmanVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.businessmanVO.getSno();
 	}
 	/**
 	 * 获取商家编号的属性值.
 	 */
 	public  String   getShopmSno(){
 		return this.businessmanVO.getShopmSno();
 	}
 	/**
 	 * 获取商家名称 的属性值.
 	 */
 	public  String   getShopmName(){
 		return this.businessmanVO.getShopmName();
 	}
 	/**
 	 * 获取商家简称 的属性值.
 	 */
 	public  String   getShopmShortName(){
 		return this.businessmanVO.getShopmShortName();
 	}
 	/**
 	 * 获取商家类型 的属性值.
 	 */
 	public  String   getShopmType(){
 		return this.businessmanVO.getShopmType();
 	}
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getShopmEmail(){
 		return this.businessmanVO.getShopmEmail();
 	}
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getShopmConPhone(){
 		return this.businessmanVO.getShopmConPhone();
 	}
 	/**
 	 * 获取联系人名称的属性值.
 	 */
 	public  String   getShopmContactName(){
 		return this.businessmanVO.getShopmContactName();
 	}
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getShopmAddress(){
 		return this.businessmanVO.getShopmAddress();
 	}
 	/**
 	 * 获取开户行的属性值.
 	 */
 	public  String   getOpenBank(){
 		return this.businessmanVO.getOpenBank();
 	}
 	/**
 	 * 获取户名的属性值.
 	 */
 	public  String   getOpenBankName(){
 		return this.businessmanVO.getOpenBankName();
 	}
 	/**
 	 * 获取银行帐号的属性值.
 	 */
 	public  String   getOpenBankNo(){
 		return this.businessmanVO.getOpenBankNo();
 	}
 	/**
 	 * 获取开票单位的属性值.
 	 */
 	public  String   getOpenTicketUnit(){
 		return this.businessmanVO.getOpenTicketUnit();
 	}
 	/**
 	 * 获取开户所在省的属性值.
 	 */
 	public  String   getOpenBankProvince(){
 		return this.businessmanVO.getOpenBankProvince();
 	}
 	/**
 	 * 获取开户所在市的属性值.
 	 */
 	public  String   getOpenBankCity(){
 		return this.businessmanVO.getOpenBankCity();
 	}
 	/**
 	 * 获取理赔截止日期的属性值.
 	 */
 	public  String   getCompensationDay(){
 		return this.businessmanVO.getCompensationDay();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.businessmanVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.businessmanVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.businessmanVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.businessmanVO.getUpdateTime();
 	}

	@Override
	public String getCreateUserName() {
		// TODO Auto-generated method stub
		return this.businessmanVO.getCreateUserName();
	}

	@Override
	public String getUpdateUserName() {
		// TODO Auto-generated method stub
		return this.businessmanVO.getUpdateUserName();
	}

	@Override
	public String getGroupSno() {
		// TODO Auto-generated method stub
		return this.businessmanVO.getGroupSno();
	}
 
}
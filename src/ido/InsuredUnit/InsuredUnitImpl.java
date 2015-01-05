
package ido.InsuredUnit;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于投保单位的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredUnitImpl implements InsuredUnit {
	private InsuredUnitVO insuredunitVO = null;
	private static final long serialVersionUID = 1L;

	public InsuredUnitImpl(InsuredUnitVO insuredunitVO) {
		this.insuredunitVO = insuredunitVO;
	}

	public InsuredUnitImpl( int sno , String unitCode , String unitName , String contactName , String contactMobile , String contactEmail , int unitParentId , String unitState , String unitAddress , String unitRemark , int createUser , String createTime , int updateUser , String updateTime ) {
		this.insuredunitVO = new InsuredUnitVO( sno , unitCode , unitName , contactName , contactMobile , contactEmail , unitParentId , unitState , unitAddress , unitRemark , createUser , createTime , updateUser , updateTime );
	} 
	
	public InsuredUnitImpl(String unitCode ,String unitName ,String contactName ,String contactMobile ,String contactEmail ,int unitParentId ,String unitState ,String unitAddress ,String unitRemark ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.insuredunitVO = new InsuredUnitVO(unitCode ,unitName ,contactName ,contactMobile ,contactEmail ,unitParentId ,unitState ,unitAddress ,unitRemark ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public InsuredUnitVO getInsuredUnitVO() {
		return this.insuredunitVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.insuredunitVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.insuredunitVO.getSno();
 	}
 	/**
 	 * 获取编号的属性值.
 	 */
 	public  String   getUnitCode(){
 		return this.insuredunitVO.getUnitCode();
 	}
 	/**
 	 * 获取投保单位 的属性值.
 	 */
 	public  String   getUnitName(){
 		return this.insuredunitVO.getUnitName();
 	}
 	/**
 	 * 获取联系人的属性值.
 	 */
 	public  String   getContactName(){
 		return this.insuredunitVO.getContactName();
 	}
 	/**
 	 * 获取手机的属性值.
 	 */
 	public  String   getContactMobile(){
 		return this.insuredunitVO.getContactMobile();
 	}
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getContactEmail(){
 		return this.insuredunitVO.getContactEmail();
 	}
 	/**
 	 * 获取上级单位的属性值.
 	 */
 	public  int   getUnitParentId(){
 		return this.insuredunitVO.getUnitParentId();
 	}
 	/**
 	 * 获取状态的属性值.
 	 */
 	public  String   getUnitState(){
 		return this.insuredunitVO.getUnitState();
 	}
 	/**
 	 * 获取地址的属性值.
 	 */
 	public  String   getUnitAddress(){
 		return this.insuredunitVO.getUnitAddress();
 	}
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getUnitRemark(){
 		return this.insuredunitVO.getUnitRemark();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.insuredunitVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.insuredunitVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.insuredunitVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.insuredunitVO.getUpdateTime();
 	}
 
}

package ido.InsuredUser;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;

import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.cache.Cache;
import common.cache.CacheManager;
/**
 * 关于投保用户的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredUserImpl implements InsuredUser {
	private InsuredUserVO insureduserVO = null;
	private static final long serialVersionUID = 1L;

	public InsuredUserImpl(InsuredUserVO insureduserVO) {
		this.insureduserVO = insureduserVO;
	}

	public InsuredUserImpl( int sno , String iuserNo , String comId , String unitId , String iuserStatus , String iuserNumber , double leftMoney , double emergencyMoney , double frozenMoney , double hospitalMoney , double tesMoney , String iuserName , String iuserIsman , String iuserCardno , String iuserPhone , String iuserEmail , String iuserBirthday , String iuserRemark , int createUser , String createTime , int updateUser , String updateTime ) {
		this.insureduserVO = new InsuredUserVO( sno , iuserNo , comId , unitId , iuserStatus , iuserNumber , leftMoney , emergencyMoney , frozenMoney , hospitalMoney , tesMoney , iuserName , iuserIsman , iuserCardno , iuserPhone , iuserEmail , iuserBirthday , iuserRemark , createUser , createTime , updateUser , updateTime );
	} 
	
	public InsuredUserImpl(String iuserNo ,String comId ,String unitId ,String iuserStatus ,String iuserNumber ,double leftMoney ,double emergencyMoney ,double frozenMoney ,double hospitalMoney ,double tesMoney ,String iuserName ,String iuserIsman ,String iuserCardno ,String iuserPhone ,String iuserEmail ,String iuserBirthday ,String iuserRemark ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.insureduserVO = new InsuredUserVO(iuserNo ,comId ,unitId ,iuserStatus ,iuserNumber ,leftMoney ,emergencyMoney ,frozenMoney ,hospitalMoney ,tesMoney ,iuserName ,iuserIsman ,iuserCardno ,iuserPhone ,iuserEmail ,iuserBirthday ,iuserRemark ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public InsuredUserVO getInsuredUserVO() {
		return this.insureduserVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.insureduserVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.insureduserVO.getSno();
 	}
 	/**
 	 * 获取投保用户编号的属性值.
 	 */
 	public  String   getIuserNo(){
 		return this.insureduserVO.getIuserNo();
 	}
 	/**
 	 * 获取所投保险公司 的属性值.
 	 */
 	public  String   getComId(){
 		return this.insureduserVO.getComId();
 	}
 	/**
 	 * 获取所属投保单位 的属性值.
 	 */
 	public  String   getUnitId(){
 		return this.insureduserVO.getUnitId();
 	}
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public  String   getIuserStatus(){
 		return this.insureduserVO.getIuserStatus();
 	}
 	/**
 	 * 获取员工号的属性值.
 	 */
 	public  String   getIuserNumber(){
 		return this.insureduserVO.getIuserNumber();
 	}
 	/**
 	 * 获取余额的属性值.
 	 */
 	public  double   getLeftMoney(){
 		return this.insureduserVO.getLeftMoney();
 	}
 	/**
 	 * 获取门急诊额度的属性值.
 	 */
 	public  double   getEmergencyMoney(){
 		return this.insureduserVO.getEmergencyMoney();
 	}
 	/**
 	 * 获取冻结金额的属性值.
 	 */
 	public  double   getFrozenMoney(){
 		return this.insureduserVO.getFrozenMoney();
 	}
 	/**
 	 * 获取住院报销额度的属性值.
 	 */
 	public  double   getHospitalMoney(){
 		return this.insureduserVO.getHospitalMoney();
 	}
 	/**
 	 * 获取体检额度的属性值.
 	 */
 	public  double   getTesMoney(){
 		return this.insureduserVO.getTesMoney();
 	}
 	/**
 	 * 获取姓名的属性值.
 	 */
 	public  String   getIuserName(){
 		return this.insureduserVO.getIuserName();
 	}
 	/**
 	 * 获取性别的属性值.
 	 */
 	public  String   getIuserIsman(){
 		return this.insureduserVO.getIuserIsman();
 	}
 	/**
 	 * 获取证件号的属性值.
 	 */
 	public  String   getIuserCardno(){
 		return this.insureduserVO.getIuserCardno();
 	}
 	/**
 	 * 获取手机号的属性值.
 	 */
 	public  String   getIuserPhone(){
 		return this.insureduserVO.getIuserPhone();
 	}
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getIuserEmail(){
 		return this.insureduserVO.getIuserEmail();
 	}
 	/**
 	 * 获取生日的属性值.
 	 */
 	public  String   getIuserBirthday(){
 		return this.insureduserVO.getIuserBirthday();
 	}
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getIuserRemark(){
 		return this.insureduserVO.getIuserRemark();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.insureduserVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.insureduserVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.insureduserVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.insureduserVO.getUpdateTime();
 	}

	@Override
	public String getUnitName() {
		if(this.insureduserVO.getUnitId()!=null&&!"".equals(this.insureduserVO.getUnitId())){
			Cache cache_insured = CacheManager.getCacheInfoNotNull(AllSelectContants.INSUREDUNIT_DICT.getName());
			ParamSelect select_ownerCompany = (ParamSelect)cache_insured.getValue(); 
			return select_ownerCompany.getName(this.insureduserVO.getUnitId());
		}
		else
			return "";
	}
 
}
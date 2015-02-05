
package ido.InsuredUser;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于投保用户的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredUserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public InsuredUserVO() {

	}
	
	public InsuredUserVO( int sno , String iuserNo , String comId , String unitId , String iuserStatus , String iuserNumber , double leftMoney , double emergencyMoney , double frozenMoney , double hospitalMoney , double tesMoney , String iuserName , String iuserIsman , String iuserCardno , String iuserPhone , String iuserEmail , String iuserBirthday , String iuserRemark , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.iuserNo = iuserNo;
		 this.comId = comId;
		 this.unitId = unitId;
		 this.iuserStatus = iuserStatus;
		 this.iuserNumber = iuserNumber;
		 this.leftMoney = leftMoney;
		 this.emergencyMoney = emergencyMoney;
		 this.frozenMoney = frozenMoney;
		 this.hospitalMoney = hospitalMoney;
		 this.tesMoney = tesMoney;
		 this.iuserName = iuserName;
		 this.iuserIsman = iuserIsman;
		 this.iuserCardno = iuserCardno;
		 this.iuserPhone = iuserPhone;
		 this.iuserEmail = iuserEmail;
		 this.iuserBirthday = iuserBirthday;
		 this.iuserRemark = iuserRemark;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public InsuredUserVO(String iuserNo ,String comId ,String unitId ,String iuserStatus ,String iuserNumber ,double leftMoney ,double emergencyMoney ,double frozenMoney ,double hospitalMoney ,double tesMoney ,String iuserName ,String iuserIsman ,String iuserCardno ,String iuserPhone ,String iuserEmail ,String iuserBirthday ,String iuserRemark ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.iuserNo = iuserNo;
			 this.comId = comId;
			 this.unitId = unitId;
			 this.iuserStatus = iuserStatus;
			 this.iuserNumber = iuserNumber;
			 this.leftMoney = leftMoney;
			 this.emergencyMoney = emergencyMoney;
			 this.frozenMoney = frozenMoney;
			 this.hospitalMoney = hospitalMoney;
			 this.tesMoney = tesMoney;
			 this.iuserName = iuserName;
			 this.iuserIsman = iuserIsman;
			 this.iuserCardno = iuserCardno;
			 this.iuserPhone = iuserPhone;
			 this.iuserEmail = iuserEmail;
			 this.iuserBirthday = iuserBirthday;
			 this.iuserRemark = iuserRemark;
			 this.createUser = createUser;
			 this.createTime = createTime;
			 this.updateUser = updateUser;
			 this.updateTime = updateTime;
	}
	 
	private Integer sno; 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public Integer getSno(){
 		return sno;
 	}
 	
 	/**
 	 * 设置流水号的属性值.
 	 */
 	public void setSno(Integer sno){
 		this.sno = sno;
 	}
	private String iuserNo; 
 	/**
 	 * 获取投保用户编号的属性值.
 	 */
 	public String getIuserNo(){
 		return iuserNo;
 	}
 	
 	/**
 	 * 设置投保用户编号的属性值.
 	 */
 	public void setIuserNo(String iuserno){
 		this.iuserNo = iuserno;
 	}
	private String comId; 
 	/**
 	 * 获取所投保险公司 的属性值.
 	 */
 	public String getComId(){
 		return comId;
 	}
 	
 	/**
 	 * 设置所投保险公司 的属性值.
 	 */
 	public void setComId(String comid){
 		this.comId = comid;
 	}
	private String unitId; 
 	/**
 	 * 获取所属投保单位 的属性值.
 	 */
 	public String getUnitId(){
 		return unitId;
 	}
 	
 	/**
 	 * 设置所属投保单位 的属性值.
 	 */
 	public void setUnitId(String unitid){
 		this.unitId = unitid;
 	}
	private String iuserStatus; 
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public String getIuserStatus(){
 		return iuserStatus;
 	}
 	
 	/**
 	 * 设置状态 的属性值.
 	 */
 	public void setIuserStatus(String iuserstatus){
 		this.iuserStatus = iuserstatus;
 	}
	private String iuserNumber; 
 	/**
 	 * 获取员工号的属性值.
 	 */
 	public String getIuserNumber(){
 		return iuserNumber;
 	}
 	
 	/**
 	 * 设置员工号的属性值.
 	 */
 	public void setIuserNumber(String iusernumber){
 		this.iuserNumber = iusernumber;
 	}
	private double leftMoney; 
 	/**
 	 * 获取余额的属性值.
 	 */
 	public double getLeftMoney(){
 		return leftMoney;
 	}
 	
 	/**
 	 * 设置余额的属性值.
 	 */
 	public void setLeftMoney(double leftmoney){
 		this.leftMoney = leftmoney;
 	}
	private double emergencyMoney; 
 	/**
 	 * 获取门急诊额度的属性值.
 	 */
 	public double getEmergencyMoney(){
 		return emergencyMoney;
 	}
 	
 	/**
 	 * 设置门急诊额度的属性值.
 	 */
 	public void setEmergencyMoney(double emergencymoney){
 		this.emergencyMoney = emergencymoney;
 	}
	private double frozenMoney; 
 	/**
 	 * 获取冻结金额的属性值.
 	 */
 	public double getFrozenMoney(){
 		return frozenMoney;
 	}
 	
 	/**
 	 * 设置冻结金额的属性值.
 	 */
 	public void setFrozenMoney(double frozenmoney){
 		this.frozenMoney = frozenmoney;
 	}
	private double hospitalMoney; 
 	/**
 	 * 获取住院报销额度的属性值.
 	 */
 	public double getHospitalMoney(){
 		return hospitalMoney;
 	}
 	
 	/**
 	 * 设置住院报销额度的属性值.
 	 */
 	public void setHospitalMoney(double hospitalmoney){
 		this.hospitalMoney = hospitalmoney;
 	}
	private double tesMoney; 
 	/**
 	 * 获取体检额度的属性值.
 	 */
 	public double getTesMoney(){
 		return tesMoney;
 	}
 	
 	/**
 	 * 设置体检额度的属性值.
 	 */
 	public void setTesMoney(double tesmoney){
 		this.tesMoney = tesmoney;
 	}
	private String iuserName; 
 	/**
 	 * 获取姓名的属性值.
 	 */
 	public String getIuserName(){
 		return iuserName;
 	}
 	
 	/**
 	 * 设置姓名的属性值.
 	 */
 	public void setIuserName(String iusername){
 		this.iuserName = iusername;
 	}
	private String iuserIsman; 
 	/**
 	 * 获取性别的属性值.
 	 */
 	public String getIuserIsman(){
 		return iuserIsman;
 	}
 	
 	/**
 	 * 设置性别的属性值.
 	 */
 	public void setIuserIsman(String iuserisman){
 		this.iuserIsman = iuserisman;
 	}
	private String iuserCardno; 
 	/**
 	 * 获取证件号的属性值.
 	 */
 	public String getIuserCardno(){
 		return iuserCardno;
 	}
 	
 	/**
 	 * 设置证件号的属性值.
 	 */
 	public void setIuserCardno(String iusercardno){
 		this.iuserCardno = iusercardno;
 	}
	private String iuserPhone; 
 	/**
 	 * 获取手机号的属性值.
 	 */
 	public String getIuserPhone(){
 		return iuserPhone;
 	}
 	
 	/**
 	 * 设置手机号的属性值.
 	 */
 	public void setIuserPhone(String iuserphone){
 		this.iuserPhone = iuserphone;
 	}
	private String iuserEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getIuserEmail(){
 		return iuserEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setIuserEmail(String iuseremail){
 		this.iuserEmail = iuseremail;
 	}
	private String iuserBirthday; 
 	/**
 	 * 获取生日的属性值.
 	 */
 	public String getIuserBirthday(){
 		return iuserBirthday;
 	}
 	
 	/**
 	 * 设置生日的属性值.
 	 */
 	public void setIuserBirthday(String iuserbirthday){
 		this.iuserBirthday = iuserbirthday;
 	}
	private String iuserRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getIuserRemark(){
 		return iuserRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setIuserRemark(String iuserremark){
 		this.iuserRemark = iuserremark;
 	}
	private int createUser; 
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public int getCreateUser(){
 		return createUser;
 	}
 	
 	/**
 	 * 设置创建用户的属性值.
 	 */
 	public void setCreateUser(int createuser){
 		this.createUser = createuser;
 	}
	private String createTime; 
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public String getCreateTime(){
 		return createTime;
 	}
 	
 	/**
 	 * 设置创建时间的属性值.
 	 */
 	public void setCreateTime(String createtime){
 		this.createTime = createtime;
 	}
	private int updateUser; 
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public int getUpdateUser(){
 		return updateUser;
 	}
 	
 	/**
 	 * 设置更新用户的属性值.
 	 */
 	public void setUpdateUser(int updateuser){
 		this.updateUser = updateuser;
 	}
	private String updateTime; 
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public String getUpdateTime(){
 		return updateTime;
 	}
 	
 	/**
 	 * 设置更新时间的属性值.
 	 */
 	public void setUpdateTime(String updatetime){
 		this.updateTime = updatetime;
 	}

}

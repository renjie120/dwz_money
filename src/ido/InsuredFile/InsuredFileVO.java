
package ido.InsuredFile;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于投保单的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredFileVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String createUserName;
	private String updateUserName;
	private String insuredFileUnitName;
	public String getInsuredFileUnitName() {
		return insuredFileUnitName;
	}

	public void setInsuredFileUnitName(String insuredFileUnitName) {
		this.insuredFileUnitName = insuredFileUnitName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public InsuredFileVO() {

	}
	
	public InsuredFileVO( int sno , String insuredFileId , String insuredFileName , String insuredFileUnit , String insuredFileCompany , String insuredFileEmail , String insuredFileContact , String insuredFileConTel , String insuredFileConMobile , String insuredFileBegin , String insuredFileEnd , String insuredFileStatus , String insuredFileDuijie , String insuredFileDuijieFlag , String insuredFileRemark , double insuredFileTotal , double insuredFileEmerg , double insuredFileHospital , double insuredFileExam , String insuredFileConsumer , String insuredFileConsRule , int createUser , String createTime , int updateUser , String updateTime ) {
		 this.sno = sno;
		 this.insuredFileId = insuredFileId;
		 this.insuredFileName = insuredFileName;
		 this.insuredFileUnit = insuredFileUnit;
		 this.insuredFileCompany = insuredFileCompany;
		 this.insuredFileEmail = insuredFileEmail;
		 this.insuredFileContact = insuredFileContact;
		 this.insuredFileConTel = insuredFileConTel;
		 this.insuredFileConMobile = insuredFileConMobile;
		 this.insuredFileBegin = insuredFileBegin;
		 this.insuredFileEnd = insuredFileEnd;
		 this.insuredFileStatus = insuredFileStatus;
		 this.insuredFileDuijie = insuredFileDuijie;
		 this.insuredFileDuijieFlag = insuredFileDuijieFlag;
		 this.insuredFileRemark = insuredFileRemark;
		 this.insuredFileTotal = insuredFileTotal;
		 this.insuredFileEmerg = insuredFileEmerg;
		 this.insuredFileHospital = insuredFileHospital;
		 this.insuredFileExam = insuredFileExam;
		 this.insuredFileConsumer = insuredFileConsumer;
		 this.insuredFileConsRule = insuredFileConsRule;
		 this.createUser = createUser;
		 this.createTime = createTime;
		 this.updateUser = updateUser;
		 this.updateTime = updateTime;
	}
	
	public InsuredFileVO(String insuredFileId ,String insuredFileName ,String insuredFileUnit ,String insuredFileCompany ,String insuredFileEmail ,String insuredFileContact ,String insuredFileConTel ,String insuredFileConMobile ,String insuredFileBegin ,String insuredFileEnd ,String insuredFileStatus ,String insuredFileDuijie ,String insuredFileDuijieFlag ,String insuredFileRemark ,double insuredFileTotal ,double insuredFileEmerg ,double insuredFileHospital ,double insuredFileExam ,String insuredFileConsumer ,String insuredFileConsRule ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
			 this.insuredFileId = insuredFileId;
			 this.insuredFileName = insuredFileName;
			 this.insuredFileUnit = insuredFileUnit;
			 this.insuredFileCompany = insuredFileCompany;
			 this.insuredFileEmail = insuredFileEmail;
			 this.insuredFileContact = insuredFileContact;
			 this.insuredFileConTel = insuredFileConTel;
			 this.insuredFileConMobile = insuredFileConMobile;
			 this.insuredFileBegin = insuredFileBegin;
			 this.insuredFileEnd = insuredFileEnd;
			 this.insuredFileStatus = insuredFileStatus;
			 this.insuredFileDuijie = insuredFileDuijie;
			 this.insuredFileDuijieFlag = insuredFileDuijieFlag;
			 this.insuredFileRemark = insuredFileRemark;
			 this.insuredFileTotal = insuredFileTotal;
			 this.insuredFileEmerg = insuredFileEmerg;
			 this.insuredFileHospital = insuredFileHospital;
			 this.insuredFileExam = insuredFileExam;
			 this.insuredFileConsumer = insuredFileConsumer;
			 this.insuredFileConsRule = insuredFileConsRule;
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
	private String insuredFileId; 
 	/**
 	 * 获取投保单号 的属性值.
 	 */
 	public String getInsuredFileId(){
 		return insuredFileId;
 	}
 	
 	/**
 	 * 设置投保单号 的属性值.
 	 */
 	public void setInsuredFileId(String insuredfileid){
 		this.insuredFileId = insuredfileid;
 	}
	private String insuredFileName; 
 	/**
 	 * 获取保单名称的属性值.
 	 */
 	public String getInsuredFileName(){
 		return insuredFileName;
 	}
 	
 	/**
 	 * 设置保单名称的属性值.
 	 */
 	public void setInsuredFileName(String insuredfilename){
 		this.insuredFileName = insuredfilename;
 	}
	private String insuredFileUnit; 
 	/**
 	 * 获取投保单位的属性值.
 	 */
 	public String getInsuredFileUnit(){
 		return insuredFileUnit;
 	}
 	
 	/**
 	 * 设置投保单位的属性值.
 	 */
 	public void setInsuredFileUnit(String insuredfileunit){
 		this.insuredFileUnit = insuredfileunit;
 	}
	private String insuredFileCompany; 
 	/**
 	 * 获取保险公司的属性值.
 	 */
 	public String getInsuredFileCompany(){
 		return insuredFileCompany;
 	}
 	
 	/**
 	 * 设置保险公司的属性值.
 	 */
 	public void setInsuredFileCompany(String insuredfilecompany){
 		this.insuredFileCompany = insuredfilecompany;
 	}
	private String insuredFileEmail; 
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public String getInsuredFileEmail(){
 		return insuredFileEmail;
 	}
 	
 	/**
 	 * 设置邮箱的属性值.
 	 */
 	public void setInsuredFileEmail(String insuredfileemail){
 		this.insuredFileEmail = insuredfileemail;
 	}
	private String insuredFileContact; 
 	/**
 	 * 获取联系人的属性值.
 	 */
 	public String getInsuredFileContact(){
 		return insuredFileContact;
 	}
 	
 	/**
 	 * 设置联系人的属性值.
 	 */
 	public void setInsuredFileContact(String insuredfilecontact){
 		this.insuredFileContact = insuredfilecontact;
 	}
	private String insuredFileConTel; 
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public String getInsuredFileConTel(){
 		return insuredFileConTel;
 	}
 	
 	/**
 	 * 设置联系电话的属性值.
 	 */
 	public void setInsuredFileConTel(String insuredfilecontel){
 		this.insuredFileConTel = insuredfilecontel;
 	}
	private String insuredFileConMobile; 
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public String getInsuredFileConMobile(){
 		return insuredFileConMobile;
 	}
 	
 	/**
 	 * 设置联系人手机的属性值.
 	 */
 	public void setInsuredFileConMobile(String insuredfileconmobile){
 		this.insuredFileConMobile = insuredfileconmobile;
 	}
	private String insuredFileBegin; 
 	/**
 	 * 获取投保日期的属性值.
 	 */
 	public String getInsuredFileBegin(){
 		return insuredFileBegin;
 	}
 	
 	/**
 	 * 设置投保日期的属性值.
 	 */
 	public void setInsuredFileBegin(String insuredfilebegin){
 		this.insuredFileBegin = insuredfilebegin;
 	}
	private String insuredFileEnd; 
 	/**
 	 * 获取到期日期的属性值.
 	 */
 	public String getInsuredFileEnd(){
 		return insuredFileEnd;
 	}
 	
 	/**
 	 * 设置到期日期的属性值.
 	 */
 	public void setInsuredFileEnd(String insuredfileend){
 		this.insuredFileEnd = insuredfileend;
 	}
	private String insuredFileStatus; 
 	/**
 	 * 获取状态的属性值.
 	 */
 	public String getInsuredFileStatus(){
 		return insuredFileStatus;
 	}
 	
 	/**
 	 * 设置状态的属性值.
 	 */
 	public void setInsuredFileStatus(String insuredfilestatus){
 		this.insuredFileStatus = insuredfilestatus;
 	}
	private String insuredFileDuijie; 
 	/**
 	 * 获取系统对接方式的属性值.
 	 */
 	public String getInsuredFileDuijie(){
 		return insuredFileDuijie;
 	}
 	
 	/**
 	 * 设置系统对接方式的属性值.
 	 */
 	public void setInsuredFileDuijie(String insuredfileduijie){
 		this.insuredFileDuijie = insuredfileduijie;
 	}
	private String insuredFileDuijieFlag; 
 	/**
 	 * 获取系统对接开启的属性值.
 	 */
 	public String getInsuredFileDuijieFlag(){
 		return insuredFileDuijieFlag;
 	}
 	
 	/**
 	 * 设置系统对接开启的属性值.
 	 */
 	public void setInsuredFileDuijieFlag(String insuredfileduijieflag){
 		this.insuredFileDuijieFlag = insuredfileduijieflag;
 	}
	private String insuredFileRemark; 
 	/**
 	 * 获取备注的属性值.
 	 */
 	public String getInsuredFileRemark(){
 		return insuredFileRemark;
 	}
 	
 	/**
 	 * 设置备注的属性值.
 	 */
 	public void setInsuredFileRemark(String insuredfileremark){
 		this.insuredFileRemark = insuredfileremark;
 	}
	private double insuredFileTotal; 
 	/**
 	 * 获取投保总金额的属性值.
 	 */
 	public double getInsuredFileTotal(){
 		return insuredFileTotal;
 	}
 	
 	/**
 	 * 设置投保总金额的属性值.
 	 */
 	public void setInsuredFileTotal(double insuredfiletotal){
 		this.insuredFileTotal = insuredfiletotal;
 	}
	private double insuredFileEmerg; 
 	/**
 	 * 获取门急诊额度的属性值.
 	 */
 	public double getInsuredFileEmerg(){
 		return insuredFileEmerg;
 	}
 	
 	/**
 	 * 设置门急诊额度的属性值.
 	 */
 	public void setInsuredFileEmerg(double insuredfileemerg){
 		this.insuredFileEmerg = insuredfileemerg;
 	}
	private double insuredFileHospital; 
 	/**
 	 * 获取住院额度的属性值.
 	 */
 	public double getInsuredFileHospital(){
 		return insuredFileHospital;
 	}
 	
 	/**
 	 * 设置住院额度的属性值.
 	 */
 	public void setInsuredFileHospital(double insuredfilehospital){
 		this.insuredFileHospital = insuredfilehospital;
 	}
	private double insuredFileExam; 
 	/**
 	 * 获取体检额度的属性值.
 	 */
 	public double getInsuredFileExam(){
 		return insuredFileExam;
 	}
 	
 	/**
 	 * 设置体检额度的属性值.
 	 */
 	public void setInsuredFileExam(double insuredfileexam){
 		this.insuredFileExam = insuredfileexam;
 	}
	private String insuredFileConsumer; 
 	/**
 	 * 获取消费商家控制的属性值.
 	 */
 	public String getInsuredFileConsumer(){
 		return insuredFileConsumer;
 	}
 	
 	/**
 	 * 设置消费商家控制的属性值.
 	 */
 	public void setInsuredFileConsumer(String insuredfileconsumer){
 		this.insuredFileConsumer = insuredfileconsumer;
 	}
	private String insuredFileConsRule; 
 	/**
 	 * 获取消费规则的属性值.
 	 */
 	public String getInsuredFileConsRule(){
 		return insuredFileConsRule;
 	}
 	
 	/**
 	 * 设置消费规则的属性值.
 	 */
 	public void setInsuredFileConsRule(String insuredfileconsrule){
 		this.insuredFileConsRule = insuredfileconsrule;
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

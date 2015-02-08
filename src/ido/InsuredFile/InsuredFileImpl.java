
package ido.InsuredFile;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于投保单的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class InsuredFileImpl implements InsuredFile {
	private InsuredFileVO insuredfileVO = null;
	private static final long serialVersionUID = 1L;

	public InsuredFileImpl(InsuredFileVO insuredfileVO) {
		this.insuredfileVO = insuredfileVO;
	}

	public InsuredFileImpl( int sno , String insuredFileId , String insuredFileName , String insuredFileUnit , String insuredFileCompany , String insuredFileEmail , String insuredFileContact , String insuredFileConTel , String insuredFileConMobile , String insuredFileBegin , String insuredFileEnd , String insuredFileStatus , String insuredFileDuijie , String insuredFileDuijieFlag , String insuredFileRemark , double insuredFileTotal , double insuredFileEmerg , double insuredFileHospital , double insuredFileExam , String insuredFileConsumer , String insuredFileConsRule , int createUser , String createTime , int updateUser , String updateTime ) {
		this.insuredfileVO = new InsuredFileVO( sno , insuredFileId , insuredFileName , insuredFileUnit , insuredFileCompany , insuredFileEmail , insuredFileContact , insuredFileConTel , insuredFileConMobile , insuredFileBegin , insuredFileEnd , insuredFileStatus , insuredFileDuijie , insuredFileDuijieFlag , insuredFileRemark , insuredFileTotal , insuredFileEmerg , insuredFileHospital , insuredFileExam , insuredFileConsumer , insuredFileConsRule , createUser , createTime , updateUser , updateTime );
	} 
	
	public InsuredFileImpl(String insuredFileId ,String insuredFileName ,String insuredFileUnit ,String insuredFileCompany ,String insuredFileEmail ,String insuredFileContact ,String insuredFileConTel ,String insuredFileConMobile ,String insuredFileBegin ,String insuredFileEnd ,String insuredFileStatus ,String insuredFileDuijie ,String insuredFileDuijieFlag ,String insuredFileRemark ,double insuredFileTotal ,double insuredFileEmerg ,double insuredFileHospital ,double insuredFileExam ,String insuredFileConsumer ,String insuredFileConsRule ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.insuredfileVO = new InsuredFileVO(insuredFileId ,insuredFileName ,insuredFileUnit ,insuredFileCompany ,insuredFileEmail ,insuredFileContact ,insuredFileConTel ,insuredFileConMobile ,insuredFileBegin ,insuredFileEnd ,insuredFileStatus ,insuredFileDuijie ,insuredFileDuijieFlag ,insuredFileRemark ,insuredFileTotal ,insuredFileEmerg ,insuredFileHospital ,insuredFileExam ,insuredFileConsumer ,insuredFileConsRule ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public InsuredFileVO getInsuredFileVO() {
		return this.insuredfileVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.insuredfileVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.insuredfileVO.getSno();
 	}
 	/**
 	 * 获取投保单号 的属性值.
 	 */
 	public  String   getInsuredFileId(){
 		return this.insuredfileVO.getInsuredFileId();
 	}
 	/**
 	 * 获取保单名称的属性值.
 	 */
 	public  String   getInsuredFileName(){
 		return this.insuredfileVO.getInsuredFileName();
 	}
 	/**
 	 * 获取投保单位的属性值.
 	 */
 	public  String   getInsuredFileUnit(){
 		return this.insuredfileVO.getInsuredFileUnit();
 	}
 	/**
 	 * 获取保险公司的属性值.
 	 */
 	public  String   getInsuredFileCompany(){
 		return this.insuredfileVO.getInsuredFileCompany();
 	}
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getInsuredFileEmail(){
 		return this.insuredfileVO.getInsuredFileEmail();
 	}
 	/**
 	 * 获取联系人的属性值.
 	 */
 	public  String   getInsuredFileContact(){
 		return this.insuredfileVO.getInsuredFileContact();
 	}
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public  String   getInsuredFileConTel(){
 		return this.insuredfileVO.getInsuredFileConTel();
 	}
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getInsuredFileConMobile(){
 		return this.insuredfileVO.getInsuredFileConMobile();
 	}
 	/**
 	 * 获取投保日期的属性值.
 	 */
 	public  String   getInsuredFileBegin(){
 		return this.insuredfileVO.getInsuredFileBegin();
 	}
 	/**
 	 * 获取到期日期的属性值.
 	 */
 	public  String   getInsuredFileEnd(){
 		return this.insuredfileVO.getInsuredFileEnd();
 	}
 	/**
 	 * 获取状态的属性值.
 	 */
 	public  String   getInsuredFileStatus(){
 		return this.insuredfileVO.getInsuredFileStatus();
 	}
 	/**
 	 * 获取系统对接方式的属性值.
 	 */
 	public  String   getInsuredFileDuijie(){
 		return this.insuredfileVO.getInsuredFileDuijie();
 	}
 	/**
 	 * 获取系统对接开启的属性值.
 	 */
 	public  String   getInsuredFileDuijieFlag(){
 		return this.insuredfileVO.getInsuredFileDuijieFlag();
 	}
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getInsuredFileRemark(){
 		return this.insuredfileVO.getInsuredFileRemark();
 	}
 	/**
 	 * 获取投保总金额的属性值.
 	 */
 	public  double   getInsuredFileTotal(){
 		return this.insuredfileVO.getInsuredFileTotal();
 	}
 	/**
 	 * 获取门急诊额度的属性值.
 	 */
 	public  double   getInsuredFileEmerg(){
 		return this.insuredfileVO.getInsuredFileEmerg();
 	}
 	/**
 	 * 获取住院额度的属性值.
 	 */
 	public  double   getInsuredFileHospital(){
 		return this.insuredfileVO.getInsuredFileHospital();
 	}
 	/**
 	 * 获取体检额度的属性值.
 	 */
 	public  double   getInsuredFileExam(){
 		return this.insuredfileVO.getInsuredFileExam();
 	}
 	/**
 	 * 获取消费商家控制的属性值.
 	 */
 	public  String   getInsuredFileConsumer(){
 		return this.insuredfileVO.getInsuredFileConsumer();
 	}
 	/**
 	 * 获取消费规则的属性值.
 	 */
 	public  String   getInsuredFileConsRule(){
 		return this.insuredfileVO.getInsuredFileConsRule();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.insuredfileVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.insuredfileVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.insuredfileVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.insuredfileVO.getUpdateTime();
 	}

	@Override
	public String getCreateUserName() {
		// TODO Auto-generated method stub
		return this.insuredfileVO.getCreateUserName();
	}

	@Override
	public String getUpdateUserName() {
		// TODO Auto-generated method stub
		return this.insuredfileVO.getUpdateUserName();
	}
 
}
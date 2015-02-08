
package ido.InsuredFile;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于投保单的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface InsuredFile extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取投保单号 的属性值.
 	 */
 	public  String   getInsuredFileId();
 	/**
 	 * 获取保单名称的属性值.
 	 */
 	public  String   getInsuredFileName();
 	/**
 	 * 获取投保单位的属性值.
 	 */
 	public  String   getInsuredFileUnit();
 	/**
 	 * 获取保险公司的属性值.
 	 */
 	public  String   getInsuredFileCompany();
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getInsuredFileEmail();
 	/**
 	 * 获取联系人的属性值.
 	 */
 	public  String   getInsuredFileContact();
 	/**
 	 * 获取联系电话的属性值.
 	 */
 	public  String   getInsuredFileConTel();
 	/**
 	 * 获取联系人手机的属性值.
 	 */
 	public  String   getInsuredFileConMobile();
 	/**
 	 * 获取投保日期的属性值.
 	 */
 	public  String   getInsuredFileBegin();
 	/**
 	 * 获取到期日期的属性值.
 	 */
 	public  String   getInsuredFileEnd();
 	/**
 	 * 获取状态的属性值.
 	 */
 	public  String   getInsuredFileStatus();
 	/**
 	 * 获取系统对接方式的属性值.
 	 */
 	public  String   getInsuredFileDuijie();
 	/**
 	 * 获取系统对接开启的属性值.
 	 */
 	public  String   getInsuredFileDuijieFlag();
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getInsuredFileRemark();
 	/**
 	 * 获取投保总金额的属性值.
 	 */
 	public  double   getInsuredFileTotal();
 	/**
 	 * 获取门急诊额度的属性值.
 	 */
 	public  double   getInsuredFileEmerg();
 	/**
 	 * 获取住院额度的属性值.
 	 */
 	public  double   getInsuredFileHospital();
 	/**
 	 * 获取体检额度的属性值.
 	 */
 	public  double   getInsuredFileExam();
 	/**
 	 * 获取消费商家控制的属性值.
 	 */
 	public  String   getInsuredFileConsumer();
 	/**
 	 * 获取消费规则的属性值.
 	 */
 	public  String   getInsuredFileConsRule();
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser();
 	public  String   getCreateUserName();
 	public  String   getUpdateUserName();
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

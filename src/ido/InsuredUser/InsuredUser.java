
package ido.InsuredUser;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于投保用户的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface InsuredUser extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取投保用户编号的属性值.
 	 */
 	public  String   getIuserNo();
 	/**
 	 * 获取所投保险公司 的属性值.
 	 */
 	public  String   getComId();
 	/**
 	 * 获取所属投保单位 的属性值.
 	 */
 	public  String   getUnitId();
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public  String   getIuserStatus();
 	/**
 	 * 获取员工号的属性值.
 	 */
 	public  String   getIuserNumber();
 	/**
 	 * 获取余额的属性值.
 	 */
 	public  double   getLeftMoney();
 	/**
 	 * 获取门急诊额度的属性值.
 	 */
 	public  double   getEmergencyMoney();
 	/**
 	 * 获取冻结金额的属性值.
 	 */
 	public  double   getFrozenMoney();
 	/**
 	 * 获取住院报销额度的属性值.
 	 */
 	public  double   getHospitalMoney();
 	/**
 	 * 获取体检额度的属性值.
 	 */
 	public  double   getTesMoney();
 	/**
 	 * 获取姓名的属性值.
 	 */
 	public  String   getIuserName();
 	/**
 	 * 获取性别的属性值.
 	 */
 	public  String   getIuserIsman();
 	/**
 	 * 获取证件号的属性值.
 	 */
 	public  String   getIuserCardno();
 	/**
 	 * 获取手机号的属性值.
 	 */
 	public  String   getIuserPhone();
 	/**
 	 * 获取邮箱的属性值.
 	 */
 	public  String   getIuserEmail();
 	/**
 	 * 获取生日的属性值.
 	 */
 	public  String   getIuserBirthday();
 	/**
 	 * 获取备注的属性值.
 	 */
 	public  String   getIuserRemark();
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser();
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

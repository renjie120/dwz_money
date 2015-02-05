
package money.sequence;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于系统序列号的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface SystemSequence extends BusinessObject {
	String SEQ_COMPANY="insured_company_seq";
	String SEQ_UNIT="insured_unit_seq";
	String SEQ_USER="insured_user_seq"; 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取序列代码 的属性值.
 	 */
 	public  String   getSequenceCode();
 	/**
 	 * 获取序列名称的属性值.
 	 */
 	public  String   getSequenceName();
 	/**
 	 * 获取序列值的属性值.
 	 */
 	public  Integer   getSequenceContent();
}

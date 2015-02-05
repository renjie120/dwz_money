
package money.sequence;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于系统序列号的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class SystemSequenceImpl implements SystemSequence {
	private SystemSequenceVO systemsequenceVO = null;
	private static final long serialVersionUID = 1L;

	public SystemSequenceImpl(SystemSequenceVO systemsequenceVO) {
		this.systemsequenceVO = systemsequenceVO;
	}

	public SystemSequenceImpl( int sno , String sequenceCode , String sequenceName , Integer sequenceContent ) {
		this.systemsequenceVO = new SystemSequenceVO( sno , sequenceCode , sequenceName , sequenceContent );
	} 
	
	public SystemSequenceImpl(String sequenceCode ,String sequenceName ,Integer sequenceContent ) {
		this.systemsequenceVO = new SystemSequenceVO(sequenceCode ,sequenceName ,sequenceContent );
	} 

	public SystemSequenceVO getSystemSequenceVO() {
		return this.systemsequenceVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.systemsequenceVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.systemsequenceVO.getSno();
 	}
 	/**
 	 * 获取序列代码 的属性值.
 	 */
 	public  String   getSequenceCode(){
 		return this.systemsequenceVO.getSequenceCode();
 	}
 	/**
 	 * 获取序列名称的属性值.
 	 */
 	public  String   getSequenceName(){
 		return this.systemsequenceVO.getSequenceName();
 	}
 	/**
 	 * 获取序列值的属性值.
 	 */
 	public  Integer   getSequenceContent(){
 		return this.systemsequenceVO.getSequenceContent();
 	}
 
}
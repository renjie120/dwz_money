
package money.sequence;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于系统序列号的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class SystemSequenceVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public SystemSequenceVO() {

	}
	
	public SystemSequenceVO( int sno , String sequenceCode , String sequenceName , Integer sequenceContent ) {
		 this.sno = sno;
		 this.sequenceCode = sequenceCode;
		 this.sequenceName = sequenceName;
		 this.sequenceContent = sequenceContent;
	}
	
	public SystemSequenceVO(String sequenceCode ,String sequenceName ,Integer sequenceContent ) {
			 this.sequenceCode = sequenceCode;
			 this.sequenceName = sequenceName;
			 this.sequenceContent = sequenceContent;
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
	private String sequenceCode; 
 	/**
 	 * 获取序列代码 的属性值.
 	 */
 	public String getSequenceCode(){
 		return sequenceCode;
 	}
 	
 	/**
 	 * 设置序列代码 的属性值.
 	 */
 	public void setSequenceCode(String sequencecode){
 		this.sequenceCode = sequencecode;
 	}
	private String sequenceName; 
 	/**
 	 * 获取序列名称的属性值.
 	 */
 	public String getSequenceName(){
 		return sequenceName;
 	}
 	
 	/**
 	 * 设置序列名称的属性值.
 	 */
 	public void setSequenceName(String sequencename){
 		this.sequenceName = sequencename;
 	}
	private Integer sequenceContent; 
 	/**
 	 * 获取序列值的属性值.
 	 */
 	public Integer getSequenceContent(){
 		return sequenceContent;
 	}
 	
 	/**
 	 * 设置序列值的属性值.
 	 */
 	public void setSequenceContent(Integer sequencecontent){
 		this.sequenceContent = sequencecontent;
 	}

}

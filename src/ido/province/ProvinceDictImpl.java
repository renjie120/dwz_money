
package ido.province;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于省份字典表的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ProvinceDictImpl implements ProvinceDict {
	private ProvinceDictVO provincedictVO = null;
	private static final long serialVersionUID = 1L;

	public ProvinceDictImpl(ProvinceDictVO provincedictVO) {
		this.provincedictVO = provincedictVO;
	}

	public ProvinceDictImpl( int sno , String provName , String provType , String provState ) {
		this.provincedictVO = new ProvinceDictVO( sno , provName , provType , provState );
	} 
	
	public ProvinceDictImpl(String provName ,String provType ,String provState ) {
		this.provincedictVO = new ProvinceDictVO(provName ,provType ,provState );
	} 

	public ProvinceDictVO getProvinceDictVO() {
		return this.provincedictVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.provincedictVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.provincedictVO.getSno();
 	}
 	/**
 	 * 获取省份的属性值.
 	 */
 	public  String   getProvName(){
 		return this.provincedictVO.getProvName();
 	}
 	/**
 	 * 获取类型的属性值.
 	 */
 	public  String   getProvType(){
 		return this.provincedictVO.getProvType();
 	}
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public  String   getProvState(){
 		return this.provincedictVO.getProvState();
 	}
 
}
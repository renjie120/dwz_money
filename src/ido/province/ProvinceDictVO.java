
package ido.province;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于省份字典表的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class ProvinceDictVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	
	public ProvinceDictVO() {

	}
	
	public ProvinceDictVO( int sno , String provName , String provType , String provState ) {
		 this.sno = sno;
		 this.provName = provName;
		 this.provType = provType;
		 this.provState = provState;
	}
	
	public ProvinceDictVO(String provName ,String provType ,String provState ) {
			 this.provName = provName;
			 this.provType = provType;
			 this.provState = provState;
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
	private String provName; 
 	/**
 	 * 获取省份的属性值.
 	 */
 	public String getProvName(){
 		return provName;
 	}
 	
 	/**
 	 * 设置省份的属性值.
 	 */
 	public void setProvName(String provname){
 		this.provName = provname;
 	}
	private String provType; 
 	/**
 	 * 获取类型的属性值.
 	 */
 	public String getProvType(){
 		return provType;
 	}
 	
 	/**
 	 * 设置类型的属性值.
 	 */
 	public void setProvType(String provtype){
 		this.provType = provtype;
 	}
	private String provState; 
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public String getProvState(){
 		return provState;
 	}
 	
 	/**
 	 * 设置状态 的属性值.
 	 */
 	public void setProvState(String provstate){
 		this.provState = provstate;
 	}

	@Override
	public String getOptionId() {
		return this.getSno()+"";
	}

	@Override
	public String getOptionName() {
		return this.getProvName()+"";
	}
}

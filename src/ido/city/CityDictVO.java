
package ido.city;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
import common.base.SelectAble;
/**
 * 关于城市字典表的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class CityDictVO implements Serializable,SelectAble {
	private static final long serialVersionUID = 1L;
	
	public CityDictVO() {

	}
	
	public CityDictVO( int sno , String cityName , String provId , String cityState ) {
		 this.sno = sno;
		 this.cityName = cityName;
		 this.provId = provId;
		 this.cityState = cityState;
	}
	
	public CityDictVO(String cityName ,String provId ,String cityState ) {
			 this.cityName = cityName;
			 this.provId = provId;
			 this.cityState = cityState;
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
	private String cityName; 
 	/**
 	 * 获取名称的属性值.
 	 */
 	public String getCityName(){
 		return cityName;
 	}
 	
 	/**
 	 * 设置名称的属性值.
 	 */
 	public void setCityName(String cityname){
 		this.cityName = cityname;
 	}
	private String provId; 
 	/**
 	 * 获取省份的属性值.
 	 */
 	public String getProvId(){
 		return provId;
 	}
 	
 	/**
 	 * 设置省份的属性值.
 	 */
 	public void setProvId(String provid){
 		this.provId = provid;
 	}
	private String cityState; 
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public String getCityState(){
 		return cityState;
 	}
 	
 	/**
 	 * 设置状态 的属性值.
 	 */
 	public void setCityState(String citystate){
 		this.cityState = citystate;
 	}

	@Override
	public String getOptionId() {
		return this.getSno()+"";
	}

	@Override
	public String getOptionName() {
		return this.getCityName()+"";
	}
}

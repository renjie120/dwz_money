
package ido.city;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于城市字典表的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class CityDictImpl implements CityDict {
	private CityDictVO citydictVO = null;
	private static final long serialVersionUID = 1L;

	public CityDictImpl(CityDictVO citydictVO) {
		this.citydictVO = citydictVO;
	}

	public CityDictImpl( int sno , String cityName , String provId , String cityState ) {
		this.citydictVO = new CityDictVO( sno , cityName , provId , cityState );
	} 
	
	public CityDictImpl(String cityName ,String provId ,String cityState ) {
		this.citydictVO = new CityDictVO(cityName ,provId ,cityState );
	} 

	public CityDictVO getCityDictVO() {
		return this.citydictVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.citydictVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.citydictVO.getSno();
 	}
 	/**
 	 * 获取名称的属性值.
 	 */
 	public  String   getCityName(){
 		return this.citydictVO.getCityName();
 	}
 	/**
 	 * 获取省份的属性值.
 	 */
 	public  String   getProvId(){
 		return this.citydictVO.getProvId();
 	}
 	/**
 	 * 获取状态 的属性值.
 	 */
 	public  String   getCityState(){
 		return this.citydictVO.getCityState();
 	}
 
}
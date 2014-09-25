
package money.yaopin;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于药品销售信息的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class YaopinInfoImpl implements YaopinInfo {
	private YaopinInfoVO yaopininfoVO = null;
	private static final long serialVersionUID = 1L;

	public YaopinInfoImpl(YaopinInfoVO yaopininfoVO) {
		this.yaopininfoVO = yaopininfoVO;
	}

	public YaopinInfoImpl( int sno , String companyName , String engName , String chnName , String chemStruct , String productName , String customer , double num , double price , String chundu , Date saleTime , String cas , String connect ) {
		this.yaopininfoVO = new YaopinInfoVO( sno , companyName , engName , chnName , chemStruct , productName , customer , num , price , chundu , saleTime , cas , connect );
	} 
	
	public YaopinInfoImpl(String companyName ,String engName ,String chnName ,String chemStruct ,String productName ,String customer ,double num ,double price ,String chundu ,Date saleTime ,String cas ,String connect ) {
		this.yaopininfoVO = new YaopinInfoVO(companyName ,engName ,chnName ,chemStruct ,productName ,customer ,num ,price ,chundu ,saleTime ,cas ,connect );
	} 

	public YaopinInfoVO getYaopinInfoVO() {
		return this.yaopininfoVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.yaopininfoVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.yaopininfoVO.getSno();
 	}
 	/**
 	 * 获取业务实体的属性值.
 	 */
 	public  String   getCompanyName(){
 		return this.yaopininfoVO.getCompanyName();
 	}
 	/**
 	 * 获取物料名称（英文）的属性值.
 	 */
 	public  String   getEngName(){
 		return this.yaopininfoVO.getEngName();
 	}
 	/**
 	 * 获取物料名称（中文）的属性值.
 	 */
 	public  String   getChnName(){
 		return this.yaopininfoVO.getChnName();
 	}
 	/**
 	 * 获取结构式的属性值.
 	 */
 	public  String   getChemStruct(){
 		return this.yaopininfoVO.getChemStruct();
 	}
 	/**
 	 * 获取生产厂家的属性值.
 	 */
 	public  String   getProductName(){
 		return this.yaopininfoVO.getProductName();
 	}
 	/**
 	 * 获取厂家销售客户的属性值.
 	 */
 	public  String   getCustomer(){
 		return this.yaopininfoVO.getCustomer();
 	}
 	/**
 	 * 获取数量的属性值.
 	 */
 	public  double   getNum(){
 		return this.yaopininfoVO.getNum();
 	}
 	/**
 	 * 获取销售价格的属性值.
 	 */
 	public  double   getPrice(){
 		return this.yaopininfoVO.getPrice();
 	}
 	/**
 	 * 获取纯度的属性值.
 	 */
 	public  String   getChundu(){
 		return this.yaopininfoVO.getChundu();
 	}
 	/**
 	 * 获取销售时间的属性值.
 	 */
 	public  Date   getSaleTime(){
 		return this.yaopininfoVO.getSaleTime();
 	}
 	/**
 	 * 获取CAS的属性值.
 	 */
 	public  String   getCas(){
 		return this.yaopininfoVO.getCas();
 	}
 	/**
 	 * 获取厂家联系人的属性值.
 	 */
 	public  String   getConnect(){
 		return this.yaopininfoVO.getConnect();
 	}
 
}

package money.yaopin;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于药品销售信息的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class YaopinInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public YaopinInfoVO() {

	}
	
	public YaopinInfoVO( int sno , String companyName , String engName , String chnName , String chemStruct , String productName , String customer , Double num , Double price , String chundu , Date saleTime , String cas , String connect ) {
		 this.sno = sno;
		 this.companyName = companyName;
		 this.engName = engName;
		 this.chnName = chnName;
		 this.chemStruct = chemStruct;
		 this.productName = productName;
		 this.customer = customer;
		 this.num = num;
		 this.price = price;
		 this.chundu = chundu;
		 this.saleTime = saleTime;
		 this.cas = cas;
		 this.connect = connect;
	}
	
	public YaopinInfoVO(String companyName ,String engName ,String chnName ,String chemStruct ,String productName ,String customer ,Double num ,Double price ,String chundu ,Date saleTime ,String cas ,String connect ) {
			 this.companyName = companyName;
			 this.engName = engName;
			 this.chnName = chnName;
			 this.chemStruct = chemStruct;
			 this.productName = productName;
			 this.customer = customer;
			 this.num = num;
			 this.price = price;
			 this.chundu = chundu;
			 this.saleTime = saleTime;
			 this.cas = cas;
			 this.connect = connect;
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
	private String companyName; 
 	/**
 	 * 获取业务实体的属性值.
 	 */
 	public String getCompanyName(){
 		return companyName;
 	}
 	
 	/**
 	 * 设置业务实体的属性值.
 	 */
 	public void setCompanyName(String companyname){
 		this.companyName = companyname;
 	}
	private String engName; 
 	/**
 	 * 获取物料名称（英文）的属性值.
 	 */
 	public String getEngName(){
 		return engName;
 	}
 	
 	/**
 	 * 设置物料名称（英文）的属性值.
 	 */
 	public void setEngName(String engname){
 		this.engName = engname;
 	}
	private String chnName; 
 	/**
 	 * 获取物料名称（中文）的属性值.
 	 */
 	public String getChnName(){
 		return chnName;
 	}
 	
 	/**
 	 * 设置物料名称（中文）的属性值.
 	 */
 	public void setChnName(String chnname){
 		this.chnName = chnname;
 	}
	private String chemStruct; 
 	/**
 	 * 获取结构式的属性值.
 	 */
 	public String getChemStruct(){
 		return chemStruct;
 	}
 	
 	/**
 	 * 设置结构式的属性值.
 	 */
 	public void setChemStruct(String chemstruct){
 		this.chemStruct = chemstruct;
 	}
	private String productName; 
 	/**
 	 * 获取生产厂家的属性值.
 	 */
 	public String getProductName(){
 		return productName;
 	}
 	
 	/**
 	 * 设置生产厂家的属性值.
 	 */
 	public void setProductName(String productname){
 		this.productName = productname;
 	}
	private String customer; 
 	/**
 	 * 获取厂家销售客户的属性值.
 	 */
 	public String getCustomer(){
 		return customer;
 	}
 	
 	/**
 	 * 设置厂家销售客户的属性值.
 	 */
 	public void setCustomer(String customer){
 		this.customer = customer;
 	}
	private Double num; 
 	/**
 	 * 获取数量的属性值.
 	 */
 	public Double getNum(){
 		return num;
 	}
 	
 	/**
 	 * 设置数量的属性值.
 	 */
 	public void setNum(Double num){
 		this.num = num;
 	}
	private Double price; 
 	/**
 	 * 获取销售价格的属性值.
 	 */
 	public Double getPrice(){
 		return price;
 	}
 	
 	/**
 	 * 设置销售价格的属性值.
 	 */
 	public void setPrice(Double price){
 		this.price = price;
 	}
	private String chundu; 
 	/**
 	 * 获取纯度的属性值.
 	 */
 	public String getChundu(){
 		return chundu;
 	}
 	
 	/**
 	 * 设置纯度的属性值.
 	 */
 	public void setChundu(String chundu){
 		this.chundu = chundu;
 	}
	private Date saleTime; 
 	/**
 	 * 获取销售时间的属性值.
 	 */
 	public Date getSaleTime(){
 		return saleTime;
 	}
 	
 	/**
 	 * 设置销售时间的属性值.
 	 */
 	public void setSaleTime(Date saletime){
 		this.saleTime = saletime;
 	}
	private String cas; 
 	/**
 	 * 获取CAS的属性值.
 	 */
 	public String getCas(){
 		return cas;
 	}
 	
 	/**
 	 * 设置CAS的属性值.
 	 */
 	public void setCas(String cas){
 		this.cas = cas;
 	}
	private String connect; 
 	/**
 	 * 获取厂家联系人的属性值.
 	 */
 	public String getConnect(){
 		return connect;
 	}
 	
 	/**
 	 * 设置厂家联系人的属性值.
 	 */
 	public void setConnect(String connect){
 		this.connect = connect;
 	}
}

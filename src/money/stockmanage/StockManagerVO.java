
package money.stockmanage;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于股票交易的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class StockManagerVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public StockManagerVO() {

	}
	
	public StockManagerVO( int sno , String stockNo , String stockName , Date dealDate , double price , double dealNumber , double fee , String dealType ) {
		 this.sno = sno;
		 this.stockNo = stockNo;
		 this.stockName = stockName;
		 this.dealDate = dealDate;
		 this.price = price;
		 this.dealNumber = dealNumber;
		 this.fee = fee;
		 this.dealType = dealType;
	}
	
	public StockManagerVO(String stockNo ,String stockName ,Date dealDate ,double price ,double dealNumber ,double fee ,String dealType ) {
			 this.stockNo = stockNo;
			 this.stockName = stockName;
			 this.dealDate = dealDate;
			 this.price = price;
			 this.dealNumber = dealNumber;
			 this.fee = fee;
			 this.dealType = dealType;
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
	private String stockNo; 
 	/**
 	 * 获取股票号码的属性值.
 	 */
 	public String getStockNo(){
 		return stockNo;
 	}
 	
 	/**
 	 * 设置股票号码的属性值.
 	 */
 	public void setStockNo(String stockno){
 		this.stockNo = stockno;
 	}
	private String stockName; 
 	/**
 	 * 获取股票名称 的属性值.
 	 */
 	public String getStockName(){
 		return stockName;
 	}
 	
 	/**
 	 * 设置股票名称 的属性值.
 	 */
 	public void setStockName(String stockname){
 		this.stockName = stockname;
 	}
	private Date dealDate; 
 	/**
 	 * 获取交易时间的属性值.
 	 */
 	public Date getDealDate(){
 		return dealDate;
 	}
 	
 	/**
 	 * 设置交易时间的属性值.
 	 */
 	public void setDealDate(Date dealdate){
 		this.dealDate = dealdate;
 	}
	private double price; 
 	/**
 	 * 获取交易价格的属性值.
 	 */
 	public double getPrice(){
 		return price;
 	}
 	
 	/**
 	 * 设置交易价格的属性值.
 	 */
 	public void setPrice(double price){
 		this.price = price;
 	}
	private double dealNumber; 
 	/**
 	 * 获取交易份额的属性值.
 	 */
 	public double getDealNumber(){
 		return dealNumber;
 	}
 	
 	/**
 	 * 设置交易份额的属性值.
 	 */
 	public void setDealNumber(double dealnumber){
 		this.dealNumber = dealnumber;
 	}
	private double fee; 
 	/**
 	 * 获取交易费率的属性值.
 	 */
 	public double getFee(){
 		return fee;
 	}
 	
 	/**
 	 * 设置交易费率的属性值.
 	 */
 	public void setFee(double fee){
 		this.fee = fee;
 	}
	private String dealType; 
 	/**
 	 * 获取交易类型的属性值.
 	 */
 	public String getDealType(){
 		return dealType;
 	}
 	
 	/**
 	 * 设置交易类型的属性值.
 	 */
 	public void setDealType(String dealtype){
 		this.dealType = dealtype;
 	}
}

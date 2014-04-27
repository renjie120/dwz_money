
package money.stockmanage;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于股票交易的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class StockManagerImpl implements StockManager {
	private StockManagerVO stockmanagerVO = null;
	private static final long serialVersionUID = 1L;

	public StockManagerImpl(StockManagerVO stockmanagerVO) {
		this.stockmanagerVO = stockmanagerVO;
	}

	public StockManagerImpl( int sno , String stockNo , String stockName , Date dealDate , double price , double dealNumber , double fee , String dealType , int dealGroup ) {
		this.stockmanagerVO = new StockManagerVO( sno , stockNo , stockName , dealDate , price , dealNumber , fee , dealType , dealGroup );
	} 
	
	public StockManagerImpl(String stockNo ,String stockName ,Date dealDate ,double price ,double dealNumber ,double fee ,String dealType ,int dealGroup ) {
		this.stockmanagerVO = new StockManagerVO(stockNo ,stockName ,dealDate ,price ,dealNumber ,fee ,dealType ,dealGroup );
	} 

	public StockManagerVO getStockManagerVO() {
		return this.stockmanagerVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.stockmanagerVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.stockmanagerVO.getSno();
 	}
 	/**
 	 * 获取股票号码的属性值.
 	 */
 	public  String   getStockNo(){
 		return this.stockmanagerVO.getStockNo();
 	}
 	/**
 	 * 获取股票名称 的属性值.
 	 */
 	public  String   getStockName(){
 		return this.stockmanagerVO.getStockName();
 	}
 	/**
 	 * 获取交易时间的属性值.
 	 */
 	public  Date   getDealDate(){
 		return this.stockmanagerVO.getDealDate();
 	}
 	/**
 	 * 获取交易价格的属性值.
 	 */
 	public  double   getPrice(){
 		return this.stockmanagerVO.getPrice();
 	}
 	/**
 	 * 获取交易份额的属性值.
 	 */
 	public  double   getDealNumber(){
 		return this.stockmanagerVO.getDealNumber();
 	}
 	/**
 	 * 获取交易费率的属性值.
 	 */
 	public  double   getFee(){
 		return this.stockmanagerVO.getFee();
 	}
 	/**
 	 * 获取交易类型的属性值.
 	 */
 	public  String   getDealType(){
 		return this.stockmanagerVO.getDealType();
 	}
 	/**
 	 * 获取交易分组的属性值.
 	 */
 	public  int   getDealGroup(){
 		return this.stockmanagerVO.getDealGroup();
 	}
 
}
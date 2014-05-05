
package money.orderbase;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于订单基本信息的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class OrderBaseManagerImpl implements OrderBaseManager {
	private OrderBaseManagerVO orderbasemanagerVO = null;
	private static final long serialVersionUID = 1L;

	public OrderBaseManagerImpl(OrderBaseManagerVO orderbasemanagerVO) {
		this.orderbasemanagerVO = orderbasemanagerVO;
	}

	public OrderBaseManagerImpl( int sno , String orderNo , String customerNo , String gongLv , String dianYa , String shiDai , String bianyaChangjia , String bianyaXinghao , String isImport , Date startDate , Date endDate , String currentState ) {
		this.orderbasemanagerVO = new OrderBaseManagerVO( sno , orderNo , customerNo , gongLv , dianYa , shiDai , bianyaChangjia , bianyaXinghao , isImport , startDate , endDate , currentState );
	} 
	
	public OrderBaseManagerImpl(String orderNo ,String customerNo ,String gongLv ,String dianYa ,String shiDai ,String bianyaChangjia ,String bianyaXinghao ,String isImport ,Date startDate ,Date endDate ,String currentState ) {
		this.orderbasemanagerVO = new OrderBaseManagerVO(orderNo ,customerNo ,gongLv ,dianYa ,shiDai ,bianyaChangjia ,bianyaXinghao ,isImport ,startDate ,endDate ,currentState );
	} 

	public OrderBaseManagerVO getOrderBaseManagerVO() {
		return this.orderbasemanagerVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.orderbasemanagerVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.orderbasemanagerVO.getSno();
 	}
 	/**
 	 * 获取订单的属性值.
 	 */
 	public  String   getOrderNo(){
 		return this.orderbasemanagerVO.getOrderNo();
 	}
 	/**
 	 * 获取客户名称的属性值.
 	 */
 	public  String   getCustomerNo(){
 		return this.orderbasemanagerVO.getCustomerNo();
 	}
 	/**
 	 * 获取功率的属性值.
 	 */
 	public  String   getGongLv(){
 		return this.orderbasemanagerVO.getGongLv();
 	}
 	/**
 	 * 获取电压的属性值.
 	 */
 	public  String   getDianYa(){
 		return this.orderbasemanagerVO.getDianYa();
 	}
 	/**
 	 * 获取世代的属性值.
 	 */
 	public  String   getShiDai(){
 		return this.orderbasemanagerVO.getShiDai();
 	}
 	/**
 	 * 获取整流变压器厂家的属性值.
 	 */
 	public  String   getBianyaChangjia(){
 		return this.orderbasemanagerVO.getBianyaChangjia();
 	}
 	/**
 	 * 获取整流变压器型号的属性值.
 	 */
 	public  String   getBianyaXinghao(){
 		return this.orderbasemanagerVO.getBianyaXinghao();
 	}
 	/**
 	 * 获取是否重点客户的属性值.
 	 */
 	public  String   getIsImport(){
 		return this.orderbasemanagerVO.getIsImport();
 	}
 	/**
 	 * 获取计划开工时间的属性值.
 	 */
 	public  Date   getStartDate(){
 		return this.orderbasemanagerVO.getStartDate();
 	}
 	/**
 	 * 获取计划结束时间的属性值.
 	 */
 	public  Date   getEndDate(){
 		return this.orderbasemanagerVO.getEndDate();
 	}
 	/**
 	 * 获取当前状态的属性值.
 	 */
 	public  String   getCurrentState(){
 		return this.orderbasemanagerVO.getCurrentState();
 	}
 
}

package money.orderbase;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于订单基本信息的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class OrderBaseManagerVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public OrderBaseManagerVO() {

	}
	
	public OrderBaseManagerVO( int sno , String orderNo , String customerNo , String gongLv , String dianYa , String shiDai , String bianyaChangjia , String bianyaXinghao , String isImport , Date startDate , Date endDate , String currentState ) {
		 this.sno = sno;
		 this.orderNo = orderNo;
		 this.customerNo = customerNo;
		 this.gongLv = gongLv;
		 this.dianYa = dianYa;
		 this.shiDai = shiDai;
		 this.bianyaChangjia = bianyaChangjia;
		 this.bianyaXinghao = bianyaXinghao;
		 this.isImport = isImport;
		 this.startDate = startDate;
		 this.endDate = endDate;
		 this.currentState = currentState;
	}
	
	public OrderBaseManagerVO(String orderNo ,String customerNo ,String gongLv ,String dianYa ,String shiDai ,String bianyaChangjia ,String bianyaXinghao ,String isImport ,Date startDate ,Date endDate ,String currentState ) {
			 this.orderNo = orderNo;
			 this.customerNo = customerNo;
			 this.gongLv = gongLv;
			 this.dianYa = dianYa;
			 this.shiDai = shiDai;
			 this.bianyaChangjia = bianyaChangjia;
			 this.bianyaXinghao = bianyaXinghao;
			 this.isImport = isImport;
			 this.startDate = startDate;
			 this.endDate = endDate;
			 this.currentState = currentState;
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
	private String orderNo; 
 	/**
 	 * 获取订单的属性值.
 	 */
 	public String getOrderNo(){
 		return orderNo;
 	}
 	
 	/**
 	 * 设置订单的属性值.
 	 */
 	public void setOrderNo(String orderno){
 		this.orderNo = orderno;
 	}
	private String customerNo; 
 	/**
 	 * 获取客户名称的属性值.
 	 */
 	public String getCustomerNo(){
 		return customerNo;
 	}
 	
 	/**
 	 * 设置客户名称的属性值.
 	 */
 	public void setCustomerNo(String customerno){
 		this.customerNo = customerno;
 	}
	private String gongLv; 
 	/**
 	 * 获取功率的属性值.
 	 */
 	public String getGongLv(){
 		return gongLv;
 	}
 	
 	/**
 	 * 设置功率的属性值.
 	 */
 	public void setGongLv(String gonglv){
 		this.gongLv = gonglv;
 	}
	private String dianYa; 
 	/**
 	 * 获取电压的属性值.
 	 */
 	public String getDianYa(){
 		return dianYa;
 	}
 	
 	/**
 	 * 设置电压的属性值.
 	 */
 	public void setDianYa(String dianya){
 		this.dianYa = dianya;
 	}
	private String shiDai; 
 	/**
 	 * 获取世代的属性值.
 	 */
 	public String getShiDai(){
 		return shiDai;
 	}
 	
 	/**
 	 * 设置世代的属性值.
 	 */
 	public void setShiDai(String shidai){
 		this.shiDai = shidai;
 	}
	private String bianyaChangjia; 
 	/**
 	 * 获取整流变压器厂家的属性值.
 	 */
 	public String getBianyaChangjia(){
 		return bianyaChangjia;
 	}
 	
 	/**
 	 * 设置整流变压器厂家的属性值.
 	 */
 	public void setBianyaChangjia(String bianyachangjia){
 		this.bianyaChangjia = bianyachangjia;
 	}
	private String bianyaXinghao; 
 	/**
 	 * 获取整流变压器型号的属性值.
 	 */
 	public String getBianyaXinghao(){
 		return bianyaXinghao;
 	}
 	
 	/**
 	 * 设置整流变压器型号的属性值.
 	 */
 	public void setBianyaXinghao(String bianyaxinghao){
 		this.bianyaXinghao = bianyaxinghao;
 	}
	private String isImport; 
 	/**
 	 * 获取是否重点客户的属性值.
 	 */
 	public String getIsImport(){
 		return isImport;
 	}
 	
 	/**
 	 * 设置是否重点客户的属性值.
 	 */
 	public void setIsImport(String isimport){
 		this.isImport = isimport;
 	}
	private Date startDate; 
 	/**
 	 * 获取计划开工时间的属性值.
 	 */
 	public Date getStartDate(){
 		return startDate;
 	}
 	
 	/**
 	 * 设置计划开工时间的属性值.
 	 */
 	public void setStartDate(Date startdate){
 		this.startDate = startdate;
 	}
	private Date endDate; 
 	/**
 	 * 获取计划结束时间的属性值.
 	 */
 	public Date getEndDate(){
 		return endDate;
 	}
 	
 	/**
 	 * 设置计划结束时间的属性值.
 	 */
 	public void setEndDate(Date enddate){
 		this.endDate = enddate;
 	}
	private String currentState; 
 	/**
 	 * 获取当前状态的属性值.
 	 */
 	public String getCurrentState(){
 		return currentState;
 	}
 	
 	/**
 	 * 设置当前状态的属性值.
 	 */
 	public void setCurrentState(String currentstate){
 		this.currentState = currentstate;
 	}
}

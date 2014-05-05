
package money.orderbase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext; 
import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于订单基本信息的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class OrderBaseManagerAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	OrderBaseManagerManager pMgr = bf.getManager(BeanManagerKey.orderbasemanagerManager);
	//业务实体对象
	private OrderBaseManager vo;
	//当前页数
	private int page = 1;
	//每页显示数量
	private int pageSize = 50;
	//总页数
	private long count;
	
	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			OrderBaseManagerImpl orderbasemanagerImpl = new OrderBaseManagerImpl(orderNo ,customerNo ,gongLv ,dianYa ,shiDai ,bianyaChangjia ,bianyaXinghao ,isImport ,startDate ,endDate ,currentState );
			pMgr.createOrderBaseManager(orderbasemanagerImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeOrderBaseManagers(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getOrderBaseManager(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			OrderBaseManagerImpl orderbasemanagerImpl = new OrderBaseManagerImpl( sno , orderNo , customerNo , gongLv , dianYa , shiDai , bianyaChangjia , bianyaXinghao , isImport , startDate , endDate , currentState );
			pMgr.updateOrderBaseManager(orderbasemanagerImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号"),  ORDERNO("订单"),  CUSTOMERNO("客户名称"),  GONGLV("功率"),  DIANYA("电压"),  SHIDAI("世代"),  BIANYACHANGJIA("整流变压器厂家"),  BIANYAXINGHAO("整流变压器型号"),  ISIMPORT("是否重点客户"),  STARTDATE("计划开工时间"),  ENDDATE("计划结束时间"),  CURRENTSTATE("当前状态");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public String beforeQuery() {
		return "query";
	}

	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=OrderBaseManagerList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<OrderBaseManagerSearchFields, Object> criterias = getCriterias();

		Collection<OrderBaseManager> orderbasemanagerList = pMgr.searchOrderBaseManager(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (OrderBaseManager orderbasemanager : orderbasemanagerList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), orderbasemanager.getSno()); 
					break;
					case ORDERNO:
						 e.setCell(filed.ordinal(), orderbasemanager.getOrderNo()); 
					break;
					case CUSTOMERNO:
						 e.setCell(filed.ordinal(), orderbasemanager.getCustomerNo()); 
					break;
					case GONGLV:
						 e.setCell(filed.ordinal(), orderbasemanager.getGongLv()); 
					break;
					case DIANYA:
						 e.setCell(filed.ordinal(), orderbasemanager.getDianYa()); 
					break;
					case SHIDAI:
						 e.setCell(filed.ordinal(), orderbasemanager.getShiDai()); 
					break;
					case BIANYACHANGJIA:
						 e.setCell(filed.ordinal(), orderbasemanager.getBianyaChangjia()); 
					break;
					case BIANYAXINGHAO:
						 e.setCell(filed.ordinal(), orderbasemanager.getBianyaXinghao()); 
					break;
					case ISIMPORT:
						 e.setCell(filed.ordinal(), orderbasemanager.getIsImport()); 
					break;
					case STARTDATE:
						 e.setCell(filed.ordinal(), orderbasemanager.getStartDate()); 
					break;
					case ENDDATE:
						 e.setCell(filed.ordinal(), orderbasemanager.getEndDate()); 
					break;
					case CURRENTSTATE:
						 e.setCell(filed.ordinal(), orderbasemanager.getCurrentState()); 
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<OrderBaseManagerSearchFields, Object> criterias = getCriterias();

		Collection<OrderBaseManager> moneyList = pMgr.searchOrderBaseManager(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchOrderBaseManagerNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "list";
	}

	public String reQuery() {
		return "list";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private Map<OrderBaseManagerSearchFields, Object> getCriterias() {
		Map<OrderBaseManagerSearchFields, Object> criterias = new HashMap<OrderBaseManagerSearchFields, Object>();
			if (getOrderNo()!=null&&!"".equals(getOrderNo()))
			 	criterias.put(OrderBaseManagerSearchFields.ORDERNO,  getOrderNo());
			if (getCustomerNo()!=null&&!"".equals(getCustomerNo()))
			 	criterias.put(OrderBaseManagerSearchFields.CUSTOMERNO,  getCustomerNo());
			if (getBianyaXinghao()!=null&&!"".equals(getBianyaXinghao()))
			 	criterias.put(OrderBaseManagerSearchFields.BIANYAXINGHAO,  getBianyaXinghao());
		return criterias;
	}

	public OrderBaseManager getVo() {
		return vo;
	}

	public void setVo(OrderBaseManager vo) {
		this.vo = vo;
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

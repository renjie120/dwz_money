
package money.yaopin;

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
 * 关于药品销售信息的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class YaopinInfoAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	YaopinInfoManager pMgr = bf.getManager(BeanManagerKey.yaopininfoManager);
	//业务实体对象
	private YaopinInfo vo;
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
			YaopinInfoImpl yaopininfoImpl = new YaopinInfoImpl(companyName ,engName ,chnName ,chemStruct ,productName ,customer ,num ,price ,chundu ,saleTime ,cas ,connect );
			pMgr.createYaopinInfo(yaopininfoImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println(e.getMessage());
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeYaopinInfos(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getYaopinInfo(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			YaopinInfoImpl yaopininfoImpl = new YaopinInfoImpl( sno , companyName , engName , chnName , chemStruct , productName , customer , num , price , chundu , saleTime , cas , connect );
			pMgr.updateYaopinInfo(yaopininfoImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号"),  COMPANYNAME("业务实体"),  ENGNAME("物料名称（英文）"),  CHNNAME("物料名称（中文）"),  CHEMSTRUCT("结构式"),  PRODUCTNAME("生产厂家"),  CUSTOMER("厂家销售客户"),  NUM("数量"),  PRICE("销售价格"),  CHUNDU("纯度"),  SALETIME("销售时间"),  CAS("CAS"),  CONNECT("厂家联系人");
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
		response.addHeader("Content-Disposition","attachment;filename=YaopinInfoList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<YaopinInfoSearchFields, Object> criterias = getCriterias();

		Collection<YaopinInfo> yaopininfoList = pMgr.searchYaopinInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (YaopinInfo yaopininfo : yaopininfoList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), yaopininfo.getSno()); 
					break;
					case COMPANYNAME:
						 e.setCell(filed.ordinal(), yaopininfo.getCompanyName()); 
					break;
					case ENGNAME:
						 e.setCell(filed.ordinal(), yaopininfo.getEngName()); 
					break;
					case CHNNAME:
						 e.setCell(filed.ordinal(), yaopininfo.getChnName()); 
					break;
					case CHEMSTRUCT:
						 e.setCell(filed.ordinal(), yaopininfo.getChemStruct()); 
					break;
					case PRODUCTNAME:
						 e.setCell(filed.ordinal(), yaopininfo.getProductName()); 
					break;
					case CUSTOMER:
						 e.setCell(filed.ordinal(), yaopininfo.getCustomer()); 
					break;
					case NUM:
						 e.setCell(filed.ordinal(), yaopininfo.getNum()); 
					break;
					case PRICE:
						 e.setCell(filed.ordinal(), yaopininfo.getPrice()); 
					break;
					case CHUNDU:
						 e.setCell(filed.ordinal(), yaopininfo.getChundu()); 
					break;
					case SALETIME:
						 e.setCell(filed.ordinal(), yaopininfo.getSaleTime()); 
					break;
					case CAS:
						 e.setCell(filed.ordinal(), yaopininfo.getCas()); 
					break;
					case CONNECT:
						 e.setCell(filed.ordinal(), yaopininfo.getConnect()); 
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
		Map<YaopinInfoSearchFields, Object> criterias = getCriterias();

		Collection<YaopinInfo> moneyList = pMgr.searchYaopinInfo(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchYaopinInfoNum(criterias);
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

	private Map<YaopinInfoSearchFields, Object> getCriterias() {
		Map<YaopinInfoSearchFields, Object> criterias = new HashMap<YaopinInfoSearchFields, Object>();
			if (getCompanyName()!=null&&!"".equals(getCompanyName()))
				criterias.put(YaopinInfoSearchFields.COMPANYNAME, "%"+getCompanyName()+"%"); 
			if (getEngName()!=null&&!"".equals(getEngName()))
				criterias.put(YaopinInfoSearchFields.ENGNAME, "%"+getEngName()+"%"); 
			if (getChnName()!=null&&!"".equals(getChnName()))
				criterias.put(YaopinInfoSearchFields.CHNNAME, "%"+getChnName()+"%"); 
			if (getChemStruct()!=null&&!"".equals(getChemStruct()))
			 	criterias.put(YaopinInfoSearchFields.CHEMSTRUCT,  getChemStruct());
			if (getProductName()!=null&&!"".equals(getProductName()))
			 	criterias.put(YaopinInfoSearchFields.PRODUCTNAME,  getProductName());
			if (getCas()!=null&&!"".equals(getCas()))
			 	criterias.put(YaopinInfoSearchFields.CAS,  getCas());
		return criterias;
	}

	public YaopinInfo getVo() {
		return vo;
	}

	public void setVo(YaopinInfo vo) {
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
	private double num; 
 	/**
 	 * 获取数量的属性值.
 	 */
 	public double getNum(){
 		return num;
 	}
 	
 	/**
 	 * 设置数量的属性值.
 	 */
 	public void setNum(double num){
 		this.num = num;
 	}
	private double price; 
 	/**
 	 * 获取销售价格的属性值.
 	 */
 	public double getPrice(){
 		return price;
 	}
 	
 	/**
 	 * 设置销售价格的属性值.
 	 */
 	public void setPrice(double price){
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

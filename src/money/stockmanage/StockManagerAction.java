package money.stockmanage;

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
 * 关于股票交易的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class StockManagerAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	StockManagerManager pMgr = bf
			.getManager(BeanManagerKey.stockmanagerManager);
	// 业务实体对象
	private StockManager vo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
	private long count;

	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			StockManagerImpl stockmanagerImpl = new StockManagerImpl(stockNo,
					stockName, dealDate, price, dealNumber, fee, dealType,
					dealGroup);
			pMgr.createStockManager(stockmanagerImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeStockManagers(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getStockManager(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			StockManagerImpl stockmanagerImpl = new StockManagerImpl(sno,
					stockNo, stockName, dealDate, price, dealNumber, fee,
					dealType, dealGroup);
			pMgr.updateStockManager(stockmanagerImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		SNO("流水号"), STOCKNO("股票号码"), STOCKNAME("股票名称 "), DEALDATE("交易时间"), PRICE(
				"交易价格"), DEALNUMBER("交易份额"), FEE("交易费率"), DEALTYPE("交易类型"), DEALGROUP(
				"交易分组");
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
		response.addHeader("Content-Disposition",
				"attachment;filename=StockManagerList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<StockManagerSearchFields, Object> criterias = getCriterias();

		Collection<StockManager> stockmanagerList = pMgr.searchStockManager(
				criterias, realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (StockManager stockmanager : stockmanagerList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case SNO:
					e.setCell(filed.ordinal(), stockmanager.getSno());
					break;
				case STOCKNO:
					e.setCell(filed.ordinal(), stockmanager.getStockNo());
					break;
				case STOCKNAME:
					e.setCell(filed.ordinal(), stockmanager.getStockName());
					break;
				case DEALDATE:
					e.setCell(filed.ordinal(), stockmanager.getDealDate());
					break;
				case PRICE:
					e.setCell(filed.ordinal(), stockmanager.getPrice());
					break;
				case DEALNUMBER:
					e.setCell(filed.ordinal(), stockmanager.getDealNumber());
					break;
				case FEE:
					e.setCell(filed.ordinal(), stockmanager.getFee());
					break;
				case DEALTYPE:
					e.setCell(filed.ordinal(), stockmanager.getDealType());
					break;
				case DEALGROUP:
					e.setCell(filed.ordinal(), stockmanager.getDealGroup());
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
		Map<StockManagerSearchFields, Object> criterias = getCriterias();

		Collection<StockManager> moneyList = pMgr.searchStockManager(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchStockManagerNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
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

	private Map<StockManagerSearchFields, Object> getCriterias() {
		Map<StockManagerSearchFields, Object> criterias = new HashMap<StockManagerSearchFields, Object>();
		if (getStockNo() != null && !"".equals(getStockNo()))
			criterias.put(StockManagerSearchFields.STOCKNO, getStockNo());
		if (getStockName() != null && !"".equals(getStockName()))
			criterias.put(StockManagerSearchFields.STOCKNAME, getStockName());
		if (getDealType() != null && !"".equals(getDealType())
				&& !"-1".equals(getDealType()) && !"-2".equals(getDealType()))
			criterias.put(StockManagerSearchFields.DEALTYPE, getDealType());
		if (getDealGroup() != 0)
			criterias.put(StockManagerSearchFields.DEALGROUP, getDealGroup());
		return criterias;
	}

	public StockManager getVo() {
		return vo;
	}

	public void setVo(StockManager vo) {
		this.vo = vo;
	}

	private Integer sno;

	/**
	 * 获取流水号的属性值.
	 */
	public Integer getSno() {
		return sno;
	}

	/**
	 * 设置流水号的属性值.
	 */
	public void setSno(Integer sno) {
		this.sno = sno;
	}

	private String stockNo;

	/**
	 * 获取股票号码的属性值.
	 */
	public String getStockNo() {
		return stockNo;
	}

	/**
	 * 设置股票号码的属性值.
	 */
	public void setStockNo(String stockno) {
		this.stockNo = stockno;
	}

	private String stockName;

	/**
	 * 获取股票名称 的属性值.
	 */
	public String getStockName() {
		return stockName;
	}

	/**
	 * 设置股票名称 的属性值.
	 */
	public void setStockName(String stockname) {
		this.stockName = stockname;
	}

	private Date dealDate;

	/**
	 * 获取交易时间的属性值.
	 */
	public Date getDealDate() {
		return dealDate;
	}

	/**
	 * 设置交易时间的属性值.
	 */
	public void setDealDate(Date dealdate) {
		this.dealDate = dealdate;
	}

	private double price;

	/**
	 * 获取交易价格的属性值.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 设置交易价格的属性值.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	private double dealNumber;

	/**
	 * 获取交易份额的属性值.
	 */
	public double getDealNumber() {
		return dealNumber;
	}

	/**
	 * 设置交易份额的属性值.
	 */
	public void setDealNumber(double dealnumber) {
		this.dealNumber = dealnumber;
	}

	private double fee;

	/**
	 * 获取交易费率的属性值.
	 */
	public double getFee() {
		return fee;
	}

	/**
	 * 设置交易费率的属性值.
	 */
	public void setFee(double fee) {
		this.fee = fee;
	}

	private String dealType;

	/**
	 * 获取交易类型的属性值.
	 */
	public String getDealType() {
		return dealType;
	}

	/**
	 * 设置交易类型的属性值.
	 */
	public void setDealType(String dealtype) {
		this.dealType = dealtype;
	}

	private int dealGroup;

	/**
	 * 获取交易分组的属性值.
	 */
	public int getDealGroup() {
		return dealGroup;
	}

	/**
	 * 设置交易分组的属性值.
	 */
	public void setDealGroup(int dealgroup) {
		this.dealGroup = dealgroup;
	}
}

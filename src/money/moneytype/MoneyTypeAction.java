package money.moneytype;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import common.cache.CacheEnum;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.present.BaseAction;

/**
 * 金额类型
 * 
 * @author lsq
 * 
 */
public class MoneyTypeAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MoneyTypeManager pMgr = bf.getManager(BeanManagerKey.moneyTypeManager);
	private MoneyType moneyTypeVo;

	public String beforeAdd() {
		return "detail";
	}

	/**
	 * 添加信息.
	 * 
	 * @return
	 */
	public String doAdd() {
		try {
			MoneyTypeImpl moneyTypeImpl = new MoneyTypeImpl(moneyTypeDesc,
					moneyType, parentCode, typeCode, orderId);
			pMgr.createMoneyType(moneyTypeImpl);
			common.cache.CacheManager.clearOnly(CacheEnum.MONEYTYPE.getName());
			common.cache.CacheManager.clearOnly(CacheEnum.MONEYTYPETREE.getName());
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	/**
	 * 删除信息.
	 * 
	 * @return
	 */
	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeMoneyType(ids);
		common.cache.CacheManager.clearOnly(CacheEnum.MONEYTYPE.getName());
		common.cache.CacheManager.clearOnly(CacheEnum.MONEYTYPETREE.getName());
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	/**
	 * 得到详细信息.
	 * 
	 * @return
	 */
	public String beforeUpdate() {
		moneyTypeVo = pMgr.getMoneyType(moenyTypeSno);
		return "editdetail";
	}

	/**
	 * 更新信息.
	 * 
	 * @return
	 */
	public String doUpdate() {
		try {
			MoneyTypeImpl moneyTypeImpl = new MoneyTypeImpl(moenyTypeSno,
					moneyTypeDesc, moneyType, parentCode, typeCode, orderId);
			pMgr.updateMoneyType(moneyTypeImpl);
			common.cache.CacheManager.clearOnly(CacheEnum.MONEYTYPE.getName());
			common.cache.CacheManager.clearOnly(CacheEnum.MONEYTYPETREE.getName());
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	private int page = 1;
	private int pageSize = 50;
	private long count;

	/**
	 * 查询信息.
	 * 
	 * @return
	 */
	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<MoneyTypeSearchFields, Object> criterias = getCriterias();

		Collection<MoneyType> moneyList = pMgr.searchMoneyType(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", pMgr.searchMoneyTypeNum(criterias));
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",
				pMgr.searchMoneyTypeNum(criterias));
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

	private Map<MoneyTypeSearchFields, Object> getCriterias() {
		Map<MoneyTypeSearchFields, Object> criterias = new HashMap<MoneyTypeSearchFields, Object>();
		return criterias;
	}

	public MoneyType getMoneyTypeVo() {
		return moneyTypeVo;
	}

	public void setMoneyTypeVo(MoneyType moneyTypeVo) {
		this.moneyTypeVo = moneyTypeVo;
	}

	public MoneyType getMoneyVo() {
		return moneyTypeVo;
	}

	public void setMoneyVo(MoneyType moneyTypeVo) {
		this.moneyTypeVo = moneyTypeVo;
	}

	private int moenyTypeSno;

	public void setMoenyTypeSno(int moenyTypeSno) {
		this.moenyTypeSno = moenyTypeSno;
	}

	public int getMoenyTypeSno() {
		return moenyTypeSno;
	}

	private String moneyTypeDesc;

	public void setMoneyTypeDesc(String moneyTypeDesc) {
		this.moneyTypeDesc = moneyTypeDesc;
	}

	public String getMoneyTypeDesc() {
		return moneyTypeDesc;
	}

	private String moneyType;

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getMoneyType() {
		return moneyType;
	}

	private String parentCode;

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	private String typeCode;

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	private int orderId;

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

}

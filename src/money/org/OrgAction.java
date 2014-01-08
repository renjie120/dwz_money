package money.org;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import common.cache.CacheEnum;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

public class OrgAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OrgManager pMgr = bf.getManager(BeanManagerKey.orgManager);
	private Org orgVo; 
	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			OrgImpl orgImpl = new OrgImpl(orgName, orderCode, parentOrg,
					orderId);
			pMgr.createOrg(orgImpl);
			common.cache.CacheManager.clearOnly(CacheEnum.ORGTREE.getName());
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeOrg(ids);
		common.cache.CacheManager.clearOnly(CacheEnum.ORGTREE.getName());
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		orgVo = pMgr.getOrg(orgId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			OrgImpl orgImpl = new OrgImpl(orgId, orgName, orderCode, parentOrg,
					orderId);
			pMgr.updateOrg(orgImpl);
			common.cache.CacheManager.clearOnly(CacheEnum.ORGTREE.getName());
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	private int page = 1;
	private int pageSize = 50;
	private long count;

	public enum ExportFiled {
		ORGID("组织流水号"), ORGNAME("组织名称"), ORDERCODE("组织代码"), PARENTORG("父组织");
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
				"attachment;filename=userList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<OrgSearchFields, Object> criterias = getCriterias();

		Collection<Org> orgList = pMgr.searchOrg(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (Org org : orgList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case ORGID:
					e.setCell(filed.ordinal(), org.getOrgId());
					break;
				case ORGNAME:
					e.setCell(filed.ordinal(), org.getOrgName());
					break;
				case ORDERCODE:
					e.setCell(filed.ordinal(), org.getOrderCode());
					break;
				case PARENTORG:
					e.setCell(filed.ordinal(), org.getParentOrg());
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
		Map<OrgSearchFields, Object> criterias = getCriterias();

		Collection<Org> moneyList = pMgr.searchOrg(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", pMgr.searchOrgNum(criterias));
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",
				pMgr.searchOrgNum(criterias));
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

	private Map<OrgSearchFields, Object> getCriterias() {
		Map<OrgSearchFields, Object> criterias = new HashMap<OrgSearchFields, Object>();
		return criterias;
	}

	public Org getOrgVo() {
		return orgVo;
	}

	public void setOrgVo(Org orgVo) {
		this.orgVo = orgVo;
	}

	public Org getMoneyVo() {
		return orgVo;
	}

	public void setMoneyVo(Org orgVo) {
		this.orgVo = orgVo;
	}

	private int orgId;

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getOrgId() {
		return orgId;
	}

	private String orgName;

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgName() {
		return orgName;
	}

	private String orderCode;

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	private String parentOrg;

	public void setParentOrg(String parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getParentOrg() {
		return parentOrg;
	}

	private String orderId;

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

}

package money.plan;

import java.util.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

public class PlanAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PlanManager pMgr = bf.getManager(BeanManagerKey.planManager);
	private Plan planVo;

	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			PlanImpl planImpl = new PlanImpl(planDate, startDate, endDate,
					realStartDate, realEndDate, planDesc, planType, planStatus,
					userId);
			pMgr.createPlan(planImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removePlan(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		planVo = pMgr.getPlan(planId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			PlanImpl planImpl = new PlanImpl(planId, planDate, startDate,
					endDate, realStartDate, realEndDate, planDesc, planType,
					planStatus, userId);
			pMgr.updatePlan(planImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	private int page = 1;
	private int pageSize = 50;
	private long count;

	public enum ExportFiled {
		;
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
		Map<PlanSearchFields, Object> criterias = getCriterias();

		Collection<Plan> planList = pMgr.searchPlan(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (Plan plan : planList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {

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
		Map<PlanSearchFields, Object> criterias = getCriterias();

		Collection<Plan> moneyList = pMgr.searchPlan(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", pMgr.searchPlanNum(criterias));
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",
				pMgr.searchPlanNum(criterias));
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

	private Map<PlanSearchFields, Object> getCriterias() {
		Map<PlanSearchFields, Object> criterias = new HashMap<PlanSearchFields, Object>();
		return criterias;
	}

	public Plan getPlanVo() {
		return planVo;
	}

	public void setPlanVo(Plan planVo) {
		this.planVo = planVo;
	}

	public Plan getMoneyVo() {
		return planVo;
	}

	public void setMoneyVo(Plan planVo) {
		this.planVo = planVo;
	}

	private int planId;

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getPlanId() {
		return planId;
	}

	private Date planDate;

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public Date getPlanDate() {
		return planDate;
	}

	private Date startDate;

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	private Date endDate;

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	private Date realStartDate;

	public void setRealStartDate(Date realStartDate) {
		this.realStartDate = realStartDate;
	}

	public Date getRealStartDate() {
		return realStartDate;
	}

	private Date realEndDate;

	public void setRealEndDate(Date realEndDate) {
		this.realEndDate = realEndDate;
	}

	public Date getRealEndDate() {
		return realEndDate;
	}

	private String planDesc;

	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}

	public String getPlanDesc() {
		return planDesc;
	}

	private int planType;

	public void setPlanType(int planType) {
		this.planType = planType;
	}

	public int getPlanType() {
		return planType;
	}

	private int planStatus;

	public void setPlanStatus(int planStatus) {
		this.planStatus = planStatus;
	}

	public int getPlanStatus() {
		return planStatus;
	}

	private int userId;

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

}

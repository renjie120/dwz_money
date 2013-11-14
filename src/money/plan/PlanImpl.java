package money.plan;

import java.util.Date;

import dwz.framework.core.business.BusinessObject;

public class PlanImpl implements Plan {
	private PlanVO planVO = null;
	private static final long serialVersionUID = 1L;

	public PlanImpl(PlanVO planVO) {
		this.planVO = planVO;
	}

	public PlanImpl(Date planDate, Date startDate, Date endDate,
			Date realStartDate, Date realEndDate, String planDesc,
			int planType, int planStatus, int userId) {
		this.planVO = new PlanVO(planDate, startDate, endDate, realStartDate,
				realEndDate, planDesc, planType, planStatus, userId);
	}

	public PlanImpl(int planId, Date planDate, Date startDate, Date endDate,
			Date realStartDate, Date realEndDate, String planDesc,
			int planType, int planStatus, int userId) {
		this.planVO = new PlanVO(planId, planDate, startDate, endDate,
				realStartDate, realEndDate, planDesc, planType, planStatus,
				userId);
	}

	public PlanVO getPlanVO() {
		return this.planVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	public Integer getId() {
		return this.planVO.getPlanId();
	}

	public int getPlanId() {
		return this.planVO.getPlanId();
	}

	public Date getPlanDate() {
		return this.planVO.getPlanDate();
	}

	public Date getStartDate() {
		return this.planVO.getStartDate();
	}

	public Date getEndDate() {
		return this.planVO.getEndDate();
	}

	public Date getRealStartDate() {
		return this.planVO.getRealStartDate();
	}

	public Date getRealEndDate() {
		return this.planVO.getRealEndDate();
	}

	public String getPlanDesc() {
		return this.planVO.getPlanDesc();
	}

	public int getPlanType() {
		return this.planVO.getPlanType();
	}

	public int getPlanStatus() {
		return this.planVO.getPlanStatus();
	}

	public int getUserId() {
		return this.planVO.getUserId();
	}

	public String getPlanTypeName() {
		return this.planVO.getPlanTypeName();
	}

	public String getPlanStatusName() {
		return this.planVO.getPlanStatusName();
	}

}

package money.plan;

import java.io.Serializable;
import java.util.Date;

public class PlanVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public PlanVO() {

	}

	public PlanVO(int planId, Date planDate, Date startDate, Date endDate,
			Date realStartDate, Date realEndDate, String planDesc,
			int planType, int planStatus, int userId) {
		this.planId = planId;
		this.planDate = planDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.realStartDate = realStartDate;
		this.realEndDate = realEndDate;
		this.planDesc = planDesc;
		this.planType = planType;
		this.planStatus = planStatus;
		this.userId = userId;

	}

	public PlanVO(Date planDate, Date startDate, Date endDate,
			Date realStartDate, Date realEndDate, String planDesc,
			int planType, int planStatus, int userId) {
		this.planDate = planDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.realStartDate = realStartDate;
		this.realEndDate = realEndDate;
		this.planDesc = planDesc;
		this.planType = planType;
		this.planStatus = planStatus;
		this.userId = userId;

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

	private String planTypeName;

	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}

	public String getPlanTypeName() {
		return planTypeName;
	}

	private String planStatusName;

	public void setPlanStatusName(String planStatusName) {
		this.planStatusName = planStatusName;
	}

	public String getPlanStatusName() {
		return planStatusName;
	}

}

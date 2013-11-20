package money.org;

import java.io.Serializable;

public class OrgVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public OrgVO() {

	}

	public OrgVO(int orgId, String orgName, String orderCode, String parentOrg,
			String orderId) {
		this.orgId = orgId;
		this.orgName = orgName;
		this.orderCode = orderCode;
		this.parentOrg = parentOrg;
		this.orderId = orderId;

	}

	public OrgVO(String orgName, String orderCode, String parentOrg,
			String orderId) {
		this.orgName = orgName;
		this.orderCode = orderCode;
		this.parentOrg = parentOrg;
		this.orderId = orderId;

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

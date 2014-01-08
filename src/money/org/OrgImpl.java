package money.org;

import dwz.framework.core.business.BusinessObject;

public class OrgImpl implements Org {
	private OrgVO orgVO = null;
	private static final long serialVersionUID = 1L;

	public OrgImpl(OrgVO orgVO) {
		this.orgVO = orgVO;
	}

	public OrgImpl(String orgName, String orderCode, String parentOrg,
			String orderId) {
		this.orgVO = new OrgVO(orgName, orderCode, parentOrg, orderId);
	}

	public OrgImpl(int orgId, String orgName, String orderCode,
			String parentOrg, String orderId) {
		this.orgVO = new OrgVO(orgId, orgName, orderCode, parentOrg, orderId);
	}

	public OrgVO getOrgVO() {
		return this.orgVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	public Integer getId() {
		return this.orgVO.getOrgId();
	}

	public int getOrgId() {
		return this.orgVO.getOrgId();
	}

	public String getOrgName() {
		return this.orgVO.getOrgName();
	}

	public String getOrderCode() {
		return this.orgVO.getOrderCode();
	}

	public String getParentOrg() {
		return this.orgVO.getParentOrg();
	}

	public String getOrderId() {
		return this.orgVO.getOrderId();
	}

	@Override
	public String getParentOrgName() {
		return this.orgVO.getParentOrgName();
	}

}

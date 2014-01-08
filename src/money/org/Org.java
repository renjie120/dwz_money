package money.org;

import dwz.framework.core.business.BusinessObject;

public interface Org extends BusinessObject {

	public Integer getId();

	public int getOrgId();

	public String getOrgName();

	public String getOrderCode();

	public String getParentOrg();

	public String getOrderId();

	public String getParentOrgName();

}

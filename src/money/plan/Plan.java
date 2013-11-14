package money.plan;

import java.util.Date;
import dwz.framework.core.business.BusinessObject;

public interface Plan extends BusinessObject {

	public Integer getId();

	public int getPlanId();

	public Date getPlanDate();

	public Date getStartDate();

	public Date getEndDate();

	public Date getRealStartDate();

	public Date getRealEndDate();

	public String getPlanDesc();

	public int getPlanType();

	public int getPlanStatus();

	public int getUserId();

	public String getPlanTypeName();

	public String getPlanStatusName();

}

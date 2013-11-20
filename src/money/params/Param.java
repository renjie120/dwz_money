package money.params;

import dwz.framework.core.business.BusinessObject;

public interface Param extends BusinessObject {

	public Integer getId();

	public int getParameterType();

	public String getParameterTypeName();

	public String getParameterName();

	public int getOrderId();

	public String getUseValue();

	public int getParameterValue();

	public int getParameterID();
}

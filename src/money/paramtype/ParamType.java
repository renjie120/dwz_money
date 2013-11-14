package money.paramtype;

import dwz.framework.core.business.BusinessObject;

public interface ParamType extends BusinessObject {

	public Integer getId();

	public int getParameterTypeId();

	public String getParameterTypeName();
	
	public String getCode();

	public int getOrderId();
}

package money.moneytype;

import dwz.framework.core.business.BusinessObject;

public interface MoneyType extends BusinessObject {

	public Integer getId();

	public int getMoenyTypeSno();

	public String getMoneyTypeDesc();

	public String getMoneyType();

	public String getParentCode();

	public String getTypeCode();

	public int getOrderId();

}

package money.detail;

import java.util.Date;

import dwz.framework.core.business.BusinessObject;

public interface Money extends BusinessObject {

	public Integer getId();

	public Date getMoneyTime();

	public double getMoney();

	public String getMoneyType();
	
	public double getRealMoney();
	
	public Integer getSplitSno();
	
	public String getMoneyTypeName();

	public String getMoneyDesc();

	public int getShopCard();

	public String getBookType();
	
	/**
	 * 返回同步状态码.
	 * @return
	 */
	public String getCode();
}

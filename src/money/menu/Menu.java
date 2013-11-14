package money.menu;

import dwz.framework.core.business.BusinessObject;

public interface Menu extends BusinessObject {

	public Integer getId();

	public int getMenuId();

	public String getMenuName();

	public String getUrl();

	public String getTarget();

	public int getParentId();

	public String getLevel();

	public int getOrderId();

	public String getRelId();

}

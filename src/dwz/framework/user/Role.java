package dwz.framework.user;

import dwz.framework.core.business.BusinessObject;

public interface Role extends BusinessObject {
	public Integer getId();
	
	public String getName();

	public String getDescription();

	public boolean isSelected();

}

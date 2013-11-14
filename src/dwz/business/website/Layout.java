package dwz.business.website;

import dwz.framework.core.business.BusinessObject;

public interface Layout extends BusinessObject {
	String getName();

	String getCss();

	String getDescription();

}

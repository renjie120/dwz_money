package dwz.business.website;

import dwz.framework.core.business.BusinessObject;

public interface Theme extends BusinessObject {
	String getName();

	String getCss();

	String getLabel();

	String getDescription();

}

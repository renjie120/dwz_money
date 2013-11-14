package dwz.framework.core.business;

import java.io.Serializable;

public interface BusinessObject extends Serializable {

	java.io.Serializable getId();

	void copyProperties(BusinessObject orig);
}

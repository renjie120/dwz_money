package dwz.framework.core.business;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dwz.framework.config.Configuration;
import dwz.framework.context.AppContext;
import dwz.framework.context.AppContextHolder;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.User;

public abstract class AbstractBusinessObject implements BusinessObject {

	protected static Log log = null;

	public AbstractBusinessObject() {
		log = LogFactory.getLog(this.getClass());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (this.hashCode() == o.hashCode()) {
			return true;
		}

		if (o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			if (this.getId() != null && this.getId().equals(bo.getId())) {
				return true;
			}
		}

		return false;
	}

	protected Configuration getAppConfig() {
		return BusinessFactory.getFactory().retrieveConfiguration();
	}

	protected User getContextUser() {
		AppContext context = AppContextHolder.getContext();
		if (context != null)
			return context.getUser();
		return null;
	}

	public void copyProperties(BusinessObject orig) {
		try {
			PropertyUtils.copyProperties(this, orig);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
}

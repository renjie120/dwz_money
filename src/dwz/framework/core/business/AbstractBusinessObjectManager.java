package dwz.framework.core.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dwz.framework.config.Configuration;
import dwz.framework.context.AppContext;
import dwz.framework.context.AppContextHolder;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.User;

public abstract class AbstractBusinessObjectManager implements
		BusinessObjectManager {

	protected static Log log = null;

	public AbstractBusinessObjectManager() {
		log = LogFactory.getLog(this.getClass());
	}

	protected User getContextUser() {
		AppContext context = AppContextHolder.getContext();
		if (context != null)
			return context.getUser();
		return null;
	}

	protected Configuration getAppConfig() {
		return BusinessFactory.getFactory().retrieveConfiguration();
	}
	
}

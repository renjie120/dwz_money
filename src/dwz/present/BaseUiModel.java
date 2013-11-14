package dwz.present;

import dwz.framework.config.Configuration;
import dwz.framework.context.AppContext;
import dwz.framework.context.AppContextHolder;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.User;

import com.opensymphony.xwork2.ActionContext;

public abstract class BaseUiModel {

	protected static BusinessFactory	bf;
	protected static Configuration		appConfig;

	static {
		bf = BusinessFactory.getFactory();
		appConfig = bf.retrieveConfiguration();
	}

	public User getUser() {
		AppContext appContext = AppContextHolder.getContext();
		if (appContext == null)
			return null;
		return appContext.getUser();
	}

	public String getAppLanguage() {
		return ActionContext.getContext().getLocale().getLanguage();
	}

}

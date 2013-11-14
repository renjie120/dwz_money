package dwz.framework.el;

import javax.servlet.http.HttpServletRequest;

import dwz.framework.config.Configuration;
import dwz.framework.context.AppContext;
import dwz.framework.context.AppContextHolder;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.User;

public class ServerInfo {
	public static boolean isAjax(HttpServletRequest request) {
		if (request != null
				&& "XMLHttpRequest".equalsIgnoreCase(request
						.getHeader("X-Requested-With")))
			return true;
		return false;
	}

	public static User getUser() {
		AppContext context = AppContextHolder.getContext();
		if (context == null) {
			return null;
		}

		return context.getUser();
	}

	public static String getSystemServer() {
		return getConfig().getSystemServer();
	}

	public static String getStaticServer() {
		return getConfig().getStaticServer();
	}

	public static String getWWWServer() {
		return getConfig().getWWWServer();
	}

	public static String getManagementServer() {
		return getConfig().getManagementServer();
	}

	public static String getSystemSecureServer() {
		return getConfig().getSystemSecureServer();
	}

	public static String getWWWSecureServer() {
		return getConfig().getWWWSecureServer();
	}

	public static String getManagementSecureServer() {
		return getConfig().getManagementSecureServer();
	}

	public static String getStaticContentUri() {
		return getConfig().getStaticContentUri();
	}

	public static String getStaticPageUri() {
		return getConfig().getStaticPageUri();
	}

	public static String getStaticPageUrl() {
		Configuration config = getConfig();
		return config.getStaticServer() + config.getStaticPageUri();
	}

	public static String getDomain() {
		return getConfig().getDomain();
	}

	public static String getDomainWithoutFD() {
		Configuration config = getConfig();
		String domain = config.getDomain();
		if (domain != null && !"".equals(domain))
			return domain.substring(1);

		return "";
	}

	private static Configuration getConfig() {
		BusinessFactory factory = BusinessFactory.getFactory();
		Configuration config = factory.retrieveConfiguration();
		return config;
	}

}

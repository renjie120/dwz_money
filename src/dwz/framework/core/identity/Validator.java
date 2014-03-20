/***********************************************************************
 * Module:  Validator.java
 * Author:  Zhang Huihua
 * Purpose: Defines the Interface Validator
 ***********************************************************************/

package dwz.framework.core.identity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dwz.framework.config.Configuration;
import dwz.framework.constants.Constants;
import dwz.framework.constants.user.UserType;
import dwz.framework.context.AppContext;
import dwz.framework.context.AppContextHolder;
import dwz.framework.context.DefaultAppContext;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.core.identity.impl.SessionIdentity;
import dwz.framework.user.User;

public class Validator implements IdentityProvider {

	private static final Log log = LogFactory.getLog(Validator.class);

	private static ThreadLocal<Validator> validatorHolder = new ThreadLocal<Validator>() {

		@Override
		protected Validator initialValue() {
			return new Validator();
		}

	};

	//有效的用户类型.
	private static final List<UserType> availableTypes = new ArrayList<UserType>();

	static {
		BusinessFactory factory = BusinessFactory.getFactory();
		Configuration config = factory.retrieveConfiguration();
		List<?> type = config.getList(Constants.AVAILABLE_USER_TYPE);
		if (type != null && type.size() > 0) {
			for (Object userType : type) {
				System.out.println("user type " + userType.toString());
				availableTypes
						.add(UserType.valueOf(((String) userType).trim()));
			}
		}
	}

	private HttpSession session = null;

	private User user = null;

	private Validator() {
	}

	public static Validator getInstance() {
		return validatorHolder.get();
	}

	public void init(HttpSession session) {
		this.session = session;
	}

	public boolean validate() {
		log.debug("will validate session.");
		if (session == null) {
			log.warn("the session is null.");
			return false;
		}

		boolean expired = false;

		try {
			this.user = (User) session
					.getAttribute(Constants.AUTHENTICATION_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (this.user == null) {
			expired = true;
		}
		if (!expired) {
			if (log.isDebugEnabled()) {
				log.debug("validating session successfully.");
			}
			if (this.user != null) {
				if (log.isDebugEnabled()) {
					for (UserType type : availableTypes) {
						log.debug("available type is: " + type.toString());
					}
				}
				if (!availableTypes.contains(this.user.getUserType())) {
					return false;
				}
			}

			log
					.debug("validating available user type successfully. user type is: "
							+ this.user.getUserType());

			log.debug("validate account successfully.");

			return true;
		}

		log.debug("validating session failed.");

		return false;
	}

	public void confirm() {
		if (this.user == null) {
			throw new IllegalArgumentException("authentication is null.");
		}

		AppContext context = AppContextHolder.getContext();
		if (context == null) {
			context = new DefaultAppContext(user);
			AppContextHolder.setContext(context);
		} else {
			context.setUser(user);
		}
	}

	public void cancel() {
		this.session = null;
		this.user = null;
		AppContext context = AppContextHolder.getContext();
		if (context != null) {
			context.setUser(null);
		}
	}

	public void clear() {
		this.session = null;
		this.user = null;
		AppContext context = AppContextHolder.getContext();
		if (context != null) {
			context.setUser(null);
		}
	}

	public Identity createIdentity(String identityString) {
		if (identityString == null) {
			return null;
		}

		return new SessionIdentity(identityString);
	}
}
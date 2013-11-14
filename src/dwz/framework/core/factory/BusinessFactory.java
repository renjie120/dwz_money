package dwz.framework.core.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import dwz.business.setup.SetupManager;
import dwz.constants.BeanDaoKey;
import dwz.constants.BeanManagerKey;
import dwz.dal.BaseDao;
import dwz.framework.config.Configuration;
import dwz.framework.constants.Constants;
import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.http.session.mdb.mysqldb.schedule.SessionManager;
import dwz.framework.mail.MailManager;
import dwz.framework.user.RoleManager;
import dwz.framework.user.UserManager;

public abstract class BusinessFactory {

	private static BusinessFactory factory = null;

	protected static final Log log = LogFactory.getLog(BusinessFactory.class);

	public static BusinessFactory getFactory() {

		if (factory == null) {
			log
					.fatal("Business factory has not been inited. the system does not correctly run.");

			throw new NullPointerException("Factory has not been inited.");
		}

		return factory;
	}

	public static synchronized BusinessFactory init(Configuration config) {
		String factoryClass = config.getFactoryClass();
		if (factoryClass == null) {
			factoryClass = System.getProperty(Constants.FACTORY_CLASS_KEY);
		}

		if (factoryClass == null) {
			factoryClass = Constants.FACTORY_CLASS;
		}
		log.debug("The business factory class is " + factoryClass);

		Class<?> clazz = null;
		try {
			clazz = Class.forName(factoryClass);
			factory = (BusinessFactory) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			log.fatal("factory class " + factoryClass + " was not found", e);
		} catch (InstantiationException e) {
			log
					.fatal("factory class " + factoryClass
							+ " was not instanted", e);
		} catch (IllegalAccessException e) {
			log
					.fatal("factory class " + factoryClass
							+ " was not instanted", e);
		}

		try {
			Method method = factory.getClass().getMethod("injectConfiguration",
					new Class[] { Configuration.class });
			method.invoke(factory, new Object[] { config });
		} catch (SecurityException e) {
			log.error("method injectConfiguration() was security.", e);
		} catch (NoSuchMethodException e) {
			log.error("no such method injectConfiguration().", e);
		} catch (IllegalArgumentException e) {
			log.error(
					"method injectConfiguration()'s arguments does not match.",
					e);
		} catch (IllegalAccessException e) {
			log
					.error(
							"There are some errors in visiting method injectConfiguration().",
							e);
		} catch (InvocationTargetException e) {
			log
					.error(
							"There are some errors in visiting method injectConfiguration().",
							e);
		}

		return factory;

	}

	public abstract Configuration retrieveConfiguration();

	public abstract ApplicationContext getApplicationContext();

	public abstract <T extends BaseDao<?, ?>> T getDao(BeanDaoKey beanDaoKey);

	public abstract <T extends BusinessObjectManager> T getManager(
			BeanManagerKey beanManagerKey);

	public abstract SessionManager getSessionManager();

	public abstract UserManager getUserManager();

	public abstract RoleManager getRoleManager();

	public abstract MailManager getMailmanager();

	public abstract SetupManager getSetupManager();

}

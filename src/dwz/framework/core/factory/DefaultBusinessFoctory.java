package dwz.framework.core.factory;

import org.springframework.context.ApplicationContext;

import dwz.business.setup.SetupManager;
import dwz.constants.BeanDaoKey;
import dwz.constants.BeanManagerKey;
import dwz.dal.BaseDao;
import dwz.framework.config.Configuration;
import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.http.session.mdb.mysqldb.schedule.SessionManager;
import dwz.framework.mail.MailManager;
import dwz.framework.syslog.SystemLogManager;
import dwz.framework.user.RoleManager;
import dwz.framework.user.UserManager;

public class DefaultBusinessFoctory extends BusinessFactory {

	private Configuration	config	= null;

	public DefaultBusinessFoctory() {
	}

	public Configuration retrieveConfiguration() {
		return this.config;
	}

	public ApplicationContext getApplicationContext() {
		return this.config.getApplicationContext();
	}


	protected Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	public void injectConfiguration(Configuration config) {
		if (this.config != null) {
			this.config.clear();
			this.config = null;
			System.gc();
		}

		this.config = config;

	}

	@SuppressWarnings("unchecked")
	public <T extends BaseDao<?, ?>> T getDao(BeanDaoKey beanDaoKey) {
		return (T) getBean(beanDaoKey.toString());
	}

	@SuppressWarnings("unchecked")
	public <T extends BusinessObjectManager> T getManager(BeanManagerKey beanManagerKey) {
		return (T) getBean(beanManagerKey.toString());
	}

	public SystemLogManager getSystemLogManager() {
		return (SystemLogManager) getBean(BeanManagerKey.systemLogManager.toString());
	}

	public SessionManager getSessionManager() {
		return (SessionManager) getBean(BeanManagerKey.sessionManager.toString());
	}

	public UserManager getUserManager() {
		return (UserManager) getBean(BeanManagerKey.userManager.toString());
	}

	public RoleManager getRoleManager() {
		return (RoleManager) getBean(BeanManagerKey.roleManager.toString());
	}

	public MailManager getMailmanager() {
		return (MailManager) getBean(BeanManagerKey.mailManager.toString());
	}

	public SetupManager getSetupManager() {
		return (SetupManager) getBean(BeanManagerKey.setupManager.toString());
	}

}
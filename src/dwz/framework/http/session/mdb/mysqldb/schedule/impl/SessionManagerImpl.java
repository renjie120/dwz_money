package dwz.framework.http.session.mdb.mysqldb.schedule.impl;

import dwz.framework.config.Configuration;
import dwz.framework.constants.Constants;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.http.session.mdb.mysqldb.schedule.Session;
import dwz.framework.http.session.mdb.mysqldb.schedule.SessionException;
import dwz.framework.http.session.mdb.mysqldb.schedule.SessionManager;
import dwz.framework.timer.TaskEngine;

public class SessionManagerImpl extends AbstractBusinessObjectManager implements
		SessionManager {

	public void clearSession() throws SessionException {
		BusinessFactory factory = BusinessFactory.getFactory();
		Configuration config = factory.retrieveConfiguration();
		Session session = config.getSession();
		
		if (session != null && session.isAutoEnable()) {
			TaskEngine.scheduleTask(session, session.getAutoInterval()
					* Constants.MINUTE, session.getAutoInterval()
					* Constants.MINUTE);
		}

	}

}

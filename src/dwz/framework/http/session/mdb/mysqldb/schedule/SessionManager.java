package dwz.framework.http.session.mdb.mysqldb.schedule;

import dwz.framework.core.business.BusinessObjectManager;

public interface SessionManager extends BusinessObjectManager {
	
	public void clearSession() throws SessionException;

}

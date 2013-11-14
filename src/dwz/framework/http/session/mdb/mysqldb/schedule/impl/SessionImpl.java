package dwz.framework.http.session.mdb.mysqldb.schedule.impl;

import java.util.Date;

import dwz.constants.BeanDaoKey;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.http.session.mdb.mysqldb.schedule.Session;
import dwz.framework.http.session.mdb.mysqldb.schedule.SessionException;
import dwz.framework.timer.TaskEngine;
import dwz.persistence.daos.SysSessionDao;

public class SessionImpl implements Session {

	private String name;
	private int autoInterval;
	private int timeout;
	private boolean autoEnable;

	private boolean busy;
	private Object lock;

	private SysSessionDao dao = null;

	public SessionImpl(String name, int autoInterval, int timeout,
			boolean autoEnable) {
		this.name = name;
		this.autoInterval = autoInterval;
		this.timeout = timeout;
		this.autoEnable = autoEnable;

		this.busy = false;
		this.lock = new Object();

		SysSessionDao sysSessionDao = BusinessFactory.getFactory().getDao(BeanDaoKey.sysSessionDao);

		this.dao = sysSessionDao;
	}

	public String getName() {
		return this.name;
	}

	public int getAutoInterval() {
		return this.autoInterval;
	}

	public int getTimeout() {
		return this.timeout;
	}

	public boolean isAutoEnable() {
		return this.autoEnable;
	}

	protected final void clearSession() throws SessionException {
		if (this.getTimeout() <= 0) {
			return;
		}
		Date now = new Date();
		Date invalidTime = new Date(now.getTime() - this.getTimeout() * 60
				* 1000);

		this.dao.deleteAllTimeout(invalidTime);

	}

	public synchronized void run() {
		TaskEngine.addTask(new SessionTask());
	}

	protected class SessionTask implements Runnable {

		public void run() {
			synchronized (lock) {
				if (busy) {
					return;
				}
				busy = true;

				try {
					clearSession();
				} catch (SessionException e) {
					e.printStackTrace();
				} finally {
					busy = false;
				}
			}
		}

	}

}

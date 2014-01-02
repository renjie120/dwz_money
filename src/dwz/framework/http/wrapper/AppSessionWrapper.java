package dwz.framework.http.wrapper;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import dwz.constants.BeanDaoKey;
import dwz.constants.BeanManagerKey;
import dwz.framework.constants.Constants;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.persistence.beans.SysSession;
import dwz.persistence.daos.SysSessionDao;

public class AppSessionWrapper implements HttpSession {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1052838029721481656L;

	private HttpSession session = null;

	private SysSessionDao dao = null;

	private String token = null;

	private String sessionIp = null;

	public AppSessionWrapper(HttpSession session, String key) {
		this.session = session;
		this.token = key;

		SysSessionDao sysSessionDao = BusinessFactory.getFactory().getDao(BeanDaoKey.sysSessionDao);

		dao = sysSessionDao;

		Collection<String> ids = null;

		try {
			ids = dao.findRecordById(this.token);
		} catch (Exception e) {
			System.out.println(e + " AppSessionWrapper()");
		}

		if (ids != null && ids.size() > 0) {
			dao.updateAllaccessDate(new Date(), this.token);
		}
	}

	public Object getAttribute(String key) {
		if (Constants.AUTHENTICATION_KEY.equals(key)) {
			User user = null;
			try {
				UserManager uMgr = BusinessFactory.getFactory().getManager(
						BeanManagerKey.userManager);
				SysSession sysSession = this.getSysSession();
				if (sysSession != null)
					user = uMgr.getUser(sysSession.getUserId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
		return this.session.getAttribute(key);
	}

	@SuppressWarnings("unchecked")
	public Enumeration<String> getAttributeNames() {
		return this.session.getAttributeNames();
	}

	public long getCreationTime() {
		return this.session.getCreationTime();
	}

	public String getId() {
		return this.session.getId();
	}

	public long getLastAccessedTime() {
		return this.session.getLastAccessedTime();
	}

	public int getMaxInactiveInterval() {
		return this.session.getMaxInactiveInterval();
	}

	public ServletContext getServletContext() {
		return this.session.getServletContext();
	}

	/**
	 * @deprecated
	 */
	public HttpSessionContext getSessionContext() {
		return this.session.getSessionContext();
	}

	/**
	 * @deprecated
	 */
	public Object getValue(String arg0) {
		return this.session.getValue(arg0);
	}

	/**
	 * @deprecated
	 */
	public String[] getValueNames() {
		return this.session.getValueNames();
	}

	@SuppressWarnings("unchecked")
	public void invalidate() {
		this.session.invalidate();
		this.removeSysSession();
	}

	public boolean isNew() {
		return this.session.isNew();
	}

	/**
	 * @deprecated
	 */
	public void putValue(String arg0, Object arg1) {
		this.session.putValue(arg0, arg1);
	}

	public void removeAttribute(String key) {
		this.session.removeAttribute(key);
		if (Constants.AUTHENTICATION_KEY.equals(key)) {
			this.removeSysSession();
		}
	}

	/**
	 * @deprecated
	 */
	public void removeValue(String arg0) {
		this.session.removeValue(arg0);
	}

	public void setAttribute(String key, Object value) {
		if (value == null) {
			throw new NullPointerException("session value must not be null.");
		}
		this.session.setAttribute(key, value);

		if (Constants.AUTHENTICATION_KEY.equals(key) && value instanceof String) {
			this.setSysSession(value.toString());
		}
	}

	public void setMaxInactiveInterval(int arg0) {
		this.session.setMaxInactiveInterval(arg0);
	}

	private SysSession getSysSession() {
		Collection<String> ids = null;
		SysSession sysSession = null;

		ids = dao.findRecordById(this.token);
		if (ids != null && ids.size() > 0) {
			sysSession = dao.findByPrimaryKey(ids.iterator().next());
		}

		return sysSession;
	}

	private void removeSysSession() {

		dao.deleteAllById(this.token);
	}

	private void setSysSession(String userId) {

		SysSession sysSession = this.getSysSession();

		if (sysSession != null) {

			sysSession.setUserId(userId);
			dao.update(sysSession);

		} else {
			Date now = new Date();
			sysSession = new SysSession(this.token, userId, this.sessionIp,
					now, now);

			dao.insert(sysSession);

		}
	}

	public String getSessionIp() {
		return sessionIp;
	}

	public void setSessionIp(String sessionIp) {
		this.sessionIp = sessionIp;
	}

}

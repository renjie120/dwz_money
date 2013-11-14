package dwz.framework.http.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import dwz.framework.http.AppHttpRequestExt;

public class AppHttpRequestWrapper extends HttpServletRequestWrapper
		implements AppHttpRequestExt {

	private String token = null;
	private AppSessionWrapper sessionWrapper = null;

	public AppHttpRequestWrapper(HttpServletRequest request, String token) {
		super(request);
		this.token = token;
	}

	@Override
	public HttpSession getSession() {
		if (sessionWrapper != null) {
			return sessionWrapper;
		}
		sessionWrapper = new AppSessionWrapper(super.getSession(),
				this.token);
		sessionWrapper.setSessionIp(this.getRemoteAddr());
		return sessionWrapper;
	}

	@Override
	public HttpSession getSession(boolean arg0) {
		return this.getSession();
	}

}

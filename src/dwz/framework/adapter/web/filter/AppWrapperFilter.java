package dwz.framework.adapter.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dwz.framework.config.Configuration;
import dwz.framework.constants.Constants;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.http.wrapper.AppHttpRequestWrapper;
import dwz.framework.http.wrapper.AppHttpResponseWrapper;

public class AppWrapperFilter implements Filter {

	private String domain = "";

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String sid = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (Constants.AUTHENTICATION_TOKEN.equals(cookie.getName())) {
					sid = cookie.getValue();
					break;
				}
			}
		}

//		String domainName = request.getServerName().toLowerCase();
//		if (domainName.startsWith("www.")) {
//			domainName = domainName.replaceFirst("www.", "");
//		}

		if (sid == null
				|| request.getParameter(Constants.AUTHENTICATION_TOKEN) != null) {
			String authStr = request
					.getParameter(Constants.AUTHENTICATION_TOKEN);
			if (authStr != null) {
				sid = authStr;

				Cookie cookie = new Cookie(Constants.AUTHENTICATION_TOKEN, sid);
//				cookie.setDomain(domainName.endsWith(this.domain) ? this.domain
//						: domainName);
				cookie.setMaxAge(-1);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		if (sid == null) {
			sid = request.getSession().getId();
			Cookie cookie = new Cookie(Constants.AUTHENTICATION_TOKEN, sid);
//			cookie.setDomain(domainName.endsWith(this.domain) ? this.domain
//					: domainName);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
		}

		AppHttpRequestWrapper requestWrapper = new AppHttpRequestWrapper(
				request, sid);
		AppHttpResponseWrapper responseWrapper = new AppHttpResponseWrapper(
				response);
		try {
			chain.doFilter(requestWrapper, responseWrapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		BusinessFactory factory = BusinessFactory.getFactory();
		Configuration config = factory.retrieveConfiguration();
		this.domain = config.getString(Constants.DOMAIN_KEY);
		
	}
}

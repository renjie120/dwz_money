package dwz.framework.adapter.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.StrutsRequestWrapper;

import dwz.framework.el.ServerInfo;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

public class SiteMeshWrapperFilter extends SiteMeshFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if (ServerInfo.isAjax(request) || request.getParameter("ajax") != null) {
			
			chain.doFilter(req, res);
		} else {
			StrutsRequestWrapper sreq = new StrutsRequestWrapper(request);
			super.doFilter(sreq, res, chain);
		}

	}

}

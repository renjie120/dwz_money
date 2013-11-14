package dwz.framework.http.wrapper;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import dwz.framework.http.AppHttpResponseExt;

public class AppHttpResponseWrapper extends HttpServletResponseWrapper
		implements AppHttpResponseExt {

	public AppHttpResponseWrapper(HttpServletResponse response) {
		super(response);
	}

}

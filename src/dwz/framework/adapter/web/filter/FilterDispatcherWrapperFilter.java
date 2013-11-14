package dwz.framework.adapter.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.struts2.dispatcher.FilterDispatcher;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;

public class FilterDispatcherWrapperFilter extends FilterDispatcher {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if(req instanceof StrutsRequestWrapper){
			StrutsRequestWrapper srw =  (StrutsRequestWrapper)req;
			req = srw.getRequest();
		}
		//chain.doFilter(req, res);
		super.doFilter(req, res, chain);
	}

}

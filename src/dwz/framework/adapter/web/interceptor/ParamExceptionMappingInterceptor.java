package dwz.framework.adapter.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

public class ParamExceptionMappingInterceptor extends
		ExceptionMappingInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6527523502782222330L;

	protected String findResultFromExceptions(
			List<ExceptionMappingConfig> exceptionMappings, Throwable t) {
		System.out.println("ParamExceptionMappingInterceptor: " + t);

		if (t instanceof RuntimeException) {
			t.printStackTrace();
		}
		
//		String message = t.getLocalizedMessage();
//
//		if (message == null || message.length() < 1) {
//			Object o = ActionContext.getContext().getActionInvocation()
//					.getProxy().getAction();
//			if (o instanceof ActionSupport) {
//				message = ((ActionSupport) o).getText("msg.operation.failure");
//			}
//		}

		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("statusCode", 300);
		req.setAttribute("message", t);

		return super.findResultFromExceptions(exceptionMappings, t);
	}
}

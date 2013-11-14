package dwz.framework.core.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import dwz.framework.core.factory.BusinessFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseBusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8598824945784805206L;

	private String[] args;

	public BaseBusinessException() {
	}

	public BaseBusinessException(String message) {
		super(message);
	}

	public BaseBusinessException(Throwable cause) {
		super(cause);
	}

	public BaseBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getLocalizedMessage() {
		ActionContext actionContext = ActionContext.getContext();
		String message = getMessage();
		if (message != null && actionContext != null) {
			Object action = ActionContext.getContext().getActionInvocation()
					.getProxy().getAction();
			if (action instanceof ActionSupport) {
				ActionSupport actionSupport = (ActionSupport) action;
				return actionSupport.getText(message.toString(), args);
			}
		}
		return message;
	}

	protected void setArgs(String[] args) {
		this.args = args;
	}
}

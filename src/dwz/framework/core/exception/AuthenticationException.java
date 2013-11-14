package dwz.framework.core.exception;

public class AuthenticationException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6585782534418419709L;

	public AuthenticationException() {
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

	public AuthenticationException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public AuthenticationException(String messageKey) {
		super(messageKey);
	}

	public AuthenticationException(String messageKey, String[]args) {
		super(messageKey, args);
	}
	
	public AuthenticationException(String messageKey, String[]args, Throwable cause) {
		super(messageKey, args, cause);
	}

}

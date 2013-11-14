package dwz.framework.core.exception;

public class BusinessException extends BaseBusinessException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3686124829493398192L;

	public BusinessException() {
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, String[]args) {
		super(message);
		this.setArgs(args);
	}
	
	public BusinessException(String message, String[]args, Throwable cause) {
		super(message, cause);
		this.setArgs(args);
	}
}

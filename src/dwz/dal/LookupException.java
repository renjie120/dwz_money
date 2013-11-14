package dwz.dal;

public class LookupException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7604076762306775439L;

	public LookupException() {
	}

	public LookupException(String message) {
		super(message);
	}

	public LookupException(Throwable cause) {
		super(cause);
	}

	public LookupException(String message, Throwable cause) {
		super(message, cause);
	}

}

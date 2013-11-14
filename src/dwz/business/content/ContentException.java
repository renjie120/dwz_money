package dwz.business.content;

public class ContentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3624493867329646791L;

	public ContentException() {
	}

	public ContentException(String message) {
		super(message);
	}

	public ContentException(Throwable cause) {
		super(cause);
	}

	public ContentException(String message, Throwable cause) {
		super(message, cause);
	}

}

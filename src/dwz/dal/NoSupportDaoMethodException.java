package dwz.dal;

public class NoSupportDaoMethodException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 585802174881322393L;

	public NoSupportDaoMethodException() {
		super();
	}

	public NoSupportDaoMethodException(String errorMsg) {
		super(errorMsg);
	}

	public NoSupportDaoMethodException(Exception ex) {
		super(ex);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}

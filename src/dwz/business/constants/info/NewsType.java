package dwz.business.constants.info;

public enum NewsType {
	NEWS("News"), ANNOUNCEMENT("Announcement");
	private final String value;

	NewsType(String value) {
		this.value = value;
	}

	public String getId() {
		return this.toString();
	}

	public String getName() {
		return value;
	}
}

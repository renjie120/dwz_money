package dwz.business.constants.info;

public enum NewsStatus {
	PENDING("Unpublished"), ACTIVE("Published"), DISABLED("Disabled");
	
	private String value;
	
	NewsStatus(String value) {
		this.value = value;
	}
	
	public String getId() {
		return this.toString();
	}
	
	public String getName() {
		return this.value;
	}
}

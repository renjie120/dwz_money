package dwz.framework.constants.user;

public enum UserType {
	
	SUPER("Super"), ADMIN("Admin"), PERSON("Person");

	private final String name;

	UserType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public String toString(){ 
		return this.ordinal()+"";
	}
}

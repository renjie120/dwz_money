package money.user;

public enum UserType {
	ADMIN("1"),USER("2");
	private String type;
	UserType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
}

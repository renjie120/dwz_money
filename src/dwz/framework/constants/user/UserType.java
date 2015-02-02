package dwz.framework.constants.user;

public enum UserType {

	SUPER("Super"), ADMIN("Admin"), COMPANY("Company"), UNIT("Unit"), GROUP(
			"Group"), SHOPMAN("Shopman"), SHOP("Shop"), IDO("Ido"), Guest(
			"Guest");

	private final String name;

	UserType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		switch (this.ordinal()) {
		case 0:
			return "超级管理员"; 
		case 1:
			return "管理员"; 
		case 2:
			return "保险公司用户"; 
		case 3:
			return "投保单位用户"; 
		case 4:
			return "商家集团用户"; 
		case 5:
			return "商家用户"; 
		case 6:
			return "商铺用户"; 
		case 7:
			return "爱都内部用户"; 
		default:
			return "游客"; 
		} 
	}
}

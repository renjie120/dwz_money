package common.base;

public enum AllSelectContants {
	QUESTION_SORT("questionSort"), 
	MENU_LEVEL("menulevel"), 
	PLAN_STATUS("planstatus"),
	PLAN_TYPE("plantype"),
	MENU_TARGET("menutarget"),  
	QUESTION_STATUS("questionStatus");
	private String name;

	AllSelectContants(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

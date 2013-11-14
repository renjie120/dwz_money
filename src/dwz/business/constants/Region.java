package dwz.business.constants;

import java.util.ArrayList;

public enum Region {
	EAW("England & Wales"),
	NIR("Northern Ireland");
//	SCT("Scotland");
	private final String name;

	Region(String name) {
		this.name = name;
	}

	public String getId() {
		return this.toString();
	}

	public String getName() {
		return this.name;
	}



}
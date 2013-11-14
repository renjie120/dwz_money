package dwz.business.constants;

public enum BuildingType {
	DOMESTIC("Domestic"), NON_DOMESTIC("Non-Domestic");
	private String name;
	BuildingType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}

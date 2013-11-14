package dwz.business.constants.content;

public enum FileType {
	Additional_Evidence("Additional Evidence"),
	Floor_Plans("Floor Plans"),
	Photographs("Photographs"),
	Field_Sheets("Field Sheets"),
	
	Report1("Report 1"),
	Report2("Report 2"),
	Report3("Report 3"),
	Report4("Report 4"),
	Report5("Report 5"),
	Additional_Doc("Additional Documentation");
	
	private String name;
	FileType(String name){
		this.name = name;
	}
	
	public String getId(){
		return this.toString();
	}
	public String getName(){
		return this.name;
	}
	
	public static FileType[] getFileTypesQa() {
		return new FileType[] { Additional_Evidence, Floor_Plans, Photographs,
				Field_Sheets };
	}

	public static FileType[] getFileTypesQfe() {
		return new FileType[] { Report1, Report2, Report3, Report4, Report5,
				Additional_Doc };
	}
}

package dwz.business.constants.website;

public enum PageTarget {
	header("Header"), 
//	sidebar("Sidebar"), 
	footer("Footer");

	PageTarget(String name) {
		this.name = name;
	}

	private String	name;

	public String getId() {
		return this.toString();
	}

	public String getName() {
		return this.name;
	}

}

package common.report;

public class Table {
	private String name;

	public Table(String name) {
		this.name = name;
	}

	public String getName() {
		return " " + name + " ";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return " " + this.name + " ";
	}
}

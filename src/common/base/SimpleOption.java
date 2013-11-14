package common.base;

public class SimpleOption implements SelectAble{
	private String value;
	private String text;
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOptionId() { 
		return this.value;
	}

	public String getOptionName() { 
		return this.text;
	}

}

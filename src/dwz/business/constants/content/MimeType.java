package dwz.business.constants.content;

public enum MimeType {
	IMG("img.jpg"), FLASH("flash.jpg"), PDF("pdf.jpg"), TXT("txt.jpg"), HTML(
			"html.jpg"), FILE("file.jpg");
	private final String	icon;

	MimeType(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return "/styles/icon/" + this.icon;
	}
}
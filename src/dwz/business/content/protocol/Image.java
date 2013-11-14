package dwz.business.content.protocol;

import dwz.business.constants.content.ResizeType;

public class Image {

	public String fileId;

	// public String name;
	// public int size;
	// public String parent;

	public int width;

	public int height;

	public ResizeType type;

	public ResizeType getType() {
		if (type != null) return type;
		return ResizeType.S;
	}

	public void setType(ResizeType type) {
		this.type = type;
	}

	public Image() {
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Image(String fileId) {
		this.fileId = fileId;
	}

	public Image(String fileId, int width, int height) {
		this.fileId = fileId;
		this.width = width;
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}

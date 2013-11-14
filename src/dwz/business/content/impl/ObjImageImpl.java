package dwz.business.content.impl;

import dwz.business.constants.content.ResizeType;
import dwz.business.content.ObjImage;
import dwz.framework.core.business.AbstractBusinessObject;

public class ObjImageImpl extends AbstractBusinessObject implements ObjImage {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4250514411464120785L;

	private FileInfoImpl		fileInfo			= null;

	public ObjImageImpl(FileInfoImpl fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getMainImageUrl() {
		String uri = getUrl();
		if (uri != null && uri.length() > 0) {
			return getAppConfig().getStaticContentUri()
					+ fileInfo.getImgPath(ResizeType.M);
		} else {
			return getAppConfig().getStaticContentUri() + "/imgnone.gif";
		}
	}

	public String getThumImageUrl() {
		String uri = getUrl();
		if (uri != null && uri.length() > 0) {
			return getAppConfig().getStaticContentUri()
					+ fileInfo.getImgPath(ResizeType.S);
		} else {
			return getAppConfig().getStaticContentUri() + "/thbnone.gif";
		}
	}

	public String getUrl() {
		if (fileInfo.isImage())
			return getAppConfig().getStaticContentUri() + fileInfo.getPath();
		return null;
	}

	public String getId() {
		return fileInfo.getId();
	}

}

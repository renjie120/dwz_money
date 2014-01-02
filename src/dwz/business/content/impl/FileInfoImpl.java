package dwz.business.content.impl;

import java.util.Date;

import dwz.business.constants.content.FileExt;
import dwz.business.constants.content.MimeType;
import dwz.business.constants.content.ResizeType;
import dwz.business.content.ContentManager;
import dwz.business.content.FileInfo;
import dwz.business.content.Folder;
import dwz.constants.BeanManagerKey;
import dwz.framework.config.Configuration;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.framework.utils.EnumUtils;
import dwz.framework.utils.PowerUtil;
import dwz.persistence.beans.ConFile;
import dwz.persistence.beans.ConFolder;

public class FileInfoImpl extends AbstractBusinessObject implements FileInfo {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8770804976771397939L;

	private ConFile				conFile;

	FileInfoImpl(ConFile conFile) {
		this.conFile = conFile;
	}

	FileInfoImpl(String name, String ext, String path, int size, Date now) {
		
		conFile = new ConFile(name, path, now, now);
		conFile.setExt(ext);
		conFile.setSize(size);
	}
	
	FileInfoImpl(String name, String ext, String path, int size, Date now, ConFolder folder) {
		
		conFile = new ConFile(name, path, now, now);
		conFile.setExt(ext);
		conFile.setSize(size);
		conFile.setConFolder(folder);
	}

	ConFile getConFile() {
		return conFile;
	}

	public Date getCreateDate() {
		return conFile.getInsertDate();
	}

	public String getName() {
		return conFile.getName();
	}

	public String getPath() {
		return conFile.getPath();
	}
	public String getAbsolutePath() {
		if (getPath() != null) {
			Configuration config = getAppConfig();
			return config.getStaticRootPath()+config.getStaticContentUri()+getPath();
		}
		return null;
	}
	
	public int getSize() {
		return conFile.getSize();
	}

	public boolean isImage() {
		FileExt ext = this.getExt();
		if (ext != null)
			return ext.isImage();
		return false;
	}

	public String getId() {
		return conFile.getId();
	}

	public Date getModifiedDate() {
		return conFile.getUpdateDate();
	}

	public FileExt getExt() {
		if (EnumUtils.isDefined(FileExt.values(), conFile.getExt()))
			return FileExt.valueOf(conFile.getExt());

		return null;
	}

	public MimeType getMimeType() {
		FileExt ext = getExt();
		if (ext != null) return ext.getMimeType();
		return MimeType.FILE;
	}
	
	public String getIcon() {
		if (isImage()) {
			int optPurview = ResizeType.S.ordinal();
			int totalPurview = conFile.getResize() != null ? conFile
					.getResize() : 0;
			if (PowerUtil.checkPower(totalPurview, optPurview)) {
				return getImgPath(ResizeType.S);
			} else {
				return MimeType.IMG.getIcon();
			}
		}
		return getMimeType().getIcon();
	}

	String getImgPath(ResizeType imgType) {
		String path = conFile.getPath();
		if (path == null || path.length() < 1)
			return null;
		return "/" + imgType.toString() + path;
	}

	public Folder getFolder() {
		ContentManager contentManager = BusinessFactory.getFactory().getManager(BeanManagerKey.contentManager);
		return contentManager.getFolder(conFile.getConFolder().getId());
	}
	public String getUrl(){
		return getAppConfig().getStaticContentUri()+getPath();
	}
	
	public User getUser(){
		UserManager userManager = BusinessFactory.getFactory().getManager(BeanManagerKey.userManager);
		return userManager.getUser(conFile.getUserId());
	}

//	public FileType getFileType() {
//		if (EnumUtils.isDefined(FileType.values(), conFile.getFileType()))
//			return FileType.valueOf(conFile.getFileType());
//		return null;
//	}

}

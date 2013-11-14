package dwz.business.content;

import java.util.Date;

import dwz.business.constants.content.FileExt;
import dwz.framework.core.business.BusinessObject;
import dwz.framework.user.User;

public interface FileInfo extends BusinessObject {
	public String getId();

	public String getName();

	public String getPath();
	
	public String getAbsolutePath();

	public int getSize();

	public Date getCreateDate();

	public Date getModifiedDate();

	public FileExt getExt();

	public String getIcon();

	public boolean isImage();

//	public Folder getFolder();
	
	public String getUrl();
	
	public User getUser();
	
}

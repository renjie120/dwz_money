package dwz.business.content;

import java.util.Collection;
import java.util.Date;

import dwz.framework.core.business.BusinessObject;
import dwz.framework.user.User;

public interface Folder extends BusinessObject {

	public String getId();
	
	public String getName();

	public String getPath();

	public int getSize();

	public Date getCreateDate();
	
	public User getInsertBy();

	public Folder getParentFolder();

	public Collection<Folder> getSubFolders();
	
	public Collection<FileInfo> getFiles();

	public boolean isHasFolders();

}

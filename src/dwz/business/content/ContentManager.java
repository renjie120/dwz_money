package dwz.business.content;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dwz.business.constants.content.FileSearchFields;
import dwz.business.content.protocol.Image;
import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.user.Role;
import dwz.dal.LookupException;

public interface ContentManager extends BusinessObjectManager {

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public FileInfo uploadFile(InputStream is, String fileName,  Folder folder)
			throws ContentException, IOException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public FileInfo uploadFile(String content, String fileName, Folder folder)
			throws ContentException, IOException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void removeFile(String fileId) throws ContentException, IOException;

	public FileInfo getFile(String fileId) throws LookupException;

	public void rename(String fileId, String name) throws LookupException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void drawImage(Collection<Image> images) throws IOException;

	public Collection<FileInfo> searchFiles(Map<FileSearchFields, String> criterias,
			FileSearchFields orderField, int startIndex, int count);
	
	public int searchFilesNum(Map<FileSearchFields, String> criterias,
			FileSearchFields orderField);
	
	public Folder getFolder(String folderId);
	
	public void addFolder(String name, Role role) throws LookupException;
	
	public void renameFolder(String folderId, String name) throws LookupException;
	
	public Collection<Folder> listFolder(Role role);
	
	public Collection<Folder> listAllFolder();
	
	public Collection<FileInfo> listFile(String folderId, String userId, int startIndex, int count);



}

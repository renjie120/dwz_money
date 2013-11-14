package dwz.business.content.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.commons.lang.time.DateUtils;

import dwz.business.constants.content.FileExt;
import dwz.business.constants.content.FileSearchFields;
import dwz.business.constants.content.ResizeType;
import dwz.business.content.ContentException;
import dwz.business.content.ContentManager;
import dwz.business.content.FileInfo;
import dwz.business.content.Folder;
import dwz.business.content.protocol.Image;
import dwz.framework.config.Configuration;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.user.Role;
import dwz.framework.user.User;
import dwz.framework.utils.DateUtil;
import dwz.framework.utils.EnumUtils;
import dwz.framework.utils.PowerUtil;
import dwz.framework.utils.StringUtils;
import dwz.persistence.beans.ConFile;
import dwz.persistence.beans.ConFolder;
import dwz.persistence.beans.SysRole;
import dwz.persistence.daos.ConFileDao;
import dwz.persistence.daos.ConFolderDao;
import dwz.persistence.daos.SysRoleDao;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.raster.JimiRasterImage;

import dwz.dal.LookupException;

public class ContentManagerImpl extends AbstractBusinessObjectManager implements
		ContentManager {

	protected static final int BUFFER_SIZE = 1024 * 4;
	
	protected static final int FILE_MAX_COUNT = 500;
	private static int _dayFileCount = -1;
	private static Date _fileCountTime = null;
	
	private ConFileDao conFileDao;
	private ConFolderDao conFolderDao;
	private SysRoleDao sysRoleDao;

	public ContentManagerImpl(ConFileDao conFileDao, ConFolderDao conFolderDao, SysRoleDao sysRoleDao) {
		this.conFileDao = conFileDao;
		this.conFolderDao = conFolderDao;
		this.sysRoleDao = sysRoleDao;
	}
	private int getDayFileCount(Date now){
		if (_dayFileCount < 0 || _fileCountTime == null
				|| !DateUtils.isSameDay(_fileCountTime, now)) {
			Date startDate = DateUtils.truncate(now, Calendar.DAY_OF_MONTH);
			Date endDate = DateUtils.round(now, Calendar.DAY_OF_MONTH);

			Collection<Number> nums = conFileDao.findBetweenDateFileCount(
					startDate, endDate);
			if (nums != null && nums.size() > 0)
				_dayFileCount = nums.iterator().next().intValue();
			else _dayFileCount = 0;
			
			_fileCountTime = now;
		}
		
		return _dayFileCount;
	}
	private String generatePath(String ext, Date now) {
		String currentDir = "/"+(getDayFileCount(now)/FILE_MAX_COUNT+1);
		
		Date dayStart = DateUtils.truncate(now, Calendar.DAY_OF_MONTH);
		String fileName = (now.getTime()-dayStart.getTime())+StringUtils.randomStr(2);
		if (ext != null && ext.length() > 0) {
			fileName += "."+ext;
		}
		
		StringBuilder path = new StringBuilder(DateUtil.date2String(now,
				"/yyyy/MM/dd")).append(currentDir).append("/")
				.append(fileName);

		return path.toString();
	}

	private void updateFileInfo(ConFile conFile) {
		conFile.setUpdateDate(new Date());
		conFileDao.update(conFile);
	}

	public void removeFile(String fileId) throws ContentException, IOException {
		Configuration config = getAppConfig();
		ConFile conFile = null;
		try {
			conFile = conFileDao.findByPrimaryKey(fileId);
		} catch (LookupException e) {
			throw new ContentException(e);
		}
		if (conFile == null)
			throw new ContentException();

		File file = new File(config.getStaticRootPath()+config.getStaticContentUri()+ conFile.getPath());
		System.out.println("delPath="+file.getPath());
		if (file.exists()) {
			if (file.delete()) {
				this.conFileDao.delete(conFile);
			}
		}
	}

	public FileInfo uploadFile(InputStream is, String fileName, Folder folder)
			throws ContentException, IOException {
		
		User user = getContextUser();
		if (user == null) throw new ContentException("User is null");
		Date now = new Date();
		
		String name = fileName, ext = "";
		int lastIndex = fileName.lastIndexOf(".");
		if (lastIndex >= 0) {
			name = fileName.substring(0, fileName.lastIndexOf("."));
			ext = fileName.substring(lastIndex + 1).toLowerCase();
		}
		if (name == null || name.length() < 1
				|| !EnumUtils.isDefined(FileExt.values(), ext)) {
			throw new ContentException("The format of your document is not allowed. We can only accept JPG/JPEG, PDF and TXT files.");
		}
		String path = generatePath(ext, now);
		Configuration config = getAppConfig();
		File file = new File(config.getStaticRootPath()+config.getStaticContentUri() + path);

		File parentFolder = file.getParentFile();
		if (!parentFolder.exists())
			parentFolder.mkdirs();

		if (!file.createNewFile()) {
			throw new ContentException("file create error.");
		}

		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			bis = new BufferedInputStream(is);

			byte[] buffer = new byte[BUFFER_SIZE];
			int num = 0;
			while ((num = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, num);
			}
			bos.flush();

			// save file info to DB
			ConFolder conFolder = conFolderDao.findByPrimaryKey(folder.getId());
			FileInfoImpl fileInfo = new FileInfoImpl(name,
					ext, path, (int) file.length(), now, conFolder);
			ConFile conFile = fileInfo.getConFile();
			if (user != null) conFile.setUserId(user.getId());
			conFileDao.insert(conFile);
			return fileInfo;

		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException: ", e);
			throw new ContentException(e);
		} catch (IOException e) {
			log.error("IOException: ", e);
			throw e;
		} finally {
			if (bos != null) {
				bos.close();
				bos = null;
			}
			if (bis != null) {
				bis.close();
				bis = null;
			}
		}

	}

	public FileInfo uploadFile(String content, String fileName, Folder folder)
			throws ContentException, IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(content.getBytes());
		return this.uploadFile(bais, fileName, folder);
	}

	public FileInfo getFile(String fileId) throws LookupException {
		ConFile conFile = conFileDao.findByPrimaryKey(fileId);
		return new FileInfoImpl(conFile);
	}

	public void rename(String fileId, String name) throws LookupException {

		ConFile conFile = conFileDao.findByPrimaryKey(fileId);
		if (conFile != null) {
			conFile.setName(name);
			this.updateFileInfo(conFile);
		}
	}

	public void drawImage(Collection<Image> images)
			throws IOException {
		if (images == null || images.size() < 1) {
			return;
		}
		Configuration config = getAppConfig();
		String rootPath = config.getStaticRootPath();

		for (Image image : images) {
			String fileId = image.getFileId();

			FileInfoImpl fileInfo = null;
			try {
				fileInfo = (FileInfoImpl) this.getFile(fileId);
			} catch (LookupException e1) {
				e1.printStackTrace();
			}
			if (fileInfo == null || !fileInfo.isImage())
				continue;

			ResizeType resizeType = image.getType();
			String path = fileInfo.getPath();
			String newPath = fileInfo.getImgPath(resizeType);

			File newFile = new File(rootPath + newPath);
			
			File resizeFolder = newFile.getParentFile();
			if(!resizeFolder.exists()){
				resizeFolder.mkdirs();
			}
			newFile.createNewFile();

			String srcPath = rootPath + path;
			File srcFile = new File(srcPath);

			java.awt.Image src = null;
			if ("bmp".equals(fileInfo.getExt())) {
				src = javax.imageio.ImageIO.read(new File(srcPath));
			} else {
				src = new ImageIcon(srcPath).getImage();
			}

			if (newFile == null
					|| src == null
					|| newFile.getAbsolutePath().equals(
							srcFile.getAbsolutePath())
					|| src.getWidth(null) <= 0)
				continue;

			int owidth = src.getWidth(null);
			int oheight = src.getHeight(null);

			double scaleW = (double) image.getWidth() / (double) owidth;
			double scaleH = (double) image.getHeight() / (double) oheight;
			double scale = scaleW < scaleH ? scaleW : scaleH;
			int width = (int) (scale * owidth);
			int height = (int) (scale * oheight);
			java.awt.Image thbimg = src.getScaledInstance(width, height,
					java.awt.Image.SCALE_SMOOTH);

			if (scale < 1) { // resize thb image
				FileOutputStream fos = null;
				JimiRasterImage raster = null;
				try {
					raster = Jimi.createRasterImage(thbimg.getSource());
					fos = new FileOutputStream(newFile);
					Jimi.putImage("image/jpeg", raster, fos);
					fos.flush();

					ConFile conFile = fileInfo.getConFile();
					int purview = PowerUtil.addPower(conFile.getResize(),
							resizeType.ordinal());
					conFile.setResize(purview);
					System.out.println("purview="+purview);
					System.out.println("resize="+conFile.getResize());
					this.updateFileInfo(conFile);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (fos != null) {
						fos.close();
						fos = null;
					}
				}

			}

		}

	}

	public long getUseAmount() throws ContentException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection<FileInfo> searchFiles(
			Map<FileSearchFields, String> criterias,
			FileSearchFields orderField, int startIndex, int count) {
		ArrayList<FileInfo> bos = new ArrayList<FileInfo>();
		if (criterias == null)
			return bos;

		Collection<ConFile> pos = conFileDao.findByQuery(this
				.createQueryString(false, criterias, orderField), startIndex,
				count);

		if (pos == null || pos.size() == 0)
			return bos;

		for (ConFile po : pos) {
			bos.add(new FileInfoImpl(po));
		}

		return bos;
	}
	
	public int searchFilesNum(
			Map<FileSearchFields, String> criterias,
			FileSearchFields orderField) {
		
		if (criterias == null) {
			return 0;
		}

		Number totalCount = conFileDao.countByQuery(this
				.createQueryString(true, criterias, orderField));

		return totalCount.intValue();
	}
	
	private String createQueryString(boolean useCount,
			Map<FileSearchFields, String> criterias, FileSearchFields orderBy) {
		StringBuilder sb = new StringBuilder();

		sb.append(useCount ? "select count(po) " : "select po ").append(
				"from ConFile as po, SysUser as sysUser where po.userId = sysUser.id");
		int count = 1;

		for (FileSearchFields field : FileSearchFields.values()) {
			switch (field) {

			case USER_ID:
				if (criterias.containsKey(field)) {
					sb.append(count > 0 ?" and":" where").append(" po.userId = '").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			case FOLDER:
				if (criterias.containsKey(field)) {
					sb.append(count > 0 ?" and":" where").append(" po.conFolder.id = '").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			case ROLE:
				if (criterias.containsKey(field)) {
					sb.append(count > 0 ?" and":" where").append(" po.conFolder.role.id = '").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			case keywords:
				if (criterias.containsKey(field)) {
					String keywords = criterias.get(field);
					sb.append(" and (").append("sysUser.firstName like '%")
							.append(keywords).append("'").append(
									" or sysUser.lastName like '%").append(
									keywords).append("'").append(
									" or sysUser.userName like '%").append(
									keywords).append("'").append(
									" or sysUser.email like '%").append(
									keywords).append("'").append(
									" or sysUser.postcode like '%").append(
									keywords).append("'").append(
									" or sysUser.phone = '").append(keywords)
							.append("'").append(")");

					count++;
				}				
				break;
			case NAME:
				if (criterias.containsKey(field)) {
					sb.append(count > 0 ?" and":" where").append(" po.name like '").append(
							criterias.get(field)).append("%'");
					count++;
				}
				break;

			case FILE_TYPE:
				if (criterias.containsKey(field)) {
					sb.append(count > 0 ?" and":" where").append(" po.fileType = '").append(
							criterias.get(field)).append("'");
					count++;
				}
				break;
			
			default:
				break;
			}
		}


		if (useCount)
			return sb.toString();
		if(orderBy != null){
			switch (orderBy) {
				case NAME:
					sb.append(" order by po.name");
					break;
				default:
					sb.append(" order by po.insertDate");
					break;
			}
		}

		System.out.println("hql=" + sb);
		return sb.toString();
	}
	
	public Folder getFolder(String folderId){
		ConFolder conFolder = conFolderDao.findByPrimaryKey(folderId);
		return new FolderImpl(conFolder);
	}
		
	public void addFolder(String name, Role role) throws LookupException {
		SysRole sysRole = sysRoleDao.findByPrimaryKey(role.getId());
		User user = getContextUser();
		FolderImpl folderImpl = new FolderImpl(name, sysRole, new Date(), user.getId());
		conFolderDao.insert(folderImpl.getConfolder());
	}
	
	public void renameFolder(String folderId, String name)
			throws LookupException {
		ConFolder conFolder = conFolderDao.findByPrimaryKey(folderId);
		conFolder.setName(name);
		conFolderDao.update(conFolder);
	}
	
	public Collection<Folder> listFolder(Role role) {
		Collection<ConFolder> conFolders = new ArrayList<ConFolder>();
		try {
			conFolders = conFolderDao.findConFoldersByRole(role.getId());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		Collection<Folder> folders = new ArrayList<Folder>();
		if(conFolders == null || conFolders.size() == 0)
			return folders;
		for(ConFolder conFolder : conFolders){
			Folder folder = new FolderImpl(conFolder);
			folders.add(folder);
		}
		return folders;
	}
	
	public Collection<Folder> listAllFolder() {
		Collection<ConFolder> conFolders = new ArrayList<ConFolder>();
		try {
			conFolders = conFolderDao.findAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		Collection<Folder> folders = new ArrayList<Folder>();
		if(conFolders == null || conFolders.size() == 0)
			return folders;
		for(ConFolder conFolder : conFolders){
			Folder folder = new FolderImpl(conFolder);
			folders.add(folder);
		}
		return folders;
	}
	
	public Collection<FileInfo> listFile(String folderId, String userId, int startIndex, int count){
		String queryStr = "select conFile from ConFile as conFile where conFile.conFolder.id = ? and conFile.userId = ?";
		Object params[] = {folderId, userId};
		Collection<ConFile> conFiles = new ArrayList<ConFile>();
		try {
			conFiles = conFileDao.findByQuery(queryStr, params, startIndex, count);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		Collection<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		if(conFiles == null || conFiles.size() == 0)
			return fileInfos;
		for(ConFile conFile : conFiles){
			fileInfos.add(new FileInfoImpl(conFile));
		}
		return fileInfos;
	}



//	public Collection<ObjImage> listObjImages(String objectId,
//			FileObjectType objectType) {
//		ArrayList<ObjImage> objImages = new ArrayList<ObjImage>();
//		Collection<FileInfo> fileInfos = listFiles(objectId, objectType);
//		for (FileInfo fileInfo : fileInfos) {
//			if (fileInfo.isImage()) {
//				objImages.add(new ObjImageImpl((FileInfoImpl) fileInfo));
//			}
//		}
//		return objImages;
//	}

}

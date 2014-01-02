package dwz.business.content.impl;

import java.util.Collection;
import java.util.Date;

import dwz.business.content.FileInfo;
import dwz.business.content.Folder;
import dwz.constants.BeanDaoKey;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.framework.utils.StringUtils;
import dwz.persistence.beans.ConFolder;
import dwz.persistence.beans.SysRole;
import dwz.persistence.daos.ConFolderDao;

public class FolderImpl extends AbstractBusinessObject implements Folder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3804087712726111513L;
	private ConFolder conFolder;

	protected FolderImpl() {
		conFolder = new ConFolder();
	}

	protected FolderImpl(ConFolder conFolder) {
		this.conFolder = conFolder;
	}

	FolderImpl(String name, SysRole sysRole, Date insertDate, String insertBy) {
		conFolder = new ConFolder();
		conFolder.setName(name);
		conFolder.setRole(sysRole);
		conFolder.setInsertDate(insertDate);
		conFolder.setInsertBy(insertBy);
	}

	ConFolder getConfolder() {
		return conFolder;
	}

	public Date getCreateDate() {
		return conFolder.getInsertDate();
	}

	public User getInsertBy() {
		if (StringUtils.notEmpty(conFolder.getInsertBy())) {
			UserManager userManager = BusinessFactory.getFactory()
					.getManager(BeanManagerKey.userManager);
			return userManager.getUser(conFolder.getInsertBy());
		}
		return null;
	}

	public String getId() {
		return conFolder.getId();
	}

	public String getName() {
		return conFolder.getName();
	}

	public Folder getParentFolder() {
		ConFolderDao dao = BusinessFactory.getFactory().getDao(
				BeanDaoKey.conFolderDao);
		ConFolder model = dao.findByPrimaryKey(this.conFolder.getParentId());
		return new FolderImpl(model);
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isHasFolders() {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection<FileInfo> getFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Folder> getSubFolders() {
		// TODO Auto-generated method stub
		return null;
	}

}

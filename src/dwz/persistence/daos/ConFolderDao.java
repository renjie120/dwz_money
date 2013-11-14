package dwz.persistence.daos;

import java.util.Collection;

import dwz.persistence.beans.ConFolder;
import dwz.dal.BaseDao;

public interface ConFolderDao extends BaseDao<ConFolder, String> {
	
	public Collection<ConFolder> findConFoldersByRole(Integer roleId);
	
}

package dwz.persistence.daos;

import java.util.Collection;

import dwz.persistence.beans.SysUserRole;
import dwz.dal.BaseDao;

public interface SysUserRoleDao extends BaseDao<SysUserRole, String> {

	public Collection<SysUserRole> findRolesByUser(String userId);
	
	public void deleteAllByUser(String userId);

}

package dwz.persistence.daos;

import java.util.Collection;

import dwz.persistence.beans.SysRole;
import dwz.dal.BaseDao;

public interface SysRoleDao extends BaseDao<SysRole, Integer> {

	public Collection<SysRole> findRoleListByUser(String userId);
	
	public Collection<SysRole> findAllRoleList();

}

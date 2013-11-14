package dwz.persistence.daos;

import java.util.Collection;

import dwz.persistence.beans.SysUser;
import dwz.dal.BaseDao;

public interface SysUserDao extends BaseDao<SysUser, String> {

	public Collection<String> findByPublicCredential(String userName,
			String password);

	public Collection<SysUser> findUserByName(String userName);

	public Collection<SysUser> findAllUserPageBreak(String userType,
			Integer startIndex, Integer count);

	public Collection<Number> findAllUserNum(String userType);

	public Collection<Number> findUsernameUnique(String id, String username);

}

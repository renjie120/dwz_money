package dwz.framework.user.impl;

import java.util.ArrayList;
import java.util.Collection;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.user.Role;
import dwz.framework.user.RoleManager;
import dwz.framework.user.User;
import dwz.persistence.beans.SysRole;
import dwz.persistence.beans.SysUserRole;
import dwz.persistence.beans.SysUserRoleId;
import dwz.persistence.daos.SysRoleDao;
import dwz.persistence.daos.SysUserRoleDao;

public class RoleManagerImpl extends AbstractBusinessObjectManager implements
		RoleManager {

	private SysRoleDao sysRoleDao = null;
	private SysUserRoleDao sysUserRoleDao = null;

	public RoleManagerImpl(SysRoleDao sysRoleDao, SysUserRoleDao sysUserRoleDao) {
		this.sysRoleDao = sysRoleDao;
		this.sysUserRoleDao = sysUserRoleDao;
	}

	public Role getRole(int id) {
		SysRole sysRole = this.sysRoleDao.findByPrimaryKey(id);
		return new RoleImpl(sysRole);
	}

//	public Collection<Role> listAllRoles(User user) {
//		Collection<SysRole> sysRoles = this.sysRoleDao.findRoleListByUser(user.getId());
//		Collection<Role> roles = new ArrayList<Role>();
//		if (sysRoles == null || sysRoles.size() < 1)
//			return roles;
//		for (SysRole sysRole : sysRoles) {
//			roles.add( new RoleImpl(sysRole));
//		}
//		return roles;
//	}

	public Collection<Role> listAllRoles() {
		Collection<SysRole> sysRoles =  this.sysRoleDao.findAllRoleList();
		Collection<Role> roles = new ArrayList<Role>();
		if(sysRoles == null || sysRoles.size() < 1 ) return roles;
		for(SysRole sysRole : sysRoles){
			roles.add(new RoleImpl(sysRole));
		}
		return roles;
	}

	public Collection<Role> listRoles(User user) {
		Collection<SysUserRole> sysUserRoles = this.sysUserRoleDao
				.findRolesByUser(user.getId());
		Collection<Role> roles = new ArrayList<Role>();
		if (sysUserRoles == null || sysUserRoles.size() < 1)
			return roles;
		for (SysUserRole sysUserRole : sysUserRoles) {
			RoleImpl roleImpl = new RoleImpl(sysUserRole.getId().getSysRole());
			roles.add(roleImpl);
		}
		return roles;
	}
	
	public void saveRoles(User user, int[] roleIds)
		throws ValidateFieldsException {
		this.sysUserRoleDao.deleteAllByUser(user.getId());
		for (int roleId : roleIds) {
			UserImpl userImpl = (UserImpl) user;
			SysRole sysRole = this.sysRoleDao.findByPrimaryKey(roleId);
			SysUserRoleId id = new SysUserRoleId(userImpl.getSysUser(),sysRole);
			SysUserRole sysUserRole = new SysUserRole(id);	
			this.sysUserRoleDao.insert(sysUserRole);
			
		}
	}

}

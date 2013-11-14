package dwz.framework.user.impl;

import java.util.Date;

import dwz.framework.core.business.AbstractBusinessObject;
import dwz.framework.user.Role;
import dwz.persistence.beans.SysRole;

public class RoleImpl extends AbstractBusinessObject implements Role {

	/**
	 * 
	 */
	private static final long serialVersionUID = -601263947422459251L;

	private SysRole sysRole;
	private boolean selected = false;

	RoleImpl(SysRole sysRole) {
		this.sysRole = sysRole;
	}


	public String getDescription() {
		return sysRole.getDescription();
	}

	public String getName() {
		return sysRole.getName();
	}

	public Integer getId() {
		return sysRole.getId();
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}

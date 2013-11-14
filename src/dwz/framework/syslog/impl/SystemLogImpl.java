package dwz.framework.syslog.impl;

import java.util.Date;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.syslog.SystemLog;
import dwz.framework.syslog.SystemLogLevel;
import dwz.framework.syslog.SystemLogType;
import dwz.framework.user.User;
import dwz.framework.user.UserManager;
import dwz.framework.utils.EnumUtils;
import dwz.persistence.beans.SysLog;

public class SystemLogImpl extends AbstractBusinessObject implements SystemLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2504880141958796941L;
	private SysLog sysLog;
	private User user;

	SystemLogImpl(SysLog sysLog) {
		this.sysLog = sysLog;
	}

	SystemLogImpl(String userId, SystemLogType type, SystemLogLevel level) {
		this.sysLog = new SysLog(userId, type.toString(), level.toString(),
				new Date());
	}

	SysLog getSysLog() {
		return sysLog;
	}

	public User getUser() {
		if (user == null && sysLog.getUserId() != null
				&& sysLog.getUserId().length() > 0) {
			UserManager uMgr = BusinessFactory.getFactory().getManager(
					BeanManagerKey.userManager);
			user = uMgr.getUser(sysLog.getUserId());
		}
		return user;
	}

	public String getContent() {
		return sysLog.getContent();
	}

	public String getId() {
		return sysLog.getId();
	}

	public Date getInsertDate() {
		return sysLog.getInsertDate();
	}

	public String getIpAddress() {
		return sysLog.getIpAddress();
	}

	public String getLevel() {
		return sysLog.getLogLevel();
	}

	public String getName() {
		return sysLog.getName();
	}

	public SystemLogType getType() {
		if (EnumUtils.isDefined(SystemLogType.values(), sysLog.getLogType()))
			return SystemLogType.valueOf(sysLog.getLogType());
		return null;
	}

	public void setIpAddress(String ipAddress) {
		sysLog.setIpAddress(ipAddress);
	}

	public void setName(String name) {
		sysLog.setName(name);
	}

	public void setContent(String content) {
		sysLog.setContent(content);
	}

}

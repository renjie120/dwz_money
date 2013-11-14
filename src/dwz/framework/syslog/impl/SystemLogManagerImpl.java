package dwz.framework.syslog.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.syslog.SystemLog;
import dwz.framework.syslog.SystemLogLevel;
import dwz.framework.syslog.SystemLogManager;
import dwz.framework.syslog.SystemLogType;
import dwz.framework.user.User;
import dwz.persistence.beans.SysLog;
import dwz.persistence.daos.SysLogDao;

public class SystemLogManagerImpl extends AbstractBusinessObjectManager
		implements SystemLogManager {
	private SysLogDao	logDao;

	public SystemLogManagerImpl(SysLogDao logDao) {
		this.logDao = logDao;
	}

	public void save(SystemLogType type, SystemLogLevel level,
			String ipAddress, String name, String content) {
		User user = this.getContextUser();
		save(user, type, level, ipAddress, name, content);
	}
	
	public void save(User user, SystemLogType type, SystemLogLevel level,
			String ipAddress, String name, String content) {

		SystemLogImpl systemLog = new SystemLogImpl(user.getId(), type, level);
		systemLog.setIpAddress(ipAddress);
		systemLog.setName(name);
		systemLog.setContent(content);
		logDao.insert(systemLog.getSysLog());
	}

	public SystemLog getLog(String id) {
		SysLog sysLog = logDao.findByPrimaryKey(id);
		return new SystemLogImpl(sysLog);
	}

	public List<SystemLog> searchLogs(int start, int count) {
		List<SystemLog> systemLogList = new ArrayList<SystemLog>();

		String hql = "select sysLog from SysLog as sysLog, SysUser sysUser where sysLog.userId = sysUser.id order by sysLog.insertDate desc";
		Collection<SysLog> sysLogList = logDao.findByQuery(hql, start, count);
		for(SysLog sysLog : sysLogList){
			systemLogList.add(new SystemLogImpl(sysLog));
		}
		return systemLogList;
	}
	
	public int searchLogsNum() {
		String hql = "select count(*) from SysLog as sysLog, SysUser sysUser where sysLog.userId = sysUser.id";
		return logDao.countByQuery(hql);
	}
}

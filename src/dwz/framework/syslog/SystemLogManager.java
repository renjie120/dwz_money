package dwz.framework.syslog;

import java.util.List;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.user.User;

public interface SystemLogManager extends BusinessObjectManager {

	void save(SystemLogType type, SystemLogLevel level, String ipAddress, String name, String content);

	void save(User user, SystemLogType type, SystemLogLevel level, String ipAddress, String name, String content);

	SystemLog getLog(String id);

	List<SystemLog> searchLogs(int start, int count);
	
	int searchLogsNum();
}

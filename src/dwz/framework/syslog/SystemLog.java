package dwz.framework.syslog;

import java.util.Date;

import dwz.framework.core.business.BusinessObject;
import dwz.framework.user.User;

public interface SystemLog extends BusinessObject {
	String getId();

	User getUser();

	String getIpAddress();

	String getName();

	String getContent();

	Date getInsertDate();

	SystemLogType getType();

	String getLevel();
}

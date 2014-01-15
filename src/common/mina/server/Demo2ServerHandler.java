package common.mina.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class Demo2ServerHandler extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(Demo2ServerHandler.class);

	public void messageReceived(IoSession session, Object mes) {
		UserInfo info = (UserInfo) mes;
		session.write(new OrgInfo(info.getName()+"的组织"));
	}

}

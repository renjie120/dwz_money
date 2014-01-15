package common.mina.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class Demo2ClientHandler extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(Demo2ClientHandler.class);

	public void messageReceived(IoSession session, Object mes) {
		OrgInfo info = (OrgInfo)mes;
		System.out.println("收到的组织机构是："+info.getName());
	}

	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.error("客户端发送异常", cause);
	}
}

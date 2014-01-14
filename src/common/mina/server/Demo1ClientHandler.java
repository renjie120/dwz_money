package common.mina.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class Demo1ClientHandler extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(Demo1ClientHandler.class);

	public void messageReceived(IoSession session, Object mes) {
		String msg = mes.toString();
		logger.info("客户端接收到数据:" + msg);

	}

	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.error("客户端发送异常", cause);
	}
}

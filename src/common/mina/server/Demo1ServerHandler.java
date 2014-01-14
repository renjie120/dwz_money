package common.mina.server;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class Demo1ServerHandler extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(Demo1ServerHandler.class);

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// logger.debug("服务端与客户端创建连接");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// logger.debug("服务端与客户端连接打开");
	}

	public void messageReceived(IoSession session, Object mes) {
		String msg = mes.toString();
		// logger.debug("服务端接收到数据:" + msg);

		if ("bye".equals(msg)) {
			// 下面参数false，表示在结束完全部传输之后，再关闭会话.
			session.close(false);
		}
		Date date = new Date();
		session.write(date);
	}

	public void messageSent(IoSession session, Object mes) {
		// 发送数据到客户端之后，主动关闭的话，就实现的是短连接！
		// session.close();
		// logger.debug("服务端发送成功：");
	}

	public void sessionClosed(IoSession session) {
		logger.debug("服务端关闭!");
	}

	public void sessionIdle(IoSession session, IdleStatus status) {
		logger.info("服务端进入空闲!");
	}

	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.error("服务器端发送异常", cause);
	}
}

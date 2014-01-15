package common.mina.myserver;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import common.mina.potocol.MyJavaBeanRequest;
import common.mina.potocol.MyJavaBeanResponse;

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
		if (mes instanceof MyJavaBeanRequest) {
			MyJavaBeanRequest request = (MyJavaBeanRequest) mes;
			int realId = request.getRealId();
			String str = request.getRealData();

			logger.info("服务端接受到的数据：realId=" + realId + ",RealData=" + str);

			MyJavaBeanResponse response = new MyJavaBeanResponse();
			response.setDoubleField(213.0);
			response.setRealId(333);
			response.setStringField("服务端范虎ijie苏打水但是的的");

			session.write(response);
		} else {
			logger.info("未知请求!" + mes);
		}
	}

	public void messageSent(IoSession session, Object mes) {
		// 发送数据到客户端之后，主动关闭的话，就实现的是短连接！
		session.close(true);
		logger.debug("服务端发送成功：");
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

package common.mina.myserver;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import common.mina.potocol.MyJavaBeanResponse;

public class Demo1ClientHandler extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(Demo1ClientHandler.class);

	public void messageReceived(IoSession session, Object mes) {
		if (mes instanceof MyJavaBeanResponse) {
			MyJavaBeanResponse res = (MyJavaBeanResponse) mes;
			logger.info("返回结果如下：double==" + res.getDoubleField() + ",RealId="
					+ res.getRealId() + ",stringValue=" + res.getStringField());
		}else
			logger.info("未知类型");
	}

	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.error("客户端发送异常", cause);
	}
}

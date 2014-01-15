package common.mina.server;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient2 {
	private static Logger logger = Logger.getLogger(MinaClient2.class);

	private static String HOST = "127.0.0.1";

	private static int PORT = 3005;

	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		// 设置连接超时时间
		connector.setConnectTimeoutMillis(30000);
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		// 设置处理器
		connector.setHandler(new Demo2ClientHandler());

		IoSession session = null;

		// 客户端的连接是异步的，必须在连接上服务端之后，获得了session才可以通信.
		// 关闭的话，也要指定disponse()方法关闭客户端.
		// 从监听ip，端口获得session
		ConnectFuture future = connector.connect(new InetSocketAddress(HOST,
				PORT));
		// 等连接创建完成
		future.awaitUninterruptibly();
		session = future.getSession();
		UserInfo user = new UserInfo();
		user.setName("lishuiqing");
		session.write(user);
		// 等待连接断开
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();

	}
}

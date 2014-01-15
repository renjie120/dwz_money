package common.mina.myserver;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import common.mina.potocol.MyJavaBeanRequest;
import common.mina.potocol.MyMessageCodecFactory;
import common.mina.potocol.MyMessageDecoder;
import common.mina.potocol.MyMessageEncoder;

public class MinaClient {
	private static Logger logger = Logger.getLogger(MinaClient.class);

	private static String HOST = "127.0.0.1";

	private static int PORT = 3005;

	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		// 设置连接超时时间
		connector.setConnectTimeoutMillis(30000); 
		// 设置编码处理器
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new MyMessageCodecFactory(
						new MyMessageDecoder(Charset.forName("UTF-8")),
						new MyMessageEncoder(Charset.forName("UTF-8")))));
		// 设置处理器
		connector.setHandler(new Demo1ClientHandler());

		IoSession session = null;

		// 客户端的连接是异步的，必须在连接上服务端之后，获得了session才可以通信.
		// 关闭的话，也要指定disponse()方法关闭客户端.
		// 从监听ip，端口获得session
		try { 
			ConnectFuture future = connector.connect(new InetSocketAddress(
					HOST, PORT));
			// 等连接创建完成
			future.awaitUninterruptibly();
			session = future.getSession();

			MyJavaBeanRequest request = new MyJavaBeanRequest();
			request.setRealId(999);
			request.setRealData("测试自定义协议");
			session.write(request);
		} catch (Exception e) {
			logger.error("客户端链接异常...", e);
		}
		// 等待连接断开
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();

	}
}

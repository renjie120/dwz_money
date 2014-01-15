package common.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 演示使用mina来传递对象.
 * 
 * @author renjie120
 * 
 */
public class Demo2Server {
	private static int PORT = 3005;

	public static void main(String[] args) {
		try {
			IoAcceptor accpt = null;
			// 服务端socket
			accpt = new NioSocketAcceptor();
			// 在过滤器链里面添加上编码过滤器
			accpt.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(
							new ObjectSerializationCodecFactory()));
			// 设置限制时间
			accpt.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

			// 设置处理器
			accpt.setHandler(new Demo2ServerHandler());

			// 监听端口
			accpt.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

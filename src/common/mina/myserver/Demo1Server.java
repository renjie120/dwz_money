package common.mina.myserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import common.mina.potocol.MyMessageCodecFactory;
import common.mina.potocol.MyMessageDecoder;
import common.mina.potocol.MyMessageEncoder;

public class Demo1Server {
	private static int PORT = 3005;

	public static void main(String[] args) {
		try {
			IoAcceptor accpt = null;
			// 服务端socket
			accpt = new NioSocketAcceptor();
			// 在过滤器里面添加日志处理.下面是设置日志处理器的级别.
//			LoggingFilter lf = new LoggingFilter();
//			lf.setMessageReceivedLogLevel(LogLevel.ERROR);
//			// 如果把日志处理器放在下面的编码过滤器之后，会出现不了内容的日志输出.
//			accpt.getFilterChain().addLast("logger", lf);

			// 在过滤器链里面添加上编码过滤器
			accpt.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new MyMessageCodecFactory(
							new MyMessageDecoder(Charset.forName("UTF-8")),
							new MyMessageEncoder(Charset.forName("UTF-8")))));
			// 设置缓存大小(一般服务器会自动调节这个数值。)setMinReadBufferSize()---setMaxReadBufferSize()
			// accpt.getSessionConfig().setReadBufferSize(2048);
			// 设置限制时间
			accpt.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

			// accpt.getSessionConfig().setWriteTimeout();//设置写操作超时时间.

			// setUseReadOperation(boolean useReadOperation)：//这个方法设置IoSession
			// 的read()方法是否可用，默认是false。

			// 设置处理器
			accpt.setHandler(new Demo1ServerHandler());

			// 监听端口
			accpt.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

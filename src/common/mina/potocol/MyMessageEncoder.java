package common.mina.potocol;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * 编码器,将对象转化为二进制.
 * 
 * @author renjie120
 * 
 */
public class MyMessageEncoder implements MessageEncoder<AbsMessage> {
	private Logger logger = Logger.getLogger(MyMessageEncoder.class);

	private Charset charset;

	public MyMessageEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, AbsMessage message,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.put(message.getTag());
		buf.putShort(message.getHeaderLen(charset));

		if (message instanceof MyJavaBeanRequest) {
			MyJavaBeanRequest request = (MyJavaBeanRequest) message;
			buf.putInt(request.getRealId());// realId
			buf.putInt((int) request.getRealData().getBytes(charset).length);// realDataLen
			buf.putString(request.getRealData(), charset.newEncoder());// realData
		} else if (message instanceof MyJavaBeanResponse) {
			MyJavaBeanResponse response = (MyJavaBeanResponse) message;
			CharsetEncoder encoder = charset.newEncoder();
			// IoBuffer dataBuffer = IoBuffer.allocate(100).setAutoExpand(true);
			buf.putInt(response.getRealId());// realId
			buf.putDouble(response.getDoubleField());// doubleField
			buf.putInt((int) response.getStringField().getBytes(charset).length);// stringLen
			buf.putString(response.getStringField(), encoder);// stringfield
		}

		buf.flip();
		logger.info("编码" + buf.toString());
		out.write(buf);
	}
}

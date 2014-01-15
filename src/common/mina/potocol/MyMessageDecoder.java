package common.mina.potocol;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * 解码器. 将二进制转换为对象.
 * 
 * @author renjie120
 * 
 */
public class MyMessageDecoder implements MessageDecoder {
	private Logger logger = Logger.getLogger(MyMessageDecoder.class);

	private Charset charset;

	public MyMessageDecoder(Charset charset) {
		this.charset = charset;
	}

	/**
	 * 进行校验，看ioBuffer是不是适合解码.
	 */
	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		// 如果小于报头长度3，就直接抛出错误.
		if (in.remaining() < 3) {
			return MessageDecoderResult.NEED_DATA;
		}

		// tag
		byte tag = in.get();
		if (tag == (byte) 0x0001 || tag == (byte) 0x0002) {
			logger.info("请求标示符：" + tag);
		} else {
			logger.info("未知标示符：" + tag);
			return MessageDecoderResult.NOT_OK;
		}

		int len = in.getShort();
		int remainLen = in.remaining();
		if (remainLen < len) {
			logger.info("得到长度：" + len + ",实际剩余长度：" + remainLen);
			return MessageDecoderResult.NEED_DATA;
		}
		return MessageDecoderResult.OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		logger.info("解码的字节数组是:" + in.toString());
		CharsetDecoder decoder = charset.newDecoder();

		AbsMessage message = null;
		byte tag = in.get();

		// 得到数据区长度.
		short len = in.getShort();

		// 将数据放到temp中，准备进行解析.
		byte[] temp = new byte[len];
		in.get(temp);

		// 解析基本数据做准备
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.put(temp);
		buf.flip();

		// 为解析真实数据区长度做准备.
//		IoBuffer databuf = IoBuffer.allocate(100).setAutoExpand(true);
//		databuf.put(tag);
//		databuf.putShort(len);
//		databuf.put(temp);
//		databuf.flip(); // 为获取真实数据区长度做准备

		// 服务端解码
		if (tag == (byte) 0x0001) {
			MyJavaBeanRequest request = new MyJavaBeanRequest();
			int realId = buf.getInt();
			int relDataLen = buf.getInt();
			String realData = null;
			if (relDataLen > 0) {
				realData = buf.getString(relDataLen, decoder);
			}

			request.setRealId(realId);
			request.setRealData(realData);

			message = request;
		}
		//客户端解码
		else if (tag == (byte) 0x0002) {
			MyJavaBeanResponse response = new MyJavaBeanResponse();
			int realId = buf.getInt();
			double doubleField  = buf.getDouble();
			int stringLen = buf.getInt();
			String stringField = null;
			if (stringLen > 0) {
				stringField = buf.getString(stringLen, decoder);
			}
			
			response.setRealId(realId);
			response.setDoubleField(doubleField);
			response.setStringField(stringField);
			response.setStringLen(stringLen);
			message = response ;
		}else{
			logger.error("未找到解码器....");
		}
		out.write(message);
		return MessageDecoderResult.OK;
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		// TODO Auto-generated method stub

	}

}

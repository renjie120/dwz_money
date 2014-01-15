package common.mina.potocol;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public class MyMessageCodecFactory extends DemuxingProtocolCodecFactory {
	private MessageDecoder decoder;
	private MessageEncoder<AbsMessage> encoder;

	public MyMessageCodecFactory(MessageDecoder d, MessageEncoder<AbsMessage> e) {
		this.decoder = d;
		this.encoder = e;
		addMessageDecoder(this.decoder);
		addMessageEncoder(AbsMessage.class, this.encoder);
	}
}

package common.mina.potocol;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

/**
 * 请求的java对象
 * 0x0001
 * @author renjie120
 * 
 */
public class MyJavaBeanRequest extends AbsMessage {
	//tag(标示) |head(包数据长度) |realId(简单数据) |realDataLen(表示字符串类型数据的长度) |realData(字符串数据)
	private Logger logger = Logger.getLogger(MyJavaBeanRequest.class);

	private String realData;

	private int realDataLen;

	private int realId;

	public int getRealDataLen() {
		return realDataLen;
	}

	public void setRealDataLen(int realDataLen) {
		this.realDataLen = realDataLen;
	}

	@Override
	public byte getTag() {
		return (short) 0x0001;
	}

	public String getRealData() {
		return realData;
	}

	public void setRealData(String realData) {
		this.realData = realData;
	}

	public int getRealId() {
		return realId;
	}

	public void setRealId(int realId) {
		this.realId = realId;
	}

	@Override
	public short getHeaderLen(Charset charset) {
		int len = 4 + 4;
		try {
			if (realData != null && !"".equals(realData))
				len += realData.getBytes(charset).length;
		} catch (Exception e) {
			logger.error(e);
		}
		return (short) len;
	}

	@Override
	public int getDataOffset() {
		return 1 + 2 + 4 + 4;
	}

}

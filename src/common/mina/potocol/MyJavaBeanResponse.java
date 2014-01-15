package common.mina.potocol;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

/**
 * 返回的java对象 0x0002.
 * 
 * @author renjie120
 * 
 */
public class MyJavaBeanResponse extends AbsMessage {
	//tag(标示) |head(包数据长度) |realId(简单数据) |doubleField(简单数据) |stringLen(表示字符串类型数据的长度) |stringField(返回结果字符串数据)
	private Logger logger = Logger.getLogger(MyJavaBeanResponse.class);

	public int getRealId() {
		return realId;
	}

	public void setRealId(int realId) {
		this.realId = realId;
	}

	private int realId;
	
	private double doubleField;
	
	private int stringLen;

	private String stringField;

	public int getStringLen() {
		return stringLen;
	}

	public void setStringLen(int stringLen) {
		this.stringLen = stringLen;
	}

	@Override
	public byte getTag() {
		return (short) 0x0002;
	}

	public double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(double doubleField) {
		this.doubleField = doubleField;
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	@Override
	public short getHeaderLen(Charset charset) {
		int len = 4 + 8 + 4;
		try {
			if (stringField != null && !"".equals(stringField))
				len += stringField.getBytes(charset).length;
		} catch (Exception e) {
			logger.error(e);
		}
		return (short) len;
	}

	@Override
	public int getDataOffset() {
		return 1 + 2 + 4 + 8 + 4;
	}
}

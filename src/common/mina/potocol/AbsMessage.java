package common.mina.potocol;

import java.nio.charset.Charset;

public abstract class AbsMessage {
	/**
	 * 协议格式：tag(1)|header length(2)|realId(int 4)|realData (string )数据实际内容(真实数据)
	 */
	/**
	 * 请求访问类型：Tag 0x00 0x01
	 * 
	 * @return
	 */
	public abstract byte getTag();

	/**
	 * 头文件，数据的总长度.总共有2^16个长度.2个字节
	 * 
	 * @return
	 */
	public abstract short getHeaderLen(Charset charset);

	/**
	 * 得到真实数据的偏移
	 * 
	 * @return
	 */
	public abstract int getDataOffset(); 
}

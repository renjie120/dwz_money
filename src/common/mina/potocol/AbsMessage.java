package common.mina.potocol;

public abstract class AbsMessage {
	/**
	 * 协议格式：tag|header length|filename
	 */
	/**
	 * 请求访问类型：Tag 0x00  0x01
	 * @return
	 */
	public abstract byte getTag();
	/**
	 * 头文件长度.总共有2^16个长度.
	 * @return
	 */
	public abstract short getHeaderLen();
	
	/**
	 * 根据UUID生成唯一标示.
	 * @return
	 */
	public abstract byte[] getFilename();
	
	
}

package common.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 单向加密算法集合.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public abstract class Coder {
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256  
	 * HmacSHA384  
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		// return (new BASE64Decoder()).decodeBuffer(key);
		return Base64.decodeBase64(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		// return (new BASE64Encoder()).encodeBuffer(key);
		return Base64.encodeBase64String(key);
	}

	/**
	 * 加密.
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String toMyCoder(String key) throws Exception {
		byte[] inputData = key.getBytes();
		// 先进性base64加密
		String ans = Coder.encryptBASE64(inputData);
		// 在进行个性化的加密.
		ans = PassWord.encode(ans);
		return ans;
	}

	/**
	 * 解密.
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String fromMyCoder(String key) {
		key = PassWord.decode(key);
		// 再进行base64解密
		byte[] output;
		String outputStr = "";
		try {
			output = Coder.decryptBASE64(key);
			outputStr = new String(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputStr;
	}

	public static void main(String[] args) {
		System.out.println(fromMyCoder("X:VpHm9Da`Bm2+5SJbDzPt0RFIHS"));
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * 得到md5加密字符串.
	 * 
	 * @Title: encryptMD5Str
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param data
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public static String encryptMD5Str(String data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data.getBytes());
		byte[] ans = md5.digest();
		BigInteger inT = new BigInteger(ans);
		return inT.toString(16);
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);

	}
}
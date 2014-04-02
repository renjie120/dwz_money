package common.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class ThreeDes {
	/**
	 * 检查密钥长度,如不足24位,则加0x00补齐
	 * 
	 * @param keyString
	 * @return
	 */
	public byte[] getKeyBytes(String keyString) {
		byte[] b = new byte[24];
		byte[] key = keyString.getBytes();
		int count = keyString.getBytes().length;
		for (int i = 0; i < count; i++) {
			b[i] = key[i];
		}
		for (int i = count; i < 24; i++) {
			b[i] = 0x20;
		}
		return b;
	}

	/***
	 * 会话标识加密
	 * 
	 * @param src
	 * @return
	 */
	public String hashEncrypt(String src, String secret) {
		String ret = "";
		try {
			// 24字节密钥key,3倍DES密钥长度
			byte[] tripleKey = getKeyBytes(secret);

			// 生成密钥
			SecretKey secretKey = new SecretKeySpec(tripleKey, "DESede");

			String transformation = "DESede/CBC/PKCS5Padding";
			Cipher cipher = Cipher.getInstance(transformation);

			// CBC方式的初始化向量
			byte[] iv = new byte[] { 93, 81, 122, 33, 47, 50, 17, 103 };
			IvParameterSpec ivparam = new IvParameterSpec(iv);

			// 加密
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparam);
			byte[] encriptText = cipher.doFinal(src.getBytes());

			// Hash算法
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(encriptText);

			// 返回其 16 进制字符串
			ret = bytesToHexString(sha.digest());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return ret;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/***
	 * RTUAPID加密
	 * 
	 * @param str
	 * @return
	 */
	public String CreateDigest(String src) {
		String ret = "";
		try {
			// Hash算法
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(src.getBytes());
			// Base64加密
			ret = new BASE64Encoder().encode(sha.digest());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return ret;
	}

	/***
	 * 验证认证码
	 * 
	 * @param authentication
	 * @return
	 */
	public String[] AuthToken(String authentication, String secret) {
		// 输出参数
		String[] str = new String[2];
		// 当前时间
		Date now1 = new Date();
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 拆分认证码验证
		String[] authValue = authentication.split(":");
		if (authValue.length == 2 && "QRAPToken".equals(authValue[0])) {
			try {
				// 24字节密钥key,3倍DES密钥长度
				byte[] tripleKey = getKeyBytes(secret);

				// 生成密钥
				SecretKey secretKey = new SecretKeySpec(tripleKey, "DESede");

				String transformation = "DESede/CBC/PKCS5Padding";
				Cipher cipher = Cipher.getInstance(transformation);

				// CBC方式的初始化向量
				byte[] iv = new byte[] { 93, 81, 122, 33, 47, 50, 17, 103 };
				IvParameterSpec ivparam = new IvParameterSpec(iv);

				// 解密
				byte[] auth = new BASE64Decoder().decodeBuffer(authValue[1]);
				cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparam);
				String text = new String(cipher.doFinal(auth));

				// 验证
				String[] textValue = text.split("$");
				if (textValue.length == 2) {
					// 拆分值并存入Map中
					HashMap<String, String> map = new HashMap<String, String>();
					String[] tokenValue = textValue[0].split(";");
					for (int i = 0; i < tokenValue.length; i++) {
						String[] value = tokenValue[0].split("=");
						map.put(value[0], value[1]);
					}
					// 判断值是否相等
					if ("192.168.20.176".equals(map.get("Host"))
							&& "kentop".equals(map.get("QRAPID"))
							&& "kentop".equals(map.get("QRAPName"))) {
						// 判断是否超时
						Date now2 = d1.parse(map.get("CurrentTime"));
						// 比较两者差是否超过3分钟
						long between = 0;
						if (now1.getTime() > now2.getTime()) {
							between = (now1.getTime() - now2.getTime()) / 1000;// 除以1000是为了转换成秒
						} else if (now1.getTime() < now2.getTime()) {
							between = (now2.getTime() - now1.getTime()) / 1000;// 除以1000是为了转换成秒
						}
						if (between >= 180) {
							str[0] = "3";
							str[1] = "验证失败,超过当前时间3分钟!";
						} else {
							str[0] = "0";
							str[1] = "验证成功!";
						}
					} else {
						str[0] = "3";
						str[1] = "验证失败,数据错误!";
					}
				} else {
					str[0] = "3";
					str[1] = "验证失败,格式错误!";
				}
			} catch (Exception ex) {
				str[0] = "3";
				str[1] = "验证失败,出异常!";
			}
		} else {
			str[0] = "3";
			str[1] = "验证失败,格式错误!";
		}
		return str;
	}

	/**
	 * 认证码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createToken(String host, String apId, String apName,
			String secret) {
		String authenticator = "QRAPToken:";
		try {
			// 当前时间
			Date now = new Date();
			SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// Digest
			String digest = CreateDigest(apId);

			String orgToken = "Host=" + host + ";QRAPID=" + apId
					+ ";QRAPName =" + apName + ";CurrentTime=" + d1.format(now)
					+ "$" + digest;

			// 24字节密钥key,3倍DES密钥长度
			byte[] tripleKey = getKeyBytes(secret);

			// 生成密钥
			SecretKey secretKey = new SecretKeySpec(tripleKey, "DESede");

			String transformation = "DESede/CBC/PKCS5Padding";
			Cipher cipher = Cipher.getInstance(transformation);

			// CBC方式的初始化向量
			byte[] iv = new byte[] { 93, 81, (byte) 122, (byte) 233, 47, 50,
					17, (byte) 103 };
			IvParameterSpec ivparam = new IvParameterSpec(iv);

			// 加密
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparam);
			byte[] encriptText = cipher.doFinal(orgToken.getBytes());

			// 加密
			authenticator += new BASE64Encoder().encode(encriptText);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return authenticator;
	}
}

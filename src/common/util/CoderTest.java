package common.util;

import java.math.BigInteger;

public class CoderTest {
	public static void main(String[] args) {
		CoderTest test = new CoderTest();
		try {
			test.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void test() throws Exception {
		String inputStr = "使用说明.doc";
		System.err.println("原文:\n" + inputStr);

		byte[] inputData = inputStr.getBytes();
		String code = Coder.encryptBASE64(inputData);

		System.err.println("BASE64加密后:\n" + code);

		byte[] output = Coder.decryptBASE64(code);

		String outputStr = new String(output);

		System.err.println("BASE64解密后:\n" + outputStr);
		
		String miwen = Coder.toMyCoder(inputStr);
		String miwen2 = Coder.toMyCoder(inputStr);
		System.err.println("个性化加密后:\n" + miwen);
		System.err.println("两次个性化加密结果不一致！\n" + miwen.equals(miwen2));
		System.err.println("个性化解密后:\n" + Coder.fromMyCoder(miwen));
		//  
		// // 验证BASE64加密解密一致性
		// assertEquals(inputStr, outputStr);
		//  
		// // 验证MD5对于同一内容加密是否一致
		// assertArrayEquals(Coder.encryptMD5(inputData), Coder
		// .encryptMD5(inputData));
		//  
		// // 验证SHA对于同一内容加密是否一致
		// assertArrayEquals(Coder.encryptSHA(inputData), Coder
		// .encryptSHA(inputData));
		//  
		// String key = Coder.initMacKey();
		// System.err.println("Mac密钥:\n" + key);
		//  
		// // 验证HMAC对于同一内容，同一密钥加密是否一致
		// assertArrayEquals(Coder.encryptHMAC(inputData, key),
		// Coder.encryptHMAC(
		// inputData, key));
		//  
		BigInteger md5 = new BigInteger(Coder.encryptMD5(inputData));
		System.err.println("MD5:\n" + md5.toString(16));

		BigInteger sha = new BigInteger(Coder.encryptSHA(inputData));
		System.err.println("SHA:\n" + sha.toString(32));

		BigInteger mac = new BigInteger(Coder.encryptHMAC(inputData, inputStr));
		System.err.println("HMAC:\n" + mac.toString(16));
	}
}
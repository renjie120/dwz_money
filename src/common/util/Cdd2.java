package common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类似base64的加密算法
 * @author renjie120 419723443@qq.com
 *
 */
public class Cdd2 {
	static int w;//3-编码时数组除以3余下的数

	static char[] source;
	
	public static String DJPHttp="qC8lS#^P2s%QS@v&pM!QRsuKOszJ2s4dRb8dSYz&pN4nUM!l8cdIpizrSsLn9N7z3i3Q3ijs0b4dScdXR#lm4iOm5#BQ4i$t5iOQ3ik=";
	public static String  KEY= "B!@#$%DCj0123456789ikLNMOpqRSTUVWXYZ&dcb^*()IJKPQonmlrstuvwxyz+/=";

	private static char[] strToChars(String str) {
		try {
			byte[] temp;
			temp = str.getBytes(System.getProperty("file.encoding"));
			int len = temp.length;
			char[] oldStrbyte = new char[len];
			for (int i = 0; i < len; i++) {
				char hh = (char) temp[i];
				if (temp[i] < 0) {
					hh = (char) (temp[i] + 256);
				}
				oldStrbyte[i] = hh;
			}
			return oldStrbyte;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密字符串。
	 * @param str 密钥。
	 * @param 密码
	 * @return
	 */
	public String strToBase64Str(String str,String mima) {
		char[] oldStrbyte = Cdd2.strToChars(str);
		char[] ansChars = toAsBase64String(oldStrbyte,mima);
		return new String(ansChars);
	}

	public static void main(String[] a){
		Cdd2 c =new Cdd2();
		List list = new ArrayList();
		list.add(1);
		list.add("c");
		list.add((int)'a');
		String mima = c.strToBase64Str("http://app.deppon.com/center/decryptFile?userId=130126&serial=356380051796015","B!@#$%DCj0123456789ikLNMOpqRSTUVWXYZ&dcb^*()IJKPQonmlrstuvwxyz+/=");
		System.out.println(mima); 
		try {
			System.out.println(c.strFromAsBase64Str(mima, "B!@#$%DCj0123456789ikLNMOpqRSTUVWXYZ&dcb^*()IJKPQonmlrstuvwxyz+/="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private static char[] toAsBase64String(char[] input,String mima) {
		char[] Base64Code = Cdd2.strToChars(mima);
		source = input;
		int messageLen = input.length;
		int page = messageLen / 3;
		int messageLen2;
		if ((messageLen % 3) > 0) {
			page++;
			w = 3 - messageLen % 3;
		}
		messageLen2 = messageLen + w;
		char[] before = new char[messageLen2];
		for (int x = 0; x < messageLen2; x++) {
			if (x < messageLen) {
				before[x] = source[x];
			} else {//将补上的数组设置为0
				before[x] = 0;
			}
		}

		char[] instr = new char[3];
		char[] result = new char[page * 4];
		byte[] buffer = new byte[page * 4];
		for (int i = 0; i < page; i++) {
			instr[0] = before[i * 3];
			instr[1] = before[i * 3 + 1];
			instr[2] = before[i * 3 + 2];
			buffer[0 + i * 4] = (byte) (instr[0] >> 2);
			buffer[1 + i * 4] = (byte) (((instr[0] & 0x03) << 4) ^ (instr[1] >> 4));
			if (instr[1] != 0) {
				buffer[2 + i * 4] = (byte) (((instr[1] & 0x0f) << 2) ^ (instr[2] >> 6));
			} else {
				buffer[2 + i * 4] = 64;
			}
			if (instr[2] != 0) {
				buffer[3 + i * 4] = (byte) (instr[2] & 0x3f);
			} else {
				buffer[3 + i * 4] = 64;//这一步必不可少！！！这样的话，虽然叫Base64，
			}
			//有64个有用的代码，实际要用的是65个！！！！，多个‘=’，以后要把他去掉的！！
		}
		for (int x = 0; x < page * 4; x++) {
			result[x] = Base64Code[buffer[x]];
		}
		return result;
	}

	/**
	 * 解密字符串。
	 * @param str 加密串。
	 * @return
	 * @throws Exception
	 */
	public String strFromAsBase64Str(String str,String mima) throws Exception {
		byte[] ansBytes = fromAsBase64String(str,mima);
		String ans = new String(ansBytes, System.getProperty("file.encoding"));
		return ans;
	} 
	 

	private static byte[] fromAsBase64String(String Message1,String mima) throws Exception {
		int q = 0;//用来统计有多少个转义字符！
		for (int a = 0; a < Message1.length(); a++) {
			byte x = (byte) Message1.charAt(a);
			if (x == 10 || x == 13 || x == 9 || x == 32) {
				q++;
			}
		}
		char[] message = new char[Message1.length() - q];
		int d = 0;
		for (int a = 0; a < Message1.length(); a++) {
			byte x = (byte) Message1.charAt(a);
			if (x == 10 || x == 13 || x == 9 || x == 32) {
				d++;
				continue;
			} else {
				/*AEFGH*/
				message[a - d] = Message1.charAt(a);
			}
		}
		String Message = new String(message);//问问老师！！！！！！！！！！！！
		String regEx = "^[BCDI-Zbcdi-z0-9^&*()!@#$%/+=]*$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(Message);
		boolean ans = m.matches();
		int iq = m.end();
		if (!ans) {
			System.out.print("包含不正确的BASE64编码，请检查。");
			throw new Exception("包含不正确的BASE64编码，请检查。");
		}
		if (((Message.length()) % 4) != 0) {
			System.out.print("不是正确的BASE64编码，请检查");
			throw new Exception("不是正确的BASE64编码，请检查。");
		}
		String Base64Code = mima;
		int page = Message.length() / 4;
		byte[] buffer = new byte[page * 3];
		int length;
		int temp = 0;//用来看最后的3个字符中有多少个“=”
		for (int x = 0; x < 2; x++) {
			if (message[Message.length() - x - 1] == '=') {
				temp++;
			}
		}
		length = buffer.length - temp;
		byte[] newStr = new byte[length];
		byte[] outstr = new byte[3 * page];//用来将outMessage的内容全部复制到这里好把他传到外面去.
		for (int i = 0; i < page; i++) {
			byte[] instr = new byte[4];//下面的是用来看base64编码的对应的数是0到64的哪一个
			instr[0] = (byte) Base64Code.indexOf(message[i * 4]);
			instr[1] = (byte) Base64Code.indexOf(message[i * 4 + 1]);
			instr[2] = (byte) Base64Code.indexOf(message[i * 4 + 2]);
			instr[3] = (byte) Base64Code.indexOf(message[i * 4 + 3]);
			outstr[0 + i * 3] = (byte) ((instr[0] << 2) ^ ((instr[1] & 0x30) >> 4));
			if (instr[2] != 64) {
				outstr[1 + i * 3] = (byte) ((instr[1] << 4) ^ ((instr[2] & 0x3c) >> 2));
			} else {
				outstr[2 + i * 3] = 0;
			}
			if (instr[3] != 64) {
				outstr[2 + i * 3] = (byte) ((instr[2] << 6) ^ instr[3]);
			} else {
				outstr[2 + i * 3] = 0;
			}
		}

		for (int o = 0; o < length; o++) {
			newStr[o] = outstr[o];
		}
		return newStr;
	}
}

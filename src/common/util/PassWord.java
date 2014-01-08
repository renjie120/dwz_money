package common.util;

/**
 * 涓€涓亩鍗旷殑瀵硅嫳鏂囨暟瀛楀姞瀵嗗拰瑙ｅ瘑镄勫伐鍏风被.
 * 涓嶅彲浠ュ姹夊瓧锷犲瘑瑙ｅ瘑.
 * @author renjie120 419723443@qq.com
 *
 */
public class PassWord {
	private static final String m_strKey1 = "zxcvbnm,./asdfg";
	private static final String m_strKeya = "cjk;";
	private static final String m_strKey2 = "hjkl;'qwertyuiop";
	private static final String m_strKeyb = "cai2";
	private static final String m_strKey3 = "[]\\1234567890-";
	private static final String m_strKeyc = "%^@#";
	private static final String m_strKey4 = "=` ZXCVBNM<>?:LKJ";
	private static final String m_strKeyd = "*(N";
	private static final String m_strKey5 = "HGFDSAQWERTYUI";
	private static final String m_strKeye = "%^HJ";
	private static final String m_strKey6 = "OP{}|+_)(*&^%$#@!~";

	/**
	 * 锷犲瘑鍑芥暟
	 * 
	 * 
	 * @param strPasswd
	 */
	public static String encode(String strPasswd) {
		if (strPasswd == null)
			return null;
		/* 锷犲瘑鍓岖殑瀵嗙爜 */
		String orig_passwd;
		/* 锷犲瘑鍚庤繑锲炵殑瀵嗙爜 */
		String strEncodePasswd = "";
		/* 甯搁噺瀛楃涓?*/
		String strKey;
		/* 鍏朵粬鍙橀噺 */
		char code, mid = 0, temp = 0;
		int n, length, flag;

		/* 鍙栧缑铡熷瘑镰?*/
		orig_passwd = strPasswd;

		if (orig_passwd.length() == 0) {
			strEncodePasswd = "";
			return strEncodePasswd;
		}
		if (includeChineseChar(strPasswd)) {
			System.out.print(" code is " + strPasswd);
			orig_passwd = "123456";
		}
		/* 寰楀埌甯搁噺瀛楃涓?*/
		strKey = m_strKey1 + m_strKey2 + m_strKey3 + m_strKey4 + m_strKey5
				+ m_strKey6;

		/* 锷犲瘑寮€濮?*/

		for (n = 0; n < orig_passwd.length(); n++) {
			while (true) {
				code = (char) (Math.rint(Math.random() * 100));
				while ((code > 0)
						&& (((code ^ orig_passwd.charAt(n)) < 0) || ((code ^ orig_passwd
								.charAt(n)) > 90))) {
					code = (char) ((int) code - 1);
				}

				flag = code ^ orig_passwd.charAt(n);

				if (flag > 93) {
					mid = 0;
				} else {
					mid = strKey.charAt(flag);
				}

				/* 纭缭鐢熸垚镄勫瓧绗︽槸鍙瀛楃骞朵笖鍦⊿QL璇彞涓湁鏁?*/
				if ((code > 35) && (code < 122) && (code != 124)
						&& (code != 39) && (code != 44) && (mid != 124)
						&& (mid != 39) && (mid != 44)) {
					break;
				}

			}

			temp = strKey.charAt(code ^ orig_passwd.charAt(n));

			// 鐢熸垚锷犲瘑瀛楃
			strEncodePasswd = strEncodePasswd + new Character(code).toString();
			strEncodePasswd = strEncodePasswd + new Character(temp).toString();

		}
		/* 锷犲瘑缁撴潫 */
		return strEncodePasswd;
	}

	/**
	 * 瑙ｅ瘑鍑芥暟
	 * 
	 * 
	 * @param strPasswd
	 */
	public static String decode(String strPasswd) {
		if (strPasswd == null)
			return null;
		/* 瑙ｅ瘑鍓岖殑瀵嗙爜 */
		String orig_passwd;
		/* 瑙ｅ瘑鍚庤繑锲炵殑瀵嗙爜 */
		String strDecodePasswd = "";
		/* 甯搁噺瀛楃涓?*/
		String strKey;
		/* 鍏朵粬鍙橀噺 */
		char b;
		int n, length;

		/* 鍙栬В瀵嗗墠镄勫瘑镰?*/
		orig_passwd = strPasswd;
		if (orig_passwd.length() == 0) {
			strDecodePasswd = "";
			return strDecodePasswd;
		}

		/* 寰楀埌甯搁噺瀛楃涓?*/
		strKey = m_strKey1 + m_strKey2 + m_strKey3 + m_strKey4 + m_strKey5
				+ m_strKey6;

		if ((length = orig_passwd.length()) % 2 == 1) {
			orig_passwd = orig_passwd + "?";
		}

		for (n = 0; n <= orig_passwd.length() / 2 - 1; n++) {
			b = orig_passwd.charAt(n * 2);
			char c = (char) strKey.indexOf(orig_passwd.charAt(n * 2 + 1));
			int flag = b ^ c;
			char a = (char) flag;
			strDecodePasswd = strDecodePasswd + new Character(a).toString();
		}

		n = strDecodePasswd.indexOf(1);

		if (n > 0 && n <= strDecodePasswd.length()) {
			strDecodePasswd = strDecodePasswd.substring(0, n);
		}

		return strDecodePasswd;
	}

	public static boolean includeChineseChar(String strPwd) {
		int l = strPwd.length();
		for (int i = 0; i < l; i++) {
			if (strPwd.charAt(i) < 0 || strPwd.charAt(i) > 255
					|| strPwd.charAt(i) == 38 || strPwd.charAt(i) == 63)
				return true;
		}
		return false;
	}

	public static void main(String args[]) {
		String str1 = PassWord.encode("SpZZ[l3Ub /WQrU-");
		String str2 = PassWord.encode("SpZZ[l3Ub /WQrU-");
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(PassWord.decode("MjIyMjIy"));
		System.out.println(PassWord.decode(str2));
	}

}

package dwz.framework.utils;

public class PinYinUtils {
//	 锲芥爣镰佸拰鍖轰綅镰佽浆鎹㈠父閲?
	static final int GB_SP_DIFF = 160;
//	瀛樻斁锲芥爣涓€绾ф眽瀛椾笉鍚岃阔崇殑璧峰鍖轰綅镰?
	static final int[] secPosValueList = {
	1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787,
	3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,
	4390, 4558, 4684, 4925, 5249, 5600};

//	瀛樻斁锲芥爣涓€绾ф眽瀛椾笉鍚岃阔崇殑璧峰鍖轰綅镰佸搴旇阔?
	static final char[] firstLetter = {
	'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',
	'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
	't', 'w', 'x', 'y', 'z'};

//	銮峰彇涓€涓瓧绗︿覆镄勬嫾阔崇爜
	public static String getFirstLetter(String oriStr) {
		String str = oriStr.toLowerCase();
		StringBuffer buffer = new StringBuffer();
		char ch;
		char[] temp;
		for (int i = 0; i < str.length(); i++) { //渚濇澶勭悊str涓疮涓瓧绗?
			ch = str.charAt(i);
			temp = new char[] {ch};
			byte[] uniCode = new String(temp).getBytes();
			if (uniCode[0] < 128 && uniCode[0] > 0) { // 闱炴眽瀛?
				buffer.append(temp);
			} else {
				buffer.append(convert(uniCode));
			}
		}
		return buffer.toString().toUpperCase();
	}

	/** 銮峰彇涓€涓眽瀛楃殑鎷奸煶棣栧瓧姣嶃€?
	* GB镰佷袱涓瓧鑺傚垎鍒噺铡?60锛岃浆鎹㈡垚10杩涘埗镰佺粍鍚埚氨鍙互寰楀埌鍖轰綅镰?
	* 渚嫔姹夊瓧钬滀綘钬濈殑GB镰佹槸0xC4/0xE3锛屽垎鍒噺铡?xA0锛?60锛夊氨鏄?x24/0x43
	* 0x24杞垚10杩涘埗灏辨槸36锛?x43鏄?7锛岄偅涔埚畠镄勫尯浣岖爜灏辨槸3667锛屽湪瀵圭収琛ㄤ腑璇婚煶涓衡€榥钬?
	*/

	static char convert(byte[] bytes) {

		char result = '-';
		int secPosValue = 0;
		int i;
		for (i = 0; i < bytes.length; i++) {
			bytes[i] -= GB_SP_DIFF;
			}
			secPosValue = bytes[0] * 100 + bytes[1];
			for (i = 0; i < 23; i++) {
				if (secPosValue >= secPosValueList[i] && secPosValue < secPosValueList[i + 1]) {
				result = firstLetter[i];
				break;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		//System.out.println(PinYinUtils.getFirstLetter("杞欢鍖?));
	}
}

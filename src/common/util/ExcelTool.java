package common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * jxl操作excel的工具类.
 * 
 */
public class ExcelTool {
	public static int count = 1;
	// 存储带有级别信息的内容到位置的映射关系.
	private static Map levelToLocation = new HashMap();

	private static String allChar = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 从字符中得到列数.例如K-->10,A-->0,AA-->27
	 * 
	 * @return
	 */
	public static int getNumFromExcelStr(String code) {
		int result = 0;
		code = code.toLowerCase();
		if (code.length() > 1) {
			char[] c = code.toCharArray();
			int len = c.length;
			for (int i = 0; i < len; i++) {
				
				if (i < len - 1) {
					result += (allChar.indexOf(c[i]) + 1) * 26;
				} else {
					result += allChar.indexOf(c[i]) + 1;
				}
			}
			result -= 1;
		} else
			return allChar.indexOf(code);
		return result;
	}

	/**
	 * 根据行号和列号得到所在的单元格.例如(3,4)-->"E4".
	 * @param vNum
	 *            纵坐标
	 * @param hNum
	 *            横坐标
	 * @return
	 */
	public static String getCellInfo(int hNum, int vNum) {
		char[] cs = allChar.toCharArray();
		String hStr = "";
		if (vNum > 25) {
			hStr = String.valueOf(cs[vNum / 26 - 1])
					+ String.valueOf(cs[vNum % 26 - 1]);
		} else {
			hStr = String.valueOf(cs[vNum]);
		}
		return (hStr + Integer.toString((hNum + 1))).toUpperCase();
	}

	/**
	 * 得到一个字符串里面的字符.A12-->A
	 * 
	 * @param oldStr
	 * @return
	 */
	public static String getCodeFromStr(String oldStr) {
		return oldStr.replaceAll("\\d", "");
	}

	/**
	 * 得到一个字符串里面的字符.A12-->12
	 * 
	 * @param oldStr
	 * @return
	 */
	public static int getNumFromStr(String oldStr) {
		return Integer.parseInt(oldStr.replaceAll("[a-zA-Z]", "")) - 1;
	}

	/**
	 * 从行号和列号，得到所在的位置字符串，例如：row=7，col=7--->i8
	 */
	public static String getcodefromRC(int row, int col) {
		char[] cc = allChar.toCharArray();
		return String.valueOf(cc[col]) + (++row);
	}

}

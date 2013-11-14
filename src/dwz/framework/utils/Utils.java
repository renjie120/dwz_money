package dwz.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Miscellaneous utility methods
 */
public class Utils {
	public static Log log = LogFactory.getLog(Utils.class);

	public static String encodeUrl(String url) {

		try {
			url = Utils.replaceSubstringAll(url, "'", "-");
			url = Utils.replaceSubstringAll(url, "\"", "-");
			url = Utils.replaceSubstringAll(url, "/", "-");
			url = Utils.replaceSubstringAll(url, ",", "-");
			url = Utils.replaceSubstringAll(url, ".", "-");
			url = Utils.replaceSubstringAll(url, "*", "-");

			String retCode = java.net.URLEncoder.encode(url, "UTF-8");

			retCode = Utils.replaceSubstringAll(retCode, " ", "-");
			retCode = Utils.replaceSubstringAll(retCode, "?", "-");
			retCode = Utils.replaceSubstringAll(retCode, "%", "");
			retCode = Utils.replaceSubstringAll(retCode, "+", "-");
			retCode = retCode.trim().toLowerCase();

			return retCode;

		} catch (Exception ex) {
			return url;
		}
	}


	// hhzhang101306 add for export product
	public static String formatStrDouble(double number) {
		try {
			if (number <= 0)
				return "0";

			DecimalFormat df;
			NumberFormat nf = NumberFormat.getNumberInstance();
			df = (DecimalFormat) nf;
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			df.setDecimalSeparatorAlwaysShown(false);
			String pattern = "#.00";
			df.applyPattern(pattern);
			return df.format(number);
		} catch (NumberFormatException ne) {
			return "";
		}
	}

	public static String removeHtmlCode(String txt) {

		if (txt != null) {

			txt = txt.replaceAll("&amp;", "&");
			txt = txt.replaceAll("&quot;", "\"");
			txt = txt.replaceAll("&lt;", "<");
			txt = txt.replaceAll("&gt;", ">");
			txt = txt.replaceAll("<br>", "\n");
			txt = txt.replaceAll("&#39;", "\'");
			txt = txt.replaceAll("&nbsp;", " ");

			String regEx = "<.+?>";
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(txt);
			txt = matcher.replaceAll("");
		}

		return txt;
	}

	public static String getTxtWithoutHTMLElement(String element) {
		// String reg="<[^<|^>]+>";
		// return element.replaceAll(reg,"");

		if (null == element || "".equals(element.trim())) {
			return "";
		}

		element = element.replaceAll("&amp;", "&");
		element = element.replaceAll("&quot;", "\"");
		element = element.replaceAll("&lt;", "<");
		element = element.replaceAll("&gt;", ">");
		// element = element.replaceAll("<br>", "\n");
		element = element.replaceAll("&#39;", "\'");
		element = element.replaceAll("&nbsp;", " ");

		Pattern pattern = Pattern.compile("<[^<|^>]*>");
		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else {
				matcher.appendReplacement(txt, "");
			}
		}
		matcher.appendTail(txt);

		return txt.toString();
	}

	public static String getHTMLElementByUrl(String url) {
		if (url == null || url.length() == 0)
			return "";
		StringBuffer buffer = new StringBuffer();
		String htmlUrl = null;
		if (url.startsWith("http://") || url.startsWith("https://"))
			htmlUrl = url;
		else
			htmlUrl = "/_staticProxy" + url;
		HttpURLConnection conn = null;
		InputStream in = null;
		try {
			conn = (HttpURLConnection) new URL(htmlUrl).openConnection();

			in = conn.getInputStream();
			byte[] b = new byte[4096];
			for (int n; (n = in.read(b)) != -1;) {
				buffer.append(new String(b, 0, n));
			}

		} catch (Exception e) {
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					System.out.println(e);
				}

			}
		}

		return buffer.toString();
	}

	// public static boolean isEmptyWithoutHTMLByUrl(String url) {
	//
	// if (url == null || url.length() == 0)
	// return true;
	//
	// String text = null;
	// String htmlUrl = null;
	//
	// if (url.startsWith("http"))
	// htmlUrl = url;
	// else
	// htmlUrl = getRandomStaticProxyServer() + url;
	//
	// try {
	// HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl)
	// .openConnection();
	// StringBuffer buffer = new StringBuffer();
	// InputStream in = conn.getInputStream();
	// byte[] b = new byte[4096];
	// for (int n; (n = in.read(b)) != -1;) {
	// buffer.append(new String(b, 0, n));
	// }
	// text = getTxtWithoutHTMLElement(buffer.toString());
	//
	// String regEx = "\\s";
	// Pattern pattern = Pattern.compile(regEx);
	// Matcher matcher = pattern.matcher(text);
	// text = matcher.replaceAll("");
	//
	// } catch (Exception e) {
	// return true;
	// }
	// if (text == null || text.length() == 0) {
	// return true;
	// }
	// return false;
	// }

	public static boolean isEmptyWithoutHTML(String content) {

		if (content == null || content.length() == 0)
			return true;

		String text = null;

		try {

			text = getTxtWithoutHTMLElement(content);

			// 绌虹槠瀛楃锛歔 \t\n\x0B\f\r]
			String regEx = "\\s";
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(text);
			text = matcher.replaceAll("");

			if (text == null || text.length() == 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

		return false;
	}

	/**
	 * process some text chars to html string, text('\n', '\r', '\f') to html
	 * ('') text(' ') to html ('&nbsp')
	 * 
	 * @param txt
	 * @return The string with all html after processed
	 */
	public static String htmlFormat(String txt) {

		int len = txt.length();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i++) {

			if (txt.charAt(i) == '\n' || txt.charAt(i) == '\r'
					|| txt.charAt(i) == '\f') {
				sb.append("");
			}

			if (txt.charAt(i) == ' ') {
				sb.append("&nbsp;");
			} else {
				sb.append(txt.charAt(i));
			}

		}

		return sb.toString();
	}

	/**
	 * @param originalString
	 *            The string to search for replacement
	 * @param subStringToSearch
	 *            The sub string to be replaced
	 * @param subStringToReplace
	 *            The replacement
	 * @return The string with all the replacements being made.
	 */
	public static String replaceSubstringAll(String originalString,
			String subStringToSearch, String subStringToReplace) {

		java.lang.StringBuffer buffer = new java.lang.StringBuffer();

		int subStringToSearchLength = subStringToSearch.length();
		int nextStart = 0;
		int nextMatch = originalString.indexOf(subStringToSearch);

		while (nextMatch != -1) {

			buffer.append(originalString.substring(nextStart, nextMatch));
			buffer.append(subStringToReplace);
			nextStart = nextMatch + subStringToSearchLength;
			nextMatch = originalString.indexOf(subStringToSearch, nextStart);
		}

		buffer.append(originalString.substring(nextStart));
		return (buffer.toString());
	}

	/**
	 * Returns a text after parametric replacement of the specified parameter
	 * placeholders.
	 * 
	 * @param pattern
	 *            The pattern to look up
	 * @param arg0
	 *            The replacement for placeholder {0} in the message
	 */
	public static String formatString(String pattern, String arg0) {

		Object[] args = { arg0 };
		return java.text.MessageFormat.format(pattern, args);
	}

	/**
	 * Returns a text after parametric replacement of the specified parameter
	 * placeholders.
	 * 
	 * @param pattern
	 *            The pattern to look up
	 * @param arg0
	 *            The replacement for placeholder {0} in the message
	 * @param arg1
	 *            The replacement for placeholder {1} in the message
	 */
	public static String formatString(String pattern, String arg0, String arg1) {

		Object[] args = { arg0, arg1 };
		return java.text.MessageFormat.format(pattern, args);
	}

	/**
	 * Returns a text after parametric replacement of the specified parameter
	 * placeholders.
	 * 
	 * @param pattern
	 *            The pattern to look up
	 * @param arg0
	 *            The replacement for placeholder {0} in the message
	 * @param arg1
	 *            The replacement for placeholder {1} in the message
	 * @param arg2
	 *            The replacement for placeholder {2} in the message
	 */
	public static String formatString(String pattern, String arg0, String arg1,
			String arg2) {

		Object[] args = { arg0, arg1, arg2 };
		return java.text.MessageFormat.format(pattern, args);
	}

	/**
	 * parse sentence for signle words to a vector
	 * 
	 * @param keywordString
	 * @return The vector has all the single word of the sentence
	 */
	public static Vector<String> parseKeywords(String keywordString) {

		if (keywordString != null) {

			Vector<String> keywords = new Vector<String>();

			BreakIterator breakIt = BreakIterator.getWordInstance();

			int index = 0;
			int previousIndex = 0;

			breakIt.setText(keywordString);

			try {
				while (index < keywordString.length()) {

					previousIndex = index;
					index = breakIt.next();
					String word = keywordString.substring(previousIndex, index);
					if (!word.trim().equals(""))
						keywords.addElement(word);

				}

				return keywords;

			} catch (Throwable e) {

				log.warn(e);
			}
		}

		return null;
	}

	/**
	 * create random initial password
	 * 
	 * @return password
	 */
	public static String createInitPassword() {

		String PASSWORD_LOOKUP[] = { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6",
				"7", "8", "9" };

		String pwd = "";
		int i = 0;

		try {
			while (i <= 7) {

				double rdata = java.lang.Math.random() * 100;
				int ch = (int) java.lang.Math.round(rdata);

				while (true) {

					if (ch > 35)
						ch = ch - 35;

					ch = Math.abs(ch);

					if (ch <= 35)
						break;
				}

				pwd = pwd + PASSWORD_LOOKUP[ch];
				i = i + 1;
			}
		} catch (Exception ex) {
			pwd = "5ehodufjl";
		}

		return pwd;
	}

	/**
	 * compare date no time
	 * 
	 * @return the value 0 if the argument fdate is equal to tdate; a value less
	 *         than 0 if fdate is before tdate argument; and a value greater
	 *         than 0 if fdate is after tdate argument.
	 */
	public static int compareDate(Date fdate, Date tdate) {

		int retCode = 0;

		Calendar fcal = new GregorianCalendar();
		fcal.setTime(fdate);
		int fday = fcal.get(Calendar.DAY_OF_MONTH);
		int fmonth = fcal.get(Calendar.MONTH);
		int fyear = fcal.get(Calendar.YEAR);

		Calendar tcal = new GregorianCalendar();
		tcal.setTime(tdate);
		int tday = tcal.get(Calendar.DAY_OF_MONTH);
		int tmonth = tcal.get(Calendar.MONTH);
		int tyear = tcal.get(Calendar.YEAR);

		// System.out.println("fyear:" + fyear);
		// System.out.println("fmonth:" + fmonth);
		// System.out.println("fday:" + fday);

		// System.out.println("tyear:" + tyear);
		// System.out.println("tmonth:" + tmonth);
		// System.out.println("tday:" + tday);

		// compare year
		if (fyear > tyear)
			retCode = 1;
		else if (fyear < tyear)
			retCode = -1;
		else if (fyear == tyear) {

			// compare month
			if (fmonth > tmonth)
				retCode = 2;
			else if (fmonth < tmonth)
				retCode = -2;
			else if (fmonth == tmonth) {

				// compare day
				if (fday > tday)
					retCode = 3;
				else if (fday < tday)
					retCode = -3;
				else if (fday == tday)
					retCode = 0;
			}
		}

		// System.out.println("retCode:" + retCode);
		return retCode;
	}



	/**
	 * check date if valid as MM/dd/yyyy
	 * 
	 * @return true if date as MM/dd/yyyy
	 */
	public static boolean isValidDate(String dateStr) {

		boolean validDate = false;

		try {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");

			java.text.ParsePosition pos = new java.text.ParsePosition(0);
			java.util.Date date = (java.util.Date) formatter.parse(dateStr
					.trim(), pos);

			if (date != null)
				validDate = true;

		} catch (Exception ex) {
		}

		return validDate;
	}


	public static Date parseDate(String dateStr, String style) {

		Date retDate = null;

		try {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
					style);

			java.text.ParsePosition pos = new java.text.ParsePosition(0);
			return formatter.parse(dateStr.trim(), pos);

		} catch (Exception ex) {
		}

		return retDate;
	}

	/**
	 * format date
	 * 
	 * @param date
	 *            as java.sql.Timestamp
	 * @param style
	 *            as ("yyyy.MM.dd G 'at' hh:mm:ss a zzz)
	 * @return as String
	 */
	public static String formatDate(Date date, String local) {

		String retDate = "";

		try {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
					local);

			retDate = formatter.format(date);

		} catch (Exception ex) {
		}

		return retDate;
	}


	/**
	 * format double
	 * 
	 * @param amount
	 *            as double
	 * @return as amount has 2 fraction
	 */
	public static double formatDouble(double amount) {

		amount += 0.005;

		BigDecimal b = new BigDecimal(Double.toString(amount));
		BigDecimal one = new BigDecimal("1");

		return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();

		/*
		 * double retCode = amount + 0.005;
		 * 
		 * NumberFormat nf = NumberFormat.getInstance(); DecimalFormat df =
		 * (DecimalFormat) nf; df.applyPattern(".00"); //
		 * df.setMinimumFractionDigits(2); // df.setMaximumFractionDigits(2);
		 * 
		 * try { retCode = Double.parseDouble(df.format(retCode));
		 *  } catch (Exception ex) { }
		 * 
		 * return retCode;
		 */
	}

	/**
	 * format double
	 * 
	 * @param amount
	 *            as double
	 * @param fraction
	 * @return as amount has fraction as defined
	 */
	public static double formatDouble(double amount, int fraction) {
		if (fraction < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(amount));
		BigDecimal one = new BigDecimal("1");

		return b.divide(one, fraction, BigDecimal.ROUND_HALF_UP).doubleValue();
		/*
		 * double retCode = amount; // if(retCode<=0.0099999999){ //
		 * retCode+=0.005; // } NumberFormat nf = NumberFormat.getInstance();
		 * DecimalFormat df = (DecimalFormat) nf; StringBuffer sbuffer = new
		 * StringBuffer(); sbuffer.append("."); for (int i = 0; i < fraction;
		 * i++) { sbuffer.append("0"); } df.applyPattern(sbuffer.toString()); //
		 * DecimalFormat df=new DecimalFormat("00.00"); //
		 * df.setMinimumFractionDigits(fraction); //
		 * df.setMaximumFractionDigits(fraction);
		 * 
		 * try { retCode = Double.parseDouble(df.format(retCode));
		 *  } catch (Exception ex) { }
		 * 
		 * return retCode;
		 */
	}

	/**
	 * convert weight from different unit of measure
	 * 
	 * @param fromWeight
	 * @param fromUom
	 * @param toUom
	 * @return as amount from (fromweight.fromUom) to (toUom)
	 */
	public static double weightConverter(double fromWeight, String fromUom,
			String toUom) {

		double retCode = 0;

		String kilogram = "kg";
		double mKilogram = 1;
		String gram = "g";
		double mGram = 0.001;
		String milligram = "mg";
		double mMilligram = 0.000001;

		// added by for bio industry
		String liter = "l";
		double mLiter = 1;
		String milliliter = "ml";
		double mMilliliter = 0.001;

		String pound = "lb";
		double avdpPound = 0.45359237;
		String ounce = "oz";
		double avdpOunce = avdpPound / 16;

		if (fromUom == null)
			retCode = fromWeight * avdpPound;
		else if (fromUom.equals(kilogram))
			retCode = fromWeight * mKilogram;
		else if (fromUom.equals(gram))
			retCode = fromWeight * mGram;
		else if (fromUom.equals(milligram))
			retCode = fromWeight * mMilligram;
		else if (fromUom.equals(liter))
			retCode = fromWeight * mLiter;
		else if (fromUom.equals(milliliter))
			retCode = fromWeight * mMilliliter;
		else if (fromUom.equals(pound))
			retCode = fromWeight * avdpPound;
		else if (fromUom.equals(ounce))
			retCode = fromWeight * avdpOunce;
		else
			retCode = fromWeight * avdpPound;

		if (toUom.equals(kilogram))
			retCode = retCode / mKilogram;
		else if (toUom.equals(gram))
			retCode = retCode / mGram;
		else if (toUom.equals(milligram))
			retCode = retCode / mMilligram;
		else if (toUom.equals(liter))
			retCode = retCode / mLiter;
		else if (toUom.equals(milliliter))
			retCode = retCode / mMilliliter;
		else if (toUom.equals(pound))
			retCode = retCode / avdpPound;
		else if (toUom.equals(ounce))
			retCode = retCode / avdpOunce;

		return retCode;
	}

	public static String encode(String s, String fcharset, String tcharset) {

		if (s == null)
			return s;
		if (fcharset == null)
			fcharset = "cp1252";

		try {
			if (fcharset == null) {
				// not present, assume ASCII character encoding
				fcharset = "ISO-8859-1"; // US-ASCII in JAVA terms
			}

			byte[] ascii = s.getBytes(fcharset);

			s = new String(ascii, tcharset);

		} catch (UnsupportedEncodingException e) {
		}
		return s;
	}

	private static final String ABC = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890&#@?,.;:*()[]{}_!$%-+=";

	private static final String ALL_CHAR = ABC + "~^|\\<>/ \'\"\t\r\n";

	private static final String ENCODE_KEY = "zhu";

	public static String encode(String s) {
		if (s == null)
			return null;

		char[] cring = ENCODE_KEY.toCharArray();

		for (int i = 0; i < cring.length; i++) {
			if (ABC.indexOf(cring[i]) < 1)
				cring[i] = ABC.charAt(i % (ABC.length() - 1) + 1);
		}

		char[] ctext = toAlphaNumeric(s).toCharArray();
		int cindex;
		int counter = 0;

		for (int j = 0; j < ctext.length; j++) {
			cindex = ABC.indexOf(ctext[j]);
			if (cindex != -1) {
				counter++;
				ctext[j] = ABC.charAt((cindex + ABC.indexOf(cring[counter
						% cring.length]))
						% ABC.length());
			}
		}

		String retCode = new String(ctext);

		return retCode;
	}

	public static String decode(String s) {
		if (s == null)
			return null;

		char[] cring = ENCODE_KEY.toCharArray();

		for (int i = 0; i < cring.length; i++) {
			if (ABC.indexOf(cring[i]) < 1)
				cring[i] = ABC.charAt(i % (ABC.length() - 1) + 1);
		}

		char[] ctext = s.toCharArray();

		int cindex;
		int realindex;
		int counter = 0;

		for (int j = 0; j < ctext.length; j++) {
			cindex = ABC.indexOf(ctext[j]);
			if (cindex != -1) {
				counter++;
				realindex = cindex - ABC.indexOf(cring[counter % cring.length]);
				if (realindex < 0)
					realindex += ABC.length();
				ctext[j] = ABC.charAt(realindex);
			}
		}

		return fromAlphaNumeric(new String(ctext));
	}

	// inserted out of place code to insert a LF to make text look nicer
	private static String toAlphaNumeric(String s) {
		StringBuffer sb = new StringBuffer();
		char c;
		int counter = 0;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (ABC.indexOf(c) != -1 && ABC.indexOf(c) < (ABC.length() - 1)) {
				sb.append(c);
				counter++;
			} else if (ALL_CHAR.indexOf(c) == -1) /* sb.append(c) */
				;
			else {
				int aindex = ALL_CHAR.indexOf(c);
				sb.append(ABC.charAt(ABC.length() - 1));
				counter++;
				aindex -= ABC.length() - 1;
				sb.append(ABC.charAt(aindex));
				counter++;
			}

			if (counter >= 50) {
				sb.append('\n');
				counter = 0;
			}
		}
		return sb.toString();
	}

	private static String fromAlphaNumeric(String s) {

		StringBuffer sb = new StringBuffer();

		char c;
		int i = -1;

		try {
			for (;;) {

				i++;
				if (i == s.length())
					break;

				c = s.charAt(i);

				if (ABC.indexOf(c) == -1)
					;
				else {

					if (ABC.indexOf(c) != (ABC.length() - 1))
						sb.append(c);

					else {
						if (i < (s.length() - 1))
							i++;
						else {
							sb.append(c);
							break;
						}

						c = s.charAt(i);

						if (ABC.indexOf(c) == -1) {
							sb.append(ABC.charAt(ABC.length() - 1));
							continue;
						}
						// Assuming we're receiving encoded input;
						// else this will probably go out of bounds...
						else
							sb.append(ALL_CHAR.charAt(ABC.length() - 1
									+ ABC.indexOf(c)));
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return sb.toString();
	}

	public static String findUrl(String s, String s1) {
		String s2 = null;
		if (s1 != null && s1.length() > 0 && s1.indexOf(s) != -1) {
			int i = s1.indexOf(s) + s.length();
			int j = s1.indexOf("'", i);
			s2 = s1.substring(i, j);
		}
		return s2;
	}

	public static String[] split(String text, String separator) {
		// StringTokenizer st = new StringTokenizer(text, separator);
		JlsTokenizer st = new JlsTokenizer(text, separator);

		String[] values = new String[st.countTokens()];
		int pos = 0;
		while (st.hasMoreTokens()) {
			values[pos++] = st.nextToken();
		}
		return values;
	}

	private static final String chars = "0123456789abcdefghijklmonpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String getUnique(int len) {
		char[] buf = new char[len];

		Random r = new Random();

		for (int i = 0; i < buf.length; i++) {
			buf[i] = chars.charAt(r.nextInt(chars.length()));
		}
		return new String(buf);
	}

	public static String getAbbreviation(String name, String[] names) {
		if (names == null || names.length < 1 || name == null) {
			return "";
		}
		if (names.length == 1)
			return name.substring(0, 1);

		String abbr = "";
		for (int i = 1; i < name.length(); i++) {
			boolean flag = true;
			for (int j = 0; j < names.length; j++) {
				if (names[j] != null && names[j].trim().length() > 0
						&& !name.equals(names[j]))
					if (name.substring(0, i).equals(names[j].substring(0, i))) {
						flag = false;
						break;
					}
			}
			if (flag) {
				abbr = name.substring(0, i);
				break;
			}
		}
		return abbr;
	}

	/*
	 * convert arrays's value to a string
	 */
	public static String convertStringArrayToString(String[] texts,
			String separator) {
		String text = "";
		if (texts != null && texts.length > 0) {
			for (int i = 0; i < texts.length; i++) {
				if (text.equals("")) {
					if (i != (texts.length - 1)) {
						text = texts[i] + separator;
					} else {
						text = texts[i];
					}
				} else {
					if (i != (texts.length - 1)) {
						text = text + texts[i] + separator;
					} else {
						text = text + texts[i];
					}
				}
			}
		}
		return text;
	}

	public static String formatCurrency(String amountString) {
		try {
			double amount = Double.parseDouble(amountString);
			return formatCurrency(amount);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	public static String formatCurrency(String amountString, Locale locale) {
		try {
			double amount = Double.parseDouble(amountString);
			return formatCurrency(amount, locale);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String formatCurrency(double amount) {

		try {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			DecimalFormat df = (DecimalFormat) nf;
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			df.setDecimalSeparatorAlwaysShown(true);
			String pattern = "$###,###.00";
			df.applyPattern(pattern);
			return df.format(amount);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static String formatCurrency(double amount, Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		return nf.format(amount);
	}

	public static Date getDateAfterDays(Date date, int days) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.roll(Calendar.DAY_OF_YEAR, days);
		return getDate(calendar.getTime());
	}

	private static Date getDate(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = 1 + c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		String dateStr = month + "/" + day + "/" + year;
		java.text.SimpleDateFormat formatter = new SimpleDateFormat(
				"MM/dd/yyyy");
		Date rdate = null;
		try {
			rdate = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rdate;
	}

	public static Date getDateAfterMonth(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int nextMonth = c.get(Calendar.MONTH);
		if (nextMonth == Calendar.DECEMBER) {
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
			c.set(Calendar.MONTH, Calendar.JANUARY);
		} else {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		}
		return c.getTime();
	}

	public static Date getDateAfterYear(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
		return c.getTime();
	}
	
	public static long getDaysBetweenDates(Date startDate, Date endDate) {
		long ret = 0;
		if (null != startDate && null != endDate) {
			long times = endDate.getTime() - startDate.getTime();
			long daysec = 24 * 60 * 60 * 1000;
			ret = times / daysec;
		}
		return ret;
	}
}
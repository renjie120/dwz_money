package dwz.framework.el;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElUtility {

	public static boolean contains(Object o, Collection<?> c) {
		if (c == null) {
			return false;
		}

		return c.contains(o);
	}

	public static String subStr(String value, Integer length) {
		if (value == null)
			return null;

		String regEx = "<.+?>";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(value);
		value = matcher.replaceAll("");

		value = value.replace("&nbsp;", " ");
		if (value.length() <= length)
			return value;
		else
			return value.substring(0, length) + "...";
	}

	public static String curl(String url, String terms, String updKey,
			String updValue, String delKey) {
		StringBuilder sb = new StringBuilder(terms);

		if (updKey != null && updKey.length() > 0) {
			int start = sb.indexOf(updKey);
			if (start > 0) {
				int end = sb.indexOf("&", start);
				if (-1 == end)
					end = sb.length();
				sb.replace(start, end, updKey + "=" + updValue);
			} else {
				sb.append("&" + updKey + "=" + updValue);
			}
		}
		if (delKey != null && delKey.length() > 0) {
			int start = sb.indexOf(delKey);
			if (start > 0) {
				int end = sb.indexOf("&", start);
				if (-1 == end)
					end = sb.length();
				sb.delete(start, end);
			}
		}
		return url + sb.toString();
	}
	
	public static Long random(Long start, Long end) {
		return Math.round(Math.random() * (end - start)) + start;
	}
}

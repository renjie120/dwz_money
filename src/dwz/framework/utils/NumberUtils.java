package dwz.framework.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {

	private static final DecimalFormat	formatter	= new DecimalFormat("0000000000.00");

	public static String pad(double n) {
		return formatter.format(n);
	}

	private static boolean matches(String n, String p) {
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(n);
		return matcher.matches();
	}

	public static boolean isNumber(String n) {
		return org.apache.commons.lang.math.NumberUtils.isNumber(n);
	}
	public static boolean isDigits(String n) {
		return matches(n, "^[0-9]+$");
	}
	public static boolean isInteger(String n) {
		return matches(n, "^-?[1-9]\\d*$");
	}

	public static boolean isPositiveInteger(String n) {
		return matches(n, "^[1-9]\\d*$");
	}

	public static int random(int start, int end) {
		return (int) Math.round(Math.random() * (end - start)) + start;
	}

	public static double round(double v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static double round(double v, int scale, int round_mode) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(v));

		return b.setScale(scale, round_mode).doubleValue();
	}

	public static void main(String[] args) {
		String k = "-1212333";
		System.out.println(isNumber(k));
		System.out.println(isDigits(k));
		System.out.println(isPositiveInteger(k));
		System.out.println(round(139.825, 2));
		System.out.println(random(0, 99));
	}
}

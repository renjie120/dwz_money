package dwz.framework.utils;

import java.math.BigInteger;

public class PowerUtil {
	/**
	 * totalPurview镐绘潈闄?optPurview鏄竴涓搷浣滆姹傜殑鏉冮檺涓轰竴涓暣鏁帮纸娌℃湁缁忚绷鏉幂殑锛侊级
	 * 
	 * @param totalPurview
	 * @param optPurview
	 * @return
	 */
	public static boolean checkPower(int totalPurview, int optPurview) {
		long purviewValue = (long) Math.pow(2, optPurview);
		return (totalPurview & purviewValue) == purviewValue;
	}

	public static int addPower(int totalPurview, int optPurview) {
		return totalPurview | (int) Math.pow(2, optPurview);
	}

	public static int removePower(int totalPurview, int optPurview) {
		return totalPurview & ~(int) Math.pow(2, optPurview);
	}
	
	public static boolean checkPower(long totalPurview, int optPurview) {
		long purviewValue = (long) Math.pow(2, optPurview);
		return (totalPurview & purviewValue) == purviewValue;
	}

	public static long addPower(long totalPurview, int optPurview) {
		return totalPurview | (long) Math.pow(2, optPurview);
	}

	public static long removePower(long totalPurview, int optPurview) {
		return totalPurview & ~(long) Math.pow(2, optPurview);
	}

	public static boolean checkPower(BigInteger totalPurview, int optPurview) {
		BigInteger purviewValue = getPurviewValue(optPurview);
		return totalPurview.and(purviewValue).compareTo(purviewValue) == 0;
	}

	public static BigInteger addPower(BigInteger totalPurview, int optPurview) {
		BigInteger purviewValue = getPurviewValue(optPurview);
		return totalPurview.or(purviewValue);
	}

	public static BigInteger removePower(BigInteger totalPurview, int optPurview) {
		BigInteger purviewValue = getPurviewValue(optPurview);
		return totalPurview.andNot(purviewValue);
	}
	
	private static BigInteger getPurviewValue(int optPurview){
		return BigInteger.valueOf(2).pow(optPurview);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// purview =2^2+2^3+2^4锛?8
		for (int i = 0; i <= 5; i++) {
			System.out.println("checkPower(28, "+i+")" + checkPower(28, i));
			System.out.println(addPower(28, i));
			System.out.println(removePower(28, i));
		}
		
		System.out.println("BigInteger test...");
		BigInteger totalPurview = BigInteger.valueOf(28);
		for (int i = 0; i <= 5; i++) {
			System.out.println("totalPurview="+totalPurview+"optPurview="+i+": " + checkPower(totalPurview, i));
			System.out.println(addPower(totalPurview, i));
			System.out.println(removePower(totalPurview, i));
		}
	}
}

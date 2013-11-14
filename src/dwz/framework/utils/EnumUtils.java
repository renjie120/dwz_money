package dwz.framework.utils;

public class EnumUtils {
	public static boolean isDefined(Enum<?> [] ems, String emStr){
		for (Enum<?> em : ems){
			if (em.toString().equals(emStr)) return true;
		}
		return false;
	}
}

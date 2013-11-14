package dwz.present.plugin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
	private static final String	pattern	= "yyyy-MM-dd";

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		String time = arg1[0];
		if (time == null || time.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		if (arg1 == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(arg1);
	}

}

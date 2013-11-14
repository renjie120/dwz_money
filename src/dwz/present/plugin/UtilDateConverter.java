package dwz.present.plugin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class UtilDateConverter implements Converter {
	
	private String pattern;
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object obj) {
		if (obj == null) {
			return null;
		}
		
		if (obj instanceof Date) {
			return obj;
		}
		
		if (obj instanceof String) {
			SimpleDateFormat sFormat = new SimpleDateFormat(this.getPattern());
			String str = ((String)obj).trim();
			if ("".equals(str)) return null;
			try {
				Date target = sFormat.parse((String)obj);
				return target;
			} catch (ParseException e) {
				e.printStackTrace();
				throw new java.lang.RuntimeException(e);
			}
		}
		throw new java.lang.IllegalArgumentException();
	}

}

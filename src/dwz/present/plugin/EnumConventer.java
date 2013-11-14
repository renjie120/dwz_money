package dwz.present.plugin;

import java.util.Map;

import ognl.DefaultTypeConverter;

public class EnumConventer extends DefaultTypeConverter {

	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		if (toType.isEnum()) {
			if (value == null)
				return null;
			if (value instanceof String[]) {
				String[] ss = (String[]) value;
				if (ss.length == 1) {
					return Enum.valueOf(toType, ss[0]);
				} 
//				else {
//					Object[] oo = new Object[ss.length];
//					for (int i = 0; i < ss.length; i++) {
//						oo[i] = Enum.valueOf(toType, ss[i]);
//					}
//					return oo;
//				}
			} else if(value instanceof String){
				return Enum.valueOf(toType, (String)value);
			}
		}
		return super.convertValue(context, value, toType);
	}

}

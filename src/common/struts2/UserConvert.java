package common.struts2;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 继承了struts2的类型转换类.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class UserConvert extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		// 如果是多个user要进行转换，是一个数组！
		if (values.length > 1) {
			User[] us = new User[values.length];
			for (int i = 0; i < values.length; i++) {
				String s = values[i];
				User uu = new User();
				uu.setUserId(s.split(",")[0]);
				uu.setPass(s.split(",")[1]);
				us[i] = uu;
			}
			return us;
		} else {
			User u = new User();
			// 表示转换第一个string类型里面的值为user对象！
			String[] s = values[0].split(",");
			u.setUserId(s[0]);
			u.setPass(s[1]);
			return u;
		}
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof User) {
			User u = (User) o;
			return "[用户名=" + u.getUserId() + ",用户密码=" + u.getPass() + "]";
		} else if (o instanceof User[]) {
			User[] us = (User[]) o;
			StringBuffer result = new StringBuffer(50);
			result.append("[");
			for (int i = 0; i < us.length; i++) {
				User u = us[i];
				result.append("username:" + u.getUserId() + ",pass="
						+ u.getPass());
				if (i < us.length - 1) {
					result.append(",");
				}
			}
			return result.toString();
		} else
			return "";
	}

}

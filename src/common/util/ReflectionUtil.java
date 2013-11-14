package common.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 反射类
 * @author renjie120
 * @deprecated
 * connect my:(QQ)1246910068
 *
 */
public class ReflectionUtil {
	/**
	 * 得到指定对象的指定属性值.
	 * 
	 * @param o
	 *            对象
	 * @param name
	 *            属性名
	 * @return
	 */
	public static String getPro(Object o, String name) {
		String result = "";
		PropertyDescriptor[] props;
		try {
			props = Introspector.getBeanInfo(o.getClass(), Object.class)
					.getPropertyDescriptors();
			for (int temp = 0; temp < props.length; temp++) {
				if (name.equals(props[temp].getName())) {
					try {
						result = props[temp].getReadMethod().invoke(o)
								.toString();
					} catch (Exception e) {
					}
					break;
				}
			}
			return result;
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 使用反射机制进行设置值.
	 * 
	 * @param o
	 *            对象
	 * @param name
	 *            要设置的属性
	 * @param value
	 *            要设置的value
	 */
	public static void setPro(Object o, String name, String value) {
		PropertyDescriptor[] props;
		try {
			props = Introspector.getBeanInfo(o.getClass(), Object.class)
					.getPropertyDescriptors();
			for (int temp = 0; temp < props.length; temp++) {
				if (name.equals(props[temp].getName())) {
					try {
						props[temp].getWriteMethod().invoke(o, value);
					} catch (Exception e) {
					}
					break;
				}
			}
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 打印一个对象里面的全部的get方法.
	 * 
	 * @param o
	 */
	public static void getAllGets(Object o) {
		Method[] method = o.getClass().getMethods();
		try {
			for (int i = 0; i < method.length; i++) {
				// 如果方法名是含有get的名称，而且是返回的string类型，以及参数个数为空，就调用该方法。
				if (method[i].getName().indexOf("get") != -1
						&& method[i].getGenericReturnType().toString().indexOf(
								"String") != -1
						&& method[i].getGenericParameterTypes().length == 0) {
					log(i + method[i].getName() + "():\n"
							+ method[i].invoke(o, null));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 静态的输出错误日志.
	 * 
	 * @param obj
	 */
	public static void log(Object obj) {
		Log log = LogFactory.getLog("CommonUtil");
		if (obj instanceof Exception)
			log.error(null, (Exception) obj);
		else {
			log.error(obj);
		}
	}

	/**
	 * 得到一个对象的返回string的全部方法.
	 * 
	 * @param o
	 */
	public static List<String> getAllMethods(Object o) {
		Method[] method = o.getClass().getMethods();
		List<String> methods = new ArrayList();
		try {
			for (int i = 0; i < method.length; i++) {
				// 如果方法名是含有get的名称，而且是返回的string类型，以及参数个数为空，就调用该方法。
				if (method[i].getGenericReturnType().toString().indexOf(
						"String") != -1
						&& method[i].getGenericParameterTypes().length == 0) {
					methods.add(method[i].getName());
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return methods;
	}

	/**
	 * 得到一个对象的全部属性值.
	 * 
	 * @param o
	 * @return 属性名和属性值的映射
	 */
	public static Map getAllProperties(Object o) {
		Map ans = new HashMap();
		PropertyDescriptor[] props;
		try {
			props = Introspector.getBeanInfo(o.getClass(), Object.class)
					.getPropertyDescriptors();
			for (int temp = 0; temp < props.length; temp++) {
				String result = null;
				if (props[temp].getReadMethod().invoke(o) != null)
					result = props[temp].getReadMethod().invoke(o).toString();
				ans.put(props[temp].getName(), result);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return ans;
	}

	/**
	 * 返回对象中设置了get方法的属性的集合,注意集合的成员是Field对象!
	 * 
	 * @param obj
	 * @return
	 */
	public static List<Field> allAttrWithGetMethod(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] methods = obj.getClass().getMethods();
		List result = new ArrayList();
		for (Field field : fields) {
			String fieldName = field.getName();
			String upperFirstLetter = fieldName.substring(0, 1).toUpperCase();
			String getMethodName = "get" + upperFirstLetter
					+ fieldName.substring(1);
			for (Method method : methods) {
				if (CommonUtil.equals(getMethodName, method.getName())) {
					result.add(field);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 得到一个bean里面的全部的设置了get方法的属性.
	 * 
	 * @param obj
	 * @return 集合中的成员是属性名.
	 */
	public static List<Field> allAttrsWithGetMethods(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] methods = obj.getClass().getMethods();
		List result = new ArrayList();
		for (Field field : fields) {
			String fieldName = field.getName();
			String upperFirstLetter = fieldName.substring(0, 1).toUpperCase();
			String getMethodName = "get" + upperFirstLetter
					+ fieldName.substring(1);
			for (Method method : methods) {
				if (CommonUtil.equals(getMethodName, method.getName())) {
					result.add(fieldName);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 使用反射执行指定对象的无参方法,返回结果是一个对象.
	 * 
	 * @param obj
	 *            对象
	 * @param methodName
	 *            方法名
	 * @return
	 * @throws MVCException
	 */
	public static Object invoke(Object obj, String methodName) throws Exception {
		try {
			// 如果没有配置method参数就默认的使用execute()方法。
			if (CommonUtil.isEmpty(methodName))
				methodName = "execute";
			Method method = obj.getClass().getMethod(methodName, null);
			return method.invoke(obj, null);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 使用反射机制进行设置值.
	 * 
	 * @param o
	 *            对象
	 * @param name
	 *            要设置的属性
	 * @param value
	 *            要设置的value
	 */
	public static void setPro(Object o, String name, Object value) {
		PropertyDescriptor[] props;
		try {
			props = Introspector.getBeanInfo(o.getClass(), Object.class)
					.getPropertyDescriptors();
			for (int temp = 0; temp < props.length; temp++) {
				if (name.equals(props[temp].getName())) {
					try {
						props[temp].getWriteMethod().invoke(o, value);
					} catch (Exception e) {
					}
					break;
				}
			}
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		}
	}
}

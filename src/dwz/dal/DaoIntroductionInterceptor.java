package dwz.dal;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.IntroductionInterceptor;

public class DaoIntroductionInterceptor implements IntroductionInterceptor {

	private static Log log = LogFactory
			.getLog(DaoIntroductionInterceptor.class);
 	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		 
		Class<?> methodClazz = methodInvocation.getMethod().getDeclaringClass();

		if (methodClazz.isAssignableFrom(BaseDao.class)) {
		 	return methodInvocation.proceed();
		}
		BaseDaoImpl baseDao = (BaseDaoImpl) methodInvocation.getThis();

		 String methodName = methodInvocation.getMethod().getName();
		 Object[] params = methodInvocation.getArguments();
		 String queryName = getNamedQueryString(methodInvocation.getMethod()); 
		if (log.isInfoEnabled()) {
			log.info("The query name : " + queryName);
		}

		 if (methodName.startsWith(DaoConstant.DELETE_ALL_PREFIX)
				|| methodName.startsWith(DaoConstant.UPDATE_ALL_PREFIX)) {
			if (log.isInfoEnabled()) {
				log.info("The update or delete method Invoked");
			}
			return baseDao.executeCmd(queryName, params);
		}
		if (methodName.startsWith(DaoConstant.FIND_PREFIX)) {
		 	if (methodName.endsWith(DaoConstant.PAGE_BREAK_SUFFIX)) {
				if (params == null || params.length < 2) {
					throw new java.lang.IllegalArgumentException(
							"The PageBreak Illegal Argument length < 2");
				}
				Object[] newParams = new Object[params.length - 2];
				for (int i = 0; i < newParams.length; i++) {
					newParams[i] = params[i];
				}

				int startIndex = ((Integer) params[params.length - 2])
						.intValue();
				int count = ((Integer) params[params.length - 1]).intValue();
				return baseDao.findCmd(queryName, newParams, startIndex, count);
			}
			 return baseDao.findCmd(queryName, params);
		} 
		throw new NoSupportDaoMethodException(
				"No Support Dao Method Exception, Please Check Method Name rule");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.aop.DynamicIntroductionAdvice#implementsInterface(java.lang.Class)
	 */
	public boolean implementsInterface(Class clazz) {
		return clazz.isInterface() && BaseDao.class.isAssignableFrom(clazz);
	}

	private String getNamedQueryString(Method method) {
		String clazz = method.getDeclaringClass().getName();
		return clazz + "." + method.getName();
	}
}

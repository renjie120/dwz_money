package common.struts2;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 简单的自定义拦截器.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class MyInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		long s  = System.currentTimeMillis();  
		//添加action之前的监听器.
		invocation.addPreResultListener(new MyPreresultListiner());
		System.out.println("自定义拦截器MyInterceptor开始。");
		String result = invocation.invoke();
		long e = System.currentTimeMillis();
		System.out.println("自定义拦截器MyInterceptor开始结束："+invocation.getAction().getClass().getName()+"。共耗时："+(e-s));
		return result;
	}

}

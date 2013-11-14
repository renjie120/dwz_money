package common.struts2;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 方法拦截器.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class MyMethodInterceptor extends MethodFilterInterceptor{
	//可以配置这个拦截器的名称
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		long s  = System.currentTimeMillis();  
		System.out.println("方法拦截器"+name+"开始。");
		String result = invocation.invoke();
		long e = System.currentTimeMillis();
		System.out.println(name+"拦截器执行完毕。共耗时："+(e-s));
		return result;
	}
 

}

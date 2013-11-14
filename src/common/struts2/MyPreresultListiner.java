package common.struts2;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * 拦截器前的监听器。
 * 这里的类用来得到action的返回结果！
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class MyPreresultListiner implements PreResultListener{

	public void beforeResult(ActionInvocation invocation, String result) {
		ActionProxy action = invocation.getProxy(); 
		System.out.println("Action是："+action.getActionName()+",执行的方法为:"+action.getMethod()+",返回的逻辑视图是："+result);
	}

}

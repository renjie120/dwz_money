package common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 过滤器验证登陆.
 * @author renjie120 419723443@qq.com
 *
 */
public class SessionFilter implements Filter {
	private String failurePage;
	private String loginPage;
	private String limitUrlPatterStrs;
	private String loginAction;  
	private String nocheckurlstr;
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(); 
		String requestURI = request.getRequestURI();
		// 得到根目录
		String webPath = request.getContextPath();
		boolean doContinue = false;
		boolean doFilter = true;
		// 只要连接是以配置文件中配置的连接方式之一结果,就要进行第三步的过滤.否则不用.
		if (limitUrlPatterStrs != null) {
			limitUrlPatterStrs = limitUrlPatterStrs.trim();
			String[] urlTypes = limitUrlPatterStrs.split(";");
			// 解析simpleUrlPattern的类型集合进行判断
			for (int i = 0; i < urlTypes.length; i++) {
				String urlT = urlTypes[i].trim();
				if (urlT.length() < 1)
					continue;
				// 如果以*.开头，去掉*
				if (urlT.startsWith("*.")) {
					urlT = urlT.substring(1);
				}
				// 如果类型长度大于0，并且以该类型结尾，验证通过，跳出循环
				if (urlTypes[i].trim().length() > 0
						&& requestURI.endsWith(urlT)) {
					doContinue = true;
					break;
				}
			}
		}
		if (doContinue) {
			// 如果是根目录,登录界面,或者错误界面,不用进行过滤!
			if (CommonUtil.equalsIgnoreCase(requestURI, webPath + "/")
					|| CommonUtil.equalsIgnoreCase(requestURI, webPath + failurePage) 
					|| CommonUtil.equalsIgnoreCase(requestURI, webPath + loginPage) ) {
				doFilter = false;
			} 
			
			//只要是不进行session处理的列表就直接跳过session处理!为了在和手机程序进行互动中进行处理!!重要!!
			String[] nocheckurls = null;
			if (!CommonUtil.isEmpty(nocheckurlstr)) {
				nocheckurls = nocheckurlstr.split(";");
				for (int i = 0; i < nocheckurls.length; i++) {
					if (requestURI.indexOf(nocheckurls[i]) != -1) {
						doFilter = false;
						break;
					}
				}
			}
		}
		if (doContinue && doFilter) {
			if (CommonUtil.equalsIgnoreCase(requestURI, webPath + "/")) {
				chain.doFilter(request, response);
			}
			else if(session.getAttribute("pass")!= null){
				chain.doFilter(request, response);
			}
			else if(requestURI.indexOf(loginAction)!=-1){
				chain.doFilter(request, response);
			}
			else if(requestURI.indexOf(failurePage)!=-1){
				chain.doFilter(request, response);
			}
			// 如果session中没有用户名信息或者不是登录的action,就直接跳转到错误页面
			else {
				request.setAttribute("message", "session失效或者没有登录.");
				request.getSession().setAttribute("lastUrl", requestURI);
				int port = request.getServerPort();
				// session失效页面的跳转链接
				String targetUrl = request.getScheme() + "://"
						+ request.getServerName() + ":" + port
						+ request.getContextPath() + failurePage;
				// 跳转页面
				response.sendRedirect(targetUrl);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		failurePage = config.getInitParameter("failure");
		loginPage = config.getInitParameter("login"); 
		limitUrlPatterStrs = config.getInitParameter("limitUrlPattern");
		loginAction = config.getInitParameter("loginAction"); 
		nocheckurlstr = config.getInitParameter("nocheckurlstr");
	}

}

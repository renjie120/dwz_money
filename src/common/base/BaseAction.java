package common.base;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{ 
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(getClass()); 
	
	protected void writeLog(String str){
		log.info(str);
	}
	
	protected void error(String str){
		log.error(str);
	}
	
	/**
	 * 返回字符串传前台输出,适用于ajax的调用
	 * @param response
	 * @param str
	 */
	protected void writeToPage(HttpServletResponse response ,String str){
		try {
			response.setContentType("text/html;charset=GBK"); 
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}

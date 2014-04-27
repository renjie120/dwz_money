package dwz.present;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

import dwz.framework.config.Configuration;
import dwz.framework.constants.Constants;
import dwz.framework.context.AppContext;
import dwz.framework.context.AppContextHolder;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.el.ServerInfo;
import dwz.framework.user.User;
import dwz.framework.utils.StringUtils;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Log log = null;  
	protected static BusinessFactory bf;
	protected static Configuration appConfig;

	@Deprecated
	protected HttpServletRequest request = null;
	@Deprecated
	protected HttpServletResponse response = null;
	protected static final String OPERATION_DONE = "operationDone";
	
	private ActionProxy proxy;

	private int statusCode = 200;
	private String message = null;
	private String forwardUrl = null;

	private boolean skipVC = false;
	private String validationCode; // 楠岃瘉镰?

	// search form fields
	public final static int PAGE_SHOW_COUNT = 100;
	private int pageNum = 1;
	private int numPerPage = 0;
	private String orderField;
	private String orderDirection;
	private String keywords;
	private int totalCount = 0;
	protected void writeLog(String str){
		log.info(str);
	}
	
	protected void error(String str){
		log.error(str);
	}
	
	/**
	 * 杩斿洖瀛楃涓蹭紶鍓嶅彴杈揿嚭,阃傜敤浜巃jax镄勮皟鐢?
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

	static {
		bf = BusinessFactory.getFactory();
		appConfig = bf.retrieveConfiguration();
	}

	public ActionProxy getProxy() {
		return proxy;
	}
	
	public String getNamespace(){
		String namespace = proxy.getNamespace();
		if ("/".equals(namespace)) namespace = "";

		return namespace;
	}

	public BaseAction() {
		super();
		log = LogFactory.getLog(this.getClass());
		proxy = ActionContext.getContext().getActionInvocation().getProxy();
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	protected boolean verifyValidationCode(
			String validationCode) {

		boolean enableVerify = appConfig
				.getBoolean("validation-code.validation-code-enable");

		if (enableVerify) {
			if (validationCode == null)
				return false;

			String randomCode = null;
			try {
				randomCode = (String) ActionContext.getContext().getSession().get(
						Constants.VALIDATION_CODE);
				System.out.println(randomCode + " : " + validationCode);
			} catch (Exception e) {
				log.error(e);
			}

			if (randomCode == null)
				return false;
			else if (!randomCode.equalsIgnoreCase(validationCode.trim()))
				return false;
		}
		return true;
	}

	protected boolean uploadFile(File file, String filePath) {

		boolean retCode = false;
		byte[] buffer = new byte[1024];
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);

			fos = new FileOutputStream(new File(filePath));

			int n = -1;
			while ((n = is.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, n);
			}

			retCode = true; 
		} catch (FileNotFoundException fnfe) {
			System.out.println("fnfe:" + fnfe);
		} catch (IOException ioe) {
			System.out.println("ioe:" + ioe);
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					log.error(e);
				}
			}
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					log.error(e);
				}

			}
		}

		return retCode;
	}

	protected AppContext getAppContext() {
		return AppContextHolder.getContext();
	}

	public User getContextUser() {
		AppContext context = getAppContext();

		if (context != null)
			return context.getUser();
		return null;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	// protected void margeForwardUrl(Map<String, String> params) {
	// if (StringUtils.notEmpty(forwardUrl) && params != null) {
	// for (String key : params.keySet()) {
	// forwardUrl = forwardUrl.replace(key, params.get(key));
	// }
	// }
	// }
	//
	// protected void margeForwardUrl(String[] target, String[] replacement) {
	// if (StringUtils.notEmpty(forwardUrl) && target != null
	// && replacement != null && target.length == replacement.length) {
	// for (int i = 0; i < target.length; i++) {
	// forwardUrl = forwardUrl.replace(target[i], replacement[i]);
	// }
	// }
	// }

	protected void margeForwardUrl(String target, String replacement) {
		if (StringUtils.notEmpty(forwardUrl) && target != null
				&& replacement != null) {
			forwardUrl = forwardUrl.replace(target, replacement);
		}
	}

	public String getOperationDone() {
		if (ServerInfo.isAjax(request) || request.getParameter("ajax") != null)
			return "ajaxDone";
		else
			return "alert";
	}
	
	private String ajaxForward(int statusCode) {
		this.statusCode = statusCode;
		return OPERATION_DONE;
	}
	protected String ajaxForwardSuccess(String message) {
		this.message = message;
		return ajaxForward(200);
	}
	protected String ajaxForwardError(String message) {
		this.message = message;
		return ajaxForward(300);
	}

	public boolean isSkipVC() {
		return skipVC;
	}

	public void setSkipVC(boolean skipVC) {
		this.skipVC = skipVC;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}  

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNumPerPage() {
		return numPerPage > 0 ? numPerPage : PAGE_SHOW_COUNT;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String realOrderField(){
		if ("desc".equalsIgnoreCase(orderDirection))
			return orderField+"_DESC";
		return null;
	}
}
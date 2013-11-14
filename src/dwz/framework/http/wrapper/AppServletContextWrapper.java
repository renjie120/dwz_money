package dwz.framework.http.wrapper;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppServletContextWrapper implements ServletContext {

	private ServletContext context = null;

	public AppServletContextWrapper(ServletContext context) {
		this.context = context;
	}

	public Object getAttribute(String arg0) {
		return this.context.getAttribute(arg0);
	}

	public Enumeration getAttributeNames() {
		return this.context.getAttributeNames();
	}

	public ServletContext getContext(String arg0) {
		return this.context.getContext(arg0);
	}

	public String getInitParameter(String arg0) {
		return this.context.getInitParameter(arg0);
	}

	public Enumeration getInitParameterNames() {
		return this.context.getInitParameterNames();
	}

	public int getMajorVersion() {
		return this.context.getMajorVersion();
	}

	public String getMimeType(String arg0) {
		return this.context.getMimeType(arg0);
	}

	public int getMinorVersion() {
		return this.context.getMinorVersion();
	}

	public RequestDispatcher getNamedDispatcher(String arg0) {
		return this.context.getNamedDispatcher(arg0);
	}

	public String getRealPath(String arg0) {
		return this.context.getRealPath(arg0);
	}

	public RequestDispatcher getRequestDispatcher(String arg0) {
		return this.context.getRequestDispatcher(arg0);
	}

	public URL getResource(String arg0) throws MalformedURLException {
		return this.context.getResource(arg0);
	}

	public InputStream getResourceAsStream(String arg0) {
		return this.context.getResourceAsStream(arg0);
	}

	public Set getResourcePaths(String arg0) {
		return this.context.getResourcePaths(arg0);
	}

	public String getServerInfo() {
		return this.context.getServerInfo();
	}

	/**
	 * @deprecated
	 */
	public Servlet getServlet(String arg0) throws ServletException {
		return this.context.getServlet(arg0);
	}

	public String getServletContextName() {
		return this.context.getServletContextName();
	}

	/**
	 * @deprecated
	 */
	public Enumeration getServletNames() {
		return this.context.getServletNames();
	}

	/**
	 * @deprecated
	 */
	public Enumeration getServlets() {
		return this.context.getServlets();
	}

	public void log(String arg0) {
		this.context.log(arg0);
	}

	/**
	 * @deprecated
	 */
	public void log(Exception arg0, String arg1) {
		this.context.log(arg0, arg1);
	}

	public void log(String arg0, Throwable arg1) {
		this.context.log(arg0, arg1);
	}

	public void removeAttribute(String arg0) {
		this.context.removeAttribute(arg0);
	}

	public void setAttribute(String arg0, Object arg1) {
		this.context.setAttribute(arg0, arg1);
	}

	public String getContextPath() {
		//return this.context.getContextPath();
		return null;
	}

}

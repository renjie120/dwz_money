package dwz.framework.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerWriteTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3665682993311843318L;
	private String name;
	private String property;

	public int doStartTag() throws JspException {
		Object obj = pageContext.findAttribute(name);
		try {
			JspWriter out = pageContext.getOut();
			PropertyDescriptor label = new PropertyDescriptor(property, obj
					.getClass());
			out.print(label.getReadMethod().invoke(obj, new Object[] {})
					.toString()+"<br>");
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}

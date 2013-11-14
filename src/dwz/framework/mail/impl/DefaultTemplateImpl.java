package dwz.framework.mail.impl;

import dwz.framework.core.business.AbstractBusinessObject;
import dwz.framework.mail.Template;

public class DefaultTemplateImpl extends AbstractBusinessObject implements Template{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5406892904412519631L;

	private String name;
	private String subject;
	private String body;

	public DefaultTemplateImpl(String name, String subject, String body) {
		this.name = name;
		this.subject = subject;
		this.body = body;
	}

	public String getId() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}

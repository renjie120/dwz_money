package dwz.framework.mail.impl;

import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import dwz.framework.config.Configuration;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.mail.Mail;

public class MailImpl extends AbstractBusinessObject implements Mail {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6724779372853699727L;

	private Map<String, Object>	content				= new HashMap<String, Object>();

	private static final String	CHARSET				= "UTF-8";
	private boolean				isHtmlBody;
	private HtmlEmail			mail;

	protected MailImpl(boolean isHtmlBody) {
		Configuration appconfig = BusinessFactory.getFactory()
				.retrieveConfiguration();
		String host = appconfig.getString("app.mail.server.host");
		// String protocol = appconfig.getString("app.mail.server.protocol");
		String userName = appconfig.getString("app.default.username");
		String password = appconfig.getString("app.default.password");
		int port = appconfig.getInt("app.mail.server.port");
		boolean isSecurity = appconfig.getBoolean("app.mail.server.isSecurity");
		boolean isDebug = appconfig.getBoolean("app.mail.server.isDebug");

		this.isHtmlBody = isHtmlBody;

		mail = new HtmlEmail();
		mail.setCharset(CHARSET);
		mail.addHeader("X-Mailer", "WEB MAILER 1.0.1");
		mail.setDebug(isDebug);

		mail.setHostName(host);
		mail.setAuthentication(userName, password);
		mail.setSmtpPort(port);

		if (isSecurity)
			mail.setSSL(true);
	}

	HtmlEmail getMail() {
		return mail;
	}

	public void addAttache(URL url, String name, String desc)
			throws EmailException {

		mail.attach(url, name, desc);

	}

	public void addAttache(String path, String name, String desc)
			throws EmailException {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(path);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setName(name);
		attachment.setDescription(desc);

		mail.attach(attachment);
	}

	public void addBcc(String email) throws EmailException {

		mail.addBcc(email);

	}

	public void addBcc(String email, String name) throws EmailException {

		mail.addBcc(email, name);
	}

	public void addCc(String email) throws EmailException {
		mail.addCc(email);
	}

	public void addCc(String email, String name) throws EmailException {

		mail.addBcc(email, name);
	}

	public void addContent(String key, Object o) {
		content.put(key, o);
	}

	public Map<String, Object> getContentMap() {
		return content;
	}

	public void addReplyTo(String email) throws EmailException {

		mail.addReplyTo(email);
	}

	public void addReplyTo(String email, String name) throws EmailException {

		mail.addReplyTo(email, name);
	}

	public void addTo(String email) throws EmailException {
		mail.addTo(email);
	}

	public void addTo(String email, String name) throws EmailException {

		mail.addBcc(email, name);
	}

	public void clear() {
		content.clear();
	}

	public String getSubject() {
		return mail.getSubject();
	}

	public void setFrom(String email) throws EmailException {
		mail.setFrom(email);
	}

	public void setMsg(String msg) throws EmailException {
		if (isHtmlBody)
			mail.setHtmlMsg(msg);
		else
			mail.setTextMsg(msg);
	}

	public void setSubject(String subject) {
		mail.setSubject(subject);
	}

	public Serializable getId() {
		return null;
	}

}

package dwz.framework.mail;

import java.net.URL;

import org.apache.commons.mail.EmailException;

import dwz.framework.core.business.BusinessObject;

public interface Mail extends BusinessObject {
	// boolean isHtmlBody();
	// String getSubject();

	void setSubject(String subject);

	void setFrom(String email) throws EmailException;

	void addTo(String email) throws EmailException;

	void addTo(String email, String name) throws EmailException;

	void addReplyTo(String email) throws EmailException;

	void addReplyTo(String email, String name) throws EmailException;

	void addCc(String email) throws EmailException;

	void addCc(String email, String name) throws EmailException;

	void addBcc(String email) throws EmailException;

	void addBcc(String email, String name) throws EmailException;

	void addAttache(URL url, String name, String desc) throws EmailException;

	void addAttache(String path, String name, String desc)
			throws EmailException;

	void addContent(String key, Object o);

	void setMsg(String msg) throws EmailException;

}

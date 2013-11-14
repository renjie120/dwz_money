package dwz.framework.mail.impl;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import dwz.constants.MailTemplateKey;
import dwz.framework.config.Configuration;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.mail.Mail;
import dwz.framework.mail.MailManager;
import dwz.framework.mail.Template;
import dwz.framework.utils.FreeMarkerUtil;

public class MailManagerImpl extends AbstractBusinessObjectManager implements
		MailManager {

	public Mail newMail(boolean isHtmlBody) {

		return new MailImpl(isHtmlBody);
	}

	public void sendMail(Mail mail) throws EmailException {
		if (mail == null)
			return;
		MailImpl mailImpl = (MailImpl) mail;
		HtmlEmail htmlEmail = mailImpl.getMail();
		
		System.out.println("Send mail: " + htmlEmail.getSubject());
		htmlEmail.send();
		mailImpl.clear();
	}

	public void sendMail(Mail mail, Template template) throws EmailException {
		if (mail == null || template == null || template.getBody() == null)
			return;
		MailImpl mailImpl = (MailImpl) mail;
		
		String subject = FreeMarkerUtil.template2String(template.getSubject(),
				mailImpl.getContentMap(), true);
		String msg =  FreeMarkerUtil.template2String(template.getBody(),
				mailImpl.getContentMap(), true);
		mailImpl.setSubject(subject);
		mailImpl.setMsg(msg);
		
		System.out.println("Send mail: " + subject);
		mailImpl.getMail().send();
		mailImpl.clear();
	}

	public void sendMail(Mail mail, MailTemplateKey vmTemplate)
			throws EmailException {
		Template template = getTemplateByName(vmTemplate.toString());
		this.sendMail(mail, template);
	}

	// private class MailAuthenticator extends Authenticator {
	//
	// protected String m_user = null;
	//
	// protected String m_password = null;
	//
	// public MailAuthenticator(String user, String password) {
	// m_user = user;
	// m_password = password;
	// }
	//
	// protected PasswordAuthentication getPasswordAuthentication() {
	// if (m_user != null && m_password != null)
	// return new PasswordAuthentication(m_user, m_password);
	// else
	// return null;
	// }
	// }

	private Template getTemplateByName(String name) {
		Configuration config = BusinessFactory.getFactory()
				.retrieveConfiguration();
		return config.getVmTemplate(name);
	}

}

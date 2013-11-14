package dwz.framework.mail;

import org.apache.commons.mail.EmailException;

import dwz.constants.MailTemplateKey;
import dwz.framework.core.business.BusinessObjectManager;

public interface MailManager extends BusinessObjectManager {

	public void sendMail(Mail mail) throws EmailException;

	public void sendMail(Mail mail, Template template) throws EmailException;

	public void sendMail(Mail mail, MailTemplateKey vmTemplate)
			throws EmailException;

	public Mail newMail(boolean isHtmlBody);

}

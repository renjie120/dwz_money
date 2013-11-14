package dwz.business.website;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import dwz.business.constants.website.PageTarget;
import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public interface WebsiteManager extends BusinessObjectManager {

	public Page newPage(String name);

	@Transactional
	public String createPage(Page page) throws ValidateFieldsException;

	@Transactional
	public void updatePage(Page page);

	@Transactional
	public void deletePage(int id);

	public Page getPage(int id);

	public Collection<Page> getPages();
	public Collection<Page> getPages(PageTarget target);

	public Collection<Template> getTemplates();

	public Template getTemplateByName(String templateName);

	public Template getDefaultTemplate();

	public Website getWebsite();
	
	@Transactional
	public void saveWebsite(Website website)
			throws ValidateFieldsException;

	

}

package dwz.business.website.impl;

import java.util.ArrayList;
import java.util.Collection;

import dwz.business.constants.website.PageTarget;
import dwz.business.website.Page;
import dwz.business.website.Template;
import dwz.business.website.Website;
import dwz.business.website.WebsiteManager;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.persistence.beans.WebPage;
import dwz.persistence.beans.WebWebsite;
import dwz.persistence.daos.WebPageDao;
import dwz.persistence.daos.WebsiteDao;

public class WebsiteManagerImpl extends AbstractBusinessObjectManager implements
		WebsiteManager {
	private WebsiteDao	websiteDao;
	private WebPageDao	webPageDao;

	public WebsiteManagerImpl(WebsiteDao websiteDao, WebPageDao webPageDao) {
		this.websiteDao = websiteDao;
		this.webPageDao = webPageDao;
	}

	public String createPage(Page page) throws ValidateFieldsException {
		String ret = null;

		if (page == null || page.getTitle() == null) {
			log.debug("page is null.");
			throw new ValidateFieldsException("page is null.");
		}
		webPageDao.insert(((PageImpl) page).getPersistentObject());

		return ret;
	}

	public void deletePage(int id) {
		WebPage po = webPageDao.findByPrimaryKey(id);
		if (po != null) {
			webPageDao.delete(po);
		}
	}

	public Page getPage(int id) {

		WebPage po = webPageDao.findByPrimaryKey(id);
		if (po != null) {
			return new PageImpl(po);
		}

		return null;
	}

	public Collection<Page> getPages() {
		ArrayList<Page> bos = new ArrayList<Page>();
		Collection<WebPage> pos = webPageDao.findAllWebPage();

		if (pos != null && pos.size() > 0) {
			for (WebPage po : pos) {
				bos.add(new PageImpl(po));
			}
		}

		return bos;
	}

	public Collection<Page> getPages(PageTarget target) {
		ArrayList<Page> bos = new ArrayList<Page>();
		Collection<WebPage> pos = webPageDao.findWebPageByTarget("%"+target.toString()+"%");

		if (pos != null && pos.size() > 0) {
			for (WebPage po : pos) {
				bos.add(new PageImpl(po));
			}
		}

		return bos;
	}
	
	public void updatePage(Page page) {
		webPageDao.update(((PageImpl) page).getPersistentObject());
	}

	public Website getWebsite() {
		Collection<WebWebsite> pos = websiteDao.findAll();
		if (pos != null && pos.size() > 0) {
			return new WebsiteImpl(pos.iterator().next());
		}

		return new WebsiteImpl();
	}

	public void saveWebsite(Website bo) throws ValidateFieldsException {
		if (bo == null) {
			log.debug("Website is null.");
			throw new ValidateFieldsException("Website is null.");
		}

		if (websiteDao.countAll() < 1) {
			websiteDao.insert(((WebsiteImpl) bo).getWebWebsite());
		} else {
			websiteDao.update(((WebsiteImpl) bo).getWebWebsite());
		}
	}

	public Collection<Template> getTemplates() {
		return this.getAppConfig().getTemplates().values();
	}

	public Template getDefaultTemplate() {
		return this.getTemplateByName("template1");
	}

	public Template getTemplateByName(String templateName) {
		return this.getAppConfig().getTemplates().get(templateName);
	}

	public Page newPage(String name) {
		return new PageImpl(name);
	}

}

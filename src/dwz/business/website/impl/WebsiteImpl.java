package dwz.business.website.impl;

import java.io.Serializable;

import dwz.business.website.Layout;
import dwz.business.website.Template;
import dwz.business.website.Theme;
import dwz.business.website.Website;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.persistence.beans.WebWebsite;

public class WebsiteImpl extends AbstractBusinessObject implements Website {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6659352165997238894L;
	private WebWebsite			webWebsite;

	public WebsiteImpl() {
		webWebsite = new WebWebsite();
	}

	public WebsiteImpl(WebWebsite webWebsite) {
		this.webWebsite = webWebsite;
	}

	WebWebsite getWebWebsite() {
		return webWebsite;
	}

	public String getAreaBanner() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAreaFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAreaHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAreaSidebar() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIcp() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getImageUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	public Layout getLayout() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLayoutName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLogo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMetaDescription() {
		return webWebsite.getMetaDescription();
	}

	public String getMetaKeyword() {
		return webWebsite.getMetaKeywords();
	}

	public String getMetaTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Template getTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTemplateName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Theme getTheme() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getThemeName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getThemeUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		return webWebsite.getTitle();
	}

	public void setAreaBanner(String areaBanner) {
		// TODO Auto-generated method stub

	}

	public void setAreaFooter(String areaFooter) {
		// TODO Auto-generated method stub

	}

	public void setAreaHeader(String areaHeader) {
		// TODO Auto-generated method stub

	}

	public void setAreaSidebar(String areaSidebar) {
		// TODO Auto-generated method stub

	}

	public void setIcp(String icp) {
		// TODO Auto-generated method stub

	}

	public void setImageUrl(String imageUrl) {
		// TODO Auto-generated method stub

	}

	public void setLayoutName(String layoutName) {
		// TODO Auto-generated method stub

	}

	public void setLogo(String logo) {
		// TODO Auto-generated method stub

	}

	public void setMetaDescription(String metaDescription) {
		webWebsite.setMetaDescription(metaDescription);

	}

	public void setMetaKeyword(String metaKeyword) {
		webWebsite.setMetaKeywords(metaKeyword);

	}

	public void setMetaTitle(String metaTitle) {
		// TODO Auto-generated method stub

	}

	public void setTemplateName(String templateName) {
		// TODO Auto-generated method stub

	}

	public void setThemeName(String themeName) {
		// TODO Auto-generated method stub

	}

	public void setThemeUrl(String themeUrl) {
		// TODO Auto-generated method stub

	}

	public void setTitle(String title) {
		webWebsite.setTitle(title);

	}

	public Serializable getId() {
		return webWebsite.getId();
	}

}

package dwz.business.website;

import dwz.framework.core.business.BusinessObject;

public interface Website extends BusinessObject {

	String getTitle();

	void setTitle(String title);

	String getMetaTitle();

	void setMetaTitle(String metaTitle);

	String getMetaKeyword();

	void setMetaKeyword(String metaKeyword);

	String getMetaDescription();

	void setMetaDescription(String metaDescription);

	String getIcp();

	void setIcp(String icp);

	Template getTemplate();

	String getTemplateName();

	void setTemplateName(String templateName);

	Layout getLayout();

	String getLayoutName();

	void setLayoutName(String layoutName);

	Theme getTheme();

	String getThemeName();

	void setThemeName(String themeName);

	String getImageUrl();

	void setImageUrl(String imageUrl);

	String getThemeUrl();

	void setThemeUrl(String themeUrl);

	String getAreaHeader();

	void setAreaHeader(String areaHeader);

	String getAreaBanner();

	void setAreaBanner(String areaBanner);

	String getAreaFooter();

	void setAreaFooter(String areaFooter);

	String getAreaSidebar();

	void setAreaSidebar(String areaSidebar);

	String getLogo();

	void setLogo(String logo);

}

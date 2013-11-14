package dwz.business.website;

import java.util.Collection;

import dwz.framework.core.business.BusinessObject;

public interface Template extends BusinessObject {

	String getName();

	String getDefaultThumbnailPictureUrl();

	String getDefaultPictureUrl();

	String getPrePicBase();

	String getPrePicExt();

	String getLabel();

	String getDescription();

	Layout getDefaultLayout();

	Theme getDefaultTheme();
	
	Layout getLayoutByName(String layoutName);

	Theme getThemeByName(String themeName);
	
	void addLayout(String layoutName, String css, int index, String desc);
	
	void addTheme(String themeName, String css, String label, String desc);

	Collection<Layout> getLayouts();

	Collection<Theme> getThemes();


}

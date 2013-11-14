package dwz.business.website.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import dwz.framework.core.business.AbstractBusinessObject;
import dwz.business.website.Layout;
import dwz.business.website.LayoutKey;
import dwz.business.website.Template;
import dwz.business.website.Theme;

public class TemplateImpl extends AbstractBusinessObject implements Template {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6533573941461123531L;

	private String name;

	private String label;

	private String description;

	private String prePicBase;

	private String prePicExt;
	
	private TreeMap<String, LayoutKey> layoutKeys = null;
	private TreeMap<LayoutKey, Layout> layouts = null;

	private TreeMap<String, Theme> themes = null;

	public TemplateImpl(String name, String label, String description,
			String prePicBase, String prePicExt) {
		this.name = name;
		this.label = label;
		this.description = description;
		this.prePicBase = prePicBase;
		this.prePicExt = prePicExt;
		this.layoutKeys = new TreeMap<String, LayoutKey>();
		this.layouts = new TreeMap<LayoutKey, Layout>();
		this.themes = new TreeMap<String, Theme>();
	}

	public Collection<Layout> getLayouts() {
		// TODO Auto-generated method stub
		return this.layouts == null ? new ArrayList<Layout>(0) : this.layouts
				.values();
	}

	public Collection<Theme> getThemes() {
		// TODO Auto-generated method stub
		return this.themes == null ? new ArrayList<Theme>(0) : this.themes
				.values();
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return this.label;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Layout getDefaultLayout() {
		// TODO Auto-generated method stub
		return this.layouts.get(this.layouts.firstKey());
	}

	public String getDefaultPictureUrl() {
		String defaultPictureUrl;
		defaultPictureUrl = this.getPrePicBase() + "/"
				+ this.getDefaultLayout().getName() + "_"
				+ this.getDefaultTheme().getName() + "_lg."
				+ this.getPrePicExt();
		return defaultPictureUrl;
	}

	public Theme getDefaultTheme() {
		return this.getThemeByName("theme1");
	}

	public String getDefaultThumbnailPictureUrl() {
		String defaultThumbnailPictureUrl;
		defaultThumbnailPictureUrl = this.getPrePicBase() + "/"
				+ this.getDefaultLayout().getName() + "_"
				+ this.getDefaultTheme().getName() + "_sm."
				+ this.getPrePicExt();
		return defaultThumbnailPictureUrl;
	}

	public String getPrePicBase() {
		// TODO Auto-generated method stub
		return this.prePicBase;
	}

	public String getPrePicExt() {
		// TODO Auto-generated method stub
		return this.prePicExt;
	}

	public void addLayout(String layoutName, String css, int index, String desc) {
		Layout commonLayout = new LayoutImpl(layoutName, css, desc);
		LayoutKey layoutKey = new LayoutKeyImpl(layoutName, index);
		layouts.put(layoutKey, commonLayout);
		layoutKeys.put(layoutName, layoutKey);
	}

	public void addTheme(String themeName, String css, String label, String desc) {
		Theme commonTheme = new ThemeImpl(themeName, css, label, desc);
		themes.put(themeName, commonTheme);
	}

	public Layout getLayoutByName(String layoutName) {
		return this.layouts.get(layoutKeys.get(layoutName));
	}

	public Theme getThemeByName(String themeName) {
		return this.themes.get(themeName);
	}
}

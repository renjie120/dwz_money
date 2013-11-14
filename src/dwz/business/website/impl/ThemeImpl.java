package dwz.business.website.impl;

import java.io.Serializable;

import dwz.framework.core.business.AbstractBusinessObject;
import dwz.business.website.Theme;

public class ThemeImpl extends AbstractBusinessObject implements
		Theme {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1037153559510076369L;

	private String name;

	private String css;

	private String label;

	private String description;

	public ThemeImpl(String name, String css, String label,
			String description) {
		this.name = name;
		this.css = css;
		this.label = label;
		this.description = description;
	}
	public String getCss() {
		// TODO Auto-generated method stub
		return this.css;
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

}

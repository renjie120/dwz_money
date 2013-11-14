package dwz.business.website.impl;

import java.io.Serializable;

import dwz.framework.core.business.AbstractBusinessObject;
import dwz.business.website.Layout;

public class LayoutImpl extends AbstractBusinessObject implements Layout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7669164334289696964L;

	private String name;

	private String css;

	private String description;

	public LayoutImpl(String name, String css, String description) {
		this.name = name;
		this.css = css;
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

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public String getId() {
		return null;
	}

}

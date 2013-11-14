package dwz.framework.config.impl;

import dwz.framework.config.AppServiletConfiguration;

public class AppServiletConfigurationImpl implements AppServiletConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5420733287057585200L;

	private String appServiletName = null;

	private String implementClass = null;

	public AppServiletConfigurationImpl(String appServiletName,
			String implementClass) {
		this.appServiletName = appServiletName;
		this.implementClass = implementClass;
	}

	public String getAppServiletName() {
		return this.appServiletName;
	}

	public String getImplementClass() {
		return this.implementClass;
	}

}

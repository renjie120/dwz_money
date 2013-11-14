package dwz.framework.config.impl;

import java.util.HashSet;

import dwz.framework.config.ManagerConfiguration;

public class ManagerConfigurationImpl implements ManagerConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2112535464030910940L;

	private String managerName = null;

	private String managerLabel = null;

	private String managerDescription = null;

	private String implementClass = null;

	private HashSet<String> authMethod = null;

	private boolean pool = false;

	private boolean singleton = false;

	public ManagerConfigurationImpl(String managerName, String managerLabel,
			String managerDescription,
			String implementClass, boolean pool, boolean singleton,
			String[] methodList) {
		this.managerName = managerName;
		this.managerLabel = managerLabel;
		this.managerDescription = managerDescription;
		this.implementClass = implementClass;
		this.pool = pool;
		this.singleton = singleton;
		this.authMethod = new HashSet<String>();
		if (methodList != null) {
			for (String method : methodList) {
				this.authMethod.add(method.trim());
			}
		}
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getImplementClass() {
		return this.implementClass;
	}

	public boolean isPool() {
		return this.pool;
	}

	public boolean isSingleton() {
		return this.singleton;
	}

	public void setImplementClass(String implementClass) {
		this.implementClass = implementClass;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	public String getManagerDescription() {
		return this.managerDescription;
	}

	public String getManagerLabel() {
		return this.managerLabel;
	}

	public boolean containAuthMethod(String methodName) {
		return this.authMethod.contains(methodName);
	}

}

package dwz.framework.config.impl;

import dwz.framework.config.BeanConfiguration;

public class BeanConfigurationImpl implements BeanConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197501063950425133L;

	private String beanName = null;

	private String implementClass = null;

	private String manager = null;

	private boolean pool = false;

	private boolean singleton = false;

	public BeanConfigurationImpl(String beanName, String implementClass,
			String manager, boolean pool, boolean singleton) {
		this.beanName = beanName;
		this.implementClass = implementClass;
		this.manager = manager;
		this.pool = pool;
		this.singleton = singleton;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
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

	public String getBeanName() {
		return this.beanName;
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

	public String getManager() {
		return this.manager;
	}

}

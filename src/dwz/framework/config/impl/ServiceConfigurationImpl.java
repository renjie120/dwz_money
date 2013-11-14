package dwz.framework.config.impl;

import java.util.ArrayList;
import java.util.List;

import dwz.framework.config.ServiceConfiguration;

public class ServiceConfigurationImpl implements ServiceConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4267665059492319923L;

	private String name = null;

	private String label = null;

	private String description = null;

	private List<String> managers = null;

	public ServiceConfigurationImpl(String name, String label,
			String description) {
		this.name = name;
		this.label = label;
		this.description = description;
		this.managers = new ArrayList<String>();
	}

	public void addManager(String name) {
		this.managers.add(name);
	}

	public String getDescription() {
		return this.description;
	}

	public String getLabel() {
		return this.label;
	}

	public String getName() {
		return this.name;
	}

	public List<String> listManagerNames() {
		return this.managers;
	}

}

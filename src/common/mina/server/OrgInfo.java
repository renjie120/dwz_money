package common.mina.server;

import java.io.Serializable;

public class OrgInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	public OrgInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

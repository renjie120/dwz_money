package dwz.framework.core.identity.impl;

import java.io.Serializable;

import dwz.framework.core.identity.Identity;

public class SessionIdentity implements Identity {

	private Serializable accessToken = null;

	public SessionIdentity(Serializable accessToken) {
		this.accessToken = accessToken;
	}

	public Serializable getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(Serializable accessToken) {
		this.accessToken = accessToken;
	}

}

package dwz.framework.context;

import dwz.framework.user.User;

public class DefaultAppContext implements AppContext {

	private User user = null;

//	private Website website = null;

	public DefaultAppContext() {
	}

	public DefaultAppContext(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

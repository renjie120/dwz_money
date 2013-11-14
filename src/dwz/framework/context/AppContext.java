package dwz.framework.context;

import dwz.framework.user.User;

public interface AppContext {

	public User getUser();

	public void setUser(User user);

//	public Website getWebsite();
//	public void setWebsite(Website website);

}

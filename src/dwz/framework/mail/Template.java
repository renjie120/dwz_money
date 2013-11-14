package dwz.framework.mail;

import dwz.framework.core.business.BusinessObject;

public interface Template extends BusinessObject {
	
	public String getName();
	
	public String getBody();
	
	public String getSubject();
	
//	public void setName(String name);
//	public void setBody(String body);
//	public void setSubject(String subject);
	
	public String getId();

}

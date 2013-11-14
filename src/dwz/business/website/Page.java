package dwz.business.website;

import java.util.Collection;

import dwz.business.constants.website.PageTarget;
import dwz.framework.core.business.BusinessObject;

public interface Page extends BusinessObject {

	Integer getId();

	String getName();

	void setName(String name);

	String getTitle();

	void setTitle(String title);
 
	String getContent();

	void setContent(String content);

	String getMetaKeyword();

	void setMetaKeyword(String keywords);

	String getMetaDescription();

	void setMetaDescription(String description);
	
	Collection<String> getTarget();
	
	String  getTargetStr();

	void setTarget(String [] target);
	
	boolean isOnTarget(PageTarget target);
	
	int getSequence();
	
	void setSequence(int sequence);
}
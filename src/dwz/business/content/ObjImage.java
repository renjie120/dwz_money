package dwz.business.content;

import dwz.framework.core.business.BusinessObject;

public interface ObjImage extends BusinessObject {
	
	public String getMainImageUrl();
	
	public String getThumImageUrl();

	public String getUrl();
}

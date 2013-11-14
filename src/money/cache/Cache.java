package money.cache;

import dwz.framework.core.business.BusinessObject;

public interface Cache extends BusinessObject {

	public Integer getId();

	public int getSno();

	public String getCacheId();

	public String getCacheName();

}

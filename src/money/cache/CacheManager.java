
package money.cache;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface CacheManager extends BusinessObjectManager {  

	public Collection<Cache> searchCache(Map<CacheSearchFields, Object> criterias,
			String orderField, int startIndex, int count); 
	
	public Integer searchCacheNum(Map<CacheSearchFields, Object> criterias); 

	@Transactional
	public void createCache(Cache cache)
			throws ValidateFieldsException;

	@Transactional
	public void updateCache(Cache cache) throws ValidateFieldsException;
	
	@Transactional
	public void deleteAllCache();

	@Transactional
	public void removeCache(String cacheId);
	
	public Cache getCache(Integer id);
}


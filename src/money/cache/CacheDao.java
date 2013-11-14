package money.cache;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface CacheDao extends BaseDao<CacheVO, Integer> {

	public Collection<CacheVO> findRecordById(int sno);
	
	public Collection<CacheVO> findRecordByCacheId(String cacheId);

	public Collection<CacheVO> findAll();

	public void deleteAllById(String id);
	
	public void deleteAll(); 

	public void updateAllaccessCacheVO(CacheVO vo, String id);
}

package money.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class CacheManagerImpl extends AbstractBusinessObjectManager implements
		CacheManager {

	private CacheDao cacheDao = null;

	public CacheManagerImpl(CacheDao cacheDao) {
		this.cacheDao = cacheDao;
	}

	public Integer searchCacheNum(Map<CacheSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.cacheDao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<Cache> searchCache(
			Map<CacheSearchFields, Object> criterias, String orderField,
			int startIndex, int count) {
		ArrayList<Cache> eaList = new ArrayList<Cache>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<CacheVO> voList = this.cacheDao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (CacheVO po : voList) {
			eaList.add(new CacheImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<CacheSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct cache) "
						: "select distinct cache ").append(
				"from CacheVO as cache ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<CacheSearchFields, Object> entry : criterias
					.entrySet()) {
				CacheSearchFields fd = entry.getKey();
				switch (fd) {
				case SNO:
					sb.append(count == 0 ? " where" : " and").append(
							" cache.sno =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case CACHEID:
					sb.append(count == 0 ? " where" : " and").append(
							" cache.cacheId like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case CACHENAME:
					sb.append(count == 0 ? " where" : " and").append(
							" cache.cacheName like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			} 
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createCache(Cache cache) throws ValidateFieldsException {
		CacheImpl cacheImpl = (CacheImpl) cache;
		Collection<CacheVO> collection = this.cacheDao.findRecordByCacheId(cacheImpl.getCacheId());
		//检查唯一性
		if(collection==null||collection.size()<1)
			this.cacheDao.insert(cacheImpl.getCacheVO());
	}

	public void removeCache(String snos) {
		String[] idArr = snos.split(","); 
		for (String s : idArr) {
			Collection<CacheVO> list = this.cacheDao.findRecordByCacheId(s);
			for(CacheVO vo :list){
				this.cacheDao.delete(vo);
				//从缓存中删除指定的缓存！
				common.cache.CacheManager.clearOnly(vo.getCacheId());
			}
		}
	}

	public void updateCache(Cache cache) throws ValidateFieldsException {
		CacheImpl cacheImpl = (CacheImpl) cache;

		CacheVO po = cacheImpl.getCacheVO();
		this.cacheDao.update(po);
	}

	public Cache getCache(Integer id) {
		Collection<CacheVO> caches = this.cacheDao.findRecordById(id);

		if (caches == null || caches.size() < 1)
			return null;

		CacheVO cache = caches.toArray(new CacheVO[caches.size()])[0];

		return new CacheImpl(cache);
	}

	public void deleteAllCache() {
		this.cacheDao.deleteAll();
	}

}

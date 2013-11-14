package money.cache;

import dwz.framework.core.business.BusinessObject;

public class CacheImpl implements Cache {
	private CacheVO cacheVO = null;
	private static final long serialVersionUID = 1L;

	public CacheImpl(CacheVO cacheVO) {
		this.cacheVO = cacheVO;
	}

	public CacheImpl(String cacheId, String cacheName) {
		this.cacheVO = new CacheVO(cacheId, cacheName);
	}

	public CacheImpl(int sno, String cacheId, String cacheName) {
		this.cacheVO = new CacheVO(sno, cacheId, cacheName);
	}

	public CacheVO getCacheVO() {
		return this.cacheVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	public Integer getId() {
		return this.cacheVO.getSno();
	}

	public int getSno() {
		return this.cacheVO.getSno();
	}

	public String getCacheId() {
		return this.cacheVO.getCacheId();
	}

	public String getCacheName() {
		return this.cacheVO.getCacheName();
	}

}

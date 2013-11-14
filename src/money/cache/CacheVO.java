package money.cache;

import java.io.Serializable;

public class CacheVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public CacheVO() {

	}

	public CacheVO(int sno, String cacheId, String cacheName) {
		this.sno = sno;
		this.cacheId = cacheId;
		this.cacheName = cacheName;

	}

	public CacheVO(String cacheId, String cacheName) {
		this.cacheId = cacheId;
		this.cacheName = cacheName;

	}

	private int sno;

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getSno() {
		return sno;
	}

	private String cacheId;

	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}

	public String getCacheId() {
		return cacheId;
	}

	private String cacheName;

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getCacheName() {
		return cacheName;
	}

}

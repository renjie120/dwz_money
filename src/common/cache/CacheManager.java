package common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import money.cache.CacheImpl;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.core.factory.BusinessFactory;

/**
 * 缓存管理器.
 * @author wblishq
 *
 */
public class CacheManager { 
	private static HashMap cacheMap = new HashMap();

	// 单实例构造方法
	private CacheManager() {
		super();
	}

	/**
	 * 获取布尔值的缓存
	 * @param key
	 * @return
	 */
	public static boolean getSimpleFlag(String key) {
		try {
			return (Boolean) cacheMap.get(key);
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * 取得服务器上键值的
	 * @param key
	 * @return
	 */
	public static long getServerStartdt(String key) {
		try {
			return (Long) cacheMap.get(key);
		} catch (Exception ex) {
			return 0;
		}
	}

	// 设置布尔值的缓存
	public synchronized static boolean setSimpleFlag(String key, boolean flag) {
		if (flag && getSimpleFlag(key)) {// 假如为真不允许被覆盖
			return false;
		} else {
			cacheMap.put(key, flag);
			return true;
		}
	}

	public synchronized static boolean setSimpleFlag(String key,
			long serverbegrundt) {
		if (cacheMap.get(key) == null) {
			cacheMap.put(key, serverbegrundt);
			return true;
		} else {
			return false;
		}
	}

	// 得到缓存。同步静态方法
	private synchronized static Cache getCache(String key) {
		return (Cache) cacheMap.get(key);
	}

	// 判断是否存在一个缓存
	private synchronized static boolean hasCache(String key) {
		return cacheMap.containsKey(key);
	}

	// 清除所有缓存
	public synchronized static void clearAll() {
		cacheMap.clear();
	}

	// 清除某一类特定缓存,通过遍历HASHMAP下的所有对象，来判断它的KEY与传入的TYPE是否匹配
	public synchronized static void clearAll(String type) {
		Iterator i = cacheMap.entrySet().iterator();
		String key;
		ArrayList<String> arr = new ArrayList();
		try {
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				key = (String) entry.getKey();
				if (key.startsWith(type)) { // 如果匹配则删除掉
					arr.add(key);
				}
			}
			for (int k = 0; k < arr.size(); k++) {
				clearOnly(arr.get(k));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 清除指定的缓存
	public synchronized static void clearOnly(String key) {
		BusinessFactory bf = BusinessFactory.getFactory();
		//删除数据库中的数据
		money.cache.CacheManager pMgr = bf.getManager(BeanManagerKey.cacheManager);
		pMgr.removeCache(key);
		cacheMap.remove(key);
	}

	// 载入缓存
	public synchronized static void putCache(String key, Cache obj) {
		BusinessFactory bf = BusinessFactory.getFactory();
		//添加到数据库中的数据
		money.cache.CacheManager pMgr = bf.getManager(BeanManagerKey.cacheManager);
		CacheImpl cacheImpl = new CacheImpl(obj.getKey(), obj.getName());
		try {
			pMgr.createCache(cacheImpl);
		} catch (ValidateFieldsException e) { 
			e.printStackTrace();
		}
		cacheMap.put(key, obj);
	}

	/**
	 * 获取缓存信息.
	 * @param key
	 * @return
	 */
	public static Cache getCacheInfo(String key) {
		if (hasCache(key)) {
			Cache cache = getCache(key);
			if (cacheExpired(cache)) { // 调用判断是否终止方法
				cache.setExpired(true);
			}
			return cache;
		} else
			return null;
	}

	/**
	 * 载入缓存信息.
	 * @param key
	 * @param obj
	 * @param dt
	 * @param expired
	 */
	public static void putCacheInfo(String key, Cache obj, long dt,
			boolean expired) {
		Cache cache = new Cache();
		cache.setKey(key);
		cache.setTimeOut(dt + System.currentTimeMillis()); // 设置多久后更新缓存
		cache.setValue(obj);
		cache.setExpired(expired); // 缓存默认载入时，终止状态为FALSE
		cacheMap.put(key, cache);
	}

	/**
	 * 重写载入缓存信息方法.
	 * @param key
	 * @param obj
	 * @param dt
	 */
	public static void putCacheInfo(String key, Cache obj, long dt) {
		Cache cache = new Cache();
		cache.setKey(key);
		cache.setTimeOut(dt + System.currentTimeMillis());
		cache.setValue(obj);
		cache.setExpired(false);
		cacheMap.put(key, cache);
	}
	
	/**	
	 * 判断缓存是否终止.
	 * @param cache
	 * @return
	 */
	public static boolean cacheExpired(Cache cache) {
		if (null == cache) { // 传入的缓存不存在
			return false;
		}
		long nowDt = System.currentTimeMillis(); // 系统当前的毫秒数
		long cacheDt = cache.getTimeOut(); // 缓存内的过期毫秒数
		if (cacheDt <= 0 || cacheDt > nowDt) { // 过期时间小于等于零时,或者过期时间大于当前时间时，则为FALSE
			return false;
		} else { // 大于过期时间 即过期
			return true;
		}
	}

	/**
	 * 获取缓存中的大小.
	 * @return
	 */
	public static int getCacheSize() {
		return cacheMap.size();
	}

	/**
	 * 获取指定的类型的大小
	 * @param type
	 * @return
	 */
	public static int getCacheSize(String type) {
		int k = 0;
		Iterator i = cacheMap.entrySet().iterator();
		String key;
		try {
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				key = (String) entry.getKey();
				if (key.indexOf(type) != -1) { // 如果匹配则删除掉
					k++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return k;
	}

	/**
	 * 获取缓存对象中的所有键值名称.
	 * @return
	 */
	public static ArrayList getCacheAllkey() {
		ArrayList a = new ArrayList();
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				a.add((String) entry.getKey());
			}
		} catch (Exception ex) {
		} finally {
			return a;
		}
	}
	
	/**
	 * 返回指定行数之间的缓存.
	 * @return
	 */
	public static ArrayList getCacheAllkey(int startIndex,int endIndex) {
		ArrayList a = new ArrayList();
		try {
			Iterator i = cacheMap.entrySet().iterator();
			int index=0;
			while (i.hasNext()&&index<endIndex) { 
				if(index>=startIndex){
					java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
					a.add((String) entry.getKey());
				}
				index++;
			}
		} catch (Exception ex) {
		} finally {
			return a;
		}
	}

	/**
	 * 获取缓存对象中指定类型的键值名称.
	 * @param type
	 * @return
	 */
	public static ArrayList getCacheListkey(String type) {
		ArrayList a = new ArrayList();
		String key;
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				key = (String) entry.getKey();
				if (key.indexOf(type) != -1) {
					a.add(key);
				}
			}
		} catch (Exception ex) {
		} finally {
			return a;
		}
	}
	
	public static void main(String[] a){
		List ans = null;
		if(CacheManager.getCacheInfo("unit")==null){
			//ans = getListColl("SYS_UNIT_T", "UNIT_ID",	"UNIT_NAME");
			Cache c = new Cache();
			c.setKey("unit");
			c.setValue(ans);
			CacheManager.putCache("unit",c);
		}
		else
			ans = (List)CacheManager.getCacheInfo("unit").getValue();
	}
}
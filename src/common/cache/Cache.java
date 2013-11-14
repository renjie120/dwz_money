package common.cache;

/**
 * 缓存管理对象
 * @author renjie120 419723443@qq.com
 *
 */
public class Cache {
	private String key;// 缓存ID
	private String name;//缓存名称
	private Object value;// 缓存数据
	private long timeOut;// 更新时间
	private boolean expired; // 是否终止

	public Cache() {
		super();
	}

	public Cache(String key, Object value, long timeOut, boolean expired,String name) {
		this.key = key;
		this.value = value;
		this.timeOut = timeOut;
		this.expired = expired;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public long getTimeOut() {
		return timeOut;
	}

	public Object getValue() {
		return value;
	}

	public void setKey(String string) {
		key = string;
	}

	public void setTimeOut(long l) {
		timeOut = l;
	}

	public void setValue(Object object) {
		value = object;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean b) {
		expired = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
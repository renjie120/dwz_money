package dwz.framework.config;

import java.io.Serializable;

public interface BeanConfiguration extends Serializable {

	public String getBeanName();

	public String getImplementClass();

	public String getManager();

	public boolean isSingleton();

	public boolean isPool();

}

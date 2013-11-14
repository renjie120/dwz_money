package dwz.framework.config;

import java.io.Serializable;

public interface ManagerConfiguration extends Serializable {

	public String getManagerName();

	public String getManagerLabel();

	public String getManagerDescription();

	public String getImplementClass();

	public boolean isSingleton();

	public boolean isPool();

	public boolean containAuthMethod(String methodName);

}

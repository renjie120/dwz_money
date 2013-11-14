package dwz.framework.config;

import java.io.Serializable;
import java.util.List;

public interface ServiceConfiguration extends Serializable {

	public String getName();

	public String getLabel();

	public String getDescription();

	public List<String> listManagerNames();

	public void addManager(String name);

}

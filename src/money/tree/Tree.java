package money.tree;

import dwz.framework.core.business.BusinessObject;

public interface Tree extends BusinessObject {

	public Integer getId();

	public String getTreeId();

	public String getTreeName();

	public String getParentId();

	public String getLabel();

}

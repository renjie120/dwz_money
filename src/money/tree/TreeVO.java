package money.tree;

import dwz.framework.core.business.BusinessObject;

public class TreeVO implements Tree {
	private String treeId;
	private String treeName;
	private String parentId;
	private String label;

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMoenyTypeSno() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMoneyType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMoneyTypeDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getOrderId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getParentCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTypeCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public void copyProperties(BusinessObject orig) {
		// TODO Auto-generated method stub
		
	}

}

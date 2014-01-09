package common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础的树节点. 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class TreeNode implements ITree {
	private String id;
	private String name;
	private String parent;
	private String url;
	private String other;
	public String target;
	public boolean isParent;
	public int level; 
	public String relId;
	public boolean isChecked;
	public String open; 
	private List<TreeNode> child;

	public TreeNode(String id) {
		this.id = id;
		this.name = id;
	}

	public TreeNode() {
	}

	public TreeNode(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public TreeNode(String id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public List<TreeNode> getChild() {
		return child;
	}

	public void addChild(TreeNode node) {
		if (child == null) {
			child = new ArrayList<TreeNode>();
		}
		node.setParent(id);
		node.level = this.level+1;
		child.add(node);
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String toString() {
		return "id:" + this.getId() + ";name:" + this.getName() + ";level:" + this.level + ";parent:"
				+ this.getParent() + ";\n";
	}
}

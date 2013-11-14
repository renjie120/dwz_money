package common.taglib;

/**
 * 树节点.
 * 
 * @author lsq
 * 
 */
public class TreeNode {
	public TreeNode() {
	}

	public TreeNode(String id, String name, String parent) {
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.print = 0;
	}

	public TreeNode(String id, String name, String desc, String parent) {
		this.id = id;
		this.name = name;
		this.des = desc;
		this.parent = parent;
		this.print = 0;
	}

	private String id;
	private String des;
	private String name;
	private String parent;
	private String left;
	private String right;
	private int print;
	private String level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getPrint() {
		return print;
	}

	public void setPrint(int print) {
		this.print = print;
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	public String toString(){
		return "id:"+this.id+";name:"+this.name+";parentId:"+this.parent+";left:"+this.left+";right:"+this.right;
	}
}

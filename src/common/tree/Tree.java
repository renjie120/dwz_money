package common.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 树节点. 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class Tree implements ITree {
	private String treeId;
	private TreeNode root;

	public Tree(String treeId) {
		this.treeId = treeId;
		root = new TreeNode(treeId);
	}
	
	public Tree(String treeId,String treeName) {
		this.treeId = treeId; 
		root = new TreeNode(treeId,treeName);
	}

	public void addChild(TreeNode node) {
		root.addChild(node);
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * 返回全部的树节点id.
	 * @return
	 */
	public List getAllIds(){
		List allIds = new ArrayList();
		LinkedList<TreeNode> allP = new LinkedList(); 
		allP.add(root);
		do {
			TreeNode nd = allP.poll();
			if (nd.getChild() != null) {
				List<TreeNode> l = nd.getChild();
				Iterator<TreeNode> it = l.iterator();
				while (it.hasNext()) {
					TreeNode child = it.next();
					// 如果有孩子就添加到队列里面等待下次进行处理.
					if (child.getChild() != null)
						allP.add(child);
					allIds.add(child.getId());
				}
			}
		} while (allP.size() > 0);
		return allIds;
	}
	
	/**
	 * 对树节点进行遍历. 
	 * @param c  遍历算法.
	 */
	public void travelTree(ITreeNodeTravel c) {
		LinkedList<TreeNode> allP = new LinkedList();
		c.travel(root);
		allP.add(root);
		do {
			TreeNode nd = allP.poll();
			if (nd.getChild() != null) {
				List<TreeNode> l = nd.getChild();
				Iterator<TreeNode> it = l.iterator();
				while (it.hasNext()) {
					TreeNode child = it.next();
					// 如果有孩子就添加到队列里面等待下次进行处理.
					if (child.getChild() != null)
						allP.add(child);
					c.travel(child);
				}
			}
		} while (allP.size() > 0);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(root);
		LinkedList<TreeNode> allP = new LinkedList();
		allP.add(root);
		do {
			TreeNode nd = allP.poll();
			if (nd.getChild() != null) {
				List<TreeNode> l = nd.getChild();
				Iterator<TreeNode> it = l.iterator();
				while (it.hasNext()) {
					TreeNode child = it.next();
					// 如果有孩子就添加到队列里面等待下次进行处理.
					if (child.getChild() != null)
						allP.add(child);
					buf.append(child);
				}
			}
		} while (allP.size() > 0);
		return buf.toString();
	}

	/**
	 * 返回zTree树需要的json串形式.
	 * @return
	 */
	public String toZTreeJson(){
		final StringBuffer buf = new StringBuffer();
		StringBuffer ans = new StringBuffer("[");
	    travelTree(new ITreeNodeTravel() {
			public void travel(TreeNode node) {
				buf.append("{'id':'" + node.getId() + "','pId':'"+ node.getParent() + "','name':'" + node.getName() + "'},");
			}
		});
	    ans.append(buf.deleteCharAt(buf.lastIndexOf(",")));
	    ans.append("]");
	    return ans.toString();
	}
	public static void main(String[] args) {
		Tree tree = new Tree("root");
		TreeNode node1 = new TreeNode("1", "中国");
		TreeNode node2 = new TreeNode("2", "湖北");
		TreeNode node3 = new TreeNode("3", "湖南");
		TreeNode node4 = new TreeNode("4", "四川");
		TreeNode node5 = new TreeNode("5", "北京");
		TreeNode node6 = new TreeNode("6", "上海");
		TreeNode node21 = new TreeNode("2.1", "石首"); 
		node1.addChild(node2);
		node1.addChild(node3);
		node1.addChild(node4);
		node1.addChild(node5);
		node1.addChild(node6);
		node2.addChild(node21);
		tree.addChild(node1);
//		System.out.println(tree.getRoot());
//		System.out.println(tree.getAllIds());
//		System.out.println(tree.getRoot().getChild().size());

		final StringBuffer buf = new StringBuffer();
		tree.travelTree(new ITreeNodeTravel() {
			public void travel(TreeNode node) {
				buf.append(node.getId() + ",,," + node.getName()+"\n");
			}
		});
		System.out.println(tree.toZTreeJson());
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public TreeNode getRoot() {
		return root;
	}
}

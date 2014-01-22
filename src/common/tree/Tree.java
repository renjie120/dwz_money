package common.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import common.util.CommonUtil;

/**
 * 树节点.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class Tree implements ITree {
	private String treeId;
	private TreeNode root;

	public Tree(String treeId) {
		this.treeId = treeId;
		root = new TreeNode(treeId);
		root.level = 0;
	}

	public Tree(String treeId, String treeName) {
		this.treeId = treeId;
		root = new TreeNode(treeId, treeName);
		root.level = 0;
	}

	public void addChild(TreeNode node) {
		node.level = this.root.level + 1;
		root.addChild(node);
	}

	public void setRoot(TreeNode root) {
		this.root = root;
		this.root.level = 0;
	}

	/**
	 * 返回全部的树节点id.
	 * 
	 * @return
	 */
	public List getAllIds() {
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
	 * 
	 * @param c
	 *            遍历算法.
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
	 * 
	 * @return
	 */
	public String toZTreeJson() {
		return toZTreeJson(false);
	}

	public String toZTreeJson(boolean schecked) {
		final StringBuffer buf = new StringBuffer();
		StringBuffer ans = new StringBuffer("[");
		final boolean checked = schecked;
		travelTree(new ITreeNodeTravel() {
			public void travel(TreeNode node) {
				buf.append("{'id':'");
				buf.append(node.getId());
				buf.append("',level:");
				buf.append(node.level);
				if ("true".equals(node.open))
					buf.append(",open:'true'");
				if (checked && node.isChecked)
					buf.append(",checked:true");
				buf.append(",'pId':'");
				buf.append(node.getParent());
				buf.append("','name':'");
				buf.append(node.getName());
				buf.append("'},");
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
		TreeNode node4 = new TreeNode("4", "长沙");
		// TreeNode node5 = new TreeNode("5", "北京");
		// TreeNode node6 = new TreeNode("6", "上海");
		TreeNode node21 = new TreeNode("2.1", "石首");
		TreeNode node22 = new TreeNode("2.2", "监利");

		TreeNode node_1 = new TreeNode("A1", "美国");
		TreeNode node_1_1 = new TreeNode("A2", "加州");
		TreeNode node_1_1_1 = new TreeNode("A3", "某市");
		TreeNode node_1_1_2 = new TreeNode("A4", "某市2");
		TreeNode node_1_2 = new TreeNode("A22", "加州1");
		TreeNode node_1_2_1 = new TreeNode("A32", "某市1");
		TreeNode node_1_2_2 = new TreeNode("A42", "某市22");
		tree.addChild(node1);
		tree.addChild(node_1);
		node_1.addChild(node_1_1);
		node_1_1.addChild(node_1_1_1);
		node_1_1.addChild(node_1_1_2);

		node_1.addChild(node_1_2);
		node_1_2.addChild(node_1_2_1);
		node_1_2.addChild(node_1_2_2);
		node1.addChild(node2);
		node2.addChild(node21);
		node2.addChild(node22);
		node1.addChild(node3);
		node3.addChild(node4);
		// node1.addChild(node4);
		// node1.addChild(node5);
		// node1.addChild(node6);

		// System.out.println(tree.getRoot());
		// System.out.println(tree.getAllIds());
		// System.out.println(tree.getRoot().getChild().size());

		// System.out.println(tree.toZTreeJson());

		System.out.println(tree.getDeepTree());
	}

	private String getNd(TreeNode node) {
		if (CommonUtil.isNotEmpty(node.getUrl()))
			return "<a href='"
					+ node.getUrl()
					+ "' target='"
					+ (node.target == null || "null".equals(node.target) ? "navTab"
							: node.target) + "' rel='" + node.relId + "'>"
					+ node.getName() + "</a> ";
		else
			return "<a >" + node.getName() + "</a> ";
	}

	public String getNodeStr(TreeNode node, int lastLevel) {
		if (node.level == 1) {
			int _temp = lastLevel - node.level;
			String ans = "";
			while (_temp-- > 0) {
				ans += "</li> </ul>";
			}
			if (lastLevel != 0)
				ans += "</div>";
			ans += "<div class='accordionHeader'> <h2> <span>Folder</span>"
					+ node.getName() + " </h2> </div> ";
			return ans;
		} else {
			if (lastLevel < node.level) {
				if (node.level == 2) {
					return "<div class='accordionContent'> <ul class='tree treeFolder expand'> <li>"
							+ getNd(node);
				} else {
					return " <ul> <li>" + getNd(node);
				}
			} else if (lastLevel > node.level) {
				int _temp = lastLevel - node.level;
				String ans = "";
				while (_temp-- > 0) {
					ans += "</li> </ul>";
				}
				return ans + "</li> <li>" + getNd(node);
			} else {
				return "</li> <li>" + getNd(node);
			}
		}
	}

	/**
	 * 返回树形的dwz 菜单形式.
	 * 
	 * @return
	 */
	public String getDeepTree() {
		StringBuffer ans = new StringBuffer();
		LinkedList<TreeNode> allNodes = new LinkedList<TreeNode>();
		if (root.getChild() != null)
			allNodes.addAll(root.getChild());
		int lastLevel = 0;
		while (!allNodes.isEmpty()) {
			TreeNode node = allNodes.removeFirst();
			ans.append(getNodeStr(node, lastLevel));
			lastLevel = node.level;
			// 添加全部的孩子节点到堆栈中.
			if (node.getChild() != null && node.getChild().size() > 0) {
				int size = node.getChild().size();
				for (int i = size - 1; i >= 0; i--) {
					allNodes.addFirst(node.getChild().get(i));
				}
			}
		}
		while (lastLevel-- > 1) {
			ans.append("</li> </ul>");
		}
		ans.append("</div>");
		return ans.toString();
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

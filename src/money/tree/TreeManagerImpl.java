package money.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import money.moneytype.MoneyTypeDao;
import money.moneytype.MoneyTypeVO;
import money.rolemanager.RoleWithMenuDao;
import money.rolemanager.RoleWithMenuVO;

import org.apache.commons.collections.map.ListOrderedMap;

import common.MyJdbcTool;
import common.base.TreeTool;
import common.cache.Cache;
import common.cache.CacheManager;
import common.tree.ITreeNodeTravel;
import common.tree.Tree;
import common.tree.TreeNode;

import dwz.framework.core.business.AbstractBusinessObjectManager;

public class TreeManagerImpl extends AbstractBusinessObjectManager implements
		TreeManager {

	private MoneyTypeDao moneyTypeDao = null;
	private RoleWithMenuDao roleWithMenuDao = null;

	public TreeManagerImpl(MoneyTypeDao moneyTypeDao,
			RoleWithMenuDao roleWithMenuDao) {
		this.moneyTypeDao = moneyTypeDao;
		this.roleWithMenuDao = roleWithMenuDao;
	}

	private MyJdbcTool jdbc;

	public MyJdbcTool getJdbc() {
		return jdbc;
	}

	public void setJdbc(MyJdbcTool jdbc) {
		this.jdbc = jdbc;
	}

	/**
	 * 查询树形的json串.
	 */
	public String getMoneyTypeTree() {
		if (CacheManager.getCacheInfo("moneyTypeTree") == null) {
			Collection<MoneyTypeVO> allNodeCollection = new ArrayList<MoneyTypeVO>();

			Collection<MoneyTypeVO> firstlevel = this.moneyTypeDao
					.findFirstLevel();
			allNodeCollection.addAll(firstlevel);

			Iterator<MoneyTypeVO> it = firstlevel.iterator();
			while (it.hasNext()) {
				MoneyTypeVO vo = it.next();
				List c = this.moneyTypeDao.findChildCount(vo.getTypeCode());
				if (c != null && c.size() > 0) {
					int totalCount = Integer.parseInt("" + c.get(0));
					if (totalCount > 0) {
						Collection<MoneyTypeVO> child = this.moneyTypeDao
								.findChildren(vo.getTypeCode());
						allNodeCollection.addAll(child);
					}
				}
			}
			TreeTool<MoneyTypeVO> tree = new TreeTool<MoneyTypeVO>();
			String ans = tree.getTreeStr(allNodeCollection);

			Cache c = new Cache();
			c.setKey("moneyTypeTree");
			c.setValue(ans);
			c.setName("金额类型树");
			CacheManager.putCache("moneyTypeTree", c);

			return ans;
		} else {
			return (String) CacheManager.getCacheInfo("moneyTypeTree")
					.getValue();
		}
	}

	public String getMenuTree() {
		return initMenuCache().toZTreeJson();
	}

	@Override
	public Tree initMenuCache() {
		Tree tree = null;
		if (CacheManager.getCacheInfo("menuTree") == null) {
			tree = new Tree("0", "菜单树");
			LinkedList<TreeNode> allP = new LinkedList();
			allP.add(tree.getRoot());

			do {
				TreeNode nd = allP.poll();
				int totalCount = jdbc.queryForInt(
						"select count(1) from menu_t where parentid=?",
						new Object[] { nd.getId() });
				if (totalCount > 0) {
					List child = jdbc
							.queryForList(
									"select menuid,menuname,url,relId,target from menu_t where parentid=? ",
									new Object[] { nd.getId() });
					nd.open = "true";
					for (int ii = 0, jj = child.size(); ii < jj; ii++) {
						ListOrderedMap _objs = (ListOrderedMap) child.get(ii);
						TreeNode _nd = new TreeNode(_objs.getValue(0) + "",
								_objs.getValue(1) + "");
						_nd.level = nd.level + 1;
						_nd.setUrl("" + _objs.getValue(2));
						_nd.relId = "" + _objs.getValue(3);
						_nd.target = "" + _objs.getValue(4);
						nd.addChild(_nd);
						allP.add(_nd);
					}
				}
			} while (allP.size() > 0);

			Cache c = new Cache();
			c.setKey("menuTree");
			c.setValue(tree);
			c.setName("菜单树");
			CacheManager.putCache("menuTree", c);
		} else {
			tree = (Tree) CacheManager.getCacheInfo("menuTree").getValue();
		}
		return tree;
	}

	@Override
	public String getOrgTree() {
		return initOrgCache().toZTreeJson();
	}

	@Override
	public Tree initOrgCache() {
		Tree tree = null;
		if (CacheManager.getCacheInfo("orgTree") == null) {
			tree = new Tree("0", "组织机构树");
			LinkedList<TreeNode> allP = new LinkedList();
			allP.add(tree.getRoot());

			do {
				TreeNode nd = allP.poll();
				int totalCount = jdbc
						.queryForInt(
								"select count(1) from organization_t where parentorg=?",
								new Object[] { nd.getId() });
				if (totalCount > 0) {
					List child = jdbc
							.queryForList(
									"select id,orgname  from organization_t where parentorg=? ",
									new Object[] { nd.getId() });
					for (int ii = 0, jj = child.size(); ii < jj; ii++) {
						ListOrderedMap _objs = (ListOrderedMap) child.get(ii);
						TreeNode _nd = new TreeNode(_objs.getValue(0) + "",
								_objs.getValue(1) + "");
						_nd.level = nd.level + 1;
						nd.addChild(_nd);
						allP.add(_nd);
					}
				}
			} while (allP.size() > 0);

			Cache c = new Cache();
			c.setKey("orgTree");
			c.setValue(tree);
			c.setName("组织机构树");
			CacheManager.putCache("orgTree", c);
		} else {
			tree = (Tree) CacheManager.getCacheInfo("orgTree").getValue();
		}
		return tree;
	}

	@Override
	public String getOrgWithPeopleTree(String pid) {
		return toZTreeJson(initOrgWithPeopleTree(pid));
	}

	private String toZTreeJson(List<TreeNode> nodes) {
		StringBuilder buil = new StringBuilder();
		buil.append("[");
		if (nodes != null && nodes.size() > 0) {
			int j = 0, z = nodes.size();
			for (TreeNode nd : nodes) {
				buil.append("{id:'");
				buil.append(nd.getId());
				buil.append("',name:'");
				buil.append(nd.getName());
				buil.append("',isParent:");
				buil.append(nd.isParent);
				buil.append("}");
				if (j++ < z)
					buil.append(",");
			}
		}
		buil.append("]");
		return buil.toString();
	}

	public List<TreeNode> initOrgWithPeopleTree(String pid) {
		List<TreeNode> ans = new ArrayList<TreeNode>();
		if (pid == null)
			pid = "0";
		List child = jdbc.queryForList(
				"select id,orgname  from organization_t where parentorg=? ",
				new Object[] { pid });
		for (int ii = 0, jj = child.size(); ii < jj; ii++) {
			ListOrderedMap _objs = (ListOrderedMap) child.get(ii);
			TreeNode _nd = new TreeNode(_objs.getValue(0) + "",
					_objs.getValue(1) + "");
			_nd.isParent = true;
			ans.add(_nd);
		}

		child = jdbc.queryForList(
				"select id,username  from user_t where orgid=? ",
				new Object[] { pid });
		for (int ii = 0, jj = child.size(); ii < jj; ii++) {
			ListOrderedMap _objs = (ListOrderedMap) child.get(ii);
			TreeNode _nd = new TreeNode(_objs.getValue(0) + "",
					_objs.getValue(1) + "");
			_nd.isParent = false;
			ans.add(_nd);
		}
		return ans;
	}

	@Override
	public Tree initMenuWithRight(String userId) {
		Tree tree = null;
		tree = new Tree("0", "菜单树");
		LinkedList<TreeNode> allP = new LinkedList();
		allP.add(tree.getRoot());

		do {
			TreeNode nd = allP.poll();
			int totalCount = jdbc
					.queryForInt(
							"select count(distinct t1.menuid) from menu_t t1,user_menu_right t2 where t1.menuid=t2.menuid and t1.parentid=? and t2.userid=?",
							new Object[] { nd.getId(), userId });
			if (totalCount > 0) {
				List child = jdbc
						.queryForList(
								"select distinct t1.menuid,menuname,url,relId,target from menu_t t1,user_menu_right t2 where t1.menuid=t2.menuid and t1.parentid=? and t2.userid=? ",
								new Object[] { nd.getId(), userId });
				nd.open = "true";
				for (int ii = 0, jj = child.size(); ii < jj; ii++) {
					ListOrderedMap _objs = (ListOrderedMap) child.get(ii);
					TreeNode _nd = new TreeNode(_objs.getValue(0) + "",
							_objs.getValue(1) + "");
					_nd.level = nd.level + 1;
					_nd.setUrl("" + _objs.getValue(2));
					_nd.relId = "" + _objs.getValue(3);
					_nd.target = "" + _objs.getValue(4);
					nd.addChild(_nd);
					allP.add(_nd);
				}
			}
		} while (allP.size() > 0);

		return tree;
	}

	@Override
	public String getRoleMenuTree(int roleId) {
		Tree menuTree = initMenuCache();
		Collection<RoleWithMenuVO> set = roleWithMenuDao
				.findRecordByRoleId(roleId);
		Set<Integer> allHaveRightMenu = new HashSet<Integer>();
		if (set != null && set.size() > 0)
			for (RoleWithMenuVO vo : set) {
				allHaveRightMenu.add(vo.getMenuId());
			}
		final Set<Integer> allMenus = allHaveRightMenu;
		menuTree.travelTree(new ITreeNodeTravel(){ 
			@Override
			public void travel(TreeNode node) {
				if (allMenus.contains(node.getId())) {
					node.isChecked = true;
				} else
					node.isChecked = false;
			} 
		});
		 
		return menuTree.toZTreeJson();
	}
}

package money.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import money.rolemanager.RoleWithMenuDao;
import money.rolemanager.RoleWithMenuVO;

import org.springframework.util.LinkedCaseInsensitiveMap;

import common.MyJdbcTool;
import common.base.AllSelectContants;
import common.cache.Cache;
import common.cache.CacheManager;
import common.tree.ITreeNodeTravel;
import common.tree.Tree;
import common.tree.TreeNode;
import common.util.DateUtil;

import dwz.framework.constants.user.UserType;
import dwz.framework.core.business.AbstractBusinessObjectManager;

public class TreeManagerImpl extends AbstractBusinessObjectManager implements
		TreeManager {

	private RoleWithMenuDao roleWithMenuDao = null;

	public TreeManagerImpl(RoleWithMenuDao roleWithMenuDao) {
		this.roleWithMenuDao = roleWithMenuDao;
	}

	private MyJdbcTool jdbc;

	public MyJdbcTool getJdbc() {
		return jdbc;
	}

	public void setJdbc(MyJdbcTool jdbc) {
		this.jdbc = jdbc;
	}

	 

	public String getMenuTree() {
		return initMenuCache().toZTreeJson();
	}

	@Override
	public Tree initMenuCache() {
		Tree tree = null;
		if (CacheManager.getCacheInfo(AllSelectContants.MENUTREE.getName()) == null) {
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
						LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
								.get(ii);
						TreeNode _nd = new TreeNode(_objs.get("menuid") + "",
								_objs.get("menuname") + "");
						_nd.level = nd.level + 1;
						_nd.setUrl("" + _objs.get("url"));
						_nd.relId = "" + _objs.get("relId");
						_nd.target = "" + _objs.get("target");
						nd.addChild(_nd);
						allP.add(_nd);
					}
				}
			} while (allP.size() > 0);

			Cache c = new Cache();
			c.setKey(AllSelectContants.MENUTREE.getName());
			c.setValue(tree);
			c.setName("菜单树");
			CacheManager.putCache(AllSelectContants.MENUTREE.getName(), c);
		} else {
			tree = (Tree) CacheManager.getCacheInfo(AllSelectContants.MENUTREE.getName()).getValue();
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
		if (CacheManager.getCacheInfo(AllSelectContants.ORGTREE.getName()) == null) {
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
						LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
								.get(ii);
						TreeNode _nd = new TreeNode(_objs.get("id") + "",
								_objs.get("orgname") + "");
						_nd.level = nd.level + 1;
						nd.addChild(_nd);
						allP.add(_nd);
					}
				}
			} while (allP.size() > 0);

			Cache c = new Cache();
			c.setKey(AllSelectContants.ORGTREE.getName());
			c.setValue(tree);
			c.setName("组织机构树");
			CacheManager.putCache(AllSelectContants.ORGTREE.getName(), c);
		} else {
			tree = (Tree) CacheManager.getCacheInfo(AllSelectContants.ORGTREE.getName()).getValue();
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
				if (nd.isChecked)
					buil.append("',checked:true");
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

	/**
	 * 初始化人员组织机构树.
	 * @param pid
	 * @return
	 */
	public List<TreeNode> initOrgWithPeopleTree(String pid) {
		List<TreeNode> ans = new ArrayList<TreeNode>();
		if (pid == null)
			pid = "0";
		List child = jdbc.queryForList(
				"select id,orgname  from organization_t where parentorg=? ",
				new Object[] { pid });
		for (int ii = 0, jj = child.size(); ii < jj; ii++) {
			LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
					.get(ii);
			TreeNode _nd = new TreeNode(_objs.get("id") + "",
					_objs.get("orgname") + "");
			_nd.isParent = true;
			ans.add(_nd);
		}

		child = jdbc.queryForList(
				"select id,username  from user_t where orgid=? ",
				new Object[] { pid });
		for (int ii = 0, jj = child.size(); ii < jj; ii++) {
			LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
					.get(ii);
			TreeNode _nd = new TreeNode(_objs.get("id") + "",
					_objs.get("username") + "");
			_nd.isParent = false;
			ans.add(_nd);
		}
		return ans;
	}
	
	/**
	 * 判断一个保险单位是否有孩子单位.
	 * @param pid
	 * @return
	 */
	private boolean hasChildInsuredUnit(String pid){
		int count= jdbc.queryForInt(
				"select count(1) from insured_unit where unit_parent_id = ? ",
				new Object[] { pid });
		return count>0;
	}
	
	/**
	 * 初始化保险单位机构树.
	 * @param pid
	 * @return
	 */
	public List<TreeNode> initInsuredTree(String pid) {
		List<TreeNode> ans = new ArrayList<TreeNode>();
		if (pid == null)
			pid = "0";
		List child = jdbc.queryForList(
				"select id,unit_name from insured_unit where unit_parent_id = ? ",
				new Object[] { pid });
		for (int ii = 0, jj = child.size(); ii < jj; ii++) {
			LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
					.get(ii);
			TreeNode _nd = new TreeNode(_objs.get("id") + "",
					_objs.get("unit_name") + "");
			_nd.isParent = hasChildInsuredUnit(""+_objs.get("id"));
			ans.add(_nd);
		} 
		return ans;
	}

	@Override
	public Tree initMenuWithRight(String userId, UserType tp) {
		Tree tree = null;
		tree = new Tree("0", "菜单树");
		LinkedList<TreeNode> allP = new LinkedList();
		allP.add(tree.getRoot());
		String orgSql = "(select distinct r.menuid from user_role_right t,role_menu_right r where r.roleid=t.roleid and t.userid = ?)";
		if (UserType.SUPER.equals(tp)) {
			orgSql = "(select  menuid from menu_t where 1=1 ";
			//如果没有到1月31号，就不显示指定菜单 87！
			if(DateUtil.now().compareTo(DateUtil.getDate(2015, 2, 12))<=0){
				orgSql+=" and  menuid not in (93) ";
			}
			orgSql+=" ) ";
		}
		// 查询一个人的角色里面全部的菜单.
		// select distinct r.menuid from user_role_right t,role_menu_right r
		// where r.roleid=t.roleid and t.userid = 1;
		do {
			// 逐层次查询有权限的菜单
			TreeNode nd = allP.poll();
			int totalCount = jdbc
					.queryForInt(
							"select count(distinct t1.menuid) from menu_t t1,"
									+ orgSql
									+ " t2 where t1.menuid=t2.menuid and t1.parentid=? and t1.level!=66 ",
							UserType.SUPER.equals(tp) ? new Object[] { nd
									.getId() } : new Object[] { userId,
									nd.getId() });
			if (totalCount > 0) {
				List child = jdbc
						.queryForList(
								"select distinct t1.menuid,menuname,url,relId,target from menu_t t1,"
										+ orgSql
										+ "t2 where t1.menuid=t2.menuid and t1.parentid=?  ",
								UserType.SUPER.equals(tp) ? new Object[] { nd
										.getId() } : new Object[] { userId,
										nd.getId() });
				nd.open = "true";
				for (int ii = 0, jj = child.size(); ii < jj; ii++) {
					LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
							.get(ii);
					TreeNode _nd = new TreeNode(_objs.get("menuid") + "",
							_objs.get("menuname") + "");
					_nd.level = nd.level + 1;
					_nd.setUrl("" + _objs.get("url"));
					_nd.relId = "" + _objs.get("relId");
					_nd.target = "" + _objs.get("target");
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
		Set<String> allHaveRightMenu = new HashSet<String>();
		if (set != null && set.size() > 0)
			for (RoleWithMenuVO vo : set) {
				allHaveRightMenu.add("" + vo.getMenuId());
			}
		final Set<String> allMenus = allHaveRightMenu;
		menuTree.travelTree(new ITreeNodeTravel() {
			@Override
			public void travel(TreeNode node) {
				if (allMenus.contains(node.getId())) {
					node.isChecked = true;
				} else
					node.isChecked = false;
			}
		});

		return menuTree.toZTreeJson(true);
	}

	@Override
	public String getInsuredTree(String pid) {
		return toZTreeJson(initInsuredTree(pid));
	}

	@Override
	public String getInsuredTree() {
		return initInsuredCache().toZTreeJson();
	} 
	
	
	@Override
	public Tree initInsuredCache() {
		Tree tree = null;
		if (CacheManager.getCacheInfo(AllSelectContants.INSUREDTREE.getName()) == null) {
			tree = new Tree("0", "投保单位树");
			LinkedList<TreeNode> allP = new LinkedList();
			allP.add(tree.getRoot());

			do {
				TreeNode nd = allP.poll();
				int totalCount = jdbc
						.queryForInt(
								"select count(1) from insured_unit where unit_parent_id=?",
								new Object[] { nd.getId() });
				nd.open = "true";
				if (totalCount > 0) {
					List child = jdbc
							.queryForList(
									"select id,unit_name  from insured_unit where unit_parent_id=? ",
									new Object[] { nd.getId() });
					for (int ii = 0, jj = child.size(); ii < jj; ii++) {
						LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
								.get(ii);
						TreeNode _nd = new TreeNode(_objs.get("id") + "",
								_objs.get("unit_name") + "");
						_nd.level = nd.level + 1;
						nd.addChild(_nd);
						allP.add(_nd);
					}
				}
			} while (allP.size() > 0);

			Cache c = new Cache();
			c.setKey(AllSelectContants.INSUREDTREE.getName());
			c.setValue(tree);
			c.setName("投保单位树");
			CacheManager.putCache(AllSelectContants.INSUREDTREE.getName(), c);
		} else {
			tree = (Tree) CacheManager.getCacheInfo(AllSelectContants.INSUREDTREE.getName()).getValue();
		}
		return tree;
	}

	@Override
	public String getCityTree() {
		return initCityCache().toZTreeJson();
	}

	@Override 
	public Tree initInsuredUnitCache() {
		Tree tree = null;
		if (CacheManager.getCacheInfo(AllSelectContants.INSURED_UNIT_TREE.getName()) == null) {
			tree = new Tree("0", "投保单位树");
			LinkedList<TreeNode> allP = new LinkedList();
			allP.add(tree.getRoot());

			do {
				TreeNode nd = allP.poll();
				int totalCount = jdbc
						.queryForInt(
								"select count(1) from insured_unit where unit_parent_id=?",
								new Object[] { nd.getId() });
				nd.open = "false";
				if (totalCount > 0) {
					List child = jdbc
							.queryForList(
									"select id,unit_name  from insured_unit where unit_parent_id=? ",
									new Object[] { nd.getId() });
					for (int ii = 0, jj = child.size(); ii < jj; ii++) {
						LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
								.get(ii);
						TreeNode _nd = new TreeNode(_objs.get("id") + "",
								_objs.get("unit_name") + "");
						_nd.level = nd.level + 1;
						nd.addChild(_nd);
						allP.add(_nd);
					}
				}
			} while (allP.size() > 0);

			Cache c = new Cache();
			c.setKey(AllSelectContants.INSURED_UNIT_TREE.getName());
			c.setValue(tree);
			c.setName("投保单位树");
			CacheManager.putCache(AllSelectContants.INSURED_UNIT_TREE.getName(), c);
		} else {
			tree = (Tree) CacheManager.getCacheInfo(AllSelectContants.INSURED_UNIT_TREE.getName()).getValue();
		}
		return tree;
	}
	
	@Override
	public Tree initCityCache() {
		Tree tree = null;
		if (CacheManager.getCacheInfo(AllSelectContants.CITYTREE.getName()) == null) {
			tree = new Tree("0", "城市树");
			LinkedList<TreeNode> allP = new LinkedList();
			allP.add(tree.getRoot());

			do {
				TreeNode nd = allP.poll();
				int totalCount = jdbc
						.queryForInt(
								"select count(1) from dict_province where unit_parent_id=?",
								new Object[] { nd.getId() });
				nd.open = "true";
				if (totalCount > 0) {
					List child = jdbc
							.queryForList(
									"select id,unit_name  from insured_unit where unit_parent_id=? ",
									new Object[] { nd.getId() });
					for (int ii = 0, jj = child.size(); ii < jj; ii++) {
						LinkedCaseInsensitiveMap _objs = (LinkedCaseInsensitiveMap) child
								.get(ii);
						TreeNode _nd = new TreeNode(_objs.get("id") + "",
								_objs.get("unit_name") + "");
						_nd.level = nd.level + 1;
						nd.addChild(_nd);
						allP.add(_nd);
					}
				}
			} while (allP.size() > 0);

			Cache c = new Cache();
			c.setKey(AllSelectContants.CITYTREE.getName());
			c.setValue(tree);
			c.setName("城市树");
			CacheManager.putCache(AllSelectContants.CITYTREE.getName(), c);
		} else {
			tree = (Tree) CacheManager.getCacheInfo(AllSelectContants.CITYTREE.getName()).getValue();
		}
		return tree;
	}

	@Override
	public String getInsuredUnitTree() {
		return initInsuredUnitCache().toZTreeJson();
	}
}

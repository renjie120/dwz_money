﻿package money.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import money.moneytype.MoneyTypeDao;
import money.moneytype.MoneyTypeVO;

import org.apache.commons.collections.map.ListOrderedMap;

import common.MyJdbcTool;
import common.base.TreeTool;
import common.cache.Cache;
import common.cache.CacheManager;
import common.tree.Tree;
import common.tree.TreeNode;

import dwz.framework.core.business.AbstractBusinessObjectManager;

public class TreeManagerImpl extends AbstractBusinessObjectManager implements
		TreeManager {

	private MoneyTypeDao moneyTypeDao = null;

	public TreeManagerImpl(MoneyTypeDao moneyTypeDao) {
		this.moneyTypeDao = moneyTypeDao;
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
		return  initMenuCache().toZTreeJson();
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
						_nd.level = nd.level+1;
						_nd.setUrl(""+_objs.getValue(2));
						_nd.relId = ""+_objs.getValue(3);
						_nd.target =  ""+_objs.getValue(4);
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
			tree  =(Tree)CacheManager.getCacheInfo("menuTree").getValue();
		} 
		return tree;
	} 
}

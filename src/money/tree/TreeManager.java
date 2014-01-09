﻿package money.tree;

import java.util.Set;

import dwz.framework.core.business.BusinessObjectManager;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface TreeManager extends BusinessObjectManager {
	
	/**
	 * 查询树形的json串.
	 * @return
	 */
	public String getMoneyTypeTree();
	
	/**
	 * 查询菜单树的json串.
	 * @return
	 */
	public String getMenuTree();
	
	/**
	 * 组织机构树的json串.
	 * @return
	 */
	public String getOrgTree();
	
	/**
	 * 人员组织机构树
	 * @return
	 */
	public String getOrgWithPeopleTree(String pid); 
	
	/**
	 * 得到菜单树形的缓存.
	 * @return
	 */
	public common.tree.Tree initMenuCache();
	
	public common.tree.Tree initMenuWithRight(String userId);
	
	/**
	 * 得到组织机构树形的缓存.
	 * @return
	 */
	public common.tree.Tree initOrgCache();
}

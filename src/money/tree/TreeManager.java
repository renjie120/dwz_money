package money.tree;

import common.tree.Tree;

import dwz.framework.constants.user.UserType;
import dwz.framework.core.business.BusinessObjectManager;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface TreeManager extends BusinessObjectManager {
	 
	
	/**
	 * 得到角色有权限的菜单树 .
	 * @return
	 */
	public String getRoleMenuTree(int roleId);
	
	/**
	 * 查询菜单树的json串.
	 * @return
	 */
	public String getMenuTree();
	
	/**
	 * 查询保险单位树的json串。
	 * @return
	 */
	public String getInsuredTree();
	
	/**
	 * 返回城市树.
	 * @return
	 */
	public String getCityTree();
	
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
	 * 得到参保单位的架构树.
	 * @param pid
	 * @return
	 */
	public String getInsuredTree(String pid); 
	
	 /**
	  * 投保单位树.
	  * @return
	  */
	 public String getInsuredUnitTree();

	/**
	 * 得到菜单树形的缓存.
	 * @return
	 */
	public common.tree.Tree initMenuCache();
	
	/**
	 * 得到用户有权限的菜单树形.
	 * @param userId
	 * @return
	 */
	public common.tree.Tree initMenuWithRight(String userId,UserType tp);
	 
	/**
	 * 投保单位树形的缓存.
	 * @return
	 */
	public  common.tree.Tree initInsuredUnitCache();
	
	/**
	 * 得到组织机构树形的缓存.
	 * @return
	 */
	public common.tree.Tree initOrgCache();
	
	/**
	 * 得到保险单位树形的缓存.
	 * @return
	 */
	public common.tree.Tree initInsuredCache();
	
	/**
	 * 得到城市树形的缓存.
	 * @return
	 */
	public common.tree.Tree initCityCache(); 
}

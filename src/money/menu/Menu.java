package money.menu;

import dwz.framework.core.business.BusinessObject;

/**
 * 关于菜单信息表的业务类接口
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public interface Menu extends BusinessObject  {
	/**
	 * 获取菜单流水号的属性值.
	 */
	public Integer getMenuId();

	/**
	 * 返回父亲节点name.
	 * @return
	 */
	public String getParentName();

	/**
	 * 获取目标的属性值.
	 */
	public String getTarget();

	/**
	 * 获取菜单名称的属性值.
	 */
	public String getMenuName();

	/**
	 * 获取上级菜单的属性值.
	 */
	public String getParentId();

	/**
	 * 获取排序号的属性值.
	 */
	public int getOrderId();

	/**
	 * 获取连接的属性值.
	 */
	public String getUrl();

	/**
	 * 获取菜单级别的属性值.
	 */
	public String getLevel();

	/**
	 * 获取关联id的属性值.
	 */
	public String getRelId();
}

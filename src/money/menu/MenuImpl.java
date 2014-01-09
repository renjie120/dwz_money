package money.menu;

import common.base.CheckAble;

import dwz.framework.core.business.BusinessObject;

/**
 * 关于菜单信息表的业务实体类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class MenuImpl implements Menu,CheckAble {
	private MenuVO menuVO = null;
	private static final long serialVersionUID = 1L;

	public MenuImpl(MenuVO menuVO) {
		this.menuVO = menuVO;
	}

	public MenuImpl(int menuId, String target, String menuName,
			String parentId, int orderId, String url, String level, String relId) {
		this.menuVO = new MenuVO(menuId, target, menuName, parentId, orderId,
				url, level, relId);
	}

	public MenuImpl(String target, String menuName, String parentId,
			int orderId, String url, String level, String relId) {
		this.menuVO = new MenuVO(target, menuName, parentId, orderId, url,
				level, relId);
	}

	public MenuVO getMenuVO() {
		return this.menuVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.menuVO.getMenuId();
	}

	/**
	 * 获取菜单流水号的属性值.
	 */
	public Integer getMenuId() {
		return this.menuVO.getMenuId();
	}

	/**
	 * 获取目标的属性值.
	 */
	public String getTarget() {
		return this.menuVO.getTarget();
	}

	/**
	 * 获取菜单名称的属性值.
	 */
	public String getMenuName() {
		return this.menuVO.getMenuName();
	}

	/**
	 * 获取上级菜单的属性值.
	 */
	public String getParentId() {
		return this.menuVO.getParentId();
	}

	/**
	 * 获取排序号的属性值.
	 */
	public int getOrderId() {
		return this.menuVO.getOrderId();
	}

	/**
	 * 获取连接的属性值.
	 */
	public String getUrl() {
		return this.menuVO.getUrl();
	}

	/**
	 * 获取菜单级别的属性值.
	 */
	public String getLevel() {
		return this.menuVO.getLevel();
	}

	/**
	 * 获取关联id的属性值.
	 */
	public String getRelId() {
		return this.menuVO.getRelId();
	}

	@Override
	public String getParentName() {
		return this.menuVO.getParentName();
	}

	@Override
	public String getChecked() { 
		return this.menuVO.getChecked(); 
	}

}
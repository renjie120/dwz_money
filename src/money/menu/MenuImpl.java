package money.menu;

import dwz.framework.core.business.BusinessObject;

public class MenuImpl implements Menu {
	private MenuVO menuVO = null;
	private static final long serialVersionUID = 1L;

	public MenuImpl(MenuVO menuVO) {
		this.menuVO = menuVO;
	}

	public MenuImpl(String menuName, String url, String target, int parentId,
			String level, int orderId, String relId) {
		this.menuVO = new MenuVO(menuName, url, target, parentId, level,
				orderId, relId);
	}

	public MenuImpl(int menuId, String menuName, String url, String target,
			int parentId, String level, int orderId, String relId) {
		this.menuVO = new MenuVO(menuId, menuName, url, target, parentId,
				level, orderId, relId);
	}

	public MenuVO getMenuVO() {
		return this.menuVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	public Integer getId() {
		return this.menuVO.getMenuId();
	}

	public int getMenuId() {
		return this.menuVO.getMenuId();
	}

	public String getMenuName() {
		return this.menuVO.getMenuName();
	}

	public String getUrl() {
		return this.menuVO.getUrl();
	}

	public String getTarget() {
		return this.menuVO.getTarget();
	}

	public int getParentId() {
		return this.menuVO.getParentId();
	}

	public String getLevel() {
		return this.menuVO.getLevel();
	}

	public int getOrderId() {
		return this.menuVO.getOrderId();
	}

	public String getRelId() {
		return this.menuVO.getRelId();
	}

}

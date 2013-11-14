package money.menu;

import java.io.Serializable;

public class MenuVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public MenuVO() {

	}

	public MenuVO(int menuId, String menuName, String url, String target,
			int parentId, String level, int orderId, String relId) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.url = url;
		this.target = target;
		this.parentId = parentId;
		this.level = level;
		this.orderId = orderId;
		this.relId = relId;

	}

	public MenuVO(String menuName, String url, String target, int parentId,
			String level, int orderId, String relId) {
		this.menuName = menuName;
		this.url = url;
		this.target = target;
		this.parentId = parentId;
		this.level = level;
		this.orderId = orderId;
		this.relId = relId;

	}

	private int menuId;

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getMenuId() {
		return menuId;
	}

	private String menuName;

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuName() {
		return menuName;
	}

	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	private String target;

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return target;
	}

	private int parentId;

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getParentId() {
		return parentId;
	}

	private String level;

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	private int orderId;

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	private String relId;

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getRelId() {
		return relId;
	}

}

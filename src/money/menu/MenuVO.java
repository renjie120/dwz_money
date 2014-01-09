package money.menu;

import java.io.Serializable;

/**
 * 关于菜单信息表的实体bean.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class MenuVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String checked;
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public MenuVO() {

	}

	public MenuVO(int menuId, String target, String menuName, String parentId,
			int orderId, String url, String level, String relId) {
		this.menuId = menuId;
		this.target = target;
		this.menuName = menuName;
		this.parentId = parentId;
		this.orderId = orderId;
		this.url = url;
		this.level = level;
		this.relId = relId;
	}

	public MenuVO(int menuId, String target, String menuName, String parentId,
			int orderId, String url, String level, String relId,
			String parentName) {
		this.menuId = menuId;
		this.target = target;
		this.menuName = menuName;
		this.parentId = parentId;
		this.orderId = orderId;
		this.url = url;
		this.level = level;
		this.relId = relId;
		this.parentName = parentName;
	}

	public MenuVO(String target, String menuName, String parentId, int orderId,
			String url, String level, String relId) {
		this.target = target;
		this.menuName = menuName;
		this.parentId = parentId;
		this.orderId = orderId;
		this.url = url;
		this.level = level;
		this.relId = relId;
	}

	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	private Integer menuId;

	/**
	 * 获取菜单流水号的属性值.
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单流水号的属性值.
	 */
	public void setMenuId(Integer menuid) {
		this.menuId = menuid;
	}

	private String target;

	/**
	 * 获取目标的属性值.
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 设置目标的属性值.
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	private String menuName;

	/**
	 * 获取菜单名称的属性值.
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * 设置菜单名称的属性值.
	 */
	public void setMenuName(String menuname) {
		this.menuName = menuname;
	}

	private String parentId;

	/**
	 * 获取上级菜单的属性值.
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置上级菜单的属性值.
	 */
	public void setParentId(String parentid) {
		this.parentId = parentid;
	}

	private int orderId;

	/**
	 * 获取排序号的属性值.
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * 设置排序号的属性值.
	 */
	public void setOrderId(int orderid) {
		this.orderId = orderid;
	}

	private String url;

	/**
	 * 获取连接的属性值.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置连接的属性值.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	private String level;

	/**
	 * 获取菜单级别的属性值.
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 设置菜单级别的属性值.
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	private String relId;

	/**
	 * 获取关联id的属性值.
	 */
	public String getRelId() {
		return relId;
	}

	/**
	 * 设置关联id的属性值.
	 */
	public void setRelId(String relid) {
		this.relId = relid;
	}
}

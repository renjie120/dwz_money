package money.role;

import java.io.Serializable;

/**
 * 关于用户菜单权限信息的实体bean.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class UserMenuRightVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserMenuRightVO() {

	}

	public UserMenuRightVO(Integer id, int menuId, int userId) {
		this.menuId = menuId;
		this.id = id;
		this.userId = userId;
	}

	public UserMenuRightVO(int menuId, int userId) {
		this.menuId = menuId;
		this.userId = userId;
	}

	private Integer menuId;

	/**
	 * 获取菜单id的属性值.
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单id的属性值.
	 */
	public void setMenuId(Integer menuid) {
		this.menuId = menuid;
	}

	private int userId;

	/**
	 * 获取用户id的属性值.
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 设置用户id的属性值.
	 */
	public void setUserId(int userid) {
		this.userId = userid;
	}
}

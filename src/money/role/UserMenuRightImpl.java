package money.role;

import dwz.framework.core.business.BusinessObject;

/**
 * 关于用户菜单权限信息的业务实体类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class UserMenuRightImpl implements UserMenuRight {
	private UserMenuRightVO usermenurightVO = null;
	private static final long serialVersionUID = 1L;

	public UserMenuRightImpl(UserMenuRightVO usermenurightVO) {
		this.usermenurightVO = usermenurightVO;
	}

	public UserMenuRightImpl(int id, int menuId, int userId) {
		this.usermenurightVO = new UserMenuRightVO(id, menuId, userId);
	}

	public UserMenuRightImpl(int menuId, int userId) {
		this.usermenurightVO = new UserMenuRightVO(menuId, userId);
	}

	public UserMenuRightVO getUserMenuRightVO() {
		return this.usermenurightVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.usermenurightVO.getMenuId();
	}

	/**
	 * 获取菜单id的属性值.
	 */
	public int getMenuId() {
		return this.usermenurightVO.getMenuId();
	}

	/**
	 * 获取用户id的属性值.
	 */
	public int getUserId() {
		return this.usermenurightVO.getUserId();
	}

}
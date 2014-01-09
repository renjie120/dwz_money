package money.rolemanager;

import dwz.framework.core.business.BusinessObject;

/**
 * 关于角色拥有的菜单权限的业务实体类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class RoleWithMenuImpl implements RoleWithMenu {
	private RoleWithMenuVO rolewithmenuVO = null;
	private static final long serialVersionUID = 1L;

	public RoleWithMenuImpl(RoleWithMenuVO rolewithmenuVO) {
		this.rolewithmenuVO = rolewithmenuVO;
	}

	public RoleWithMenuImpl(int id, int roleId, int menuId) {
		this.rolewithmenuVO = new RoleWithMenuVO(id, roleId, menuId);
	}

	public RoleWithMenuImpl(int roleId, int menuId) {
		this.rolewithmenuVO = new RoleWithMenuVO(roleId, menuId);
	}

	public RoleWithMenuVO getRoleWithMenuVO() {
		return this.rolewithmenuVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.rolewithmenuVO.getId();
	}

	/**
	 * 获取角色id 的属性值.
	 */
	public int getRoleId() {
		return this.rolewithmenuVO.getRoleId();
	}

	/**
	 * 获取菜单id的属性值.
	 */
	public int getMenuId() {
		return this.rolewithmenuVO.getMenuId();
	}

}
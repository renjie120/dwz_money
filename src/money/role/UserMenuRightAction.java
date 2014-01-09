package money.role;

import java.util.HashMap;
import java.util.Map;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.present.BaseAction;

/**
 * 关于用户菜单权限信息的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class UserMenuRightAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	UserMenuRightManager pMgr = bf
			.getManager(BeanManagerKey.usermenurightManager);
	// 业务实体对象
	private UserMenuRight vo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
	private long count;

	 

	public String doAdd() {
		String ids = request.getParameter("ids");
		String userId = request.getParameter("userId");
		pMgr.addUserMenuRights(ids,userId);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String doUpdate() {
		try {
			UserMenuRightImpl usermenurightImpl = new UserMenuRightImpl(menuId,
					userId);
			pMgr.updateUserMenuRight(usermenurightImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String beforeQuery() {
		return "query";
	}

	public String reQuery() {
		return "list";
	}

	public String menulist() {
		return "menulist";
	}

	/**
	 * 初始化权限管理界面，进入主菜单.
	 * 
	 * @return
	 */
	public String init() {
		return "rolepage";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private Map<UserMenuRightSearchFields, Object> getCriterias() {
		Map<UserMenuRightSearchFields, Object> criterias = new HashMap<UserMenuRightSearchFields, Object>();
		return criterias;
	}

	public UserMenuRight getVo() {
		return vo;
	}

	public void setVo(UserMenuRight vo) {
		this.vo = vo;
	}

	private int menuId;
	private Integer id;

	/**
	 * 获取菜单id的属性值.
	 */
	public int getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单id的属性值.
	 */
	public void setMenuId(int menuid) {
		this.menuId = menuid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

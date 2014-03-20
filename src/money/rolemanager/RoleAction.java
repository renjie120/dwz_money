package money.rolemanager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import money.tree.TreeManager;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于角色信息的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class RoleAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	RoleManager pMgr = bf.getManager(BeanManagerKey.roleManager);
	UserRoleRightManager userRoleRightMgr = bf.getManager(BeanManagerKey.userrolerightManager);
	// 业务实体对象
	private Role vo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
	private long count;
	private String userId;

	public String beforeAdd() {
		return "detail";
	}

	TreeManager tMgr = bf.getManager(BeanManagerKey.treeManager);

	public String doAdd() {
		try {
			RoleImpl roleImpl = new RoleImpl(roleDesc, roleName);
			pMgr.createRole(roleImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	/**
	 * 跳转到角色拥有的菜单树形.
	 * 
	 * @return
	 */
	public String updateMenuWithRole() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("roleId", "" + roleId);
		return "roleMenuTree";
	}

	/**
	 * 保存选择的权限
	 * 
	 * @return
	 */
	public String saveMenuWithRole() {
		String ids = request.getParameter("ids");
		String roleId = request.getParameter("roleId");
		try {
			pMgr.createRoleWithMenu(Integer.parseInt(roleId), ids);
		} catch (Exception e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}
	
	/**
	 * 对用户添加角色.
	* @Title: saveUserWithRole
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return
	* @return String
	* @throws
	 */
	public String saveUserWithRole() {
		String ids = request.getParameter("ids");
		String userId = request.getParameter("userId");
		try {
			pMgr.createUserWithRole(Integer.parseInt(userId), ids);
		} catch (Exception e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	/**
	 * 得到角色拥有的菜单权限树.
	 * 
	 * @return
	 */
	public String getRoleMenuTree() {
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToPage(response, tMgr.getRoleMenuTree(roleId));
		return null;
	}
	
	/**
	 * 添加用户的角色列表.
	* @Title: beforeRoleInUser
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return
	* @return String
	* @throws
	 */
	public String beforeRoleInUser() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<RoleSearchFields, Object> criterias = getCriterias();

		Collection<Role> moneyList = pMgr.searchRole(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchRoleNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
		return "addUserRolelist";
	}
	
	
	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeRoles(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getRole(roleId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			RoleImpl roleImpl = new RoleImpl(roleId, roleDesc, roleName);
			pMgr.updateRole(roleImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		ROLEID("角色id"), ROLEDESC("角色描述 "), ROLENAME("角色名称");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public String beforeQuery() {
		return "query";
	}
	
	/**
	 * 删除一个人拥有的角色权限.
	 * @return
	 */
	public String deleteRoleInUser() {
		String ids = request.getParameter("ids");
		userRoleRightMgr.removeUserRoleRights(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}
	
	/**
	 * 查询人员拥有的角色列表.
	 * @return
	 */
	public String queryRoleByUserId() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		int count = pMgr.searchRoleByUser(Integer.parseInt(userId));

		Collection<Role> moneyList = pMgr.searchRoleByUserId(
				Integer.parseInt(userId), startIndex, count);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("userId", userId);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
		return "userRoleList";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=RoleList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<RoleSearchFields, Object> criterias = getCriterias();

		Collection<Role> roleList = pMgr.searchRole(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (Role role : roleList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case ROLEID:
					e.setCell(filed.ordinal(), role.getRoleId());
					break;
				case ROLEDESC:
					e.setCell(filed.ordinal(), role.getRoleDesc());
					break;
				case ROLENAME:
					e.setCell(filed.ordinal(), role.getRoleName());
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<RoleSearchFields, Object> criterias = getCriterias();

		Collection<Role> moneyList = pMgr.searchRole(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchRoleNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
		return "list";
	}

	public String reQuery() {
		return "list";
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

	private Map<RoleSearchFields, Object> getCriterias() {
		Map<RoleSearchFields, Object> criterias = new HashMap<RoleSearchFields, Object>();
		if (getRoleName() != null && !"".equals(getRoleName()))
			criterias.put(RoleSearchFields.ROLENAME, "%" + getRoleName() + "%");
		return criterias;
	}

	public Role getVo() {
		return vo;
	}

	public void setVo(Role vo) {
		this.vo = vo;
	}

	private Integer roleId;

	/**
	 * 获取角色id的属性值.
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色id的属性值.
	 */
	public void setRoleId(Integer roleid) {
		this.roleId = roleid;
	}

	private String roleDesc;

	/**
	 * 获取角色描述 的属性值.
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * 设置角色描述 的属性值.
	 */
	public void setRoleDesc(String roledesc) {
		this.roleDesc = roledesc;
	}

	private String roleName;

	/**
	 * 获取角色名称的属性值.
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置角色名称的属性值.
	 */
	public void setRoleName(String rolename) {
		this.roleName = rolename;
	}
}

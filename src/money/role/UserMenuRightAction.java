package money.role;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于用户菜单权限信息的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UserMenuRightAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	UserMenuRightManager pMgr = bf.getManager(BeanManagerKey.usermenurightManager);
	//业务实体对象
	private UserMenuRight vo;
	//当前页数
	private int page = 1;
	//每页显示数量
	private int pageSize = 50;
	//总页数
	private long count;
	
	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			UserMenuRightImpl usermenurightImpl = new UserMenuRightImpl(userId );
			pMgr.createUserMenuRight(usermenurightImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeUserMenuRights(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getUserMenuRight(menuId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			UserMenuRightImpl usermenurightImpl = new UserMenuRightImpl( menuId , userId );
			pMgr.updateUserMenuRight(usermenurightImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  MENUID("菜单id"),  USERID("用户id");
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

	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=UserMenuRightList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<UserMenuRightSearchFields, Object> criterias = getCriterias();

		Collection<UserMenuRight> usermenurightList = pMgr.searchUserMenuRight(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (UserMenuRight usermenuright : usermenurightList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case MENUID:
						 e.setCell(filed.ordinal(), usermenuright.getMenuId()); 
					break;
					case USERID:
						 e.setCell(filed.ordinal(), usermenuright.getUserId()); 
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
		Map<UserMenuRightSearchFields, Object> criterias = getCriterias();

		Collection<UserMenuRight> moneyList = pMgr.searchUserMenuRight(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchUserMenuRightNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
		return "list";
	}

	public String reQuery() {
		return "list";
	}
	
	public String init() {
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
  
	private Integer menuId; 
 	/**
 	 * 获取菜单id的属性值.
 	 */
 	public Integer getMenuId(){
 		return menuId;
 	}
 	
 	/**
 	 * 设置菜单id的属性值.
 	 */
 	public void setMenuId(Integer menuid){
 		this.menuId = menuid;
 	}
	private int userId; 
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public int getUserId(){
 		return userId;
 	}
 	
 	/**
 	 * 设置用户id的属性值.
 	 */
 	public void setUserId(int userid){
 		this.userId = userid;
 	}
}

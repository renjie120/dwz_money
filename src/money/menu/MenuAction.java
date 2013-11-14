package money.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import common.util.CommonUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

public class MenuAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MenuManager pMgr = bf.getManager(BeanManagerKey.menuManager); 
	private Menu menuVo;

	public String beforeAdd() { 
		return "detail";
	}

	public String doAdd() {
		try {
			MenuImpl menuImpl = new MenuImpl(menuName, url, target, parentId,
					level, orderId, relId);
			pMgr.createMenu(menuImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeMenu(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() { 
		menuVo = pMgr.getMenu(menuId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			MenuImpl menuImpl = new MenuImpl(menuId, menuName, url, target,
					parentId, level, orderId, relId);
			pMgr.updateMenu(menuImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	private int page = 1;
	private int pageSize = 50;
	private long count;

	public enum ExportFiled {
		MENUNAME("菜单名"), URL("链接"), TARGET("菜单指向"), PARENTID("父级菜单"), LEVEL(
				"菜单级别"), ORDERID("排序号"), RELID("菜单页编码");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public String export() {
		response.setContentType("Application/excel");
		String fileNameString = CommonUtil.toUtf8String("菜单列表.xls");
		response.addHeader("Content-Disposition",
				"attachment;filename="+fileNameString);

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<MenuSearchFields, Object> criterias = getCriterias();

		Collection<Menu> menuList = pMgr.searchMenu(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (Menu menu : menuList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case MENUNAME:
					e.setCell(filed.ordinal(), menu.getMenuName());
					break;
				case URL:
					e.setCell(filed.ordinal(), menu.getUrl());
					break;
				case TARGET:
					e.setCell(filed.ordinal(), menu.getTarget());
					break;
				case PARENTID:
					e.setCell(filed.ordinal(), menu.getParentId());
					break;
				case LEVEL:
					e.setCell(filed.ordinal(), menu.getLevel());
					break;
				case ORDERID:
					e.setCell(filed.ordinal(), menu.getOrderId());
					break;
				case RELID:
					e.setCell(filed.ordinal(), menu.getRelId());
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
		Map<MenuSearchFields, Object> criterias = getCriterias();

		Collection<Menu> moneyList = pMgr.searchMenu(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", pMgr.searchMenuNum(criterias)); 
		ActionContext.getContext().put("list", moneyList); 
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

	private Map<MenuSearchFields, Object> getCriterias() {
		Map<MenuSearchFields, Object> criterias = new HashMap<MenuSearchFields, Object>();
		return criterias;
	}

	public Menu getMenuVo() {
		return menuVo;
	}

	public void setMenuVo(Menu menuVo) {
		this.menuVo = menuVo;
	}

	public Menu getMoneyVo() {
		return menuVo;
	}

	public void setMoneyVo(Menu menuVo) {
		this.menuVo = menuVo;
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

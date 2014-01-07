
package money.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于菜单信息表的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class MenuAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	MenuManager pMgr = bf.getManager(BeanManagerKey.menuManager);
	//业务实体对象
	private Menu vo;
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
			MenuImpl menuImpl = new MenuImpl(target ,menuName ,parentId ,orderId ,url ,level ,relId );
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
		pMgr.removeMenus(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getMenu(menuId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			MenuImpl menuImpl = new MenuImpl( menuId , target , menuName , parentId , orderId , url , level , relId );
			pMgr.updateMenu(menuImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  MENUID("菜单流水号"),  TARGET("目标"),  MENUNAME("菜单名称"),  PARENTID("上级菜单"),  ORDERID("排序号"),  URL("连接"),  LEVEL("菜单级别"),  RELID("关联id");
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
		response.addHeader("Content-Disposition","attachment;filename=MenuList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<MenuSearchFields, Object> criterias = getCriterias();

		Collection<Menu> menuList = pMgr.searchMenu(criterias, realOrderField(),
				startIndex, numPerPage);

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
					case MENUID:
						 e.setCell(filed.ordinal(), menu.getMenuId()); 
					break;
					case TARGET:
						 e.setCell(filed.ordinal(), menu.getTarget()); 
					break;
					case MENUNAME:
						 e.setCell(filed.ordinal(), menu.getMenuName()); 
					break;
					case PARENTID:
						 e.setCell(filed.ordinal(), menu.getParentId()); 
					break;
					case ORDERID:
						 e.setCell(filed.ordinal(), menu.getOrderId()); 
					break;
					case URL:
						 e.setCell(filed.ordinal(), menu.getUrl()); 
					break;
					case LEVEL:
						 e.setCell(filed.ordinal(), menu.getLevel()); 
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

		Collection<Menu> moneyList = pMgr.searchMenu(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchMenuNum(criterias);
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
			if (getMenuId()!=null&&getMenuId() !=0)
				criterias.put(MenuSearchFields.MENUID, getMenuId()); 
			if (getMenuName()!=null&&!"".equals(getMenuName()))
				criterias.put(MenuSearchFields.MENUNAME, "%"+getMenuName()+"%"); 
			if (getParentId()!=null&&!"".equals(getParentId()))
			 	criterias.put(MenuSearchFields.PARENTID,  getParentId());
			if (getLevel()!=null&&!"".equals(getLevel())&&!"-2".equals(getLevel())&&!"-1".equals(getLevel()))
			 	criterias.put(MenuSearchFields.LEVEL,  getLevel());
		return criterias;
	}

	public Menu getVo() {
		return vo;
	}

	public void setVo(Menu vo) {
		this.vo = vo;
	} 
  
	private Integer menuId; 
 	/**
 	 * 获取菜单流水号的属性值.
 	 */
 	public Integer getMenuId(){
 		return menuId;
 	}
 	
 	/**
 	 * 设置菜单流水号的属性值.
 	 */
 	public void setMenuId(Integer menuid){
 		this.menuId = menuid;
 	}
	private String target; 
 	/**
 	 * 获取目标的属性值.
 	 */
 	public String getTarget(){
 		return target;
 	}
 	
 	/**
 	 * 设置目标的属性值.
 	 */
 	public void setTarget(String target){
 		this.target = target;
 	}
	private String menuName; 
 	/**
 	 * 获取菜单名称的属性值.
 	 */
 	public String getMenuName(){
 		return menuName;
 	}
 	
 	/**
 	 * 设置菜单名称的属性值.
 	 */
 	public void setMenuName(String menuname){
 		this.menuName = menuname;
 	}
	private String parentId; 
 	/**
 	 * 获取上级菜单的属性值.
 	 */
 	public String getParentId(){
 		return parentId;
 	}
 	
 	/**
 	 * 设置上级菜单的属性值.
 	 */
 	public void setParentId(String parentid){
 		this.parentId = parentid;
 	}
	private int orderId; 
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public int getOrderId(){
 		return orderId;
 	}
 	
 	/**
 	 * 设置排序号的属性值.
 	 */
 	public void setOrderId(int orderid){
 		this.orderId = orderid;
 	}
	private String url; 
 	/**
 	 * 获取连接的属性值.
 	 */
 	public String getUrl(){
 		return url;
 	}
 	
 	/**
 	 * 设置连接的属性值.
 	 */
 	public void setUrl(String url){
 		this.url = url;
 	}
	private String level; 
 	/**
 	 * 获取菜单级别的属性值.
 	 */
 	public String getLevel(){
 		return level;
 	}
 	
 	/**
 	 * 设置菜单级别的属性值.
 	 */
 	public void setLevel(String level){
 		this.level = level;
 	}
	private String relId; 
 	/**
 	 * 获取关联id的属性值.
 	 */
 	public String getRelId(){
 		return relId;
 	}
 	
 	/**
 	 * 设置关联id的属性值.
 	 */
 	public void setRelId(String relid){
 		this.relId = relid;
 	}
}

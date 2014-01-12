
package money.homepage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于首页url的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class HomePageUrlAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	HomePageUrlManager pMgr = bf.getManager(BeanManagerKey.homepageurlManager);
	//业务实体对象
	private HomePageUrl vo;
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
			HomePageUrlImpl homepageurlImpl = new HomePageUrlImpl(urlDesc ,url ,orderId ,typeId );
			pMgr.createHomePageUrl(homepageurlImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeHomePageUrls(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getHomePageUrl(urlId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			HomePageUrlImpl homepageurlImpl = new HomePageUrlImpl( urlId , urlDesc , url , orderId , typeId );
			pMgr.updateHomePageUrl(homepageurlImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  URLID("首页流水号id"),  URLDESC("链接描述 "),  URL("链接"),  ORDERID("排序号"),  TYPEID("类型");
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
		response.addHeader("Content-Disposition","attachment;filename=HomePageUrlList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<HomePageUrlSearchFields, Object> criterias = getCriterias();

		Collection<HomePageUrl> homepageurlList = pMgr.searchHomePageUrl(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (HomePageUrl homepageurl : homepageurlList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case URLID:
						 e.setCell(filed.ordinal(), homepageurl.getUrlId()); 
					break;
					case URLDESC:
						 e.setCell(filed.ordinal(), homepageurl.getUrlDesc()); 
					break;
					case URL:
						 e.setCell(filed.ordinal(), homepageurl.getUrl()); 
					break;
					case ORDERID:
						 e.setCell(filed.ordinal(), homepageurl.getOrderId()); 
					break;
					case TYPEID:
						 e.setCell(filed.ordinal(), homepageurl.getTypeId()); 
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
		Map<HomePageUrlSearchFields, Object> criterias = getCriterias();

		Collection<HomePageUrl> moneyList = pMgr.searchHomePageUrl(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchHomePageUrlNum(criterias);
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

	private Map<HomePageUrlSearchFields, Object> getCriterias() {
		Map<HomePageUrlSearchFields, Object> criterias = new HashMap<HomePageUrlSearchFields, Object>();
		return criterias;
	}

	public HomePageUrl getVo() {
		return vo;
	}

	public void setVo(HomePageUrl vo) {
		this.vo = vo;
	} 
  
	private Integer urlId; 
 	/**
 	 * 获取首页流水号id的属性值.
 	 */
 	public Integer getUrlId(){
 		return urlId;
 	}
 	
 	/**
 	 * 设置首页流水号id的属性值.
 	 */
 	public void setUrlId(Integer urlid){
 		this.urlId = urlid;
 	}
	private String urlDesc; 
 	/**
 	 * 获取链接描述 的属性值.
 	 */
 	public String getUrlDesc(){
 		return urlDesc;
 	}
 	
 	/**
 	 * 设置链接描述 的属性值.
 	 */
 	public void setUrlDesc(String urldesc){
 		this.urlDesc = urldesc;
 	}
	private String url; 
 	/**
 	 * 获取链接的属性值.
 	 */
 	public String getUrl(){
 		return url;
 	}
 	
 	/**
 	 * 设置链接的属性值.
 	 */
 	public void setUrl(String url){
 		this.url = url;
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
	private int typeId; 
 	/**
 	 * 获取类型的属性值.
 	 */
 	public int getTypeId(){
 		return typeId;
 	}
 	
 	/**
 	 * 设置类型的属性值.
 	 */
 	public void setTypeId(int typeid){
 		this.typeId = typeid;
 	}
}

package money.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import money.tree.TreeManager;

import com.opensymphony.xwork2.ActionContext;
import common.base.AllSelect;
import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.present.BaseAction;

 
public class CacheAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CacheManager pMgr = bf.getManager(BeanManagerKey.cacheManager);
	private Cache cacheVo;

	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			CacheImpl cacheImpl = new CacheImpl(cacheId, cacheName);
			pMgr.createCache(cacheImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeCache(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}
	
	/**
	 * 刷新全部缓存.
	 * @return
	 */
	public String doUpdateAll() { 
		common.cache.CacheManager.clearAll();
		pMgr.deleteAllCache();
		
		//初始化全部的缓存
		AllSelect allselect  = (AllSelect)SpringContextUtil.getBean(AllSelect.BEANNAME);
		allselect.getAllMoneyType();
		allselect.getAllParamType();
		allselect.getAllParamTypeCode();
		allselect.cacheAllParams();
		
		//初始化金额树.
		TreeManager tMgr = (TreeManager)SpringContextUtil.getBean(BeanManagerKey.treeManager.toString());
		tMgr.getMoneyTypeTree();  
		
		//初始化业务菜单缓存.
		tMgr.initMenuCache();
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		cacheVo = pMgr.getCache(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			CacheImpl cacheImpl = new CacheImpl(sno, cacheId, cacheName);
			pMgr.updateCache(cacheImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	private int page = 1;
	private int pageSize = 50;
	private long count;

	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<CacheSearchFields, Object> criterias = getCriterias();

		Collection<Cache> moneyList = pMgr.searchCache(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", pMgr.searchCacheNum(criterias));
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",
				pMgr.searchCacheNum(criterias));
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

	private Map<CacheSearchFields, Object> getCriterias() {
		Map<CacheSearchFields, Object> criterias = new HashMap<CacheSearchFields, Object>();
		return criterias;
	}

	public Cache getCacheVo() {
		return cacheVo;
	}

	public void setCacheVo(Cache cacheVo) {
		this.cacheVo = cacheVo;
	}

	public Cache getMoneyVo() {
		return cacheVo;
	}

	public void setMoneyVo(Cache cacheVo) {
		this.cacheVo = cacheVo;
	}

	private int sno;

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getSno() {
		return sno;
	}

	private String cacheId;

	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}

	public String getCacheId() {
		return cacheId;
	}

	private String cacheName;

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getCacheName() {
		return cacheName;
	}

}

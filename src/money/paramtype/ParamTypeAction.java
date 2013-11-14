
package money.paramtype;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import common.base.AllSelect;
import common.base.ParamSelect;
import common.util.CommonUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.present.BaseAction;

/**
 * 参数类型
 * @author lsq
 *
 */
public class ParamTypeAction extends BaseAction {
	/**
	 * 
	 */  
	private static final long serialVersionUID = 1L;
	ParamTypeManager pMgr = bf.getManager(BeanManagerKey.paramTypeManager);
	AllSelect allSelect = bf.getManager(BeanManagerKey.allSelectManager); 
	
	private ParamType paramTypeVo; 
	private int parameterTypeId;
	private String parameterTypeName;
	private int orderId; 
	private String code; 

	public int getParameterTypeId() {
		return parameterTypeId;
	}

	public void setParameterTypeId(int parameterTypeId) {
		this.parameterTypeId = parameterTypeId;
	}

	public String getParameterTypeName() {
		return parameterTypeName;
	}

	public void setParameterTypeName(String parameterTypeName) {
		this.parameterTypeName = parameterTypeName;
	} 

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String beforeAdd() {
		return "detail";
	}
 
	/**
	 * 添加信息.
	 * @return
	 */
	public String doAdd() {
		try {
			ParamTypeImpl paramImpl = new ParamTypeImpl( parameterTypeName,
					orderId,code);
			pMgr.createParamType(paramImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	/**
	 * 删除信息.
	 * @return
	 */
	public String doDelete() {
		String ids = request.getParameter("ids");
		int allCount  = ids.split(",").length;
		int count = pMgr.removeParamType(ids);
		if(allCount==count)
			return ajaxForwardSuccess(getText("msg.operation.success"));
		return ajaxForwardSuccess("删除成功:"+count+",未删除正在被使用的参数类型！");
	}

	/**
	 * 得到详细信息.
	 * @return
	 */
	public String beforeUpdate() {
		paramTypeVo = pMgr.getParamType(parameterTypeId);
		return "editdetail";
	}

	/**
	 * 更新信息.
	 * @return
	 */
	public String doUpdate() {
		try {
			ParamTypeImpl paramImpl = new ParamTypeImpl(parameterTypeId, parameterTypeName,
					orderId,code);
			pMgr.updateParamType(paramImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	private int page = 1;
	private int pageSize = 50;
	private long count;

	/**
	 * 查询信息.
	 * @return
	 */
	public String query() {
		ParamSelect paramSelect = allSelect.getAllMoneyType();
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<ParamTypeSearchFields, Object> criterias = getCriterias();

		Collection<ParamType> moneyList = pMgr.searchParamType(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", pMgr.searchParamTypeNum(criterias));
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",
				pMgr.searchParamTypeNum(criterias));
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

	private Map<ParamTypeSearchFields, Object> getCriterias() {
		Map<ParamTypeSearchFields, Object> criterias = new HashMap<ParamTypeSearchFields, Object>();
	
		if (!CommonUtil.isEmpty(parameterTypeName)){
			criterias.put(ParamTypeSearchFields.PARAMETER_TYPE_NAME, "%"+parameterTypeName+"%"); 
		}
		return criterias;
	}

	public ParamType getParamTypeVo() {
		return paramTypeVo;
	}

	public void setParamTypeVo(ParamType paramTypeVo) {
		this.paramTypeVo = paramTypeVo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	} 

	 
}


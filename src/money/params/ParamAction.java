package money.params;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import money.paramtype.ParamTypeManager;

import com.opensymphony.xwork2.ActionContext;
import common.base.AllSelect;
import common.util.CommonUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.present.BaseAction;

/**
 * 理财管理.
 * 
 * @author lsq
 * 
 */
public class ParamAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ParamManager pMgr = bf.getManager(BeanManagerKey.paramManager);
	ParamTypeManager ptMgr = bf.getManager(BeanManagerKey.paramTypeManager);
	private Param paramVo;
	private int parameterID; 
	private int parameterType;
	private String parameterName;
	private int orderId;
	AllSelect allSelect = bf.getManager(BeanManagerKey.allSelectManager);
	public String beforeAdd() {
		//得到全部的参数类型. 
		ActionContext.getContext().put("allType", allSelect.getAllParamType().getDatas());
		return "detail";
	}

	private int parameterValue;
	private String useValue;
	/**
	 * 添加信息.
	 * 
	 * @return
	 */
	public String doAdd() {
		try {
			ParamImpl paramImpl = new ParamImpl(parameterType,  
					parameterName, orderId,  parameterValue,  useValue);
			pMgr.createParam(paramImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	/**
	 * 删除信息.
	 * 
	 * @return
	 */
	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeParam(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	/**
	 * 得到详细信息.
	 * 
	 * @return
	 */
	public String beforeUpdate() {
		//得到全部的参数类型. 
		ActionContext.getContext().put("allType", allSelect.getAllParamType().getDatas());
		paramVo = pMgr.getParam(parameterID);
		return "editdetail";
	}

	/**
	 * 更新信息.
	 * 
	 * @return
	 */
	public String doUpdate() {
		try {
			ParamImpl paramImpl = new ParamImpl(parameterID, parameterType,
					  parameterName, orderId,parameterValue,useValue);
			pMgr.updateParam(paramImpl);
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
	 * 
	 * @return
	 */
	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<ParamSearchFields, Object> criterias = getCriterias(); 
		Collection<Param> moneyList = pMgr.searchParam(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("totalCount", pMgr.searchParamNum(criterias));
		ActionContext.getContext().put("list", moneyList);
		//得到全部的参数类型. 
		ActionContext.getContext().put("allType", allSelect.getAllParamType().getDatas());
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

	private Map<ParamSearchFields, Object> getCriterias() {
		Map<ParamSearchFields, Object> criterias = new HashMap<ParamSearchFields, Object>();
		if (parameterType > 0)
			criterias.put(ParamSearchFields.PARAM_TYPE, parameterType);
		if (CommonUtil.isNotEmpty(parameterName))
			criterias.put(ParamSearchFields.PARAM_NAME, "%" + parameterName
					+ "%"); 
		return criterias;
	}

	public Param getParamVo() {
		return paramVo;
	}

	public void setParamVo(Param paramVo) {
		this.paramVo = paramVo;
	}

	public Param getMoneyVo() {
		return paramVo;
	}

	public void setMoneyVo(Param paramVo) {
		this.paramVo = paramVo;
	}

	public int getParameterID() {
		return parameterID;
	}

	public void setParameterID(int parameterID) {
		this.parameterID = parameterID;
	}

	public int getParameterType() {
		return parameterType;
	}

	public void setParameterType(int parameterType) {
		this.parameterType = parameterType;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(int parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getUseValue() {
		return useValue;
	}

	public void setUseValue(String useValue) {
		this.useValue = useValue;
	} 
}

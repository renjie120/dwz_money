package money.param;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import common.cache.CacheEnum;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于参数的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class ParamAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	ParamManager pMgr = bf.getManager(BeanManagerKey.paramManager);
	// 业务实体对象
	private Param paramVo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
	private long count;

	public String beforeAdd() {
		return "detail";
	}

	private int change(String str) {
		if (str == null || "".equals(str.trim())) {
			return 0;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public String doAdd() {
		try {
			ParamImpl paramImpl = new ParamImpl(paramType, paramName,
					change(paramValue), usevalue, change(orderId));
			pMgr.createParam(paramImpl);
			common.cache.CacheManager.clearOnly(CacheEnum.ALLPARAMTYPECODE
					.getName());
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeParams(ids);
		common.cache.CacheManager.clearOnly(CacheEnum.ALLPARAMTYPECODE
				.getName());
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		paramVo = pMgr.getParam(paramId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			ParamImpl paramImpl = new ParamImpl(paramId, paramType, paramName,
					change(paramValue), usevalue, change(orderId));
			pMgr.updateParam(paramImpl);
			common.cache.CacheManager.clearOnly(CacheEnum.ALLPARAMTYPECODE
					.getName());
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		PARAMID("参数流水号"), PARAMTYPE("参数类型"), PARAMNAME("参数描述"), PARAMVALUE(
				"参数值"), USEVALUE("用户自定义值"), ORDERID("排序号");
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
		response.addHeader("Content-Disposition",
				"attachment;filename=ParamList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<ParamSearchFields, Object> criterias = getCriterias();

		Collection<Param> paramList = pMgr.searchParam(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (Param param : paramList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case PARAMID:
					e.setCell(filed.ordinal(), param.getParamId());
					break;
				case PARAMTYPE:
					e.setCell(filed.ordinal(), param.getParamType());
					break;
				case PARAMNAME:
					e.setCell(filed.ordinal(), param.getParamName());
					break;
				case PARAMVALUE:
					e.setCell(filed.ordinal(), param.getParamValue());
					break;
				case USEVALUE:
					e.setCell(filed.ordinal(), param.getUsevalue());
					break;
				case ORDERID:
					e.setCell(filed.ordinal(), param.getOrderId());
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
		Map<ParamSearchFields, Object> criterias = getCriterias();

		Collection<Param> moneyList = pMgr.searchParam(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchParamNum(criterias);
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

	private Map<ParamSearchFields, Object> getCriterias() {
		Map<ParamSearchFields, Object> criterias = new HashMap<ParamSearchFields, Object>();
		return criterias;
	}

	public Param getParamVo() {
		return paramVo;
	}

	public void setParamVo(Param paramVo) {
		this.paramVo = paramVo;
	}

	private Integer paramId;

	/**
	 * 获取参数流水号的属性值.
	 */
	public Integer getParamId() {
		return paramId;
	}

	/**
	 * 设置参数流水号的属性值.
	 */
	public void setParamId(Integer paramid) {
		this.paramId = paramid;
	}

	private int paramType;

	/**
	 * 获取参数类型的属性值.
	 */
	public int getParamType() {
		return paramType;
	}

	/**
	 * 设置参数类型的属性值.
	 */
	public void setParamType(int paramtype) {
		this.paramType = paramtype;
	}

	private String paramName;

	/**
	 * 获取参数描述的属性值.
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * 设置参数描述的属性值.
	 */
	public void setParamName(String paramname) {
		this.paramName = paramname;
	}

	private String paramValue;

	/**
	 * 获取参数值的属性值.
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * 设置参数值的属性值.
	 */
	public void setParamValue(String paramvalue) {
		this.paramValue = paramvalue;
	}

	private String usevalue;

	/**
	 * 获取用户自定义值的属性值.
	 */
	public String getUsevalue() {
		return usevalue;
	}

	/**
	 * 设置用户自定义值的属性值.
	 */
	public void setUsevalue(String usevalue) {
		this.usevalue = usevalue;
	}

	private String orderId;

	/**
	 * 获取排序号的属性值.
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置排序号的属性值.
	 */
	public void setOrderId(String orderid) {
		this.orderId = orderid;
	}
}

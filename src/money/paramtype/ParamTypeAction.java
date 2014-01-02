package money.paramtype;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于参数类型的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class ParamTypeAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	ParamTypeManager pMgr = bf.getManager(BeanManagerKey.paramtypeManager);
	// 业务实体对象
	private ParamType vo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
	private long count;

	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			ParamTypeImpl paramtypeImpl = new ParamTypeImpl(paramTypeName,
					orderId, code);
			pMgr.createParamType(paramtypeImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeParamTypes(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getParamType(paramTypeId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			ParamTypeImpl paramtypeImpl = new ParamTypeImpl(paramTypeId,
					paramTypeName, orderId, code);
			pMgr.updateParamType(paramtypeImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		PARAMTYPEID("参数类型流水号"), PARAMTYPENAME("参数类型"), ORDERID("排序号"), CODE(
				"参数类型编码");
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
				"attachment;filename=ParamTypeList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<ParamTypeSearchFields, Object> criterias = getCriterias();

		Collection<ParamType> paramtypeList = pMgr.searchParamType(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (ParamType paramtype : paramtypeList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case PARAMTYPEID:
					e.setCell(filed.ordinal(), paramtype.getParamTypeId());
					break;
				case PARAMTYPENAME:
					e.setCell(filed.ordinal(), paramtype.getParamTypeName());
					break;
				case ORDERID:
					e.setCell(filed.ordinal(), paramtype.getOrderId());
					break;
				case CODE:
					e.setCell(filed.ordinal(), paramtype.getCode());
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
		Map<ParamTypeSearchFields, Object> criterias = getCriterias();

		Collection<ParamType> moneyList = pMgr.searchParamType(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchParamTypeNum(criterias);
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

	private Map<ParamTypeSearchFields, Object> getCriterias() {
		Map<ParamTypeSearchFields, Object> criterias = new HashMap<ParamTypeSearchFields, Object>();
		return criterias;
	}

	public ParamType getVo() {
		return vo;
	}

	public void setVo(ParamType vo) {
		this.vo = vo;
	}

	private Integer paramTypeId;

	/**
	 * 获取参数类型流水号的属性值.
	 */
	public Integer getParamTypeId() {
		return paramTypeId;
	}

	/**
	 * 设置参数类型流水号的属性值.
	 */
	public void setParamTypeId(Integer paramtypeid) {
		this.paramTypeId = paramtypeid;
	}

	private String paramTypeName;

	/**
	 * 获取参数类型的属性值.
	 */
	public String getParamTypeName() {
		return paramTypeName;
	}

	/**
	 * 设置参数类型的属性值.
	 */
	public void setParamTypeName(String paramtypename) {
		this.paramTypeName = paramtypename;
	}

	private int orderId;

	/**
	 * 获取排序号的属性值.
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * 设置排序号的属性值.
	 */
	public void setOrderId(int orderid) {
		this.orderId = orderid;
	}

	private String code;

	/**
	 * 获取参数类型编码的属性值.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置参数类型编码的属性值.
	 */
	public void setCode(String code) {
		this.code = code;
	}
}

<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext; 
import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于${model.classDesc}的Action操作类.
 * @author ${author}
 * ${auth}
 * ${website}
 */ 
public class ${bignm}Action extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	${bignm}Manager pMgr = bf.getManager(BeanManagerKey.${classarg}Manager);
	//业务实体对象
	private ${bignm} vo;
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
			${bignm}Impl ${classarg}Impl = new ${bignm}Impl(<@allfield2notkey nm=model.attributes />);
			pMgr.create${bignm}(${classarg}Impl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.remove${bignm}s(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.get${bignm}(${model.keyName});
		return "editdetail";
	}

	public String doUpdate() {
		try {
			${bignm}Impl ${classarg}Impl = new ${bignm}Impl(<@allfield2 nm=model.attributes />);
			pMgr.update${bignm}(${classarg}Impl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		<#assign index=0><#assign size=model.attributes?size>
		<#list model.attributes as attr>  ${attr.name?upper_case}("${attr.desc}")<#assign index=index+1><#if index<size>,</#if></#list>;
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
		response.addHeader("Content-Disposition","attachment;filename=${bignm}List.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<${bignm}SearchFields, Object> criterias = getCriterias();

		Collection<${bignm}> ${classarg}List = pMgr.search${bignm}(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (${bignm} ${classarg} : ${classarg}List) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				<#list model.attributes as attr>
					case ${attr.name?upper_case}:
						 e.setCell(filed.ordinal(), ${classarg}.get${attr.name?cap_first}()); 
					break;
				</#list>  
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
		Map<${bignm}SearchFields, Object> criterias = getCriterias();

		Collection<${bignm}> moneyList = pMgr.search${bignm}(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.search${bignm}Num(criterias);
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

	private Map<${bignm}SearchFields, Object> getCriterias() {
		Map<${bignm}SearchFields, Object> criterias = new HashMap<${bignm}SearchFields, Object>();
		<#list model.attributes as attr>  
		<#if "${attr.query}"='true'>
		 	<#if '${attr.type}'='int'||'${attr.type}'='double'||'${attr.type}'='float'>
			if (get${attr.name?cap_first}()!=null&&get${attr.name?cap_first}() !=0)
				criterias.put(${bignm}SearchFields.${attr.name?upper_case}, get${attr.name?cap_first}()); 
			<#else>
			 	<#if '${attr.type}'='string'>
			 	<#if '${attr.selectType}'=''>
			if (get${attr.name?cap_first}()!=null&&!"".equals(get${attr.name?cap_first}()))
				<#else>
			if (get${attr.name?cap_first}()!=null&&!"".equals(get${attr.name?cap_first}())&&!"-1".equals(get${attr.name?cap_first}())&&!"-2".equals(get${attr.name?cap_first}()))
				</#if>
				<#if '${attr.querylike}'='true'>
				criterias.put(${bignm}SearchFields.${attr.name?upper_case}, "%"+get${attr.name?cap_first}()+"%"); 
				<#else>
			 	criterias.put(${bignm}SearchFields.${attr.name?upper_case},  get${attr.name?cap_first}());
				</#if>
				</#if> 
			</#if> 
		</#if>
		</#list>  
		return criterias;
	}

	public ${bignm} getVo() {
		return vo;
	}

	public void setVo(${bignm} vo) {
		this.vo = vo;
	} 
  
  	<@allGetAndSet nm=model.attributes />
}

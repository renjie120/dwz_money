<#include "/com.renjie120.codegenerate.common.ftl">
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/${nm}!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection"
		value="${orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/${nm}!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<#list model.attributes as attr>  
					<#if "${attr.query}"='true'>
					<td> 
						${attr.desc}</td><td>
						<#if '${attr.type}'='date'>
							<input type="text" name="${attr.name}" class="date" size="30" />
							<a class="inputDateButton" href="javascript:;">选择</a>
						<#else>
						 	<#if '${attr.showType}'='select'>
							<my:newselect tagName="${attr.name}"  paraType="${attr.selectCode}" width="100" allSelected="true" />
							<#else>
								<#if '${attr.showType}'='email'>
							<input name="${attr.name}" <#if '${attr.size}'!=''>size="${attr.size}"</#if>  class="email"     type="text"  value="<s:property value="vo.${attr.name}"/>" />
									<#else>
										<#if '${attr.showType}'='digits'>
							<input name="${attr.name}"<#if '${attr.size}'!=''>size="${attr.size}"</#if>   class="digits"   type="text"  value="<s:property value="vo.${attr.name}"/>" />
										<#else>
											<#if '${attr.showType}'='number'>
							<input name="${attr.name}" <#if '${attr.size}'!=''>size="${attr.size}"</#if>  class="number "  type="text"  value="<s:property value="vo.${attr.name}"/>" />
											<#else>
							<input name="${attr.name}" <#if '${attr.size}'!=''>size="${attr.size}"</#if>  class="textInput " type="text"  value="<s:property value="vo.${attr.name}"/>" />
											</#if>
										</#if>
									</#if>
							</#if> 
						</#if>
					</td> 
					</#if>
					</#list>    
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									检索
								</button>
							</div>
						</div>
					</li>
					<li>
						<a class="button" href="/money/${nm}!beforeQuery.do"
							target="dialog" mask="true" title="查询框"><span>高级检索</span> </a>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<#if  '${model.canAdd}'='true'>
			<li>
				<a class="add" href="/money/${nm}!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			</#if>
			<#if  '${model.canDelete}'='true'>
			<li>
				<a class="delete" href="/money/${nm}!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			</#if><#if  '${model.canUpdate}'='true'>
			<li>
				<a class="edit" href="/money/${nm}!beforeUpdate.do?${model.keyName}={${model.keyName}}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			</#if><#if  '${model.canExport}'='true'>
			<li>
				<a class="icon" href="/money/${nm}!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
			</#if><#if  '${model.canImport}'='true'>
			<li>
				<a class="icon" href="/money/${nm}!initImport.do" target="dialog"><span>从EXCEL导入</span> </a>
			</li> 
			</#if>
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<#list model.attributes as attr> 
				<#if "${attr.visible}"!='false'>
				<th width="${attr.width}"    orderField="${attr.name?upper_case}" >
						${attr.desc} 
				</th> 
				</#if>
				</#list>    
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="${model.keyName}" rel="<s:property value="${model.keyName}" />">
					<td>
						<input name="ids" value="<s:property value="${model.keyName}" />"
							type="checkbox">
					</td>
					<#list model.attributes as attr>  
					<#if "${attr.visible}"!='false'>
					<td>
						<s:property value="${attr.name}" />
					</td> 
					</#if>
					</#list>   
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value,pageNum:1})">
				<option value="20"
					<%if((request.getAttribute("numPerPage")+"").equals("20")){%>
					selected <%} %>>
					20
				</option>
				<option value="50"
					<%if((request.getAttribute("numPerPage")+"").equals("50")){%>
					selected <%} %>>
					50
				</option>
				<option value="100"
					<%if((request.getAttribute("numPerPage")+"").equals("100")){%>
					selected <%} %>>
					100
				</option>
				<option value="200"
					<%if((request.getAttribute("numPerPage")+"").equals("200")){%>
					selected <%} %>>
					200
				</option>
			</select>
			<span>条，总共${totalCount}条记录</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>


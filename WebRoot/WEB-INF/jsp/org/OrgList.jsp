<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/org!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />

</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/org!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						组织名称:
						<input name="orgName" class="textInput" size="30" type="text" />
					</td>

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
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="/money/org!beforeAdd.do" target="dialog"  mask="true"
					title="添加组织机构"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/org!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/org!beforeUpdate.do?orgId={orgId}" mask="true"
					target="dialog" title="修改组织机构"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/org!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>

		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100">
					组织流水号
				</th>
				<th width="100" orderField="ORGNAME" class="asc">
					组织名称
				</th>
				<th width="100" orderField="ORDERCODE" class="asc">
					组织代码
				</th>
				<th width="100" orderField="PARENTORG" class="asc">
					父组织
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="orgId" rel="<s:property value="orgId" />">
					<td>
						<input name="ids" value="<s:property value="orgId" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="orgId" />
					</td>
					<td>
						<s:property value="orgName" />
					</td>
					<td>
						<s:property value="orderCode" />
					</td>
					<td>
						<s:property value="parentOrg" />
					</td>  
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
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


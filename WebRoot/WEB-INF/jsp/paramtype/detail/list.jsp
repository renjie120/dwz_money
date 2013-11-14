<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/paramType!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>
<script type="text/javascript">
function closedialog(param) {
	alert(param.msg);
	return true;
}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/paramType!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						参数类型
						<input type="text" name="parameterTypeName" />
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
				<a class="add" href="/money/paramType!beforeAdd.do" target="dialog"
					rel="dlg_page10" mask="true" title="添加参数类型" width="500"
					height="300"><span>添加</span> </a>
			</li>
			<%
			if (isAdmin) {
			%>
			<li>
				<a class="delete" href="/money/paramType!doDelete.do"
					postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit"
					href="/money/paramType!beforeUpdate.do?parameterTypeId={parameterTypeId}"
					target="dialog"><span>修改</span> </a>
			</li>
			<%
			}
			%>
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100">
					参数类型id
				</th>
				<th width="100">
					参数类型描述
				</th>
				<th width="100">
					参数代码
				</th>
				<th width="100">
					排序
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="parameterTypeId"
					rel="<s:property value="parameterTypeId" />">
					<td>
						<input name="ids" value="<s:property value="parameterTypeId" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="parameterTypeId" />
					</td>
					<td>
						<s:property value="parameterTypeName" />
					</td>
					<td>
						<s:property value="code" />
					</td>
					<td>
						<s:property value="orderId" />
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
			<span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>

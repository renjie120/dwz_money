<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/org.js" type="text/javascript" />
<script type="text/javascript">
	
</script>
<div id='addUserRoleDiv'>
<form id="pagerForm" method="post" action="/money/role!beforeRoleInUser.do">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="orderField" value="${param.orderField}" /> <input
		type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/role!beforeRoleInUser.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>角色名称</td>
					<td><input name="roleName" class="textInput" size="30"
						type="text" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="/money/role!beforeQuery.do"><span>选择</span>
					</a></li>
					<li><a class="button" href="/money/role!beforeQuery.do"><span>取消</span>
					</a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="80"  >角色描述</th>
				<th width="150"  >角色名称</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="roleId" rel="<s:property value="roleId" />">
					<td><input name="ids" value="<s:property value="roleId" />"
						type="checkbox"></td>
					<td><s:property value="roleDesc" /></td>
					<td><s:property value="roleName" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20"
					<%if ((request.getAttribute("numPerPage") + "").equals("20")) {%>
					selected <%}%>>20</option>
				<option value="50"
					<%if ((request.getAttribute("numPerPage") + "").equals("50")) {%>
					selected <%}%>>50</option>
				<option value="100"
					<%if ((request.getAttribute("numPerPage") + "").equals("100")) {%>
					selected <%}%>>100</option>
				<option value="200"
					<%if ((request.getAttribute("numPerPage") + "").equals("200")) {%>
					selected <%}%>>200</option>
			</select> <span>条，总共${totalCount}条记录</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${totalCount}" rel="addUserRoleDiv"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div> 
</div>
</div>

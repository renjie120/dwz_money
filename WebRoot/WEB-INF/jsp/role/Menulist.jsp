
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
function navTabSearch(form, navTabId){
	var $form = $(form);
	if (form[DWZ.pageInfo.pageNum]) form[DWZ.pageInfo.pageNum].value = 1;
	navTab.reload($form.attr('action'), {data: $form.serializeArray(), navTabId:navTabId},function(){
		//initLayout();
	});
	return false;
} 
</script>
<div id="myrolemenudiv">
	<form id="pagerForm" method="post"  
		action="/money/menu!queryByUser.do?userId=<%=request.getParameter("userId")%>">
		<input type="hidden" name="pageNum" value="${pageNum}" /> <input
			type="hidden" name="numPerPage" value="${numPerPage}" /> <input
			type="hidden" name="orderField" value="${param.orderField}" /> <input
			type="hidden" name="orderDirection" value="${param.orderDirection}" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return refreshSelf(this);"
			action="/money/menu!queryByUser.do?userId=<%=request.getParameter("userId")%>"
			method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>菜单名称</td>
						<td><input name="menuName" class="textInput" size="30"
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
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent" id='tbtb' style="overflow:hidden;">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add"
					href="/money/usermenuright!doAdd.do?userId=<%=request.getParameter("userId")%>"
					postType="string" target="selectedTodo" rel="ids" alownotcheck="true" refreshtarget="myrolemenudiv"><span >授权</span>
				</a>
				</li>
			</ul>
		</div>
		<table class="table" layoutH="10" id="tableArea" setHeight='true' modifyHeight="100">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids"
						class="checkboxCtrl"></th>
					<th width="100"  >菜单流水号</th>
					<th width="140"  >菜单名称</th>
					<th width="100"  >上级菜单</th>
					<th width="240"  >连接</th>
					<th width="100"  >菜单级别</th>
					<th width="100"  >菜单编码</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="list" var="haha" status="stu">
					<tr target="menuId" rel="<s:property value="menuId" />">
						<s:if test="%{checked=='true'}">
							<td><input name="ids" value="<s:property value="menuId" />"
								checked type="checkbox"></td>
						</s:if>
						<s:else>
							<td><input name="ids" value="<s:property value="menuId" />"
								type="checkbox"></td>
						</s:else>
						<td><s:property value="menuId" /></td>
						<td><s:property value="menuName" /></td>
						<td><s:property value="parentId" /></td>
						<td><s:property value="url" /></td>
						<td><s:property value="level" /></td>
						<td><s:property value="relId" /></td>
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
			<div class="pagination" targetType="navTab"
				totalCount="${totalCount}" rel="myrolemenudiv"
				numPerPage="${numPerPage}" pageNumShown="20"
				currentPage="${pageNum}"></div>
		</div>
	</div>
</div>
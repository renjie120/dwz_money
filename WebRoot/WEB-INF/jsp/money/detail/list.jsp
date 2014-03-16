<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/treeCombox.js" type="text/javascript" />
<form id="pagerForm" method="post" action="/money/newmoney!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="orderField" value="${param.orderField}" /> <input
		type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<%
	String year = (String) request.getAttribute("year");
	String month = (String) request.getAttribute("month");
	String moneyType = (String) request.getAttribute("moneyType");
	String moneyTypeName = (String) request
			.getAttribute("moneyTypeName"); 
	if ("null".equals(moneyType) || moneyType == null)
		moneyType = "";
	if ("null".equals(moneyTypeName) || moneyTypeName == null)
		moneyTypeName = "";
%>

<script src="/js/money/list.js" type="text/javascript"></script>
<div class="pageHeader" id="moneylist">
	<form onsubmit="return navTabSearch(this);"
		action="/money/newmoney!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>年度</td>
					<td><my:newselect tagName="year" width="200"
							idColumn="paramvalue" nameColumn="parametername"
							tableName="params" where="parameterType=7" selectFlag="true"
							selectedValue="<%=year %>" /></td>
					<td>月份</td>
					<td><my:newselect tagName="month" width="200"
							idColumn="paramvalue" selectedValue="<%=month %>"
							nameColumn="parametername" tableName="params"
							where="parameterType=8" selectFlag="true" /></td>
					<td>分类</td>
					<td><input name="moneyTypeName" id="moneyTypeName2" type="text"
						readOnly="true" value="<%=moneyTypeName%>" /> <input
						name="moneyType" id="moneyType2" type="hidden" id="moneyType2"
						value="<%=moneyType%>" /></td>
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
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="/money/newmoney!beforeAdd.do"
				target="dialog" mask="true" title="添加金额信息"><span>添加</span> </a></li>
			<li><a class="delete" href="/money/newmoney!doDelete.do"
				postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
			</a></li>
			<li><a class="edit"
				href="/money/newmoney!beforeUpdate.do?moneySno={moneysno}"
				target="dialog" mask="true" title="修改金额信息"><span>修改</span> </a></li>
			<li class="line">line</li>
			<li><a class="icon" href="/money/newmoney!export.do"
				target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span>
			</a></li>
			<li><a class="icon" href="/money/newmoney!initImport.do"
				target="dialog"> <span>从EXCEL导入</span> </a></li>
			<li> <span>收入：</span><span type="in">${shouru}</span><span>支出：</span><span type="out">${zhichu}</span></li>
		</ul>
	</div>
	<table class="table" layoutH='-138'>
		<thead>
			<tr>
				<th width="30"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="200" orderField="MONEY_TIME" class="desc">时间</th>
				<th width="200" orderField="MONEY" class="asc">金额</th>
				<th width="200" orderField="MONEY_TYPE" class="desc">开支类型</th>
				<th width="200">描述</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="moneysno" rel="<s:property value="moneySno" />">
					<td><input name="ids" value="<s:property value="moneySno" />"
						type="checkbox"></td>
					<td><s:property value="moneyTime" /></td>
					<td><s:property value="money" /></td>
					<td><s:property value="moneyTypeName" /></td>
					<td><s:property value="moneyDesc" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value,arglist:'year,month,moneyType2,moneyTypeName2',pageNum:1})">
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
			</select> <span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			arglist="year,month,moneyType2,moneyTypeName2"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>

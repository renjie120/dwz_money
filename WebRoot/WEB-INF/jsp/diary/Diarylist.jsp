
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%
	String diaryType = (String) request.getAttribute("diaryType");
	System.out.println("diaryType---" + diaryType);
	if ("null".equals(diaryType) || diaryType == null)
		diaryType = "";
%>

<form id="pagerForm" method="post" action="/money/diary!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="orderField" value="${param.orderField}" /> <input
		type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/diary!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>日志内容 <input name="diaryContent" class="textInput"
						size="30" type="text" />
					</td>
					<td>日志类型</td>
					<td><my:newselect tagName="diaryType" width="200"  allSelected="true" selectedValue="<%=diaryType %>"
							paraType="diarytype"  /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
					<li><a class="button" href="/money/diary!beforeQuery.do"
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
			<li><a class="add" href="/money/diary!beforeAdd.do"
				target="dialog" mask="true" title="添加"><span>添加</span> </a>
			</li>
			<li><a class="delete" href="/money/diary!doDelete.do"
				postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
			</a>
			</li>
			<li><a class="edit"
				href="/money/diary!beforeUpdate.do?diaryId={diaryId}" mask="true"
				target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li><a class="icon" href="/money/diary!export.do"
				target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span>
			</a>
			</li>

		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" group="ids"
					class="checkboxCtrl">
				</th>
				<th width="100" orderField="DIARYID">日志id</th>
				<th width="800" orderField="DIARYCONTENT">日志内容</th>
				<th width="80" orderField="DIARYTIME">开始时间</th>
				<th width="100" orderField="DIARYTYPE">日志类型</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="diaryId" rel="<s:property value="diaryId" />">
					<td><input name="ids" value="<s:property value="diaryId" />"
						type="checkbox">
					</td>
					<td><s:property value="diaryId" />
					</td>
					<td><s:property value="diaryContent" />
					</td>
					<td><s:property value="diaryTime" />
					</td>
					<td><s:property value="diaryType" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage" 
				onchange="navTabPageBreak({numPerPage:this.value,arglist:'diaryType',pageNum:1})">
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
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" arglist="diaryType"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>



<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/loginfo!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/loginfo!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						时间 </td><td>
							<input name="operTime" size="30"  class="textInput " type="text"  value="<s:property value="vo.operTime"/>" />
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
				<a class="delete" href="/money/loginfo!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="icon" href="/money/loginfo!export.do" target="dwzExport"
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
				<th width="100"    orderField="OPERUSERNAME" >
						用户 
				</th> 
				<th width="140"    orderField="OPERTIME" >
						时间  
				</th> 
				<th width="100"    orderField="OPERTYPE" >
						操作类型 
				</th> 
				<th width="100"    orderField="OPERIP" >
						ip地址 
				</th> 
				<th width="120"    orderField="OPERURL" >
						操作地址 
				</th> 
				<th width="100"    orderField="OPERBEFORE" >
						修改前 
				</th> 
				<th width="100"    orderField="OPERAFTER" >
						修改后 
				</th> 
				<th width="300"    orderField="OPERDESC" >
						备注 
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td style="text-align:center;">
						<input name="ids" value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="operUserName" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:140px'><s:property value="operTime" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="operType" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="operIp" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:120px'><s:property value="operUrl" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="operBefore" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="operAfter" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:300px'><s:property value="operDesc" /></div>
					</td> 
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


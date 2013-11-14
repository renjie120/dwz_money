<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/moneyType!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="/money/moneyType!query.do"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						类型描述:
						<input name="moneyTypeDesc" class="textInput" size="30"
							type="text" />
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
				<a class="add" href="/money/moneyType!beforeAdd.do" target="dialog" mask="true"
					title="添加金额类型"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/moneyType!doDelete.do"
					postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" mask="true"
					href="/money/moneyType!beforeUpdate.do?moenyTypeSno={moenyTypeSno}"
					target="dialog" title="修改金额类型"><span>修改</span> </a>
			</li>
			<li class="line">
				line
			</li> 
			<li>
				<a class="icon" href="/money/moneyType!export.do" target="dwzExport"
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
					类型流水号
				</th>
				<th width="100">
					类型描述
				</th>
				<th width="100">
					收支类型
				</th>
				<th width="100">
					上级编码
				</th>
				<th width="100">
					类型编码
				</th>  
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="moenyTypeSno" rel="<s:property value="moenyTypeSno" />">
					<td>
						<input name="ids" value="<s:property value="moenyTypeSno" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="moenyTypeSno" />
					</td>
					<td>
						<s:property value="moneyTypeDesc" />
					</td>
					<td>
						<s:property value="moneyType" />
					</td>
					<td>
						<s:property value="parentCode" />
					</td>
					<td>
						<s:property value="typeCode" />
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


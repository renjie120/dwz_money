<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/param!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/param!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						参数
						<input type="text" name="parameterName" />
					</td>
					<td>
						参数类型
					</td>
					<td>
						<select class="combox" name="parameterType">
							<option value="">
								所有类型
							</option>
							<s:iterator value="allType" status="stu2">
								<option value="<s:property value="parameterTypeId" />">
									<s:property value="parameterTypeName" />
								</option>
							</s:iterator>
						</select>
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
				<a class="add" href="/money/param!beforeAdd.do" target="dialog"
					mask="true" title="添加参数"><span>添加</span> </a>
			</li>
			<%
			if (isAdmin) {
			%>
			<li>
				<a class="delete" href="/money/param!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit"
					href="/money/param!beforeUpdate.do?parameterID={parameterID}"
					mask="true" target="dialog" title="修改参数"><span>修改</span> </a>
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
				<th width="50">
					参数id
				</th>
				<th width="100">
					参数类型
				</th>
				<th width="300">
					参数描述
				</th>
				<th width="80">
					使用参数值
				</th>
				<th width="100">
					参数值
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="parameterID" rel="<s:property value="parameterID" />">
					<td>
						<input name="ids" value="<s:property value="parameterID" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="Id" />
					</td>
					<td>
						<s:property value="parameterTypeName" />
					</td>
					<td>
						<s:property value="parameterName" />
					</td>
					<td>
						<s:if test="useValue==1">是</s:if>
						<s:else>否</s:else>
					</td>
					<td>
						<s:property value="parameterValue" />
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

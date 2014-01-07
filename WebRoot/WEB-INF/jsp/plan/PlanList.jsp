<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/plan!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />

</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/plan!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr> 
					<td>
						计划类型:
					</td>
					<td>
						<input name="planType" class="textInput" size="30" type="text" />
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
					<li>
						<a class="button" href="/money/plan!beforeQuery.do"
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
			<li>
				<a class="add" href="/money/plan!beforeAdd.do" target="dialog"
					title="添加行动计划"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/plan!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/plan!beforeUpdate.do?planId={planId}"
					target="dialog" title="修改行动计划"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/plan!export.do" target="dwzExport"
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
				<th width="60">
					流水号
				</th>
				<th width="100" orderField="PLANDATE" class="desc">
					计划时间
				</th>
				<th width="100" orderField="STARTDATE" class="desc">
					开始时间
				</th>
				<th width="100" orderField="ENDDATE" class="desc">
					结束时间
				</th>
				<th width="100" orderField="REALSTARTDATE" class="desc">
					实际开始时间
				</th>
				<th width="100" orderField="REALENDDATE" class="desc">
					实际结束时间
				</th>
				<th width="200" orderField="PLANDESC" class="desc">
					计划描述
				</th>
				<th width="100" orderField="PLANTYPE" class="desc">
					计划类型
				</th>
				<th width="100">
					计划状态
				</th>
				<th width="100">
					制定人
				</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="planId" rel="<s:property value="planId" />">
					<td>
						<input name="ids" value="<s:property value="planId" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="planId" />
					</td>
					<td>
						<s:property value="planDate" />
					</td>
					<td>
						<s:property value="startDate" />
					</td>
					<td>
						<s:property value="endDate" />
					</td>
					<td>
						<s:property value="realStartDate" />
					</td>
					<td>
						<s:property value="realEndDate" />
					</td>
					<td>
						<s:property value="planDesc" />
					</td>
					<td>
						<s:property value="planTypeName" />
					</td>
					<td>
						<s:property value="planStatusName" />
					</td>
					<td>
						<s:property value="userId" />
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



<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
function activiti(obj) { 
	$.ajax( {
		type : 'POST',
		url : '/workflow/process!processActive.do?processDefinitionId=' + obj, 
		success : mydone,
		error : DWZ.ajaxError
	});
}

function suspend(obj) { 
	$.ajax( {
		type : 'POST',
		url : '/workflow/process!processSuspend.do?processDefinitionId=' + obj, 
		success : mydone,
		error : DWZ.ajaxError
	});
}

function mydone(json){ 
	//alert(JSON.stringify(json));
	navTabAjaxDone(json);  
	alertMsg.error(json.message);
	navTab.reloadFlag("lcfb");
}
</script>
<form id="pagerForm" method="post" action="/money/homepageurl!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/homepageurl!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						类型
					</td>
					<td>
						<input name="typeId" class="textInput" size="30" type="text" />
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
						<a class="button" href="/money/homepageurl!beforeQuery.do"
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
				<a class="add" href="/money/homepageurl!beforeAdd.do"
					target="dialog" mask="true" title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/homepageurl!doDelete.do"
					postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit"
					href="/money/homepageurl!beforeUpdate.do?urlId={urlId}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/homepageurl!export.do"
					target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span>
				</a>
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
					流程定义id
				</th>
				<th width="100">
					流程发布id
				</th>
				<th width="100">
					名称
				</th>
				<th width="100">
					KEY
				</th>
				<th width="100">
					版本号
				</th>
				<th width="100">
					XML
				</th>
				<th width="100">
					图片
				</th>
				<th width="100">
					部署时间
				</th>
				<th width="100">
					是否挂起
				</th>
				<th width="100">
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="stu">
				<c:set var="process" value="${stu[0] }" />
				<c:set var="deployment" value="${stu[1] }" />
				<tr target="urlId">
					<td>
						<input type='checkbox' name='deploymentid'
							value="${process.deploymentId }">
					</td>
					<td>
						${process.id }
					</td>
					<td>
						${process.deploymentId }
					</td>
					<td>
						${process.name }
					</td>
					<td>
						${process.key }
					</td>
					<td>
						${process.version }
					</td>
					<td>
						<a target="_blank"
							href='${ctx }/workflow/resource/read?processDefinitionId=${process.id}&resourceType=xml'>${process.resourceName
							}</a>
					</td>
					<td>
						<a target="_blank"
							href='${ctx }/workflow/resource/read?processDefinitionId=${process.id}&resourceType=image'>${process.diagramResourceName
							}</a>
					</td>
					<td>
						${deployment.deploymentTime }
					</td>
					<td>
						${process.suspended} |
						<c:if test="${process.suspended }">
							<a
								href="javascript:activiti('${process.id}')">激活</a>
						</c:if>
						<c:if test="${!process.suspended }">
							<a
								href="javascript:suspend('${process.id}')">挂起</a>
						</c:if>
					</td>
					<td>
						<a
							href='${ctx }/workflow/deployment!processDelete.do?deploymentId=${process.deploymentId}'>删除</a>
						<a
							href='${ctx }/workflow/process!processDelete.do?deploymentId=${process.deploymentId}'>转换为Model</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20"
					<%if ((request.getAttribute("numPerPage") + "").equals("20")) {%>
					selected <%}%>>
					20
				</option>
				<option value="50"
					<%if ((request.getAttribute("numPerPage") + "").equals("50")) {%>
					selected <%}%>>
					50
				</option>
				<option value="100"
					<%if ((request.getAttribute("numPerPage") + "").equals("100")) {%>
					selected <%}%>>
					100
				</option>
				<option value="200"
					<%if ((request.getAttribute("numPerPage") + "").equals("200")) {%>
					selected <%}%>>
					200
				</option>
			</select>
			<span>条，总共${totalCount}条记录</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>


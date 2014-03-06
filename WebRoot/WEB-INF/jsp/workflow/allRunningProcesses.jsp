<%@page import="com.renjie120.workflow.util.ProcessDefinitionCache,org.activiti.engine.RepositoryService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils,org.apache.commons.lang3.ObjectUtils"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
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
						类型</td><td>
								<input name="typeId" class="textInput" size="30" type="text"   />
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
				<a class="add" href="/money/homepageurl!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/homepageurl!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/homepageurl!beforeUpdate.do?urlId={urlId}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/homepageurl!export.do" target="dwzExport"
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
				<th width="100"     >
						流程定义id
				</th> 
				<th width="100"     >
						流程发布id 
				</th> 
				<th width="100"     >
						名称
				</th> 
				<th width="100"     >
						KEY
				</th>
				<th width="100"     >
						版本号
				</th> 
				<th width="100"     >
						XML
				</th> 
				<th width="100"     >
						图片
				</th>  
				<th width="100"     >
						部署时间
				</th>  
				<th width="100"     >
						是否挂起
				</th>  
				<th width="100"     >
						操作
				</th>  
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result }" var="p">
		<c:set var="pdid" value="${p.processDefinitionId }" />
		<c:set var="activityId" value="${p.activityId }" />
		<tr>
			<td>${p.id }</td>
			<td>${p.processInstanceId }</td>
			<td>${p.processDefinitionId }</td>
			<td><a class="trace" href='#' pid="${p.id }" title="点击查看流程图"><%=ProcessDefinitionCache.getActivityName(pageContext.getAttribute("pdid").toString(), ObjectUtils.toString(pageContext.getAttribute("activityId"))) %></a></td>
			<td>${p.suspended }</td>
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


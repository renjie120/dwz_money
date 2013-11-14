<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/myuser!query.do"> 
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" /> 
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="/money/myuser!query.do"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						用户名
						<input type="text" name="userName" />
					</td> 
					<td>
						登陆名
						<input type="text" name="longinId" />
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
				<a class="add" callbackType="closeCurrent"  href="/money/myuser!beforeAdd.do" target="dialog" width="550" title="添加用户信息" height="400"><span>添加</span>
				</a>
			</li>
			<%
			if (isAdmin) {
			%>
			<li>
				<a class="delete" href="/money/myuser!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?" ><span>删除</span> </a>
			</li>
			<li>
				<a class="edit" href="/money/myuser!beforeUpdate.do?userId={userId}" title="修改用户信息"  width="550" height="400"
					target="dialog"><span>修改</span> </a>
			</li> 
			<%} %>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="-138">
		<thead>
			<tr>
				<th width="22">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th> 
				<th width="100">
					用户id
				</th> 
				<th width="100">
					用户名
				</th>
				<th width="100">
					登陆名
				</th>
				<th width="100">
					组织id
				</th> 
				<th width="100">
					邮件
				</th>
				<th width="100">
					电话
				</th>
				<th width="100">
					手机
				</th>
				<th width="100">
					地址
				</th>
				<th width="100">
					排序
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="userId" rel="<s:property value="userId" />">
					<td> 
						<input name="ids" value="<s:property value="userId" />" type="checkbox">
					</td>
					<td>
						<s:property value="userId" />
					</td> 
					<td>
						<s:property value="userName" />
					</td>
					<td>
						<s:property value="loginId" />
					</td> 
					<td>
						<s:property value="orgId" />
					</td> 
					<td>
						<s:property value="email" />
					</td> 
					<td>
						<s:property value="phone" />
					</td> 
					<td>
						<s:property value="mobile" />
					</td> 
					<td>
						<s:property value="address" />
					</td> 
					<td>
						<s:property value="orderId" />
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
				<option value="20"  <%if((request.getAttribute("numPerPage")+"").equals("20")){%> selected<%} %> >
					20
				</option>
				<option value="50"   <%if((request.getAttribute("numPerPage")+"").equals("50")){%> selected<%} %> >
					50
				</option>
				<option value="100"   <%if((request.getAttribute("numPerPage")+"").equals("100")){%> selected<%} %> >
					100
				</option>
				<option value="200"  <%if((request.getAttribute("numPerPage")+"").equals("200")){%> selected<%} %> >
					200
				</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>  
	</div>
</div>

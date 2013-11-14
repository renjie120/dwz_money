<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="/management/user!list.do">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	
	<input type="hidden" name="keywords" value="${param.keywords}"/>
	<input type="hidden" name="userName" value="${param.userName}"/>
	<input type="hidden" name="userStatus" value="${userStatus}"/>
	<input type="hidden" name="user.firstName" value="${user.firstName}"/>
	<input type="hidden" name="user.lastName" value="${user.lastName}"/>
	<input type="hidden" name="user.email" value="${user.email}"/>
	<input type="hidden" name="user.postcode" value="${user.postcode}"/>
</form> 
<form method="post" action="/management/user!list.do" onsubmit="return navTabSearch(this)" >	
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>Keywords：</label>
					<input type="text" name="keywords" value="${param.keywords}"/>
				</li>
				<li>
					<label>Status：</label>
					<select name="userStatus">
						<option value="">All</option>
						<option value="ACTIVE" ${'ACTIVE' eq param.userStatus ? 'selected="selected"' : ''}>ACTIVE</option>
						<option value="INACTIVE" ${'INACTIVE' eq param.userStatus ? 'selected="selected"' : ''}>INACTIVE</option>
					</select>
				</li>
				
				<%-- <li>
					<label>Role：</label>
					<select name="roleId">
						<option value="">All</option>
						<c:forEach var="role" items="${allRoles}">
							<option ${param.roleId eq role.id ? 'selected="selected"' : '' } value="${role.id}">${role.name}</option>
						</c:forEach>
					</select>
				</li> --%>
			</ul>
			<div class="subBar">
				<ul>						
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Search</button></div></div></li>
					<!-- 
					<li><a class="button" href="/management/user!advancedSearch.do?userType=TRAINING" target="dialog" title="Advanced Search"><span>Advanced Search</span></a></li>
				 --></ul>
			</div>
		</div>
	</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="navTab" rel="userNav" href="/management/user!add.do" title="Add new assessors"><span>Add</span></a></li>
			<li><a class="edit" target="navTab" rel="userNav" href="/management/user!edit.do?uid={slt_uid}" ><span>Edit</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="/management/user!delete.do?uid={slt_uid}" title="Are you sure remove?"><span>Remove</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="/management/user!export.do" target="dwzExport" targetType="navTab" title="Are you sure export?"><span>Export</span></a></li>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="100" orderField="USER_NAME" class="${param.orderField eq 'USER_NAME' ? param.orderDirection : ''}">Username</th>
				<th width="100" orderField="FIRST_NAME" class="${param.orderField eq 'FIRST_NAME' ? param.orderDirection : ''}">Name</th>
				<th orderField="EMAIL" class="${param.orderField eq 'EMAIL' ? param.orderDirection : ''}">Email</th>
				<th width="100">Post Code</th>
				<th width="80" orderField="INSERT_DATE" class="${param.orderField eq 'INSERT_DATE' ? param.orderDirection : ''}">Create Date</th>
				<th width="90" orderField="STATUS" class="${param.orderField eq 'STATUS' ? param.orderDirection : ''}">Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${userList}">
			<tr target="slt_uid" rel="${item.id}">
				<td>${item.userName}</td>
				<td>${item.firstName}&nbsp;${item.lastName}</td>
				<td>${item.email}</td>
				<td>${item.postcode}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.insertDate}"/></td>
				<td>${item.status}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
	
</div>



<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">Edit User</h2>
<form method="post" action="/management/user!update.do?navTabId=userLiNav&callbackType=closeCurrent" class="required-validate pageForm" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageFormContent" layoutH="97">
		<input type="hidden" name="uid" value="${user.id}"/>
		<p>
			<label>Username</label><span class="unit">${user.userName}</span>
		</p>
		<p>
			<label>Password</label>
			<input type="text" name="user.password" class="required" size="30" maxlength="20" value="${user.password}"/>
		</p>
		<p>
			<label>Title</label>
			<select name="user.title">
				<c:forEach var="item" items="${titles}">
				<option value="${item}" ${item eq user.title ? 'selected="selected"' : ''}>${item.name}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<label>E-Mail</label>
			<input type="text" name="user.email" class="required email" size="30" value="${user.email}"/>
		</p>
		<p>
			<label>First Name</label>
			<input type="text" name="user.firstName" class="required" size="30" value="${user.firstName}"/>
		</p>
		<p>
			<label>Last Name</label>
			<input type="text" name="user.lastName" class="required" size="30" value="${user.lastName}"/>
		</p>
		<p>
			<label>Post Code</label>
			<input type="text" name="user.postcode" class="required" size="30" value="${user.postcode}"/>
		</p>

		<p>
			<label>Telephone</label>
			<input type="text" name="user.phone" class="required" size="30" value="${user.phone}"/>
		</p>
		<p>
			<label>Date of Birth</label>
			<input type="text" name="user.birthDate" format="yyyy-MM-dd" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${user.birthDate}"/>' class="date required" yearstart="-80" yearend="0" />
			<a class="inputDateButton" href="#">Choose</a>
		</p>
		<p>
			<label>Status</label>
			<select name="user.status">
				<option value="ACTIVE" ${'ACTIVE' eq user.status ? 'selected="selected"' : ''}>ACTIVE</option>
				<option value="INACTIVE" ${'INACTIVE' eq user.status ? 'selected="selected"' : ''}>INACTIVE</option>
			</select>
		</p>
		

		<%-- <div class="divider"></div>
		
		<table class="userRole">
		<tr><th colspan="2">Roles</th></tr>
		<c:forEach var="item" items="${allRoles}" varStatus="s">
		<tr>
			<td width="10">
				<input type="checkbox" value="${item.id}" name="roleIds" id="userRole_${item.id}" ${elutil:contains(item, user.roles) ? "checked" : ""} />
			</td>
			<td><label for="userRole_${item.id}">${item.description}</label></td>
		</tr>
		</c:forEach>
		</table> --%>
		

	</div>
			
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Save</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">Close</button></div></div></li>
		</ul>
	</div>
</form>
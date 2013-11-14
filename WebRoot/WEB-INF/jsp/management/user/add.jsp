<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">Add User</h2>
<form method="post" action="/management/user!insert.do?navTabId=userLiNav&callbackType=closeCurrent" class="required-validate pageForm" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="userType" value="PERSON"/>
	<div class="pageFormContent" layoutH="97">
	
		<p>
			<label>Username</label>
			<input type="text" name="userName" class="required" size="10" minlength="6" maxlength="20"/>
		</p>
		<!--<p>
			<label>Password</label>
			<input type="text" name="user.password" class="required" size="30" maxlength="20" value=""/>
		</p>-->
		<p>
			<label>Title</label>
			<select name="user.title">
				<c:forEach var="item" items="${model.titles}">
				<option value="${item}">${item.name}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<label>E-Mail</label>
			<input type="text" name="user.email" class="required email" size="30" value=""/>
		</p>
		<p>
			<label>First Name</label>
			<input type="text" name="user.firstName" class="required" size="30" value=""/>
		</p>
		<p>
			<label>Last Name</label>
			<input type="text" name="user.lastName" class="required" size="30" value=""/>
		</p>
		<p>
			<label>Post Code</label>
			<input type="text" name="user.postcode" class="required" size="30" value=""/>
		</p>
		<p>
			<label>Telephone</label>
			<input type="text" name="user.phone" class="required" size="30" value=""/>
		</p>
		<p>
			<label>Date of Birth</label>
			<input type="text" name="user.birthDate" class="date required" yearstart="-80" yearend="0" format="yyyy-MM-dd" />
			<a class="inputDateButton" href="#">Choose</a>
		</p>
		<p>
			<label>User Status</label>
			<select name="user.status">
				<option value="ACTIVE">ACTIVE</option>
				<option value="INACTIVE">INACTIVE</option>
			</select>
		</p>
		
		<%-- <div class="divider"></div>
		
		<table class="userRole">
		<tr><th colspan="2">Roles</th></tr>
		<c:forEach var="role" items="${allRoles}" varStatus="s">
		<tr>
			<td  width="10">
				<input type="checkbox" value="${role.id}" id="userRole_${role.id}" name="roleIds" />
			</td>
			<td>
				<label for="userRole_${role.id}">${role.description}</label>
			</td>
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
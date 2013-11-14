<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>

<div class="pageContent">
<form method="post" action="/management/user!update.do?navTabId=main" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="pageFormContent" layoutH="57">
		<input type="hidden" name="uid" value="${contextUser.id}"/>
		<input type="hidden" name="user.status" value="${contextUser.status}"/>
		<input type="hidden" name="user.password" value="${contextUser.password}"/>
		<p>
			<label>Username</label><span class="unit">${contextUser.password}</span>
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
			<input type="text" name="user.email" class="required email" size="30" value="${contextUser.email}"/>
		</p>
		<p>
			<label>First Name</label>
			<input type="text" name="user.firstName" class="required" size="30" value="${contextUser.firstName}"/>
		</p>
		<p>
			<label>Last Name</label>
			<input type="text" name="user.lastName" class="required" size="30" value="${contextUser.lastName}"/>
		</p>
		<p>
			<label>Post Code</label>
			<input type="text" name="user.postcode" class="required" size="30" value="${contextUser.postcode}"/>
		</p>

		<p>
			<label>Telephone</label>
			<input type="text" name="user.phone" class="required" size="30" value="${contextUser.phone}"/>
		</p>
		<p>
			<label>Date of Birth</label>
			<input type="text" name="user.birthDate" format="yyyy-MM-dd" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${contextUser.birthDate}"/>' class="date required" yearstart="-80" yearend="0" readonly="readonly"/>
			<a class="inputDateButton" href="#">Choose</a>
		</p>
		

	</div>
			
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Save</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">Close</button></div></div></li>
		</ul>
	</div>
</form>
</div>
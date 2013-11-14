<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	
	<form method="post" action="/management/user!list.do" class="pageForm" onsubmit="return navTabSearch(this, 'userLiNav')">

		<div class="pageFormContent" layoutH="58">

			<p>
				<label>Username：</label>
				<input type="text" size="25" name="userName"/>
			</p>
			<p>
				<label>First Name：</label>
				<input type="text" size="25" name="user.firstName"/>
			</p>
			<p>
				<label>Last Name：</label>
				<input type="text" size="25" name="user.lastName"/>
			</p>
			<p>
				<label>Email：</label>
				<input type="text" size="25" name="user.email"/>
			</p>
			<p>
				<label>Post Code：</label>
				<input type="text" size="25" name="user.postcode"/>
			</p>
			<p>
				<label>Status：</label>
				<select name="userStatus">
					<option value="">All</option>
					<option value="ACTIVE" ${'ACTIVE' eq param.userStatus ? 'selected="selected"' : ''}>ACTIVE</option>
					<option value="INACTIVE" ${'INACTIVE' eq param.userStatus ? 'selected="selected"' : ''}>INACTIVE</option>
				</select>
			</p>

			<%-- <p>
				<label>Roles：</label>
				<select name="roleId">					
						<option value="">All</option>
					<c:forEach var="role" items="${model.allRoles}">
						<option value="${role.id}">${role.description}</option>
					</c:forEach>
				</select>
			</p> --%>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Search</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">Reset</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>

<div class="pageContent">
<form method="post" action="/management/index!updPwd.do" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="pageFormContent" layouth="57">
		<p>
			<label>
				<s:text name="form.pwd"/>
			</label><input type="password" name="password" class="required"/>
		</p>
		<p>
			<label>
				<s:text name="form.newPwd"/>
			</label><input type="password" name="newPassword" id="newPassword" class="required"/>
		</p>
		<p>
			<label>
				<s:text name="form.confirmPwd"/>
			</label><input type="password" name="rPassword" class="required" equalTo="#newPassword"/>
		</p>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Save</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close" >Close</button></div></div></li>
		</ul>
	</div>
</form>
</div>
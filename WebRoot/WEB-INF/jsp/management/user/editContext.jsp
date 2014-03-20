<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.myuser.MyUser"%>
<%
	MyUser vo = (MyUser) request.getAttribute("vo");
%>
<div class="pageContent">
	<form method="post" action="/money/myuser!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="useId"
			value="<s:property value="vo.useId"/>">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label>
					用户名:
				</label>
				<input name="userName" class="textInput " readonly="true" size="30"
					type="text" value="<s:property value="vo.userName"/>" />
			</div>
			<div class="unit">
				<label>
					登陆号:
				</label>
				<input name="loginId" class="textInput " readonly="true" size="30"
					type="text" value="<s:property value="vo.loginId"/>" />
			</div>
			<div class="unit">
				<label>
					邮件:
				</label>
				<input name="email" class="textInput  email" size="30" type="text"
					value="<s:property value="vo.email"/>" />
			</div>
			<div class="unit">
				<label>
					座机:
				</label>
				<input name="phone" class="textInput  " size="30" type="text"
					value="<s:property value="vo.phone"/>" />
			</div>
			<div class="unit">
				<label>
					手机:
				</label>
				<input name="mobile" class="textInput  " size="30" type="text"
					value="<s:property value="vo.mobile"/>" />
			</div>
			<div class="unit">
				<label>
					地址:
				</label>
				<input name="address" class="textInput  " size="30" type="text"
					value="<s:property value="vo.address"/>" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								保存
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>


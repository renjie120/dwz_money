<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="/money/myuser!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);"> 
		<div class="pageFormContent" layoutH="57">
		<input name="userId" type="hidden" class="textInput"
			value="<s:property value="userVo.userId"/>" />
			<div class="unit">
				<label>
					用户名：
				</label>
				<input name="userName" type="text" size="30" class="textInput required"
					value="<s:property value="userVo.userName"/>" />
			</div>
			<input name="pass" id="pass" type="hidden" 
					value="<s:property value="userVo.pass"/>" /> 
			<div class="unit">
				<label>
					登陆名：
				</label>
				<input name="loginId" type="text" class="required" size="30"
					value="<s:property value="userVo.loginId"/>" />
			</div>
			<div class="unit">
				<label>
					组织机构：
				</label>
				<input name="orgId" type="text" class="textInput" size="30"
					value="<s:property value="userVo.orgId"/>" />
			</div>
			<div class="unit">
				<label>
					邮件：
				</label>
				<input name="email" type="text" class="email" size="30"
					value="<s:property value="userVo.email"/>" />
			</div>
			<div class="unit">
				<label>
					电话：
				</label>
				<input name="phone" type="text" class="phone" size="30"
					value="<s:property value="userVo.phone"/>" />
			</div>
			<div class="unit">
				<label>
					手机：
				</label>
				<input type="text" name="mobile" class="number" size="30" maxlength="11"
					value="<s:property value="userVo.mobile"/>" />
			</div>
			<div class="unit">
				<label>
					地址：
				</label>
				<input type="text" name="address" size="30"
					value="<s:property value="userVo.address"/>" />
			</div>
			<div class="unit">
				<label>
					排序id：
				</label>
				<input type="text" name="orderId" class="number" size="30"
					value="<s:property value="userVo.orderId"/>" />
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

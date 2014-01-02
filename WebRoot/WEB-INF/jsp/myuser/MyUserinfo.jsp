
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/myuser!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							用户名:
						</label>
							<input name="userName" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							密码:
						</label>
							<input name="password" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							登陆号:
						</label>
							<input name="loginId" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							组织机构:
						</label>
							<input name="orgId" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							邮件:
						</label>
							<input name="email" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							座机:
						</label>
							<input name="phone" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							手机:
						</label>
							<input name="mobile" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							用户类型:
						</label>
							<input name="userType" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<input name="address" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							排序号:
						</label>
							<input name="orderId" class="textInput " size="30" type="text"   />
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
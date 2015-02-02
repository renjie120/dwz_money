
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/loginuser!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							用户姓名:
						</label>
							<input name="userName" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							登录名称 :
						</label>
							<input name="userId" class="alphanumeric required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							所属类别 :
						</label>
							<my:newselect tagName="userType"  paraType="aiduyonghu" width="100"  />
					</div>
					 <div class="unit">
						<label>
							所属单位 :
						</label>
							<input name="userUnit" class="textInput required" type="text"   />
					</div>
					 <div class="unit">
						<label>
							用户密码:
						</label>
							<input name="userPass" id="w_userPass" class="textInput required"  type="password" minlength="6" maxlength="20"  />
					</div>
					 <div class="unit">
						<label>
							确认密码:
						</label>
							<input name="userPass2" class="textInput required" equalto="#w_userPass" type="password"   />
					</div>
					 <div class="unit">
						<label>
							用户状态 :
						</label>
							<my:newselect tagName="userStatus"  paraType="yesorno_status" width="100"  />
					</div>
					 <div class="unit">
						<label>
							联系电话:
						</label>
							<input name="userPhone" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="userEmail"  class="email "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea class="" name="userAddress"  rows="3" cols="40"></textarea>
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

<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.LoginUser.LoginUser"%>
<% 
	LoginUser vo = (LoginUser) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/loginuser!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							用户姓名:
						</label>
							<input name="userName" readOnly="true"  class="textInput  required"    type="text"  value="<s:property value="vo.userName"/>" />
					</div>
					 <div class="unit">
						<label>
							登录名称 :
						</label>
							<input name="userId"   class="textInput  required"    type="text"  value="<s:property value="vo.userId"/>" />
					</div>
					 <div class="unit">
						<label>
							所属类别 :
						</label>
							<my:newselect tagName="userType"  paraType="aiduyonghu" width="100"  selectedValue="<%=vo.getUserType() %>"/>									
					</div>
					 <div class="unit">
						<label>
							所属单位 :
						</label>
							<input name="userUnit"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.userUnit"/>" />
					</div> 
					 <div class="unit">
						<label>
							用户密码:
						</label>
							<input name="userPass"  id="w_userPass" class="textInput  "    type="password"  value="" /> <label style="color:red">不填则不修改密码</label>
					</div>
					 <div class="unit">
						<label>
							密码确认:
						</label>
							<input name="userPass2"   class="textInput  "    type="password"  value="" equalto="#w_userPass" />
					</div>
					 <div class="unit">
						<label>
							用户状态 :
						</label>
							<my:newselect tagName="userStatus"  paraType="yesorno_status" width="100"  selectedValue="<%=vo.getUserStatus() %>"/>									
					</div>
					 <div class="unit">
						<label>
							联系电话:
						</label>
							<input name="userPhone"   class="textInput  "    type="text"  value="<s:property value="vo.userPhone"/>" />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="userEmail"   class="email  "    type="text"  value="<s:property value="vo.userEmail"/>" />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea     class="" name="userAddress" cols="30" rows="2"><s:property value="vo.userAddress"/></textarea>
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
							<input name="createUser"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createUserName"/>" />
					</div>
					 <div class="unit">
						<label>
							创建时间:
						</label>
							<input name="createTime"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createTime"/>" />
					</div>
					 <div class="unit">
						<label>
							更新用户:
						</label>
							<input name="updateUser"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateUserName"/>" />
					</div>
					 <div class="unit">
						<label>
							更新时间:
						</label>
							<input name="updateTime"   class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateTime"/>" />
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


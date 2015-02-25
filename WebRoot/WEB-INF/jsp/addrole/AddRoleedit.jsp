
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.addRole2.AddRole"%>
<% 
	AddRole vo = (AddRole) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/addrole!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							角色类型:
						</label>
							<my:newselect tagName="roleType"  paraType="roletype" width="100"  selectedValue="<%=vo.getRoleType() %>"/>									
					</div>
					 <div class="unit">
						<label>
							授权对象 :
						</label>
							<input name="roleKey" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.roleKey"/>" />
					</div>
					 <div class="unit">
						<label>
							角色 :
						</label>
							<textarea  size="30"   class="" name="roleIds" cols="30" rows="2"><s:property value="vo.roleIds"/></textarea>
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
							<input name="createUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createUser"/>" />
					</div>
					 <div class="unit">
						<label>
							创建时间:
						</label>
							<input name="createTime" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createTime"/>" />
					</div>
					 <div class="unit">
						<label>
							更新用户:
						</label>
							<input name="updateUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateUser"/>" />
					</div>
					 <div class="unit">
						<label>
							更新时间:
						</label>
							<input name="updateTime" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateTime"/>" />
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


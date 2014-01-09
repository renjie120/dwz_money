
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.rolemanager.Role"%>
<% 
	Role vo = (Role) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/role!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="roleId"
			value="<s:property value="vo.roleId"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							角色描述 :
						</label>
									<input name="roleDesc" class="textInput  "  size="30" type="text"  value="<s:property value="vo.roleDesc"/>" />
					</div>
					 <div class="unit">
						<label>
							角色名称:
						</label>
									<input name="roleName" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.roleName"/>" />
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


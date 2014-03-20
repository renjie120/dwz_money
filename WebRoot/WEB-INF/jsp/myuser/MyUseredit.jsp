
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>  
<script src="/js/treeCombox.js" type="text/javascript" /> 
<script type="text/javascript">
	/**
	 * 在弹出框里面点击关闭按钮，拖放按钮触发本事件.
	 */
	function myOperation() {
		$('#orgName').hideMenu();//隐藏弹出来的树形下拉菜单.
	}
	$(document).ready(function() {
		var content = {
			action : '/money/tree!getOrgTree.do',
			nameInput : 'orgName',
			height : '200px',
			idInput : 'orgId',
			treeId : "orgInUserTree"
		};
		$('#orgName').treeCombox(content);
	});
</script>
<%@ page import="money.myuser.MyUser"%>
<%
	MyUser vo = (MyUser) request.getAttribute("vo");
	String userType = vo.getUserType()+""; 
%>
<div class="pageContent">
	<form method="post" action="/money/myuser!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="useId"
			value="<s:property value="vo.useId"/>">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label> 用户名: </label> <input name="userName"
					class="textInput  required" size="30" type="text"
					value="<s:property value="vo.userName"/>" />
			</div>
			<div class="unit">
				<label> 登陆号: </label> <input name="loginId"
					class="textInput  required" size="30" type="text"
					value="<s:property value="vo.loginId"/>" />
			</div>
			<div class="unit">
				<label> 组织机构: </label>  
				<input name="orgId" id="orgId" type="hidden" value='<s:property value="vo.orgId"/>'  /> 
			 	<input name="orgName" size="30" id="orgName" type="text" readonly="true"  class="required"
					onclick="showOrgMenu(); return false;" value='<s:property value="vo.orgName"/>' />  
			</div>
			<div class="unit">
				<label> 邮件: </label> <input name="email" class="textInput  "
					size="30" type="text" value="<s:property value="vo.email"/>" />
			</div>
			<div class="unit">
				<label> 座机: </label> <input name="phone" class="textInput  "
					size="30" type="text" value="<s:property value="vo.phone"/>" />
			</div>
			<div class="unit">
				<label> 手机: </label> <input name="mobile" class="textInput  "
					size="30" type="text" value="<s:property value="vo.mobile"/>" />
			</div>
			<div class="unit">
				<label> 用户类型: </label>  
					<my:newselect tagName="userType"  paraType="usertype" 
					selectedValue="<%=userType%>" />
			</div>
			<div class="unit">
				<label> 地址: </label> <input name="address" class="textInput  "
					size="30" type="text" value="<s:property value="vo.address"/>" />
			</div>
			<div class="unit">
				<label> 排序号: </label> <input name="orderId" class="textInput  "
					size="30" type="text" value="<s:property value="vo.orderId"/>" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>


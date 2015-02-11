
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.bindFamily.BindFamily"%>
<% 
	BindFamily vo = (BindFamily) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/bindfamily!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							主用户号:
						</label>
							<input name="iuserNo" size="30" readOnly="true" class="textInput  required"    type="text"  value="<s:property value="vo.iuserNo"/>" />
					</div>
					 <div class="unit">
						<label>
							绑定人:
						</label>
							<input name="bindName" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.bindName"/>" />
					</div>
					 <div class="unit">
						<label>
							关系:
						</label>
							<my:newselect tagName="relation"  paraType="bindusertype" width="100"  selectedValue="<%=vo.getRelation() %>"/>									
					</div>
					 <div class="unit">
						<label>
							身份证:
						</label>
							<input name="cardNo" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.cardNo"/>" />
					</div>
					 <div class="unit">
						<label>
							手机号:
						</label>
							<input name="phone" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.phone"/>" />
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


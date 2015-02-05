
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.UserUpdateLogger.UserUpdateLogger"%>
<% 
	UserUpdateLogger vo = (UserUpdateLogger) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/userupdatelogger!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							用户 :
						</label>
							<input name="userId" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.userId"/>" />
					</div>
					 <div class="unit">
						<label>
							用户状态:
						</label>
							<my:newselect tagName="state"  paraType="toubaouser_status" width="100"  selectedValue="<%=vo.getState() %>"/>									
					</div>
					 <div class="unit">
						<label>
							操作原因:
						</label>
							<textarea  size="30"   class="" name="logDetail" cols="30" rows="2"><s:property value="vo.logDetail"/></textarea>
					</div>
					 <div class="unit">
						<label>
							备用字段1:
						</label>
							<input name="arg1" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.arg1"/>" />
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


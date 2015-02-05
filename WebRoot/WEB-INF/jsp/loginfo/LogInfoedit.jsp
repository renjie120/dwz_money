
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.loginfo.LogInfo"%>
<% 
	LogInfo vo = (LogInfo) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/loginfo!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							用户id:
						</label>
							<input name="operUser" size="30"  class="number  "    type="text"  value="<s:property value="vo.operUser"/>" />
					</div>
					 <div class="unit">
						<label>
							用户:
						</label>
							<input name="operUserName" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.operUserName"/>" />
					</div>
					 <div class="unit">
						<label>
							时间 :
						</label>
							<input name="operTime" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.operTime"/>" />
					</div>
					 <div class="unit">
						<label>
							操作类型:
						</label>
							<input name="operType" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.operType"/>" />
					</div>
					 <div class="unit">
						<label>
							ip地址:
						</label>
							<input name="operIp" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.operIp"/>" />
					</div>
					 <div class="unit">
						<label>
							操作地址:
						</label>
							<input name="operUrl" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.operUrl"/>" />
					</div>
					 <div class="unit">
						<label>
							修改前:
						</label>
							<input name="operBefore" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.operBefore"/>" />
					</div>
					 <div class="unit">
						<label>
							修改后:
						</label>
							<input name="operAfter" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.operAfter"/>" />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<input name="operDesc" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.operDesc"/>" />
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


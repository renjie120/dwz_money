
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.paramtype.ParamType"%>
<% 
	ParamType vo = (ParamType) request.getAttribute("vo");  
%>
<div class="pageContent">
	<form method="post" action="/money/paramtype!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="paramTypeId"
			value="<s:property value="vo.paramTypeId"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							参数类型:
						</label>
							<input name="paramTypeName" class="textInput  required" size="30" type="text"  value="<s:property value="vo.paramTypeName"/>" />
					</div>
					 <div class="unit">
						<label>
							排序号:
						</label>
							<input name="orderId" class="textInput  " size="30" type="text"  value="<s:property value="vo.orderId"/>" />
					</div>
					 <div class="unit">
						<label>
							参数类型编码:
						</label>
							<input name="code" class="textInput  " size="30" type="text"  value="<s:property value="vo.code"/>" />
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


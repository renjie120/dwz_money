<#include "/com.renjie120.codegenerate.common.ftl">
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="${model.packageName}.${model.className}"%>
<% 
	${model.className} vo = (${model.className}) request.getAttribute("${vo}"); 
%>
<div class="pageContent">
	<form method="post" action="/money/${nm}!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="{model.keyName}"
			value="<s:property value="${vo}.{model.keyName}"/>">
		<div class="pageFormContent" layoutH="57">
			 <#list model.attributes as attr> 
			 <div class="unit">
				<label>
					${attr.desc}:
				</label>
				<input name="${attr.name}" class="textInput  required " size="30"
					type="text" value="<s:property value="${vo}.${attr.name}"/>" />
			</div>
			</#list> 
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


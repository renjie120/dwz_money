
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.param.Param"%>
<% 
	Param vo = (Param) request.getAttribute("paramVo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/param!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="paramId"
			value="<s:property value="paramVo.paramId"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							参数类型:
						</label>
						<%String temp = vo.getParamType()+""; %>
						<my:newselect tagName="paramType" idColumn="parameter_type_id" nameColumn="parameter_type_name" tableName="parameter_type" width="100"
					allSelected="true" selectedValue="<%=temp %>"/>  
					</div>
					 <div class="unit">
						<label>
							参数描述:
						</label>
							<input name="paramName" class="textInput  " size="30" type="text"  value="<s:property value="paramVo.paramName"/>" />
					</div>
					 <div class="unit">
						<label>
							参数值:
						</label>
							<input name="paramValue" class="textInput  " size="30" type="text"  value="<s:property value="paramVo.paramValue"/>" />
					</div>
					 <div class="unit">
						<label>
							用户自定义值:
						</label>
							<input name="usevalue" class="textInput  " size="30" type="text"  value="<s:property value="paramVo.usevalue"/>" />
					</div>
					 <div class="unit">
						<label>
							排序号:
						</label>
							<input name="orderId" class="textInput  " size="30" type="text"  value="<s:property value="paramVo.orderId"/>" />
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


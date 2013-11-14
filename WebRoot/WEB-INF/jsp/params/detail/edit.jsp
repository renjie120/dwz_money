<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.params.Param"%>
<%
	Collection list = (Collection) ActionContext.getContext().get("allType");
	Param vo = (Param) request.getAttribute("paramVo");
	String typeId = vo.getParameterType() + "";
	String useValue = vo.getUseValue() + "";
%>
<div class="pageContent">
	<form method="post" action="/money/param!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="parameterID"
			value="<s:property value="paramVo.parameterID"/>">
		<div class="pageFormContent" layoutH="56" width='300px'>
			<div class="unit">
				<label>
					参数类型：
				</label>
				<my:newselect tagName="parameterType" selections="<%=list%>"
					defaultValue="<%=typeId%>" />
			</div> 
			<div class="unit">
				<label>
					参数描述：
				</label>
				<input type="text" name="parameterName" maxlengh="20"
					class="required"
					value="<s:property value="paramVo.parameterName"/>">
			</div>
			<div class="unit">
				<label>
					参数值：
				</label>
				<input type="text" name="parameterValue" maxlengh="20" 
					value="<s:property value="paramVo.parameterValue"/>">
			</div>
			<div class="unit">
				<label>
					是否使用参数值：
				</label>
				<my:newselect tagName="useValue"  listvalues="0,1" 
					listtexts="否,是" selectedValue="<%=useValue %>"/>  
			</div>
			<div class="unit">
				<label>
					排序列：
				</label>
				<input type="text" name="orderId" class="textInput"
					value="<s:property value="paramVo.orderId"/>">
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

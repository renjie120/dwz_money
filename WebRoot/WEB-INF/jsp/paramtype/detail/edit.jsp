<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/paramType!doUpdate.do" id="editparamtypeinfo"
		class="pageForm required-validate" onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="parameterTypeId"
			value="<s:property value="paramTypeVo.parameterTypeId"/>">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>
					参数描述：
				</label>
				<input name="parameterTypeName" type="text" class="textInput"
					value="<s:property value="paramTypeVo.parameterTypeName"/>" />
			</div>
			<div class="unit">
				<label>
					参数代码：
				</label>
				<input name="code" type="text" class="textInput"
					value="<s:property value="paramTypeVo.code"/>" />
			</div>
			<div class="unit">
				<label>
					排序列：
				</label>
				<input type="text" name="orderId" class="textInput"
					value="<s:property value="paramTypeVo.orderId"/>">
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" >
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


<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/param!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							参数类型:
						</label>
						<my:newselect tagName="paramType" idColumn="parameter_type_id" nameColumn="parameter_type_name" tableName="parameter_type" width="100"
					allSelected="true" /> 
					</div>
					 <div class="unit">
						<label>
							参数描述:
						</label>
							<input name="paramName" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							参数值:
						</label>
							<input name="paramValue" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							用户自定义值:
						</label>
							<input name="usevalue" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							排序号:
						</label>
							<input name="orderId" class="textInput " size="30" type="text"   />
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
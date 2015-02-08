
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/userupdatelogger!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							用户名 : 
						</label>
							<input name="userId" class="textInput required" size="30" type="hidden"  value="${userId }" />
							<input name="userName" class="textInput required" size="30" type="text"  readOnly="true" value="${userName }" />
					</div>
					 <div class="unit">
						<label>
							更新状态: 
						</label>
						<input name="state" class="textInput required" size="30" type="hidden"  value="${state }" />
							<input name="stateName" class="textInput required" size="30" type="text"  readOnly="true" value="${stateName }" /> 
					</div>
					 <div class="unit">
						<label>
							操作原因: 
						</label>
							<textarea class="required" name="logDetail" size="30" rows="3" cols="40"></textarea>
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
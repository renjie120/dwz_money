<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="/money/org!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="orgId"
			value="<s:property value="orgVo.orgId"/>">
		<div class="pageFormContent" layoutH="55">
			<div class="unit">
				<label>
					组织名称:
				</label>
				<input name="orgName" class="textInput  required " size="30"
					type="text" value="<s:property value="orgVo.orgName"/>" />
			</div>
			<div class="unit">
				<label>
					组织代码:
				</label>
				<input name="orderCode" class="textInput  " size="30" type="text"
					value="<s:property value="orgVo.orderCode"/>" />
			</div>
			<div class="unit">
				<label>
					父组织:
				</label>
				<input name="parentOrg" class="textInput  " size="30" type="text"
					value="<s:property value="orgVo.parentOrg"/>" />
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


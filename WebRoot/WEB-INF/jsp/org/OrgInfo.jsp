<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script src="/js/org.js" type="text/javascript" />
<div class="pageContent">
	<form method="post" action="/money/org!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="55">
			<div class="unit">
				<label>
					组织名称:
				</label>
				<input name="orgName" class="textInput  required " size="30"
					type="text" />
			</div>
			<div class="unit">
				<label>
					组织代码:
				</label>
				<input name="orderCode" class="textInput  " size="30" type="text" />
			</div>
			<div class="unit">
				<label>
					父组织:
				</label>
				 <input name="parentOrg" id="parentOrg" type="hidden"  /> 
			 	<input name="parentName" size="30" id="parentName" type="text" readonly="true"  class="required"
					onclick="showOrgMenu(); return false;" /> 
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



<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/addroledetail!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							角色类型: 
						</label>
							<my:newselect tagName="roleType"  paraType="roletype" width="100"  />
					</div>
					 <div class="unit">
						<label>
							授权对象 : 
						</label>
							<input name="roleKey" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							角色 : 
						</label>
							<input name="roleId" class="textInput " size="30" type="text"   />
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
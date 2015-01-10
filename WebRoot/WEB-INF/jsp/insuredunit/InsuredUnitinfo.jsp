
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/insuredunit!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							编号:
						</label>
							<input name="unitCode" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							投保单位 :
						</label>
							<input name="unitName" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人:
						</label>
							<input name="contactName" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							手机:
						</label>
							<input name="contactMobile"  class="digits "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="contactEmail"  class="email "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							上级单位:
						</label>
							<input name="unitParentId" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							状态:
						</label>
							<input name="unitState" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea class="" name="unitAddress"  rows="4" cols="40"></textarea>
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<textarea class="" name="unitRemark"  rows="4" cols="40"></textarea>
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
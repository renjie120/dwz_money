
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
									<input name="unitCode" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							投保单位 :
						</label>
									<input name="unitName" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人:
						</label>
									<input name="contactName" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							手机:
						</label>
									<input name="contactMobile" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
									<input name="contactEmail" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							上级单位:
						</label>
									<input name="unitParentId" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							状态:
						</label>
									<input name="unitState" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
								<textarea class="" name="unitAddress" cols="30" rows="2"></textarea>
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
								<textarea class="" name="unitRemark" cols="30" rows="2"></textarea>
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
									<input name="createUser" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							创建时间:
						</label>
									<input name="createTime" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							更新用户:
						</label>
									<input name="updateUser" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							更新时间:
						</label>
									<input name="updateTime" class="textInput " size="30" type="text"   />
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
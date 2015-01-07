
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/loginfo!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							用户id:
						</label>
									<input name="operUser" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							用户:
						</label>
									<input name="operUserName" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							时间 :
						</label>
									<input name="operTime" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							操作类型:
						</label>
									<input name="operType" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							ip地址:
						</label>
									<input name="operIp" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							操作地址:
						</label>
									<input name="operUrl" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							修改前:
						</label>
									<input name="operBefore" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							修改后:
						</label>
									<input name="operAfter" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
								<textarea class="" name="operDesc" cols="30" rows="2"></textarea>
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
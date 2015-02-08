
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/uploadfile!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							业务关联id : 
						</label>
							<input name="businessId" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							文件类型: 
						</label>
							<my:newselect tagName="fileType"  paraType="file_type" width="100"  />
					</div>
					 <div class="unit">
						<label>
							是否有效: 
						</label>
							<my:newselect tagName="isExist"  paraType="yesorno" width="100"  />
					</div>
					 <div class="unit">
						<label>
							文件名: 
						</label>
							<input name="fileName" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							实际文件名: 
						</label>
							<input name="realFileName" class="textInput " size="30" type="text"   />
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
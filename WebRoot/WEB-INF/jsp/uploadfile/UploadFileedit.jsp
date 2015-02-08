
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.uploadFile.UploadFile"%>
<% 
	UploadFile vo = (UploadFile) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/uploadfile!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							业务关联id :
						</label>
							<input name="businessId" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.businessId"/>" />
					</div>
					 <div class="unit">
						<label>
							文件类型:
						</label>
							<my:newselect tagName="fileType"  paraType="file_type" width="100"  selectedValue="<%=vo.getFileType() %>"/>									
					</div>
					 <div class="unit">
						<label>
							是否有效:
						</label>
							<my:newselect tagName="isExist"  paraType="yesorno" width="100"  selectedValue="<%=vo.getIsExist() %>"/>									
					</div>
					 <div class="unit">
						<label>
							文件名:
						</label>
							<input name="fileName" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.fileName"/>" />
					</div>
					 <div class="unit">
						<label>
							实际文件名:
						</label>
							<input name="realFileName" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.realFileName"/>" />
					</div>
					 <div class="unit">
						<label>
							文件大小:
						</label>
							<input name="fileSize" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.fileSize"/>" />
					</div>
					 <div class="unit">
						<label>
							上传用户:
						</label>
							<input name="createUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createUser"/>" />
					</div>
					 <div class="unit">
						<label>
							上传时间:
						</label>
							<input name="createTime" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createTime"/>" />
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


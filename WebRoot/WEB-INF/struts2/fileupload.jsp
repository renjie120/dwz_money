<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp" %>
<form  enctype="multipart/form-data" method="post" 
	action="/upload/test!saveFileToDb.do">
	上传文件：<input type="file" name="upload"/>
	<input name="dd" type="submit" value="提交"/>
</form>
 
	<!-- 
<h2 class="contentTitle">uploadify多文件上传</h2>
<script src="/js/money/upload.js" type="text/javascript" />
<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 60px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>

<div class="pageContent" style="margin: 0 10px" >
	
 input id="testFileInput" type="file" name="image" 
		uploaderOption="{
			swf:'/uploadify/scripts/uploadify.swf',
			uploader:'/upload/test!saveFile.do', 
			 formData : { 'session': '<%=session.getId()%>'}, 
			buttonText:'请选择文件',
			fileSizeLimit:'200KB',
			fileTypeDesc:'*.jpg;*.jpeg;*.gif;*.png;',
			fileTypeExts:'*.jpg;*.jpeg;*.gif;*.png;',
			auto:true,
			multi:true,
			onUploadSuccess:uploadifySuccess,
			onQueueComplete:uploadifyQueueComplete
		}"
	/> 
	<div class="divider"></div

	<input id="testFileInput2" type="file" name="upload" 
		uploaderOption="{
			swf:'/uploadify/scripts/uploadify.swf',
			uploader:'http://localhost:9999/upload/test!saveFile.do', 
			queueID:'fileQueue',
			buttonImage:'uploadify/img/add.jpg',
			buttonClass:'my-uploadify-button', 
			 fileTypeExts  : '*.gif; *.jpg; *.png',
			auto:true,//自动上传文件
			sizeLimit:2000000,//文件上传的大小限制，单位是字节
			width:102 
		}"
	/>
	
	<div id="fileQueue" class="fileQueue"></div> -->
	<!--   input type="image" src="uploadify/img/upload.jpg" onclick="$('#testFileInput2').uploadify('upload', '*');"/>
	<input type="image" src="uploadify/img/cancel.jpg" onclick="$('#testFileInput2').uploadify('cancel', '*');"/>  
	<div class="divider"></div-->  

</div>
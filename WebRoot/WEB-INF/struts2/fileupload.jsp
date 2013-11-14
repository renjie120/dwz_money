<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp" %>
<form  enctype="multipart/form-data" method="post" 
	action="/upload/test!saveFile.do">
	上传文件：<input type="file" name="upload"/><br>
	文件标题：<input name="arg"/>
	<input name="dd" type="submit" value="提交"/>
</form>
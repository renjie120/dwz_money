<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/include.inc.jsp" %>
<form  enctype="multipart/form-data" method="post" 
	action="/upload/test2!saveFile.do">
	文件标题：<input name="arg"/><br>
	上传文件：<input type="file" name="upload"/><br> 
	上传文件：<input type="file" name="upload"/><br> 
	上传文件：<input type="file" name="upload"/><br> 
	<input name="dd" type="submit" value="提交"/>
</form>
<a href='/upload/down.do'>下载文件</a><br>
<a href='/upload/down2.do'>下载中文文件</a><br>
<a href='/upload/down3.do'>下载word文件</a><br>
<a href='/upload/down3.do?inputPath=/WEB-INF/download/sss.png&fileName=test.png'>下载任意文件</a><br>
<a href='/upload/down3.do?inputPath=/WEB-INF/download/sss.png&fileName=test.png'>下载压缩文件</a><br>
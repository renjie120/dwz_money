<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp" %>
<%@ page import="java.io.*" %>
<html>
<title>上传成功</title>
标题：<s:property value="arg"/><br>
文件为：<img src="<s:property value="'/upload/'+uploadFileName"/>"/>
文件为：<img src="<s:property value="'/upload/test!getFile.do'fileId"/>"/>
</html>
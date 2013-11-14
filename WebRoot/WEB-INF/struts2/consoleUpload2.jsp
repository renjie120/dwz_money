<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/include.inc.jsp" %>
<%@ page import="java.io.*" %>
<html>
<title>上传成功</title>
标题：<s:property value="arg"/><br>
<%
String[] uploadFileName = (String[])request.getAttribute("uploadFileName");
for(int i=0;i<uploadFileName.length;i++) %>
	文件为：<img src="/upload/<%=uploadFileName[i] %>"/>
</html>
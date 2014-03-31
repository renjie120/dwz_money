<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp" %>
<%@ page import="java.io.*" %>
<html>
<title>上传成功</title>
标题：<s:property value="arg"/><br>
文件为：<img src="<s:property value="'/upload/'+uploadFileName"/>"/>
文件为：<img src="http://localhost:8083/upload/test!getFile.do?fileId=d6fc0ccd-e8f0-4d47-8f7e-e59f2e209b2a"/>
</html>
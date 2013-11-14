<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="com.jspsmart.upload.*"%>
<%
	String pageNum = request.getParameter("pId"); 
	if (pageNum == null)
		pageNum = "1";
	SmartUpload su = new SmartUpload();
	su.initialize(pageContext);
	su.setContentDisposition(null);//确保不使用浏览器打开文件，而是具体相关程序
	java.io.File f = new java.io.File("MyPic (" + pageNum + ").jpg");
	System.out.println(f.getAbsolutePath());
	su.downloadFile(request.getRealPath("files/MyPic (" + pageNum
			+ ").jpg"));
%>
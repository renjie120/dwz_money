<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">  
  </head>
  
  <body>
     <a href='gridTree/MyJsp.jsp'>前台表格树</a><br>
     <a href='gridTree/MyJspForJava.jsp'>后台表格树</a><br>
     <a href='gridTree/MyLazyTree.jsp'>懒加载树示例1</a><br>
     <a href='javascript:alert("暂时不好用!")'>懒加载树示例2(lazyPage:true)</a><br>
     <a href='javascript:alert("暂时不好用!")'>懒加载树示例3(lazyMorePage:true)</a><br> 
	 <a href='http://blog.sina.com.cn/s/blog_3efe6ef10100unl6.html'>bug反馈</a><br>
	 <a href='http://lishuiqing1987.j38.80data.com/demo/demo1.rar'>1.0版本demo下载</a><br>
	 <a href='http://lishuiqing1987.j38.80data.com/demo/demo2.rar'>2.0版本demo下载</a><br>
	 <a href='http://code.google.com/p/gridtree-jquery-plugin-demo/'>google code</a><br>
  </body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<script type="text/javascript">
  var appPath = "<%=basePath%>";
</script>
<link rel="stylesheet" type="text/css" href="core.css">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="GridTree.css">
<script language="javascript" src="jquery.min.js"></script>
<script language="javascript" src="hashMap.js"></script>
<script language="javascript" src="GridTree.All.js"></script>
</html>


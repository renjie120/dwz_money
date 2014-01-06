
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript">
  var appPath = "<%=basePath%>";
  function gridRessetSize(){
  	alert(1);
  }
$('#container').resize(function(){
alert(123);
});
</script>
<div class="pageContent" style="overflow:hidden"> 
	 <div id="newtableTree"></div>
</div>
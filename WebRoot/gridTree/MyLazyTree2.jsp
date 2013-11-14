
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<BODY>
<jsp:include page="/gridTree/commonHead.jsp"></jsp:include>
<!-- 以下是js处理页面 -->
<script language="javascript" src="myTree4.js"></script>
<SCRIPT language="javascript" src="myDemo.js"></script>
  <!-- 以下是表格树所在的div-->
  <div>
    <div style="float:left;height:350px;">
      <jsp:include page="/gridTree/MyAPIDemo.jsp"></jsp:include>
    </div>
    <div style="width:500px;float:right;margin-right:300px;">
      <div id='newtableTree'>
      </div>
    </div>
    <div style="float:bottom;clear:left;width:800px;">
      <jsp:include page="/gridTree/common.jsp"></jsp:include>
    </div>
  </div>
</BODY>
</html>


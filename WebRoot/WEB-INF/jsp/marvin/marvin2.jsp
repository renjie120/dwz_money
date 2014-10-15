<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"
   + request.getServerName() + ":" + request.getServerPort()
   + path + "/";
%>
 
<html>
 <head>
  <base href="<%=basePath%>"> 
  <script src="marvinjs-14.9.15-all/js/lib/jquery-1.9.1.min.js">
</script>
  <script src="marvinjs-14.9.15-all/js/promise-0.1.1.min.js">
</script>
  <script src="marvinjs-14.9.15-all/js/marvinjslauncher.js">
</script>
  <script> 
var marvinSketcherInstance;

var marvin;
$(document).ready(function handleDocumentReady(e) {
 MarvinJSUtil.getEditor("#sketch").then(function(sketcherInstance) {
  marvinSketcherInstance = sketcherInstance;
  // 支持监听的事件："molchange": ,"selectionchange","undo","redo"
  marvinSketcherInstance.on('molchange',function(){
   console.log('changed！');
  });
 }, function(error) {
 });

 MarvinJSUtil.getPackage("#sketch").then(function(marvinPackage) {
  marvin = marvinPackage;
 }, function(error) {
  alert("Marvin package is not available:" + error);
 });
});
 
function save(){ 
	 marvinSketcherInstance.exportStructure('mrv').then(function(source) {
		$('#source').val(source); 
		//window.close();
	  }, function(error) {
	    alert("Molecule export failed:"+error);
	  });
}
</script>
 </head>
 <body>		
 <form action='/money/map!saveSource.do'>
	<input type='hidden' name='id' id='id' value="${id}">
	<input type='hidden' name='source' id='source'  >
   <iframe src="marvinjs-14.9.15-all/editor.html" id="sketch"
    style="overflow: hidden; min-width: 400px; min-height: 350px; border: 1px solid darkgray;"></iframe> 
	<button onclick='save()'>保存</button>
  </form>
  
 </body>
</html>

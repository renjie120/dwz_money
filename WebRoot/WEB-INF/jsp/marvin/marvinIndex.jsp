<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"
   + request.getServerName() + ":" + request.getServerPort()
   + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
  <base href="<%=basePath%>">
  <title>Marvin JS Example - Set a Molecule</title>
  <script src="marvinjs-14.9.15-all/js/lib/jquery-1.9.1.min.js">
</script>
  <script src="marvinjs-14.9.15-all/js/promise-0.1.1.min.js">
</script>
  <script src="marvinjs-14.9.15-all/js/marvinjslauncher.js">
</script>
  <script>

 
function debugObjectInfo(obj) {
 traceObject(obj);

 function traceObject(obj) {
  var str = '';
  if (obj.tagName && obj.name && obj.id)
   str = "<table border='1' width='100%'><tr><td colspan='2' bgcolor='#ffff99'>traceObject 　　tag: &lt;"
     + obj.tagName
     + "&gt;　　 name = \""
     + obj.name
     + "\" 　　id = \"" + obj.id + "\" </td></tr>";
  else {
   str = "<table border='1' width='100%'>";
  }
  var key = [];
  for ( var i in obj) {
   key.push(i);
  }
  key.sort();
  for ( var i = 0; i < key.length; i++) {
   var v = new String(obj[key[i]]).replace(/</g, "&lt;").replace(/>/g,
     "&gt;");
   str += "<tr><td valign='top'>" + key[i] + "</td><td>" + v
     + "</td></tr>";
  }
  str = str + "</table>";
  writeMsg(str);
 }
 function trace(v) {
  var str = "<table border='1' width='100%'><tr><td bgcolor='#ffff99'>";
  str += String(v).replace(/</g, "&lt;").replace(/>/g, "&gt;");
  str += "</td></tr></table>";
  writeMsg(str);
 }
 function writeMsg(s) {
  traceWin = window.open("", "traceWindow",
    "height=600, width=800,scrollbars=yes");
  traceWin.document.write(s);
 }
}





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

function testIsEmpty() {
 alert(marvinSketcherInstance.isEmpty());
}

function testClean() {
 //清除面板里面的元素
 marvinSketcherInstance.clear();
}
function testGetSupportedFormats() {
 debugObjectInfo(marvinSketcherInstance.getSupportedFormats());
}

function export1() {
  marvinSketcherInstance.exportStructure('mol').then(function(source) {
   alert(source);
  }, function(error) {
   alert("Molecule export failed:"+error);
  });
}

function export2() {
  marvinSketcherInstance.exportStructure('mrv').then(function(source) {
   alert(source);
  }, function(error) {
   alert("Molecule export failed:"+error);
  });
}

function addButtonTest(){
 //添加自定义安能
 marvinSketcherInstance.addButton({
   "name": "测试", // JS String
   "image-url": "images/custom-button.png" ,// JS String
   "toolbar": "W" // JS String: "W" as West, "E" as East, "N" as North, "S" as South toolbar
  },function(){
  alert('自定义按钮');
  });
}
function getMySelection(){
 //返回选择的元素节点。atom:原子 bond：连接
 debugObjectInfo(marvinSketcherInstance.getSelection());
}

var s = "\n\n\n"+
 " 14 15 0 0 0 0 0 0 0 0999 V2000\n"+
 " 0.5089 7.8316 0.0000 C 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 1.2234 6.5941 0.0000 C 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 1.2234 7.4191 0.0000 C 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " -0.2055 6.5941 0.0000 C 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " -0.9200 7.8316 0.0000 C 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 0.5089 5.3566 0.0000 C 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " -0.2055 7.4191 0.0000 N 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 0.5089 6.1816 0.0000 N 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " -0.9200 6.1816 0.0000 O 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 0.5089 8.6566 0.0000 O 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 2.4929 7.0066 0.0000 C 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 2.0080 7.6740 0.0000 N 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 2.0080 6.3391 0.0000 N 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 2.2630 8.4586 0.0000 C 0 0 0 0 0 0 0 0 0 0 0 0\n"+
 " 1 7 1 0 0 0 0\n"+
 " 8 2 1 0 0 0 0\n"+
 " 1 3 1 0 0 0 0\n"+
 " 2 3 2 0 0 0 0\n"+
 " 7 4 1 0 0 0 0\n"+
 " 4 8 1 0 0 0 0\n"+
 " 4 9 2 0 0 0 0\n"+
 " 7 5 1 0 0 0 0\n"+
 " 8 6 1 0 0 0 0\n"+
 " 1 10 2 0 0 0 0\n"+
 " 3 12 1 0 0 0 0\n"+
 " 2 13 1 0 0 0 0\n"+
 " 13 11 2 0 0 0 0\n"+
 " 12 11 1 0 0 0 0\n"+
 " 12 14 1 0 0 0 0\n"+
 "M END\n";
 
function testImport(){ 
 marvinSketcherInstance.importStructure("mol", s);
}
</script>
 </head>
 <body> 
   <iframe src="marvinjs-14.9.15-all/editor.html" id="sketch"
    style="overflow: hidden; min-width: 400px; min-height: 350px; border: 1px solid darkgray;"></iframe>
 
  <button onclick='testIsEmpty()'>
   是否为空
  </button>
  <button onclick='testClean()'>
   清空
  </button>
  <button onclick='testGetSupportedFormats()'>
   getSupportedFormats
  </button>
  <button onclick='export1()'>
   导出mol
  </button>
  <button onclick='export2()'>
   导出mrv
  </button>
  <button onclick='addButtonTest()'>
   添加一个按钮
  </button>
  <button onclick='getMySelection()'>
   得到选中的元素
  </button>
  <button onclick='testImport()'>
   导入
  </button>
 </body>
</html>

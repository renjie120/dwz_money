<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/include.inc.jsp"%>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=AlIjG4o3nxNvi4SqMiVHVVUI"></script>
<title>地图官网展示效果</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript"> 
// 百度地图API功能
var map = new BMap.Map("allmap");                        // 创建Map实例
map.centerAndZoom(new BMap.Point(116.404, 39.915), 15);     // 初始化地图,设置中心点坐标和地图级别
map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
map.enableScrollWheelZoom();                            //启用滚轮放大缩小
map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
 
 
 
var names = ['陈柏霖','陈冠希','陈浩民','陈启泰','陈升','陈司翰','陈小春','陈晓东','陈勋奇','陈奕迅','陈志朋','成龙','丁子峻',
' 动力火车','窦智孔','杜德伟','杜汶泽','Energy','范逸臣','范值伟','方力申','房祖名','费翔','冯德伦','古巨基','古天乐',
'郭晋安','郭品超','何润东','黄安','黄家强',' 黄立行','黄品源','黄秋生','黄日华','黄仲昆'];

// 编写自定义函数,创建标注
function addMarker(point,index){
  var marker = new BMap.Marker(point);
  map.addOverlay(marker); 
  var infoWindow1 = new BMap.InfoWindow("打卡员工:"+names[index]+"<BR>时间：2013年12月13日 08:02");
  marker.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});
   
}
// 随机向地图添加25个标注
var bounds = map.getBounds();
var sw = bounds.getSouthWest();
var ne = bounds.getNorthEast();
var lngSpan = Math.abs(sw.lng - ne.lng);
var latSpan = Math.abs(ne.lat - sw.lat);
for (var i = 0; i < 25; i ++) {
  var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
  addMarker(point,i);
}
 
//map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
</script>

<%@ page language="java" import="java.util.*" pageEncoding="GBK"%> 
<%
	String basePath = request.getContextPath(); 
%>
<style type="text/css">
body {
	margin: 0px;
	background-color: #d6e3f7;
}
.std td{
 width:49px;
 font-size:11;
}
</style> 
 
<script type="text/javascript"> 
 var appPath = "<%=basePath %>"; 
 var t = 10;
 var tt = 1;
 var timel = null;	
 var haveStarted = false; 
 var main = null;	 
 var stop = true;   
 function seeThread(){
 	timel = setInterval(function(){  
   		  see(); 
   },1000 );
 }
 
  function mainThread(){
 	 main = setInterval(function(){   
   		  consoleTime(); 
   		  $('#num2').val(tt++);
      },1000);
 }
  
    $(function(){  
    see(); 
      mainThread();  
    }); 
    
    function consoleTime(){ 
      var myDate = new Date();  
      var _d = myDate.getDay();         
      var _h = myDate.getHours(); 
      var _m = myDate.getMinutes();     
      //工作日的9点20就切换状态!
      if(_d>0&&_d<6){
      	if(((_h==9&&_m>=20)||_h==10||(_h==11&&_m<35)||(_h==13&&_m>=0)||(_h==14)||(_h==15&&_m<10))){ 
	      	if(!haveStarted&&!timel){
	      	 haveStarted = true
	      	 seeThread();  
	      	} 
      	}
      	//工作日的3点10分再切换状态!
      	else if(_h==12||_h==16){ 
      		if(haveStarted&&timel){
      		 haveStarted= false;
      	     clearInterval(timel); 
      	   }
      	}
      } 
    }
    function see(){  
     var obj = $('#num');   
     /*$.ajax({
        url : appPath+"/bootstrap/stock.jsp",
        type : 'POST',
        dataType : 'json',
        error:function(x,textStatus, errorThrown){  
        	eval(x.responseText); 
        	var tb = [];
        	tb.push("<table id='stocks' class='std' ><tr><!--tr><td>名</td><td >价</td><td >赢</td><td >率</td><td >百万</td><td >时间</td></tr-->");
        	for(var i=0,j=vars.length;i<j;i++){
	     		var elements=vars[i].split(",");  
	     		tb.push("<td>"+elements[0]+"</td>"); 
	        	tb.push("<td>"+elements[3]+"</td>");
	        	tb.push("<td>"+(elements[3]-elements[2]).toFixed(3)+"</td>");
	        	tb.push("<td>"+(100*(elements[3]-elements[2])/elements[2]).toFixed(1)+"%</td>");
	        	//tb.push("<td>"+(elements[8]/1000000).toFixed(3)+"</td>");
	        	tb.push("<td>"+elements[31]+"</td>"); 
	        	//document.title=elements[0];
        	}
        	tb.push("</tr></table>");
        	obj.html(tb.join(''));
        	
        }
      });  */
    }
</script>  
<font size="2"> <span id='num'></span> <span id='num2'></span> 
</font>

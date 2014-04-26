function test(obj){ 
	eval("var arr =" + obj);    
	var xAxis_a = [];
	var yAxis_a = [];  
	var zAxis_a = new HashMap();  
	if(arr){ 
		var _len = arr.length;
		for(var _i=0;_i<_len;_i++){
			//去重添加到数组xAxis_a中
			if($.inArray(arr[_i][0],xAxis_a)==-1)
				xAxis_a.push(arr[_i][0]);
			//去重添加到数组yAxis_a中
			if($.inArray(arr[_i][1],yAxis_a)==-1)
				yAxis_a.push(arr[_i][1]);
			zAxis_a.put(arr[_i][0]+",,"+arr[_i][1],arr[_i][2]); 
		}  
		var len1 = xAxis_a.length;
		var len2 = yAxis_a.length; 
		var seriesArr = [];
		for(var _i=0;_i<len1;_i++){
			var _ans = {};
			_ans.name = xAxis_a[_i];
			_ans.data = [];
			for(var _j=0;_j<len2;_j++){
				_ans.data.push(zAxis_a.get(xAxis_a[_i]+",,"+yAxis_a[_j]));
			} 
			seriesArr.push(_ans);
		} 
		 
		options.xAxis.categories = yAxis_a;
		options.title.text = $('#money_report_year').val()+"年度每月按开支大类统计";
		options.series = seriesArr;
		chart	= new Highcharts.Chart(options);
	}else{
		alertMsg.error("没有数据!");
	}
}

var _credits = {
		href : 'http://www.thinksafari.com',
		text : '思程工作室'
	};

var options =  {};
var chart  = {};
$(document).ready(function(){ 
	var $char = $('#hm_chartdiv33'); 
	
	$.ajax({
		type : 'post',
		url : $char.attr('url'),
		success : function(msg) { 
			eval("var arr =" + msg); 
			var xAxis_a = [];
			var yAxis_a = [];  
			var zAxis_a = new HashMap();  
			var _len = arr.length;
			for(var _i=0;_i<_len;_i++){
				//去重添加到数组xAxis_a中
				if($.inArray(arr[_i][0],xAxis_a)==-1)
					xAxis_a.push(arr[_i][0]);
				//去重添加到数组yAxis_a中
				if($.inArray(arr[_i][1],yAxis_a)==-1)
					yAxis_a.push(arr[_i][1]);
				zAxis_a.put(arr[_i][0]+",,"+arr[_i][1],arr[_i][2]); 
			}  
			var len1 = xAxis_a.length;
			var len2 = yAxis_a.length; 
			var seriesArr = [];
			for(var _i=0;_i<len1;_i++){
				var _ans = {};
				_ans.name = xAxis_a[_i];
				_ans.data = [];
				for(var _j=0;_j<len2;_j++){
					_ans.data.push(zAxis_a.get(xAxis_a[_i]+",,"+yAxis_a[_j]));
				} 
				seriesArr.push(_ans);
			}  
			
			 options = {
					chart: {
					    plotBackgroundColor: null,
					    plotBorderWidth: null,
					    plotShadow: false,
					    type: 'column',
					    renderTo:'hm_chartdiv33'
					},
					credits : _credits,
					title: {
					    text: $char.attr('title')
					}, 
					/*tooltip: {
					  pointFormat: '{series.name}: <b>{point.y}</b>'
					},*/
					//显示点的值
					plotOptions: {
			            column: {
			                dataLabels: {
			                    enabled: true
			                },
			                enableMouseTracking: false
			            }
			        },
					 xAxis: {
 		              categories: yAxis_a
 			        }, 
					series: seriesArr
				};
			chart	= new Highcharts.Chart(options);
		}//end of ajax function. 
	});  
});			
 
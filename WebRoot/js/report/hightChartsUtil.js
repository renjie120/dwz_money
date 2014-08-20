var options = {};
var chart = {};
var _credits = {
	href : 'http://www.thinksafari.com',
	text : '思程工作室'
};

/**
 * 显示一个二维的列图表.
 * @param {Object} arr 数据来源.
 * @param {Object} title 列表标题
 * @param {Object} divId 要渲染的div的id。
 * @param {Object} xAxisName 横轴显示名称
 */
function show2Column_one(arr, _title, _divId, _xAxisName) {
	if (arr) {
		//店名
		var xAxis_a = [];
		//数值
		var yAxis_a = [];
		var _series = [];
		var _len = arr.length;
		for ( var _i = 0; _i < _len; _i++) {
			xAxis_a.push(arr[_i][0]);//数值.
			yAxis_a.push(arr[_i][1]);//店名
			var _o = {};
			var _d = [];
			_d.push(arr[_i][0] * 1);//这里数值不是数值类型，导致没有显示出来.
			_o.name = arr[_i][1];
			_o.data = _d;
			_series.push(_o);
		}
		$('#' + _divId).highcharts( {
			chart : {
				type : 'column'
			},
			//显示点的值
			plotOptions : {
				column : {
					dataLabels : {
						enabled : true
					},
					enableMouseTracking : false
				}
			},
			title : {
				text : _title
			},
			xAxis : {
				categories : [ _xAxisName ]
			},
			series : _series
		});
	} else {
		alertMsg.error("没有数据!");
	}
}

/**
 * 显示一个二维的列图表.第二种方式.
 * @param {Object} obj
 * @param {Object} _title
 * @param {Object} _divId
 * @param {Object} _xAxisName
 */
function show2Column_two(arr, _title, _divId, _xAxisName) {
	if (arr) {
		//店名
		var xAxis_a = [];
		//数值
		var yAxis_a = [];
		var _series = [];
		var _len = arr.length;
		for ( var _i = 0; _i < _len; _i++) {
			xAxis_a.push(arr[_i][1]);
			yAxis_a.push(arr[_i][0] * 1);
		}
		$('#' + _divId).highcharts( {
			chart : {
				type : 'column'
			},//显示点的值
			plotOptions : {
				column : {
					dataLabels : {
						enabled : true
					},
					enableMouseTracking : false
				}
			},
			title : {
				text : _title
			},
			xAxis : {
				categories : xAxis_a
			},
			series : [ {
				name : _xAxisName,
				data : yAxis_a
			} ]
		});
	} else {
		alertMsg.error("没有数据!");
	}
}

function show3Column(arr, _title, _divId) {
	if (arr) {
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
			zAxis_a.put(arr[_i][0]+",,"+arr[_i][1],1*arr[_i][2]); 
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
		$('#' + _divId).highcharts({
			credits : _credits,
		   chart: {
		            type: 'column'
		        },
			title : {
				text :_title, 
			},
			//yAxis: {  title: { text: $char.attr('yAxisName')?$char.attr('yAxisName'):'值' } },
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
			series : seriesArr
		});	
	} else {
		alertMsg.error("没有数据!");
	}
}

/**
 * 饼图.
 * @param {Object} arr
 * @param {Object} _title
 * @param {Object} _divId
 * @param {Object} _serialName
 * @param {Object} _format
 */
function showPie(arr, _title, _divId, _serialName, _format) {
if(arr)
	$('#'+_divId).highcharts({
			credits :_credits,
			title : {
				text :_title, 
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,   
	                    format:  _format?_format:'{point.name}({y}):{point.percentage:.1f} %'
	                } 
	            }
	        },
			series : [ {
				type : 'pie',
				name : _serialName,
				data : arr
			} ]
		});	
	else
		alertMsg.error("没有数据!");
}
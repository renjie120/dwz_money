$(document).ready(function(){
		var GridColumnType = [
		  {
			header : '标示列',
			headerIndex : 'disid',
			//建议下面的宽带使用百分比,效果较好.
			width:'300',
			columntype : {
				inputtype : 'html',
				htmlStr : '<font class="red">$</font>',
				nameId : 'textbox'
			}
		}, {
			header : '名称',
			headerIndex : 'disname',
			width:'300px',
			columntype : {
				inputtype : 'html',
				htmlStr : '<font color="red">$</font>',
				nameId : 'textbox'
			}
		},{
			header : '连接',
			headerIndex : 'disparentId',
			columntype : {
				inputtype : 'html',
				htmlStr : '<a href="http://www.baidu.com">$</button>',
				nameId : 'textbox'
			},
			width:'300px'
		}, {
			header : '上级标示',
			headerIndex : 'disparentId',
			width:'300px'
		}];
	var content = {columnModel:GridColumnType,        
                        dataUrl: appPath + "/commongridtree/common.do", 
                        idColumn:'disid',//id所在的列,一般是主键(不一定要显示出来)
                        parentColumn:'disparentId', //父亲列id 
                        //pageSize:3,
                        debug:true,
					    height: '300px',
					    width: '100%',
                        disabled:false, 
						rowCountOption:3,
						//expandColumnNm:'disparentId',
						//countColumnNm:'disid',
						//checkColumnNm:'disparentId',//好用...
                        //pageBar:true,
                        //analyzeAtServer:true,//设置了dataUrl属性的时候，如果此属性是false表示分析树形结构在前台进行，默认是后台分析（仅支持java）,体现在json格式不用！
                        checkOption:'multi',//1:出现单选按钮,2:出现多选按钮,其他:不出现选择按钮
                        multiChooseMode:3,//选择模式，共有1，2，3，4，5种。
                      	tableId:'testTable',//表格树的id
                      	expandAll:true,//展开全部
                      	rowCount:true,
                      	onSuccess:function(gt){  
						},
						onPagingSuccess:function(gt){  
						}
           };
	$('#newtableTree').gridTree(content);  
});
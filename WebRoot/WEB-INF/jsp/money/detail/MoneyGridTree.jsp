<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript">
  var appPath = "<%=basePath%>"; 
  $(function(){
  //
		//计算得到表格树的高度..不采用设置的高度!!
		var gridTreeHeight = $("#container .tabsPageContent").height() - $('#container div.pageHeader').height()-56;
		var GridColumnType = [{
	        header: '时间',
	        headerIndex: 'disid',
	        width: '100'
	    },
	    {
	        header: '类型1',
	        headerIndex: 'type_1',
	        width: '400'
	    },
	    {
	        header: '类型2',
	        headerIndex: 'type_3',
	        width: '100'
	    },
	    {
	        header: '类型2',
	        headerIndex: 'type_3',
	        width: '100'
	    },
	    {
	        header: '类型2',
	        headerIndex: 'type_3',
	        width: '100'
	    }]; 
	    //alert(gridTreeHeight);
		var content = {
			columnModel : GridColumnType,
			dataUrl : appPath + "/money/newmoney!moneyGridTree.do", 
			idColumn : 'time',//id所在的列,一般是主键(不一定要显示出来)
			parentColumn : 'parentTime', //父亲列id
			height:gridTreeHeight+'px',
			//tableId : 'moneyTable',//表格树的id
			exchangeColor : true
		};  
		alert($('#moneyGridTree').size());
		$('#moneyGridTree').gridTree(content);   
  });
</script>
<div class="pageHeader"  >
	<form onsubmit="return navTabSearch(this);"
		action="/money/myuser!queryGridTree.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						用户流水号11
								<input name="useId" class="textInput" size="30" type="text"   />
					</td> 
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									检索
								</button>
							</div>
						</div>
					</li> 
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent" style="overflow:hidden"> 
	 <div id="moneyGridTree"></div>
</div>
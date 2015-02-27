<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import ="common.util.*" %>
<%
	String[] yearList = (String[]) request.getAttribute("yearList");
	String[] monthList = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12" };
	TreeSet allMonthList =(TreeSet) request.getAttribute("allMonthList");
	String[] typeList = (String[]) request.getAttribute("typeList");
	Map monthToMoney = (Map) request.getAttribute("monthToMoney");
	Map monthToSum = (Map) request.getAttribute("monthToSum"); 
	Map yearTypeToSum = (Map) request.getAttribute("yearTypeToSum"); 
	Map yearToAllSum = (Map) request.getAttribute("yearToAllSum");  
	String bigtype = (String) request.getAttribute("bigtype");
%>
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	cursor:hand;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	cursor:hand;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
<script type="text/javascript">

/**
  * willcheck:要进行处理的表格对象(或者行的集合即可)
  * colnum:要进行合并单元格的列
  */
function coltogather(willcheck,colnum){ 
	var alltext = [],togotherNum = [],oldnum = [],id=1,lasttext=null,rmflag=1; 
	//逐列的数据进行扫描,得到里面的不重复的数据,以及各个数据的数目,然后将依据此结果进行设置rowspan属性
	willcheck.each(function(){
	      var _rmnum = this.getAttribute('rmnum');
		  if(!_rmnum)_rmnum=0;
	      var trdom= jQuery('td:eq('+(colnum-_rmnum)+')',this)
		  var text = jQuery(trdom).text();  
		  //如果上一行记录文本为空,说明是第一行
		  if(lasttext==null) {
			   lasttext = text;
		  }else {
			  //如果当前行和上一行记录一样,说明要进行合并,合并的单元格数目就加1
			  if(lasttext!=text){  
				   togotherNum.push(id);
				   lasttext = text;
				   id = 1;
			  } else{
			      id++;
			  }
		  }
		  
	 });   
	 togotherNum.push(id); 
	 //复制allnum数组中的数据到oldnum数组
	 jQuery.each(togotherNum, function(i, n){
         oldnum[i] =n;
     }); 
     var index = 0,len = togotherNum.length;
	 //逐行进行处理,设置指定列的rowspan属性,以及将要合并的单元格remove!
	 willcheck.each(function() {
				// 得到一个属性表示该行已经被移除了几个td
				var _rmnum = this.getAttribute('rmnum');
				if (!_rmnum)
					_rmnum = 0;
				var tddom = jQuery('td:eq(' + (colnum - _rmnum) + ')', this)
				if (togotherNum[index] == oldnum[index]) {
					tddom.attr('rowSpan', togotherNum[index]);
					if(togotherNum[index]>1)
					  togotherNum[index] = togotherNum[index] - 1;
					 else
					   index++;
				} else { 
					if (togotherNum[index] == 0) {
						index++;
					    tddom.attr('rowSpan', togotherNum[index]);
					} else {
				    	tddom.remove(); 
						if(--togotherNum[index]==0){
							index++;
						}
					}
					// 移除了td之后,要在tr里面添加一个属性标示已经移除的td的数目
					if (_rmnum == 0) {
						jQuery(this).attr('rmnum', 1);
					} else {
						jQuery(this).attr('rmnum', 1 + _rmnum * 1);
					}
				}
			});   
	 //清空数组
	 alltext = null;
	 togotherNum = null;
	 oldnum = null; 
}


function isinarr(arr,str){
  for(var i=arr.length-1;i>=0;i-- ){
    if(arr[i]==str)
	{return i;
	}
  }
  return -1;
}

function checktable(id){
  var tdnum=0;
  $('#'+id+' tr').each(function(){
	if(tdnum==0){
	   tdnum = $('td',this).size();
	}else{ 
	   if(tdnum!=$('td',this).size()){
	        tdnum = -1;
			return false;
	   }
	}
  });
  if(tdnum>0)
     return true;
  return false;
}

 function go() {    
	   coltogather($('.gridtable tr'),0);     
}

$(document).ready(function(){go();});

function baifenbi(){
	$('span[name=baifenbi]').toggle();
}

/**
 * 点击类别名称标题进行切换.
 */
function goDetail(bigtype){
	if($('#bigtype').val()=='')
		navTab.reload('/money/moneyReport!reportBigtable.do?bigtype='+bigtype); 
	else
		returnBack(); 
}
 
/**
 * 返回一月里面的小类对应的全部的明细
 */
function goMoneyDetail(year,month,tallyType){
	navTab.reload('/money/moneyReport!reportBigtableBySmallType.do?year='+year+"&month="+month+"&tallytype="+tallyType); 
}

/**
 * 返回mouyue大类对应的金额报表，饼图.
 */
function goMoneyGroup(year,month,bigType){
	navTab.reload('/money/moneyReport!reportBigtableByBigType.do?year='+year+"&month="+month+"&bigtype="+bigType); 
}

function returnBack(){
	navTab.reload('/money/moneyReport!reportBigtable.do'); 
}
</script>
<div class="pageFormContent" layoutH="-25">
	<input type="text" id="bigtype" value="${bigtype}" name="bigtype"/>
	<div class="pageContent" style='width:100%;height:100%;overflow:auto'><BUTTON onclick='baifenbi()'>百分比</BUTTON><BUTTON onclick='returnBack()'>返回</BUTTON>
		<table class="gridtable" sytle='width:100%'>
			<tr>
				<th>年份</th>
				<th>月份</th>
				<%
					for(String s:typeList){
				%>
				<th onclick="goDetail('<%=s%>')" style="cursor:hand"><%=s%></th>
				<%
					}
				%>
				<th>月结</th>
			</tr> 
				<%
					int temp = 0;
				for(String y:yearList){
					//在每一年的第一月前面出现一个标题记录.
					if(temp>0){
				%>
				<tr>
					<th>年份</th>
					<th>月份</th>
					<%
						for(String s:typeList){
					%>
					<th onclick="goDetail('<%=s%>')" style="cursor:hand"><%=s%></th>
					<%
						}
					%>
					<th>月结</th>
				</tr>
				<%
					} 
					 temp++;
					for(String m:monthList){
					if(allMonthList.contains(y+","+m)){
				%>
				<tr>
					<td><%=y%></td>
					<td><%=m%></td>
					<%
						for(String s:typeList){
							Double sum = Double.parseDouble(monthToSum.get(y+","+m)+"");
							Double money = monthToMoney.get(y+","+m+","+s)==null?0:Double.parseDouble(monthToMoney.get(y+","+m+","+s)+"");
							String ss = CommonUtil.multiply(CommonUtil.divide(money, sum, 4),100)+"%";
					%>
					<td year="<%=y%>" month="<%=m%>" type="<%=s%>" 
						<%if(money>0&&bigtype!=null&&!"".equals(bigtype))//如果是查看大类下面的具体报表
							{
						 %>onclick = "goMoneyDetail('<%=y%>','<%=m%>','<%=s%>')" <%
						  }else if(money>0){
						%>onclick = "goMoneyGroup('<%=y%>','<%=m%>','<%=s%>')" <%
						  }
						 %>>
						<span <%if(money>=2000) {%>style="color:red"<%} %>><%=money%></span><span name='baifenbi' style='display:none'><%="("+ss+")"%></span></td>
					<%
						}
					%>
					<td><%=monthToSum.get(y+","+m)  %></td>
				</tr>
				<%
					}
				 }   
				 %>
				 <tr style='color:blue'>
					<td><%=y %></td>
					<td>年结</td>
					<%
						for(String s:typeList){
					%>
					<td ><%=yearTypeToSum.get(y+","+s)==null?"0":yearTypeToSum.get(y+","+s)%></td>
					<%
						}
					%>
					<td><%=yearToAllSum.get(y) %></td>
				</tr>
				<% 
				 }
				%> 
		</table> 
	</div> 
</div>
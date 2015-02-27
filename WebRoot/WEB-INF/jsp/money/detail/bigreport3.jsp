<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="common.base.ParamSelect"%>
<%@ page import="common.base.SpringContextUtil"%>
<%@ page import="common.base.AllSelect"%>
<%@ page import="java.util.Collection"%>
<%@ page import="common.base.ParamSelect"%>
<%@ page import="dwz.constants.BeanManagerKey"%>
<%@ page import="money.param.ParamVO"%>
<%@ page import="money.report.MoneyReportVo"%>
<%
	AllSelect allSelect = (AllSelect) SpringContextUtil
	.getBean(BeanManagerKey.allSelectManager.toString());
	ParamSelect<ParamVO> select1 = allSelect.getParamsByType("nianfen");
	Collection<ParamVO> datas = select1.getDatas();
	List<MoneyReportVo> dataList = (List<MoneyReportVo>)request.getAttribute("dataList");
	 
%>
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
function returnBack(){
	navTab.reload('/money/moneyReport!reportBigtable.do'); 
} 
</script>
<div class="pageFormContent" layoutH="-18">
	<div class="pageContent" style='width:100%;height:100%;overflow:auto'><BUTTON onclick='returnBack()'>返回</BUTTON> 
		<table class="gridtable" sytle='width:500px'>
		<tr>
				<th>序号</th><th>时间</th><th>类别</th><th>金额</th><th>描述</th>
		</tr>
	<%
		int temp = 1;
		for(MoneyReportVo o:dataList){  
	%>
	 <tr>
				<td><%=temp %></td><td><%=o.getTime() %></td><td><%=o.getType() %></td><td><%=o.getMoney() %></td><td><%=o.getDesc()==null?"":o.getDesc() %></td> 
		</tr>
	<%
		temp++;
		}
	%>
	</table></div>
</div>

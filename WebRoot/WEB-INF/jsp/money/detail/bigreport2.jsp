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
	String year = (String)request.getAttribute("year");
	String month = (String)request.getAttribute("month");
	String bigtype = (String)request.getAttribute("bigtype");
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
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>

<script type="text/javascript">
/**
 * 返回一月里面的小类对应的全部的明细
 */
function goMoneyDetail(year,month,tallyType){
	navTab.reload('/money/moneyReport!reportBigtableBySmallType.do?year='+year+"&month="+month+"&tallytype="+tallyType); 
}
</script>

<div class="pageFormContent" layoutH="-18">
	<div class="pageContent" style='width:100%;height:100%;overflow:auto'> 
		<BUTTON onclick='returnBack()'>返回</BUTTON>
		<table class="gridtable" sytle='width:100%'>
			<tr>
				<th colspan="2"><%=year%>年<%=month%>月<%=bigtype%>具体明细报表</th>
			</tr>
			<tr>
				<td>具体小类</td>
				<td>金额</td>
			</tr>
			<%
				for(MoneyReportVo obj:dataList){
			%>
			<tr>
				<td><%=obj.getType()%></td>
				<td onclick="goMoneyDetail('<%=year%>','<%=month%>','<%=obj.getType()%>')"><%=obj.getMoney()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<div id="money_report2_chartdiv2" class="highcharts" type="pie"
			serialName="收支大类" title='收支数据统计数量'
			style="width: 300px; height: 250px; float: left; border: 1 red;"
			url="/money/moneyReport!reportCountByType.do?year=<%=year%>&month=<%=month%>&bigtype=<%=bigtype%>"></div>

		<div id="money_report2_chartdiv4" class="highcharts" type="pie"
			serialName="开支大类" title='开支大类饼图'
			style="width: 300px; height: 250px; float: left; border: 1 red;"
			url="/money/moneyReport!reportSumByType.do?year=<%=year%>&month=<%=month%>&bigtype=<%=bigtype%>"></div>
	</div>
</div>

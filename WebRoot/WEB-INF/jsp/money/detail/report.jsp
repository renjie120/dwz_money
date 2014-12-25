<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<div class="pageFormContent" layoutH="-25"> 
	<div id="money_report_chartdiv1" class="highcharts" type="column"
		serialName="开支大类" title='按大类统计开支金额' yAxisName="金额"
		style="width: 900px; height: 400px; float: left; border: 1 red;"
		url="/money/moneyReport!reportSumByTypeAndYear.do"></div>

	<div id="money_report_chartdiv2" class="highcharts" type="pie"
		serialName="收支大类" title='收支数据统计数量'
		style="width: 300px; height: 250px; float: left; border: 1 red;"
		url="/money/moneyReport!reportCountByType.do"></div>

	<div id="money_report_chartdiv4" class="highcharts" type="pie"
		serialName="开支大类" title='开支大类饼图'
		style="width: 300px; height: 250px; float: left; border: 1 red;"
		url="/money/moneyReport!reportSumByType.do"></div>
</div>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageFormContent" layoutH="-18">
	<div id="hm_chartdiv1" class="highcharts" type="column"
		serialName="开支大类" title='按大类统计开支金额' yAxisName="金额"
		style="width: 900px; height: 300px; float: left; border: 1 red;"
		url="/money/newmoney!reportSumByTypeAndYear.do"></div>

	<div id="hm_chartdiv3" class="highcharts" type="column"
		serialName="开支大类" title='2013年度每月按开支大类统计'
		style="width: 900px; height: 250px; float: left; border: 1 red;"
		url="/money/newmoney!reportSumByTypeAndYearAndMonth.do"></div> 

	<div id="hm_chartdiv2" class="highcharts" type="column"
		serialName="收支大类" title='收支数据统计数量'
		style="width: 300px; height: 250px; float: left; border: 1 red;"
		url="/money/newmoney!reportCountByType.do"></div>

	<div id="hm_chartdiv4" class="highcharts" type="pie"
		serialName="开支大类" title='开支大类饼图'
		style="width: 300px; height: 250px; float: left; border: 1 red;"
		url="/money/newmoney!reportSumByType.do"></div>

</div>

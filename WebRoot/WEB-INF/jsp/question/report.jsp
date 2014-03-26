<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%> 
<div class="pageContent">
	<div id="chartdiv1" class="highcharts" type="pie" serialName="问题数量"
		title='按问题状态统计'
		style="width:300px;height:300px;float:left;border:1 red;"
		url="/money/question!reportQuestionCountByStatus.do"></div>

	<div id="chartdiv2" class="highcharts" type="pie" serialName="问题数量"
		title='按问题分类统计'
		style="width:300px;height:300px;float:left;border:1 red;"
		url="/money/question!reportQuestionCountByType.do"></div>
		
	<div id="chartdiv3" class="highcharts" type="pie" serialName="问题数量"
		title='按问题提问人'
		style="width:300px;height:300px;float:left;border:1 red;"
		url="/money/question!reportQuestionCountByPerson.do"></div>
</div>

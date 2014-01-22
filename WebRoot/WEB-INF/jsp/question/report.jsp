
<script language="JavaScript" src="/freefusionchart/FusionCharts.js"></script>
<div class="pageContent">
	<div class="sortDrag">
		<div id="question_chartdiv"
			style=" border: 1px solid #000099;"></div>
	</div>
	<div class="sortDrag">
		<div id="question_chartdiv2"  
			style=" border: 1px solid #000099;"></div>
	</div>
	<script type="text/javascript">
		var myChart = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf",
				"myChartId", "500", "500");
		myChart.setDataURL("/money/question!reportQuestionByTypeXml.do");
		myChart.render("question_chartdiv");

		var myChart = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf",
				"myChartId", "500", "500");
		myChart.setDataURL("/money/question!reportQuestionByStatusXml.do");
		myChart.render("question_chartdiv2");
	</script>
</div>

<script language="JavaScript" src="/freefusionchart/FusionCharts.js"></script>
<div class="pageContent">
	<div class="pageFormContent" width='300px' layouth='-38'>
		<div style="width: 1010px; border: 1px solid #000099;">
			<div id="question_chartdiv"
				style="float: left; border: 1px solid #000099;">
			</div>
			<div id="question_chartdiv2"
				style="float: left; border: 1px solid #000099;">
			</div>
			<script type="text/javascript"> 
        var myChart = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf", "myChartId", "500", "500"); 
       	myChart.setDataURL("/money/question!reportQuestionByTypeXml.do");
        myChart.render("question_chartdiv");
        
        var myChart = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf", "myChartId", "500", "500"); 
       	myChart.setDataURL("/money/question!reportQuestionByStatusXml.do");
        myChart.render("question_chartdiv2");
    </script>
		</div>
	</div>
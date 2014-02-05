<script language="JavaScript" src="/freefusionchart/FusionCharts.js"></script>
<div class="pageContent">
	<div class="pageFormContent" width='300px' layouth='-38'>
		<div style="width: 1210px; border: 1px solid #000099;">
			<div id="chartdiv7" style="float: left; border: 1px solid #000099;">
			</div>
			<div id="chartdiv8" style="float: left; border: 1px solid #000099;">
			</div>
		</div>
		<script type="text/javascript"> 
			var myChart7 = new FusionCharts(
					"/freefusionchart/Charts/FCF_Pie3D.swf", "myChartId2",
					"400", "400");
			myChart7.setDataURL("/money/question!reportQuestionByTypeXml.do");
			myChart7.render("chartdiv7");

			var myChart8 = new FusionCharts(
					"/freefusionchart/Charts/FCF_Pie3D.swf", "myChartId",
					"400", "400");
			myChart8.setDataURL("/money/question!reportQuestionByStatusXml.do");
			myChart8.render("chartdiv8");
		</script>
	</div>
</div>

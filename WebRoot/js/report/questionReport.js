var myChart7 = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf",
		"myChartId2", "400", "400");
myChart7.setDataURL("/money/question!reportQuestionByTypeXml.do");
myChart7.render("chartdiv7");

var myChart8 = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf",
		"myChartId", "400", "400");
myChart8.setDataURL("/money/question!reportQuestionByStatusXml.do");
myChart8.render("chartdiv8");
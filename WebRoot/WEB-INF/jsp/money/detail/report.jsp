<script language="JavaScript" src="/freefusionchart/FusionCharts.js"></script>
<div class="pageContent">
	<div class="pageFormContent" width='300px' layouth='-38'>
		<div style="width: 1210px; border: 1px solid #000099;">
			<div id="chartdiv1" style="float: left; border: 1px solid #000099;">
			</div>
			<div id="chartdiv2" style="float: left; border: 1px solid #000099;">
			</div>
			<div id="chartdiv3" style="float: left; border: 1px solid #000099;">
			</div>
			<div id="chartdiv4" style="float: left; border: 1px solid #000099;">
			</div>
			<div id="chartdiv5" style="float: left; border: 1px solid #000099;">
			</div>
			<div id="chartdiv6" style="float: left; border: 1px solid #000099;">
			</div>
		</div>
		<script type="text/javascript">  
		//按照全部的类别统计总金额.
        var myChart1 = new FusionCharts("/freefusionchart/Charts/FCF_Line.swf", "myChartId1", "1200", "300"); 
       	myChart1.setDataURL("/money/newmoney!reportSumByType.do");
        myChart1.render("chartdiv1");
        
		//按照类别统计全部的数量之和
        var myChart2 = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf", "myChartId2", "400", "400"); 
       	myChart2.setDataURL("/money/newmoney!reportCountByType.do");
        myChart2.render("chartdiv2");
        
        //按照类别统计全部的数量之和
        var myChart3 = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf", "myChartId3", "400", "400"); 
       	myChart3.setDataURL("/money/newmoney!reportCountByType.do");
        myChart3.render("chartdiv3");
        
        //按照类别统计全部的数量之和
        var myChart4 = new FusionCharts("/freefusionchart/Charts/FCF_Pie3D.swf", "myChartId4", "400", "400"); 
       	myChart4.setDataURL("/money/newmoney!reportCountByType.do");
        myChart4.render("chartdiv4");
        
        //按照月度统计金额.
        var myChart5 = new FusionCharts("/freefusionchart/Charts/FCF_MSBar2D.swf", "myChartId5", "1200", "900"); 
       	myChart5.setDataURL("/money/newmoney!reportSumByTypeAndYearAndMonth.do");
        myChart5.render("chartdiv5");
        
         //按照年度统计金额.
        var myChart6 = new FusionCharts("/freefusionchart/Charts/FCF_MSColumn3D.swf", "myChartId6", "1200", "300"); 
       	myChart6.setDataURL("/money/newmoney!reportSumByTypeAndYear.do");
        myChart6.render("chartdiv6");  
        
    </script>
	</div>
</div>

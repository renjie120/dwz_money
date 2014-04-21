<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="common.base.ParamSelect"%>
<%@ page import="common.base.SpringContextUtil"%>
<%@ page import="common.base.AllSelect"%>
<%@ page import="java.util.Collection"%>
<%@ page import="common.base.ParamSelect"%>
<%@ page import="dwz.constants.BeanManagerKey"%>
<%@ page import="money.param.ParamVO"%>
<%
	AllSelect allSelect = (AllSelect) SpringContextUtil
			.getBean(BeanManagerKey.allSelectManager.toString());
	ParamSelect<ParamVO> select1 = allSelect.getParamsByType("nianfen");
	Collection<ParamVO> datas = select1.getDatas();
%>
<script src="/js/report/moneyreport.js" type="text/javascript" />
<div class="pageFormContent" layoutH="-18">
	<table>
		<tr>
			<td>选择年份：</td>
			<td><select class="combox" name="year" id="money_report_year"
				relFun="test"
				refUrl="/money/newmoney!reportSumByTypeAndYearAndMonth.do?year={value}"
				noRequest="-1">
					<option value="-1">请选择</option>
					<%
						if (datas != null && datas.size() > 0)
							for (ParamVO vo : datas) {
					%>
					<option value="<%=vo.getParamValue()%>"><%=vo.getParamName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
	</table>
	<div id="hm_chartdiv33" class="dynhighcharts" type="column"
		serialName="开支大类" title='2014年度每月按开支大类统计'
		style="width: 900px; height: 250px; float: left; border: 1 red;"
		url="/money/newmoney!reportSumByTypeAndYearAndMonth.do?year=2014"></div>

	<div id="hm_chartdiv1" class="highcharts" type="column"
		serialName="开支大类" title='按大类统计开支金额' yAxisName="金额"
		style="width: 900px; height: 300px; float: left; border: 1 red;"
		url="/money/newmoney!reportSumByTypeAndYear.do"></div>


	<div id="hm_chartdiv2" class="highcharts" type="column"
		serialName="收支大类" title='收支数据统计数量'
		style="width: 300px; height: 250px; float: left; border: 1 red;"
		url="/money/newmoney!reportCountByType.do"></div>

	<div id="hm_chartdiv4" class="highcharts" type="pie" serialName="开支大类"
		title='开支大类饼图'
		style="width: 300px; height: 250px; float: left; border: 1 red;"
		url="/money/newmoney!reportSumByType.do"></div>

	<div id="hm_chartdiv5" class="highcharts" type="columnrange" serialName="正负能量" inverted="true"
		title='功过正负能量统计图' subtitle='时间最近...' series="次数统计"
		style="width: 900px; height: 600px; float: left; border: 1 red;"
		url="/money/newmoney!reportGongguoStatis.do"></div>

</div>

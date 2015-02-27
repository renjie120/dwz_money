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
	List typeList = (List)request.getAttribute("typeList");
	
	System.out.println(typeList);
%>
<div class="pageFormContent" layoutH="-18">
	<%
		int temp = 0;
		for(Object o:typeList){ 
			String tp = (String)o;
	%>
	<div id="bigreport_chartdiv<%=temp %>" class="highcharts" type="column"
		serialName="小类" title='<%=tp%>统计金额' yAxisName="金额"
		style="width: 600px; height: 300px; float: left; border: 1 red;"
		url="/money/moneyReport!reportSumByBigTypeAndYear.do?bigtype=<%=tp%>"></div>
	<%
		temp++;
		}
	%>
</div>

<#include "/com.renjie120.codegenerate.common.ftl"><%@ page language="java" contentType="text/html; charset=GBK" %> 
<%@ include file="/systeminfo/init.jsp" %>
<jsp:useBean id="RecordSet" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="Util" class="weaver.general.Util" scope="page" />
<%
String method = Util.null2String(request.getParameter("method"));
if(method.equals("add"))
{
	if(!HrmUserVarify.checkUserRight("${model.className?cap_first}:Edit", user)) {
		response.sendRedirect("/notice/noright.jsp") ;
		return ;
	} 
	<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'> 
	String ${attr.name} = Util.null2String(request.getParameter("${attr.name}"));//${attr.desc} 
	</#if> 
</#list>   

	RecordSet.executeSql("insert into ${model.table} (<@allfield2notkey_column nm=model.attributes />) values (<@allfield2notkey_insertcolumn   nm=model.attributes />)");
	response.sendRedirect("/CRM/data/${model.className}_mainList.jsp");
	return;
}
if(method.equals("edit"))
{
	if(!HrmUserVarify.checkUserRight("${model.className?cap_first}:Edit", user)) {
		response.sendRedirect("/notice/noright.jsp") ;
		return ;
	}
	String ${model.keyName} = Util.null2String(request.getParameter("${model.keyName}")); 
	<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'> 
	String ${attr.name} = Util.null2String(request.getParameter("${attr.name}"));//${attr.desc} 
	</#if> 
</#list>    
	RecordSet.executeSql("update ${model.table} set <@allfield2notkey_updatecolumn_nosql nm=model.attributes /> where ${model.keyColumn}= "+${model.keyName} );
	response.sendRedirect("/CRM/data/${model.className}_mainList.jsp");
	return;
}
if(method.equals("delete"))
{
	if(!HrmUserVarify.checkUserRight("${model.className?cap_first}:Edit", user)) {
		response.sendRedirect("/notice/noright.jsp") ;
		return ;
	}
	String fourid = Util.null2String(request.getParameter("fourid"));
	ArrayList fourids = Util.TokenizerString(fourid,",");
    if( fourids != null ) {
        for(int i=0 ; i<fourids.size(); i++ ) {
            String tempFourId = (String)fourids.get(i) ;
        	RecordSet.executeSql(" delete from ${model.table} where  ${model.keyColumn} ="+tempFourId);
        }
    }
	response.sendRedirect("/CRM/data/${model.className}_mainList.jsp");
	return;
}
%>

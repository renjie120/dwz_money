<#include "/com.renjie120.codegenerate.common.ftl">
<%@ page language="java" contentType="text/html; charset=GBK" %> <%@ include file="/systeminfo/init.jsp" %>
<%@ page import="java.util.*,
                 java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="weaver.file.FileUpload" %>
<jsp:useBean id="Util" class="weaver.general.Util" scope="page" />
<jsp:useBean id="${nm}RecordSet" class="weaver.conn.RecordSet" scope="page" /> 
<jsp:useBean id="CustomerContacterComInfo" class="weaver.crm.Maint.CustomerContacterComInfo" scope="page" />
<%
boolean isfromtab =  Util.null2String(request.getParameter("isfromtab")).equals("true")?true:false;
FileUpload fu = new FileUpload(request);
String log=Util.null2String(fu.getParameter("log"));
char flag = 2;
String ProcPara = "";
String strTemp = "";
String CurrentUser = ""+user.getUID();
String ClientIP = fu.getRemoteAddr();
String SubmiterType = ""+user.getLogintype();

Date newdate = new Date() ;
long datetime = newdate.getTime() ;
Timestamp timestamp = new Timestamp(datetime) ;
String CurrentDate = (timestamp.toString()).substring(0,4) + "-" + (timestamp.toString()).substring(5,7) + "-" +(timestamp.toString()).substring(8,10);
String CurrentTime = (timestamp.toString()).substring(11,13) + ":" + (timestamp.toString()).substring(14,16) + ":" +(timestamp.toString()).substring(17,19);


/************************** 下面是对${model.classDesc}的处理过程********************/
String method = Util.null2String(fu.getParameter("method")); 
String crm_id=Util.null2String(fu.getParameter("CustomerID")); 
String ID= "";  
<#list model.attributes as attr> 
	<#if '${attr.name}'!='${model.keyName}'>
		String ${attr.name}=Util.fromScreen(fu.getParameter("${attr.name}"),user.getLanguage());
	</#if>
</#list>   
			
if("add".equals(method))
{  
	<#assign index=0> 
	<#list model.attributes as attr>
		 <#if '${attr.name}'!='${model.keyName}'>
			ProcPara<#if index=0>=<#else>+=flag+</#if>${attr.name};<#assign index=index+1>
		</#if> 
	</#list>    
	${nm}RecordSet.executeProc("${model.table}_Insert",ProcPara);
	${nm}RecordSet.executeSql("SELECT MAX(id) as id from ${model.table}");
	if (${nm}RecordSet.next()) ID = ${nm}RecordSet.getString("id"); 

	response.sendRedirect("/CRM/data/${model.className}_list.jsp?CustomerID="+crm_id+"&isfromtab=true"); 
	return;
} 
else if("delete".equals(method))
{   
	ID=Util.fromScreen(fu.getParameter("ID"),user.getLanguage()); 
	${nm}RecordSet.executeProc("${model.table}_Delete",ID); 
	 try { 
	 response.getWriter().write("OK!");
	 } catch (Exception e) { 
	 } 
} 
%>
<p><%=SystemEnv.getHtmlLabelName(15127,user.getLanguage())%>！</p>
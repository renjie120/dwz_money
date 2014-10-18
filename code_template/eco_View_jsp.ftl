<#include "/com.renjie120.codegenerate.common.ftl"><%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ include file="/systeminfo/init.jsp" %>
<%@ page import="weaver.general.Util" %>

<jsp:useBean id="LanguageComInfo" class="weaver.systeminfo.language.LanguageComInfo" scope="page" />
<jsp:useBean id="${nm}RecordSet" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="Util" class="weaver.general.Util" scope="page" />
<jsp:useBean id="RecordSet" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="RecordSetFF" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="RecordSetShare" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="ResourceComInfo" class="weaver.hrm.resource.ResourceComInfo" scope="page"/>
<jsp:useBean id="CheckUserRight" class="weaver.systeminfo.systemright.CheckUserRight" scope="page" />
<jsp:useBean id="RecordSetV" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="CrmShareBase" class="weaver.crm.CrmShareBase" scope="page" />
<%
String CustomerID = Util.null2String(request.getParameter("CustomerID"));
boolean isfromtab =  Util.null2String(request.getParameter("isfromtab")).equals("true")?true:false;
String ID = Util.null2String(request.getParameter("ID"));
boolean hasFF = true;
RecordSetFF.executeProc("Base_FreeField_Select","c2");
if(RecordSetFF.getCounts()<=0)
	hasFF = false;
else
	RecordSetFF.first();

//先验证一下是否已经存在这个客户信息
RecordSet.executeProc("CRM_CustomerInfo_SelectByID",CustomerID);
if(RecordSet.getCounts()<=0)
{
	response.sendRedirect("/base/error/DBError.jsp");
	return;
}
RecordSet.first();
 
${nm}RecordSet.executeProc("${model.table}_SelectByID", ID);
if (${nm}RecordSet.getCounts() <= 0) {
	response.sendRedirect("/base/error/DBError.jsp");
	return;
}
${nm}RecordSet.first();

/*权限判断－－Begin*/

String useridcheck=""+user.getUID();
//客户部门
String customerDepartment=""+RecordSet.getString("department") ;
boolean canedit=false;
boolean isCustomerSelf=false; 
//共享级别
int sharelevel = CrmShareBase.getRightLevelForCRM(""+user.getUID(),CustomerID);
if(sharelevel>1) canedit=true;

if(user.getLogintype().equals("2") && CustomerID.equals(useridcheck)){
isCustomerSelf = true ;
}
//客户是否有代理人
if(useridcheck.equals(RecordSet.getString("agent"))){
	 canedit=true;
 }

if(RecordSet.getInt("status")==7 || RecordSet.getInt("status")==8){
	canedit=false;
}

/*权限判断－－End*/

if(!canedit && !isCustomerSelf) {
	response.sendRedirect("/notice/noright.jsp") ;
	return ;
 }
%>

<HTML><HEAD>
<LINK href="/css/Weaver.css" type=text/css rel=STYLESHEET>
<SCRIPT language="javascript" src="/js/weaver.js"></script>
</HEAD>
<%
String imagefilename = "/images/hdMaintenance.gif";
String titlename = "${model.classDesc}";
String needfav ="1";
String needhelp ="";
%>
<BODY onbeforeunload="protectContacter()">
<%if(!isfromtab){ %>
<%@ include file="/systeminfo/TopTitle.jsp" %>
<%} %>
<%@ include file="/systeminfo/RightClickMenuConent.jsp" %>
<%
RCMenu += "{编辑,javascript:location.href='/CRM/data/${model.className}_edit.jsp?CustomerID="+CustomerID+"&ID="+ID+"&isfromtab=true',_top}  " ; 
RCMenuHeight += RCMenuHeightStep ;
if(!isfromtab){
RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:location.href='/CRM/data/${model.className}_List.jsp?CustomerID="+CustomerID+"&isfromtab=true',_top} " ;
RCMenuHeight += RCMenuHeightStep ;
}else{
	RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:location.href='/CRM/data/${model.className}_List.jsp?CustomerID="+CustomerID+"&isfromtab=true',_top} " ;
	RCMenuHeight += RCMenuHeightStep ;
}
%>

<%@ include file="/systeminfo/RightClickMenu.jsp" %>

<table width=100% height=100% border="0" cellspacing="0" cellpadding="0">
<colgroup>
<col width="10">
<col width="">
<col width="10">
<tr>
	<td height="10" colspan="3"></td>
</tr>
<tr>
	<td ></td>
	<td valign="top">
		<TABLE class=Shadow>
		<tr>
		<td valign="top"> 
	<input type="hidden" name="method" value="add">
	<input type="hidden" name="CustomerID" value="<%=CustomerID%>">
	<input type="hidden" name="isfromtab" value="<%=isfromtab %>"> 
	
	<TABLE class=ViewForm>
		<COLGROUP>
		<COL width="20%">
		<COL width="80%">
	
		<TBODY>
			<TR class=Title>
			<TH colSpan=2>${model.classDesc}详情</TH>
			</TR> 
			<TR class=Spacing style='height:1px'>
			<TD class=Line1 colSpan=2></TD>
			</TR>
			<#list model.attributes as attr> 
			<#if '${attr.name}'!='${model.keyName}'>
			<TR>
					<TD>${attr.desc}</TD>
					<TD class=Field>
					  <%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%> 
			</TD></TR>	 
			<tr  style="height: 1px"><td class=Line colspan=2></td></tr>
			</#if>
			</#list>
		</TBODY>
	</TABLE>   
		</td>
		</tr>
		</TABLE>
	</td>
	<td></td>
</tr> 
</table> 
</BODY> 
</HTML>

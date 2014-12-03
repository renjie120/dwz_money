<#include "/com.renjie120.codegenerate.common.ftl"><%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ include file="/systeminfo/init.jsp" %>
<jsp:useBean id="RecordSet" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="Util" class="weaver.general.Util" scope="page" />
<%@ taglib uri="/WEB-INF/weaver.tld" prefix="wea"%>
<%
String ${model.keyName}=Util.null2String(request.getParameter("${model.keyColumn}"));
<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
	<#if "${attr.query}"='true'>
String ${attr.name} = "";//${attr.desc}
	</#if> 
	</#if> 
</#list>   

if(!${model.keyName}.equals("")){
	RecordSet.executeSql("select * from ${model.table} where ${model.keyColumn}="+${model.keyName});
	if(RecordSet.next()){
	<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
	<#if "${attr.query}"='true'>
		${attr.name}=Util.null2String(RecordSet.getString("${attr.column}"));
	</#if> 
	</#if> 
</#list>   
    }
}
boolean canedit = false;
canedit = HrmUserVarify.checkUserRight("${model.className?cap_first}:Edit", user);
%>


<HTML><HEAD>
<LINK href="/css/Weaver.css" type=text/css rel=STYLESHEET>
<SCRIPT language="javascript" src="../../js/weaver.js"></script>
</HEAD>
<%
String imagefilename = "/images/hdMaintenance.gif";
String titlename = "${model.classDesc}维护界面";
String needfav ="1";
String needhelp ="";
%>
<BODY>
<%@ include file="/systeminfo/TopTitle.jsp" %>
<%@ include file="/systeminfo/RightClickMenuConent.jsp" %>
<%
if(canedit){
    if(${model.keyName}.equals("")){
		RCMenu += "{"+SystemEnv.getHtmlLabelName(611,user.getLanguage())+",javascript:submitData(),_self} " ;
		RCMenuHeight += RCMenuHeightStep ;
	}else{
		RCMenu += "{"+SystemEnv.getHtmlLabelName(86,user.getLanguage())+",javascript:submitData(),_self} " ;
		RCMenuHeight += RCMenuHeightStep ;
	}
    RCMenu += "{"+SystemEnv.getHtmlLabelName(91,user.getLanguage())+",javascript:deleteFour(),_top} " ;
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

<%if(canedit){ %>
<FORM id=weaverA name=weaverA action="${model.className}_main_Operation.jsp" method=post  >
<%if(${model.keyName}.equals("")){%>
	<input class=inputstyle type="hidden" name="method" value="add">
<%}else{%>
	<input class=inputstyle type="hidden" name="method" value="edit">
	<input class=inputstyle type="hidden" name="${model.keyName}" value="<%=${model.keyName}%>">
<%}%>
<TABLE class=Viewform>
  <COLGROUP>
  <COL width="15%">
  <COL width=85%>
  <TBODY>
  <#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'> 
	<#if '${attr.visible}'!='false'> 
	<#if "${attr.query}"='true'>
	<TR>
	<TD>${attr.desc}</TD>
	<TD class=Field>
	   <input name="${attr.name}" id="${attr.name}" 
	   value='<%if(!${attr.name}.equals("")){%><%=${attr.name}%><%}%>'  <#if '${attr.type}'
	   ='numAndChar'> valid='numAndChar' <#elseif '${attr.type}'
	   ='email'> valid='email' <#elseif '${attr.type}'
	   ='int'> valid='num' <#elseif '${attr.type}'
	   ='char'> valid='char' <#elseif '${attr.type}'
	   ='double'> valid='double' <#else> </#if> <#if 
	   '${attr.minLength}'!=''>minLength="${attr.minLength}"</#if> <#if 
	   '${attr.maxLength}'!=''>maxLength="${attr.maxLength}"</#if> <#if 
	   '${attr.notnull}'='true'>onchange='checkNotnull("${attr.name}","${attr.name}image")'</#if> /><#if
	   '${attr.notnull}'='true'><SPAN id="${attr.name}image"><%if(${model.keyName}.equals("")){%><IMG src='/images/BacoError.gif' align=absMiddle><%}%></SPAN></#if> 
	</TD>
	</TR>
	<TR><TD class=Line colSpan=2></TD></TR> 
	</#if>
	</#if> </#if> 
</#list>    
  </TBODY>
</TABLE>
</FORM>

<TABLE class=form>
  <COLGROUP>
  <COL width="20%">
  <COL width=80%>
  <TBODY>
  <TR class=separator>
          <TD class=Sep1 colSpan=2></TD></TR>
           <TR>
          <TD colSpan=2>
		  </TD>
        </TR>
  </TBODY>
</TABLE>
<% } %>
		<TABLE class=form>
		<tr>
		<td valign="top">
<% 
int perpage=10;
String backFields = " ${model.keyColumn}  <@all_query_field2notkey_column nm=model.attributes /> ";
String colString = "";
String sqlFrom = " from ${model.table} ";
String sqlWhere = "";
String orderBy = "";
String linkstr = "";


String tableString=""+"<table pagesize=\"20\"";
if(canedit){
	tableString += " tabletype=\"checkbox\">";
	linkstr = "href=\"${model.className}_mainList.jsp\" linkkey=\"${model.keyColumn}\" linkvaluecolumn=\"${model.keyColumn}\" target=\"\" ";
}else{
	tableString += " tabletype=\"none\">";
	linkstr = "";
}

tableString += "<sql backfields=\"" + backFields + "\" sqlform=\"" + sqlFrom + "\" sqlprimarykey=\"${model.keyColumn}\" sqlorderby=\"" + orderBy + "\" sqlsortway=\" \" sqlwhere=\""+Util.toHtmlForSplitPage(sqlWhere)+"\" sqlisdistinct=\"true\" />"+
"<head>"+
<#assign index=0>
<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'> 
	<#if '${attr.visible}'!='false'> 
	"<col width=\"10%\"  text=\"${attr.desc}\" column=\"${attr.column}\" orderkey=\"${attr.column}\" <#if index=0> "+linkstr+" </#if> />"+	 
	<#assign index=index+1></#if> </#if> 
</#list>     
"</head>"+
"</table>";  
%> 
     	<wea:SplitPageTag  tableString="<%=tableString%>"  mode="run" isShowTopInfo="true"/> 
		</td>
		</tr>
		</TABLE>
</td>
</tr>
</TABLE>
</td>
<td></td>
</tr>
<tr>
<td height="10" colspan="3"></td>
</tr>
</table>
<script language=javascript>

function submitData() { 
		weaverA.submit(); 
}
function deleteFour(){ 
	if(confirm("<%=SystemEnv.getHtmlLabelName(15097,user.getLanguage())%>")) {
        weaverA.method.value='delete';
        weaverA.action='/CRM/data/${model.className}_main_Operation.jsp?fourid='+_xtable_CheckedCheckboxId();
        weaverA.submit();
	}
}
</script>
</body>
</html>

<SCRIPT language="javascript" src="/CRM/data/MySelectTool.js"></script>
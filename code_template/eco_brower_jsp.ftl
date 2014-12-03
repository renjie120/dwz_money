<#include "/com.renjie120.codegenerate.common.ftl"><%@ page import="weaver.general.Util" %>
<%@ page language="java" contentType="text/html; charset=GBK" %> <%@ include file="/systeminfo/init.jsp" %>
<jsp:useBean id="RecordSet" class="weaver.conn.RecordSet" scope="page" />

<HTML><HEAD>
<LINK REL=stylesheet type=text/css HREF=/css/Weaver.css></HEAD>
<%
String sqlwhere1 = Util.null2String(request.getParameter("sqlwhere"));
<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
	<#if "${attr.brower}"='true'>
String ${attr.name} = Util.null2String(request.getParameter("${attr.name}"));//${attr.desc}
	</#if> 
	</#if> 
</#list>   
String sqlwhere = " ";
int ishead = 0;
if(!sqlwhere1.equals("")){
	if(ishead==0){
		ishead = 1;
		sqlwhere += sqlwhere1;
	}
}
<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
	<#if "${attr.brower}"='true'>
if(!${attr.name}.equals("")){
	if(ishead==0){
		ishead = 1;
		sqlwhere += " where ${attr.column} like '%";
		sqlwhere += Util.fromScreen2(${attr.name},user.getLanguage());
		sqlwhere += "%'";
	}
	else{
		sqlwhere += " and ${attr.column} like '%";
		sqlwhere += Util.fromScreen2(${attr.name},user.getLanguage());
		sqlwhere += "%'";
	}
}
	</#if> 
	</#if> 
</#list>   
%>
<BODY>
<%@ include file="/systeminfo/RightClickMenuConent.jsp" %>


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
<FORM NAME=SearchForm STYLE="margin-bottom:0" action="CustomerStatusBrowser.jsp" method=post>
<input type=hidden name=sqlwhere value="<%=sqlwhere1%>">
<DIV align=right style="display:none">
<%
RCMenu += "{"+SystemEnv.getHtmlLabelName(197,user.getLanguage())+",javascript:document.SearchForm.submit(),_top} " ;
RCMenuHeight += RCMenuHeightStep ;
%>
<button type="button" class=btnSearch accessKey=S type=submit><U>S</U>-<%=SystemEnv.getHtmlLabelName(197,user.getLanguage())%></BUTTON>
<%
RCMenu += "{"+SystemEnv.getHtmlLabelName(199,user.getLanguage())+",javascript:document.SearchForm.reset(),_top} " ;
RCMenuHeight += RCMenuHeightStep ;
%>
<button type="button" class=btnReset accessKey=T type=reset><U>T</U>-<%=SystemEnv.getHtmlLabelName(199,user.getLanguage())%></BUTTON>
<%
RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:window.parent.close(),_top} " ;
RCMenuHeight += RCMenuHeightStep ;
%>
<button type="button" class=btn accessKey=1 onclick="window.parent.close()"><U>1</U>-<%=SystemEnv.getHtmlLabelName(201,user.getLanguage())%></BUTTON>
<%
RCMenu += "{"+SystemEnv.getHtmlLabelName(311,user.getLanguage())+",javascript:document.SearchForm.btnclear.click(),_top} " ;
RCMenuHeight += RCMenuHeightStep ;
%>
<button type="button" class=btn accessKey=2 id=btnclear onclick="btnclear_onclick();"><U>2</U>-<%=SystemEnv.getHtmlLabelName(311,user.getLanguage())%></BUTTON>
</DIV>
<table width=100% class=ViewForm>
<TR class=Spacing><TD class=Line1 colspan=4></TD></TR>
<TR>
<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
	<#if "${attr.brower}"='true'>
<TD width=15%>${attr.desc}</TD>
<TD width=35% class=field><input class=InputStyle  name=${attr.name} value="<%=${attr.name}%>"></TD>
	</#if> 
	</#if> 
</#list>    
</TR><tr style="height:1px;"><td class=Line colspan=4></td></tr>
<TR class=Spacing><TD class=Line1 colspan=4></TD></TR>
</table>
<TABLE ID=BrowseTable class=BroswerStyle cellspacing=1  width="100%">
<TR class=DataHeader>
<TH width=30%><%=SystemEnv.getHtmlLabelName(84,user.getLanguage())%></TH>
<#list model.attributes as attr>  
	<#if '${attr.name}'!='${model.keyName}'>
	<#if "${attr.brower}"='true'>
<TH width=30%>${attr.desc}</TH>
	</#if> 
	</#if> 
</#list>   
</tr><TR class=Line><TH colSpan=3></TH></TR>
<%
int i=0;
sqlwhere = "select ${model.keyColumn} <@all_brower_field2notkey_column nm=model.attributes /> from ${model.table} "+sqlwhere;
RecordSet.execute(sqlwhere);
while(RecordSet.next()){
	if(i==0){
		i=1;
%>
<TR class=DataLight>
<%
	}else{
		i=0;
%>
<TR class=DataDark>
	<%
	}
	%>
	<TD><A HREF=#><%=RecordSet.getString(1)%></A></TD>
	<TD><%=RecordSet.getString(2)%></TD>
	<TD><%=RecordSet.getString(3)%></TD>
	
</TR>
<%}%>
</TABLE></FORM>
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
<%@ include file="/systeminfo/RightClickMenu.jsp" %>    
</BODY></HTML>
<script type="text/javascript">
<!--

jQuery(document).ready(function(){
	jQuery("#BrowseTable").bind("click",BrowseTable_onclick);
	jQuery("#BrowseTable").bind("mouseover",BrowseTable_onmouseover);
	jQuery("#BrowseTable").bind("mouseout",BrowseTable_onmouseout);
})

function BrowseTable_onmouseover(e){
	e=e||event;
	  var target=e.srcElement||e.target;
	  if("TD"==target.nodeName){
			jQuery(target).parents("tr")[0].className = "Selected";
	  }else if("A"==target.nodeName){
			jQuery(target).parents("tr")[0].className = "Selected";
	  }
}
function BrowseTable_onmouseout(e){
	var e=e||event;
  var target=e.srcElement||e.target;
  var p;
	if(target.nodeName == "TD" || target.nodeName == "A" ){
     p=jQuery(target).parents("tr")[0];
     if( p.rowIndex % 2 ==0){
        p.className = "DataDark"
     }else{
        p.className = "DataLight"
     }
  }
}

function BrowseTable_onclick(e){
  var e=e||event;
  var target=e.srcElement||e.target;

  if( target.nodeName =="TD"||target.nodeName =="A"  ){
    window.parent.parent.returnValue = {id:jQuery(jQuery(target).parents("tr")[0].cells[0]).text(),name:jQuery(jQuery(target).parents("tr")[0].cells[1]).text()};
	 window.parent.parent.close();
	}
}


function btnclear_onclick(){
    window.parent.returnValue = {id:"",name:""};
    window.parent.close();
}
//-->
</script>
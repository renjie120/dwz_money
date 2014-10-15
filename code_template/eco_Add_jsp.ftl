<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ include file="/systeminfo/init.jsp" %>
<%@ page import="weaver.general.Util" %>

<jsp:useBean id="LanguageComInfo" class="weaver.systeminfo.language.LanguageComInfo" scope="page" />

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

/*权限判断－－Begin*/

String useridcheck=""+user.getUID();
String customerDepartment=""+RecordSet.getString("department") ;
boolean canedit=false;
boolean isCustomerSelf=false; 

int sharelevel = CrmShareBase.getRightLevelForCRM(""+user.getUID(),CustomerID);
if(sharelevel>1) canedit=true;

if(user.getLogintype().equals("2") && CustomerID.equals(useridcheck)){
isCustomerSelf = true ;
}

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
String titlename = SystemEnv.getHtmlLabelName(82,user.getLanguage())+SystemEnv.getHtmlLabelName(572,user.getLanguage())+" - "+SystemEnv.getHtmlLabelName(136,user.getLanguage())+":<a href='/CRM/data/ViewCustomer.jsp?log=n&CustomerID="+RecordSet.getString("id")+"'>"+Util.toScreen(RecordSet.getString("name"),user.getLanguage())+"</a>";
String needfav ="1";
String needhelp ="";
%>
<BODY onbeforeunload="protectContacter()">
<%if(!isfromtab){ %>
<%@ include file="/systeminfo/TopTitle.jsp" %>
<%} %>
<%@ include file="/systeminfo/RightClickMenuConent.jsp" %>
<%
RCMenu += "{"+SystemEnv.getHtmlLabelName(86,user.getLanguage())+",javascript:onSave(this),_top} " ;
RCMenuHeight += RCMenuHeightStep ;
RCMenu += "{"+SystemEnv.getHtmlLabelName(589,user.getLanguage())+",javascript:document.weaver.reset(),_top} " ;
RCMenuHeight += RCMenuHeightStep ;
if(!isfromtab){
RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:location.href='/CRM/data/Shangwuxinxi.jsp?CustomerID="+CustomerID+"&isfromtab=true',_top} " ;
RCMenuHeight += RCMenuHeightStep ;
}else{
	RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:location.href='/CRM/data/Shangwuxinxi.jsp?CustomerID="+CustomerID+"&isfromtab=true',_top} " ;
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

<FORM id=weaver name="weaver" action="/CRM/data/${model.className}_operation.jsp" method=post onsubmit='return check_form(this,"px_people")' enctype="multipart/form-data">
	<input type="hidden" name="method" value="add">
	<input type="hidden" name="CustomerID" value="<%=CustomerID%>">
	<input type="hidden" name="isfromtab" value="<%=isfromtab %>"> 
	
	<TABLE class=ViewForm>
		<COLGROUP>
		<COL width="20%">
		<COL width="80%">
	
		<TBODY>
			<TR class=Title>
			<TH colSpan=2>添加${model.classDesc}</TH>
			</TR>
			
			<TR class=Spacing style='height:1px'>
			<TD class=Line1 colSpan=2></TD>
			</TR>
			<#list model.attributes as attr> 
				<#if '${attr.name}'!='${model.keyName}'>
					<TR>
						<TD>${attr.desc}</TD>
						<TD class=Field>
						<#if '${attr.type}'='date'>
							<BUTTON type="button" class=calendar id=px_time_a onclick=get${attr.name?cap_first}()></BUTTON>
							<SPAN id=${attr.name}_span ></SPAN>
							<input type="hidden" name="${attr.name}"  id="${attr.name}"/>  
							<SCRIPT language="javascript"> 
							function get${attr.name?cap_first}(){
									WdatePicker({lang:languageStr,el:'${attr.name}_span',onpicked:function(dp){
										$dp.$('${attr.name}').value = dp.cal.getDateStr()},oncleared:function(dp){$dp.$('${attr.name}').value = ''}});
							}
							</SCRIPT>
						<#else>  
							<input   name="${attr.name}"  id="${attr.name}"/> 
						</#if>
						<#if '${attr.notnull}'='true'>
						<SPAN id=FirstNameimage><IMG src="/images/BacoError.gif" align=absMiddle></SPAN>
						</#if> 
					</TD></TR>	
					<tr  style="height: 1px"><td class=Line colspan=2></td></tr>				
				</#if>
			</#list>   
		</TBODY>
	</TABLE> 
</FORM>

		</td>
		</tr>
		</TABLE>
	</td>
	<td></td>
</tr> 
</table>
<SCRIPT language="javascript">  
function onSave(obj){
	window.onbeforeunload=null;
	if(check_form(document.weaver,'<#list model.attributes as attr><#if '${attr.notnull}'='true'>${attr.name},</#if></#list>')){
		obj.disabled=true;
	    weaver.submit();
	}
} 
</SCRIPT>
</BODY>
<SCRIPT language="javascript" src="/js/datetime.js"></script>
<SCRIPT language="javascript" src="/js/JSDateTime/WdatePicker.js"></script> 
<script language=javascript src="/js/checkData.js"></script> 
</HTML>

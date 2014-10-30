<#include "/com.renjie120.codegenerate.common.ftl"><%@ page language="java" contentType="text/html; charset=GBK" %>
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
RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:location.href='${model.arg1}"+CustomerID+"&isfromtab=true',_top} " ;
RCMenuHeight += RCMenuHeightStep ;
}else{
	RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:location.href='${model.arg1}"+CustomerID+"&isfromtab=true',_top} " ;
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
					<#if '${attr.noedit}'!='true'>	
					<TR>
						<TD>${attr.desc}</TD>
						<TD class=Field>
						<#if '${attr.type}'='date'>
							<BUTTON type="button" <#if '${attr.notnull}'=='true'>notnull="true" inputType='date' </#if> class=calendar id=px_time_${attr.name} onclick=get${attr.name?cap_first}()></BUTTON>
							<SPAN id=${attr.name}_span ></SPAN>
							<#if '${attr.notnull}'='true'><SPAN id="${attr.name}image"><IMG src='/images/BacoError.gif' align=absMiddle></SPAN></#if>
							<input type="hidden" name="${attr.name}"  id="${attr.name}"/>  
							<SCRIPT language="javascript"> 
							function get${attr.name?cap_first}(){
									WdatePicker({lang:languageStr,el:'${attr.name}_span',onpicked:function(dp){
											$dp.$('${attr.name}').value = dp.cal.getDateStr();<#if '${attr.notnull}'=='true'>
											see${attr.name?cap_first}();</#if>
									},oncleared:function(dp){
											$dp.$('${attr.name}').value = '';<#if '${attr.notnull}'=='true'>
											see${attr.name?cap_first}();</#if>
									}});
							}<#if '${attr.notnull}'=='true'>
							function see${attr.name?cap_first}(){
									if($('#${attr.name}_span').html()==''){
										$('#${attr.name}image').html("<IMG src='/images/BacoError.gif' align=absMiddle>");
									}else{
										$('#${attr.name}image').html('');
									}	
							}</#if>
							</SCRIPT> 
						<#elseif '${attr.type}'='time'>
							<BUTTON type="button" <#if '${attr.notnull}'=='true'>notnull="true" inputType='time' </#if> class=calendar id=px_time_${attr.name} onclick=get${attr.name?cap_first}()></BUTTON>
							<SPAN id=${attr.name}_span ></SPAN>
							<#if '${attr.notnull}'='true'><SPAN id="${attr.name}image"><IMG src='/images/BacoError.gif' align=absMiddle></SPAN></#if>
							<input type="hidden" name="${attr.name}"  id="${attr.name}"/>  
							<SCRIPT language="javascript"> 
							function get${attr.name?cap_first}(){
									WdatePicker({lang:languageStr,dateFmt:'H:mm:ss',el:'${attr.name}_span',onpicked:function(dp){
											$dp.$('${attr.name}').value = dp.cal.getDateStr();<#if '${attr.notnull}'=='true'>
											see${attr.name?cap_first}();</#if>
									},oncleared:function(dp){
											$dp.$('${attr.name}').value = '';<#if '${attr.notnull}'=='true'>
											see${attr.name?cap_first}();</#if>
									}});
							}<#if '${attr.notnull}'=='true'>
							function see${attr.name?cap_first}(){
									if($('#${attr.name}_span').html()==''){
										$('#${attr.name}image').html("<IMG src='/images/BacoError.gif' align=absMiddle>");
									}else{
										$('#${attr.name}image').html('');
									}	
							}</#if>
							</SCRIPT> 
						<#elseif '${attr.type}'='resource'>
							<sq <#if '${attr.notnull}'=='true'>notnull="true" inputType='sq' </#if> type='resource' name="${attr.name}" id="${attr.name}" span="${attr.name}Span"></sq> 
						<#elseif '${attr.type}'='resources'>
							<input class=wuiBrowser type=hidden name="${attr.name}" id="${attr.name}"   _url="/systeminfo/BrowserMain.jsp?url=/hrm/resource/MutiResourceBrowser.jsp"
							 _displayTemplate="<a href='/hrm/resource/HrmResource.jsp?id=#b{id}' target='_blank' >#b{name}</a>&nbsp;" <#if '${attr.notnull}'=='true'>_required='yes' </#if>
							 _trimLeftComma="yes"	  >
							 <span id="${attr.name}span"> </span>  
						<#elseif '${attr.type}'='customers'>
							<input class=wuiBrowser type=hidden name="${attr.name}" id="${attr.name}"  _url="/systeminfo/BrowserMain.jsp?url=/CRM/data/MutiCustomerBrowser.jsp"
							 _displayTemplate="<a href='/CRM/data/ViewCustomer.jsp?CustomerID=#b{id}' target='_blank'>#b{name}</a>&nbsp;" <#if '${attr.notnull}'=='true'>_required='yes' </#if>
							 _trimLeftComma="yes"	  >
							 <span id="${attr.name}span"> </span>  
						<#elseif '${attr.type}'='customer'>
							<sq <#if '${attr.notnull}'=='true'>notnull="true" inputType='sq' </#if> type='customer' name="${attr.name}" id="${attr.name}" span="${attr.name}Span"></sq> 
						<#elseif '${attr.type}'='contact'>
							<INPUT class="wuiBrowser"  _url="/systeminfo/BrowserMain.jsp?url=/CRM/data/Crm_lianxiren_Browser.jsp?crmManager=<%=CustomerID%>" <#if '${attr.notnull}'=='true'>_required='yes' </#if> id="${attr.name}" type=hidden name="${attr.name}"  />
						<#elseif '${attr.type}'='textarea'>
							<textarea <#if '${attr.minLength}'!=''>minLength="${attr.minLength}"</#if> <#if '${attr.maxLength}'!=''>maxLength="${attr.maxLength}"</#if> <#if '${attr.notnull}'=='true'>onchange='checkNotnull("${attr.name}","${attr.name}image")'</#if>  name="${attr.name}" id="${attr.name}" <#if '${attr.cols}'!=''>cols="${attr.cols}"</#if> <#if '${attr.rows}'!=''>rows="${attr.rows}"</#if>></textarea><#if '${attr.notnull}'='true'><SPAN id="${attr.name}image"><IMG src='/images/BacoError.gif' align=absMiddle></SPAN></#if>
						<#elseif '${attr.type}'='select'>
							<#--注释：：：<#list  '${attr.names}'?split(",") as array>${array},</#list>  -->
							<select name="${attr.name}" id="${attr.name}" <#if '${attr.notnull}'=='true'>notnull="true" onchange='checkNotnullSelect(this,"${attr.name}image")' </#if> ><@getOptionStr names=attr.names values=attr.values/></select><#if '${attr.notnull}'='true'><SPAN id="${attr.name}image"><IMG src='/images/BacoError.gif' align=absMiddle></SPAN></#if>
						<#elseif '${attr.type}'='file'>
							 <input class=inputstyle  <#if '${attr.notnull}'='true'>onchange='checkNotnull("${attr.name}","${attr.name}image")'</#if> type="file" maxLength=100 size=30 name="${attr.name}" id="${attr.name}"><#if '${attr.notnull}'='true'><SPAN id="${attr.name}image"><IMG src='/images/BacoError.gif' align=absMiddle></SPAN></#if>  
						<#else> <#-- 下面对很多的input的类型 进行处理验证情况-->  
						  <input   name="${attr.name}"  id="${attr.name}" 
						    <#if  '${attr.type}'='numAndChar'>  valid='numAndChar'
						    <#elseif 	'${attr.type}'='email'>	 valid='email' 
						    <#elseif 	'${attr.type}'='int'> 	valid='num'	
						    <#elseif 	'${attr.type}'='char'> 	valid='char' 
						    <#elseif 	'${attr.type}'='double'> valid='double'	
						    <#else>	</#if>
						    <#if '${attr.minLength}'!=''>minLength="${attr.minLength}"</#if> <#if '${attr.maxLength}'!=''>maxLength="${attr.maxLength}"</#if>
						    <#if '${attr.notnull}'='true'>onchange='checkNotnull("${attr.name}","${attr.name}image")'</#if> 
						     /><#if '${attr.notnull}'='true'><SPAN id="${attr.name}image"><IMG src='/images/BacoError.gif' align=absMiddle></SPAN></#if> 
						</#if>
						<#--  #if '${attr.notnull}'='true'>
						<SPAN id=FirstNameimage><IMG src="/images/BacoError.gif" align=absMiddle></SPAN>
						</#if--> 
			</TD></TR>	
					<tr  style="height: 1px"><td class=Line colspan=2></td></tr>				
				</#if></#if>
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
<SCRIPT language="javascript" src="/CRM/data/MySelectTool.js"></script>
</HTML>

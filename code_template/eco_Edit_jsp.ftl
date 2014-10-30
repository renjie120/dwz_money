<#include "/com.renjie120.codegenerate.common.ftl"><%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ include file="/systeminfo/init.jsp" %>
<%@ page import="weaver.general.Util" %> 
<jsp:useBean id="CustomerInfoComInfo" class="weaver.crm.Maint.CustomerInfoComInfo" scope="page" />
<jsp:useBean id="LanguageComInfo" class="weaver.systeminfo.language.LanguageComInfo" scope="page" />
<jsp:useBean id="${nm}RecordSet" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="Util" class="weaver.general.Util" scope="page" />
<jsp:useBean id="RecordSet" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="RecordSetFF" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="RecordSetShare" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="ResourceComInfo" class="weaver.hrm.resource.ResourceComInfo" scope="page"/>
<jsp:useBean id="CheckUserRight" class="weaver.systeminfo.systemright.CheckUserRight" scope="page" />
<jsp:useBean id="RecordSetV" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="CustomerContacterComInfo" class="weaver.crm.Maint.CustomerContacterComInfo" scope="page" />
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
${nm}RecordSet.executeProc("${model.table}_SelectByID", ID);
 
if (${nm}RecordSet.getCounts() <= 0) {
 response.sendRedirect("/base/error/DBError.jsp");
 return;
}
${nm}RecordSet.first();

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
RCMenu += "{保存,javascript:onSave(this),_top} " ;
RCMenuHeight += RCMenuHeightStep ;
if(!isfromtab){
	RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:location.href='${model.arg1}"+CustomerID+"&ID="+ID+"isfromtab=true',_top} " ;
	RCMenuHeight += RCMenuHeightStep ;
}else{
	RCMenu += "{"+SystemEnv.getHtmlLabelName(201,user.getLanguage())+",javascript:location.href='${model.arg1}"+CustomerID+"&isfromtab=true',_top} " ;
	RCMenuHeight += RCMenuHeightStep ;
}
%>

<%@ include file="/systeminfo/RightClickMenu.jsp" %>
<FORM id=weaver name="weaver" action="/CRM/data/${model.className}_operation.jsp" method=post onsubmit='return check_form(this,"<#list model.attributes as attr><#if '${attr.notnull}'='true'>${attr.name},</#if></#list>")' enctype="multipart/form-data"> 
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
	<input type="hidden" name="method" value="edit">
	<input type="hidden" name="CustomerID" value="<%=CustomerID%>">
	<input type="hidden" name="ID" value="<%=Util.toScreen(${nm}RecordSet.getString("${model.keyColumn}"), user.getLanguage())%>">
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
					<#if '${attr.noedit}'!='true'>	
					<TR>
						<TD>${attr.desc}</TD>
						<TD class=Field>
						<#if '${attr.type}'='date'>
							<BUTTON type="button" <#if '${attr.notnull}'=='true'>notnull="true"</#if> class=calendar id=px_time_a onclick=get${attr.name?cap_first}()></BUTTON>
							<SPAN id=${attr.name}_span ><%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%></SPAN><SPAN id="${attr.name}image"><%if("".equals(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()))){%><IMG src='/images/BacoError.gif' align=absMiddle><%}%></SPAN>
							<#if '${attr.notnull}'='true'><SPAN id="${attr.name}image"></SPAN></#if>
							<input type="hidden" name="${attr.name}"  id="${attr.name}" value='<%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%>'/>  
							<SCRIPT language="javascript"> 
							function get${attr.name?cap_first}(){
									WdatePicker({lang:languageStr,el:'${attr.name}_span',onpicked:function(dp){
										$dp.$('${attr.name}').value = dp.cal.getDateStr();<#if '${attr.notnull}'=='true'>
										see${attr.name?cap_first}();</#if>
									},oncleared:function(dp){$dp.$('${attr.name}').value = '';<#if '${attr.notnull}'=='true'>
										see${attr.name?cap_first}();</#if>}});
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
							<BUTTON type="button" <#if '${attr.notnull}'=='true'>notnull="true"</#if> class=calendar id=px_time_a onclick=get${attr.name?cap_first}()></BUTTON>
							<SPAN id=${attr.name}_span ><%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%></SPAN><SPAN id="${attr.name}image"><%if("".equals(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()))){%><IMG src='/images/BacoError.gif' align=absMiddle><%}%></SPAN>
							<#if '${attr.notnull}'='true'><SPAN id="${attr.name}image"></SPAN></#if>
							<input type="hidden" name="${attr.name}"  id="${attr.name}" value='<%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%>'/>  
							<SCRIPT language="javascript"> 
							function get${attr.name?cap_first}(){
									WdatePicker({lang:languageStr,dateFmt:'H:mm:ss',el:'${attr.name}_span',onpicked:function(dp){
										$dp.$('${attr.name}').value = dp.cal.getDateStr();<#if '${attr.notnull}'=='true'>
										see${attr.name?cap_first}();</#if>
									},oncleared:function(dp){$dp.$('${attr.name}').value = '';<#if '${attr.notnull}'=='true'>
										see${attr.name?cap_first}();</#if>}});
							}<#if '${attr.notnull}'=='true'>
							function see${attr.name?cap_first}(){
									if($('#${attr.name}_span').html()==''){
										$('#${attr.name}image').html("<IMG src='/images/BacoError.gif' align=absMiddle>");
									}else{
										$('#${attr.name}image').html('');
									}	
							}</#if>
							</SCRIPT> 
						<#elseif '${attr.type}'='resources'>
							<input class=wuiBrowser type=hidden name="${attr.name}" value="<%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%>"
							   _url="/systeminfo/BrowserMain.jsp?url=/hrm/resource/MutiResourceBrowser.jsp"
							   _displayTemplate="<a href='/hrm/resource/HrmResource.jsp?id=#b{id}' target='_blank' >#b{name}</a>&nbsp;"
							   _trimLeftComma="yes" <#if '${attr.notnull}'=='true'>_required='yes' </#if>
							   >
							   <span id="callersspan">
							   <%if(!"".equals(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()))){
							    ArrayList array${attr.name}s = Util.TokenizerString(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()),",");
							    for(int i=0;i<array${attr.name}s.size();i++){
							   %>
							      <A href='/hrm/resource/HrmResource.jsp?id=<%=""+array${attr.name}s.get(i)%>' target='_blank' ><%=ResourceComInfo.getResourcename(""+array${attr.name}s.get(i))%></a>&nbsp
							   <%}}%>
							   </span> 
						<#elseif '${attr.type}'='customers'>
							<input class=wuiBrowser type=hidden name="${attr.name}" value="${nm}RecordSet.getString("${attr.column}"), user.getLanguage())"
							   _url="/systeminfo/BrowserMain.jsp?url=/CRM/data/MutiCustomerBrowser.jsp" <#if '${attr.notnull}'=='true'>_required='yes' </#if>
							   _displayTemplate="<a href='/CRM/data/ViewCustomer.jsp?CustomerID=#b{id}' target='_blank'>#b{name}</a>&nbsp;">
							   <span id="${attr.name}span">
							   <%if(!"".equals(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()))){
							    ArrayList array${attr.name}s = Util.TokenizerString(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()),",");
							    for(int i=0;i<array${attr.name}s.size();i++){
							   %>
							      <A href='/CRM/data/ViewCustomer.jsp?CustomerID=<%=""+array${attr.name}s.get(i)%>'><%=CustomerInfoComInfo.getCustomerInfoname(""+array${attr.name}s.get(i))%></a>&nbsp
							   <%}}%>
							   </span> 
						<#elseif '${attr.type}'='resource'>
							<sq type='resource' spanName='<%=Util.toScreen(ResourceComInfo.getResourcename(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())),user.getLanguage())%>' value='<%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%>' <#if '${attr.notnull}'=='true'>notnull="true"</#if> name="${attr.name}" id="${attr.name}" span="${attr.name}Span"></sq> 
						<#elseif '${attr.type}'='customer'>
							<sq type='customer'  spanName='<%=Util.toScreen(ResourceComInfo.getResourcename(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())),user.getLanguage())%>'  value='<%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%>' <#if '${attr.notnull}'=='true'>notnull="true"</#if> name="${attr.name}" id="${attr.name}"  span="${attr.name}Span"></sq>
						<#elseif '${attr.type}'='textarea'>
							<textarea name="${attr.name}"  <#if '${attr.minLength}'!=''>minLength="${attr.minLength}"</#if> <#if '${attr.maxLength}'!=''>maxLength="${attr.maxLength}"</#if> <#if '${attr.notnull}'=='true'>onchange='checkNotnull("${attr.name}","${attr.name}image")'</#if>  id="${attr.name}" <#if '${attr.cols}'!=''>cols="${attr.cols}"</#if> <#if '${attr.rows}'!=''>rows="${attr.rows}"</#if>><%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%></textarea><SPAN id="${attr.name}image"><%if("".equals(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()))){%><IMG src='/images/BacoError.gif' align=absMiddle><%}%></SPAN>
						<#elseif '${attr.type}'='contact'>
							<INPUT class="wuiBrowser"  _url="/systeminfo/BrowserMain.jsp?url=/CRM/data/Crm_lianxiren_Browser.jsp?crmManager=<%=CustomerID%>" <#if '${attr.notnull}'=='true'>_required='yes' </#if> id="${attr.name}" type=hidden name="${attr.name}"  />
							<span id="${attr.name}span">
							   <%if(!"".equals(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()))){
							    String str${attr.name} =  Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()) ; 
							   %>
							      <A href='/CRM/data/ViewContacter.jsp?ContacterID=<%=str${attr.name}%>'><%=Util.toScreen(CustomerContacterComInfo.getCustomerContacternameByID( str${attr.name}),user.getLanguage())%></a>&nbsp
							   <%} %>
							   </span> 
						<#elseif '${attr.type}'='select'>
							<#--注释：：：<#list  '${attr.names}'?split(",") as array>${array},</#list>  -->
							<select name="${attr.name}"  <#if '${attr.notnull}'=='true'>notnull="true" onchange='checkNotnullSelect(this,"${attr.name}image")' </#if>  id="${attr.name}" selectVal='<%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%>'><@getOptionStr names=attr.names values=attr.values/></select><SPAN id="${attr.name}image"><%if("".equals(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()))){%><IMG src='/images/BacoError.gif' align=absMiddle><%}%></SPAN>
						<#else> <#-- 下面对很多的input的类型 进行处理验证情况-->    
						  <input   name="${attr.name}"  id="${attr.name}" 
						    <#if  '${attr.type}'='numAndChar'>  valid='numAndChar'
						    <#elseif 	'${attr.type}'='email'>	 valid='email' 
						    <#elseif 	'${attr.type}'='int'> 	valid='num'	
						    <#elseif 	'${attr.type}'='char'> 	valid='char' 
						    <#elseif 	'${attr.type}'='double'> valid='double'	
						    <#else>	</#if>
						    <#if '${attr.minLength}'!=''>minLength="${attr.minLength}"</#if>
						    <#if '${attr.notnull}'=='true'>onchange='checkNotnull("${attr.name}","${attr.name}image")'</#if> 
						    <#if '${attr.maxLength}'!=''>maxLength="${attr.maxLength}"</#if> value='<%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%>' /><#if '${attr.notnull}'=='true'> <SPAN id="${attr.name}image"><%if("".equals(Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage()))){%><IMG src='/images/BacoError.gif' align=absMiddle><%}%></SPAN></#if>
						</#if> 
			</TD></TR>	
					<tr  style="height: 1px"><td class=Line colspan=2></td></tr>				
				<#else>
					<input type='hidden'  name="${attr.name}"  id="${attr.name}" value='<%=Util.toScreen(${nm}RecordSet.getString("${attr.column}"), user.getLanguage())%>'/>
				</#if>
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
</form>
</BODY> 
<SCRIPT language="javascript" src="/js/datetime.js"></script>
<SCRIPT language="javascript" src="/js/JSDateTime/WdatePicker.js"></script> 
<script language=javascript src="/js/checkData.js"></script> 
<SCRIPT language="javascript" src="/CRM/data/MySelectTool.js"></script>
<script type="text/javascript">
<!--
function onSave(obj){
	window.onbeforeunload=null; 
	if(check_form(weaver,'<#list model.attributes as attr><#if '${attr.notnull}'='true'>${attr.name},</#if></#list>')){
		obj.disabled=true;
		weaver.submit();
	}
}
//-->
</script>
</HTML>

<#include "/com.renjie120.codegenerate.common.ftl"><%@ page language="java" contentType="text/html; charset=GBK" %> <%@ include file="/systeminfo/init.jsp" %>
<%@ page import="weaver.general.Util" %>
<jsp:useBean id="${nm}Rs" class="weaver.conn.RecordSet" scope="page" />   
<jsp:useBean id="ResourceComInfo" class="weaver.hrm.resource.ResourceComInfo" scope="page"/>
<jsp:useBean id="RequestComInfo" class="weaver.workflow.request.RequestComInfo" scope="page"/>
<jsp:useBean id="CustomerInfoComInfo" class="weaver.crm.Maint.CustomerInfoComInfo" scope="page" />

<%
String crmId = Util.null2String(request.getParameter("CustomerID"));
int viewType = Util.getIntValue(request.getParameter("viewtype"), 0);
boolean isfromtab =  Util.null2String(request.getParameter("isfromtab")).equals("true")?true:false;
String userId = String.valueOf(user.getUID());
String userType = user.getLogintype();
userType="1";  //表WorkPlanShareDetail usertype字段都是为1，所以，如果客户门户登陆的话，永远查询不到数据
%>


<HTML><HEAD>
<%if(isfromtab) {%>
<base target='_blank'/>
<%} %>
<LINK href="/css/Weaver.css" type=text/css rel=STYLESHEET>
</HEAD>
<%
String imagefilename = "/images/hdReport.gif";
String titlename = SystemEnv.getHtmlLabelName(6082,user.getLanguage())
					+ " - " + SystemEnv.getHtmlLabelName(136,user.getLanguage())
					+ ":&nbsp;<A href='/CRM/data/ViewCustomer.jsp?CustomerID=" + crmId + "'>"
					+ Util.toScreen(CustomerInfoComInfo.getCustomerInfoname(crmId),user.getLanguage())+"</A>";

String needfav ="1";
String needhelp ="";
String currentvalue = "";
%>
<BODY>  
<%@ include file="/systeminfo/RightClickMenuConent.jsp" %> 
<%@ include file="/systeminfo/RightClickMenu.jsp" %>  
<%
String sql${bignm} = "select *  from ${model.table} order by ${model.keyColumn} desc";
${nm}Rs.executeSql(sql${bignm});
%> 
<script type="text/javascript">
<!-- 
	function view${bignm}(obj){
	   document.location.href="/CRM/data/View${bignm}.jsp?ID="+$(obj).attr('idtag');
	}

	function add${bignm}(){
		document.location.href = 
			"/CRM/data/${model.className}_add.jsp?add=1&CustomerID=<%=crmId%>&isfromtab=<%=isfromtab%>";
	}
	function see${bignm}(obj){
		 document.location.href = 
			"/CRM/data/${model.className}_view.jsp?CustomerID=<%=crmId%>&ID="+$(obj).attr('idtag');	
	} 
	function edit${bignm}(obj){
		 document.location.href = 
			"/CRM/data/${model.className}_edit.jsp?CustomerID=<%=crmId%>&ID="+$(obj).attr('idtag')+"&isfromtab=<%=isfromtab%>";		
	} 
	function delete${bignm}(obj){
		 if(confirm("确定要删除该${model.classDesc}信息吗？")){
			$.ajax({
			  type:'POST',
		      url:'/CRM/data/${model.className}_operation.jsp?method=delete&ID='+$(obj).attr('idtag')+"&CustomerID=<%=crmId%>",  
			  success: function(){  
				$(obj).parent().parent().hide(200);
			  }
			 });
		  } 
	} 
//-->
</script> 
<div style='float:top;width:100%'>
	<button onclick='add${bignm}()'>添加${model.classDesc} </button>
	<TABLE class="ListStyle">
		<COLGROUP> 	
		<#list model.attributes as attr> 
			<#if "${attr.visible}"!='false'>
			<COL width="${attr.width}"> 
			</#if>
		</#list>   
		<TBODY> 
			<TR class=Header>
				<#list model.attributes as attr> 
					<#if "${attr.visible}"!='false'>
				<th style="TEXT-ALIGN: center">${attr.desc} </th>
					</#if>
				</#list>   
			    <th style="TEXT-ALIGN: center">操作</th>
			</TR>

			<TR class=Line><TD colSpan=7 style="padding: 0"></TD></TR>
				<%
				boolean isLight${bignm} = false; 
				<#list model.attributes as attr> 
				String ${attr.name} = ""; 
				</#list>    
				while (${nm}Rs.next()) {
					<#list model.attributes as attr> 
				${attr.name} = Util.null2String(${nm}Rs.getString("${attr.column}"));
					</#list>     
					if(isLight${bignm})
					{%>	
						<TR CLASS=DataLight>
					<%		}else{%>
						<TR CLASS=DataDark>
					<%} 
					%>
					<#list model.attributes as attr> 
					<#if "${attr.visible}"!='false'>
					<#if "${attr.type}"=='resource'>
					<td><A href="/hrm/resource/HrmResource.jsp?id=<%=${attr.name}%>" ><%=Util.toScreen(ResourceComInfo.getResourcename(${attr.name}),user.getLanguage())%></a></td>					
					<#elseif "${attr.type}"=='contact'>
					<td><A href="/CRM/data/ViewContacter.jsp?ContacterID=<%=${attr.name}%>" ><%=Util.toScreen(CustomerContacterComInfo.getCustomerContacternameByID(${attr.name}),user.getLanguage())%></a></td>					
					<#elseif "${attr.type}"=='customers'>
					<td><%if(!"".equals(${attr.name})){
							    ArrayList array${attr.name}s = Util.TokenizerString(${attr.name},",");
							    for(int i=0;i<array${attr.name}s.size();i++){
							   %><A href='/CRM/data/ViewCustomer.jsp?CustomerID=<%=""+array${attr.name}s.get(i)%>'><%=CustomerInfoComInfo.getCustomerInfoname(""+array${attr.name}s.get(i))%></a>&nbsp
							   <%}}%></td>					
					<#elseif "${attr.type}"=='resources'>
					<td><%if(!"".equals(${attr.name})){
							    ArrayList array${attr.name}s = Util.TokenizerString(${attr.name},",");
							    for(int i=0;i<array${attr.name}s.size();i++){
							   %><A href='/hrm/resource/HrmResource.jsp?id=<%=""+array${attr.name}s.get(i)%>'><%=ResourceComInfo.getResourcename(""+array${attr.name}s.get(i))%></a>&nbsp
							   <%}}%></td>	
					<#elseif "${attr.type}"=='customer'>
					<td><A href="/CRM/data/ViewCustomer.jsp?CustomerID=<%=${attr.name}%>" ><%=Util.toScreen(CustomerInfoComInfo.getCustomerInfoname(${attr.name}),user.getLanguage())%></a></td>					
					<#else>
					<td><%=${attr.name}%></td>
					</#if>
					</#if>
					</#list>    
					<td><button   onclick='see${bignm}(this)' idtag='<%=${model.keyName}%>' >查看</button>
					&nbsp;<button   onclick='delete${bignm}(this)' idtag='<%=${model.keyName}%>' >删除</button>
					&nbsp;<button onclick='edit${bignm}(this)' idtag='<%=${model.keyName}%>' >编辑</button> 
					</td>
					<%
							isLight${bignm} = !isLight${bignm};
					}	
					%>	
				</tr>
		</TBODY>
	</TABLE>
</div>   
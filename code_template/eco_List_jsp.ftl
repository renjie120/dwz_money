<#include "/com.renjie120.codegenerate.common.ftl">
<%@ page language="java" contentType="text/html; charset=GBK" %> <%@ include file="/systeminfo/init.jsp" %>
<%@ page import="weaver.general.Util" %>
 
<jsp:useBean id="${nm}Rs" class="weaver.conn.RecordSet" scope="page" />  

<HTML><HEAD>
<%if(isfromtab) {%>
<base target='_blank'/>
<%} %>
<LINK href="/css/Weaver.css" type=text/css rel=STYLESHEET>
</HEAD>
<% 
String titlename = "${model.classDesc}";  
%>
<BODY>


<%
String sql${bignm} = "select *  from ${model.table} order by ${model.keyName} desc";
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
	function delete${bignm}(obj){
		 if(confirm("确定要删除该${model.classDesc}信息吗？")){
			$.ajax({
			  type:'POST',
		      url:'/CRM/data/${model.className}_operation.jsp?method=delete&ID='+$(obj).attr('idtag'), 
			  success: function(){  
				$(obj).parent().parent().hide(200);
			  }
			 });
		  } 
	} 
//-->
</script> 
<div style='float:left;width:100%'>
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
					<td><%=${attr.name}%></td>
						</#if>
					</#list>    
					<td><button onclick='delete${bignm}(this)' idtag='<%=${model.keyName}%>' >删除</button></td>
					<%
							isLight${bignm} = !isLight${bignm};
					}	
					%>	
				</tr>
		</TBODY>
	</TABLE>
</div>   
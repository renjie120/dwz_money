<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="java.io.*"%>
<%@ page import="common.struts2.JavaBeanTest"%>
<%
	Map m = new HashMap();
	m.put("1", "test1");
	m.put("2", "test2");
	m.put("3", "test3");
	m.put("4", "test4");
	m.put("5", "test5");
	
	request.setAttribute("maptest",m);
	
	String testStr = "test1,test2,test3,test4";
	
	session.setAttribute("testStr",testStr);
	request.setAttribute("teststr","test1,test2,test3,test4");
%>
<table>
	<s:if test="'bar' in {'bar','foo'}">
		<s:iterator value="ognlList" status="st">
			<tr <s:if test="#st.odd">style="background-color:#bbbbbb"</s:if>>
				<td>
					用户名：
					<s:property value="user" />
					密码：
					<s:property value="pass" />
				</td>
			</tr>
		</s:iterator>
	</s:if>
</table>
<br>
<table>  
	<!-- 下面输出map对象 -->
	<s:iterator value="#{'name':'lsq1','pass':'123456','address':'上海杨高南路'}" id='test'
		status="st">
		<tr <s:if test="#st.odd">style="background-color:#bbbbbb"</s:if>>
			<td>
				map中的key：
				<s:property value="key" />
				map中的value：
				<s:property value="value" />
			</td>
		</tr>
	</s:iterator> 
</table>
<br>

<s:iterator value="maptest"> 
${key} -- ${value} <br> 
</s:iterator>   

<table>   
	<s:generator val="#testStr" separator=",">
		<s:iterator status="st">
		<tr <s:if test="#st.odd">style="background-color:#bbbbbb"</s:if>>
			<td> 
				<s:property />
			</td>
		</tr>
		</s:iterator>
	</s:generator> 
</table>
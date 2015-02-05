
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/systemsequence!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/systemsequence!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						序列代码 </td><td>
							<input name="sequenceCode" size="20"  class="textInput " type="text"  value="<s:property value="vo.sequenceCode"/>" />
					</td> 
					<td> 
						序列名称</td><td>
							<input name="sequenceName" size="20"  class="textInput " type="text"  value="<s:property value="vo.sequenceName"/>" />
					</td> 
					<td> 
						序列值</td><td>
							<input name="sequenceContent" size="20"  class="textInput " type="text"  value="<s:property value="vo.sequenceContent"/>" />
					</td> 
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									检索
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="/money/systemsequence!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/systemsequence!initImport.do" target="dialog"><span>从EXCEL导入</span> </a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="250"    orderField="SEQUENCECODE" >
						序列代码  
				</th> 
				<th width="250"    orderField="SEQUENCENAME" >
						序列名称 
				</th> 
				<th width="80"    orderField="SEQUENCECONTENT" >
						序列值 
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td style="text-align:center;">
						<input name="ids" value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td style="text-align:center;">
						<div style='width:250px'><s:property value="sequenceCode" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:250px'><s:property value="sequenceName" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:80px'><s:property value="sequenceContent" /></div>
					</td> 
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value,pageNum:1})">
				<option value="20"
					<%if((request.getAttribute("numPerPage")+"").equals("20")){%>
					selected <%} %>>
					20
				</option>
				<option value="50"
					<%if((request.getAttribute("numPerPage")+"").equals("50")){%>
					selected <%} %>>
					50
				</option>
				<option value="100"
					<%if((request.getAttribute("numPerPage")+"").equals("100")){%>
					selected <%} %>>
					100
				</option>
				<option value="200"
					<%if((request.getAttribute("numPerPage")+"").equals("200")){%>
					selected <%} %>>
					200
				</option>
			</select>
			<span>条，总共${totalCount}条记录</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>


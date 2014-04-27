
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/stockmanager!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/stockmanager!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						股票号码</td><td>
								<input  name="stockNo" id="stockNo" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						股票名称 </td><td>
								<input id="stockName" name="stockName" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						交易类型</td><td>
								<my:newselect tagName="dealType"  paraType="dealType" width="100" allSelected="true" />
					</td> 
					<td> 
						交易分组</td><td>
								<input name="dealGroup" class="textInput" size="30" type="text"   />
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
					<li>
						<a class="button" href="/money/stockmanager!beforeQuery.do"
							target="dialog" mask="true" title="查询框"><span>高级检索</span> </a>
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
				<a class="add" href="/money/stockmanager!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/stockmanager!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/stockmanager!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/stockmanager!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>

		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="STOCKNO" >
						股票号码 
				</th> 
				<th width="100"    orderField="STOCKNAME" >
						股票名称  
				</th> 
				<th width="100"    orderField="DEALDATE" >
						交易时间 
				</th> 
				<th width="100"    orderField="PRICE" >
						交易价格 
				</th> 
				<th width="100"    orderField="DEALNUMBER" >
						交易份额 
				</th> 
				<th width="100"    orderField="FEE" >
						交易费率 
				</th> 
				<th width="100"    orderField="DEALTYPE" >
						交易类型 
				</th> 
				<th width="100"    orderField="DEALGROUP" >
						交易分组 
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td>
						<input name="ids" value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="stockNo" />
					</td> 
					<td>
						<s:property value="stockName" />
					</td> 
					<td>
						<s:property value="dealDate" />
					</td> 
					<td>
						<s:property value="price" />
					</td> 
					<td>
						<s:property value="dealNumber" />
					</td> 
					<td>
						<s:property value="fee" />
					</td> 
					<td>
						<s:property value="dealType" />
					</td> 
					<td>
						<s:property value="dealGroup" />
					</td> 
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value,arglist:'stockNo,stockName,dealType',pageNum:1})">
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
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" arglist="stockNo,stockName,dealType"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>


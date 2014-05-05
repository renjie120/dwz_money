
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/orderbasemanager!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/orderbasemanager!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						订单</td><td>
								<input name="orderNo" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						客户名称</td><td>
								<input name="customerNo" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						整流变压器型号</td><td>
								<input name="bianyaXinghao" class="textInput" size="30" type="text"   />
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
						<a class="button" href="/money/orderbasemanager!beforeQuery.do"
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
				<a class="add" href="/money/orderbasemanager!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/orderbasemanager!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/orderbasemanager!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/orderbasemanager!export.do" target="dwzExport"
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
				<th width="100"    orderField="ORDERNO" >
						订单 
				</th> 
				<th width="100"    orderField="CUSTOMERNO" >
						客户名称 
				</th> 
				<th width="100"    orderField="GONGLV" >
						功率 
				</th> 
				<th width="100"    orderField="DIANYA" >
						电压 
				</th> 
				<th width="100"    orderField="SHIDAI" >
						世代 
				</th> 
				<th width="100"    orderField="BIANYACHANGJIA" >
						整流变压器厂家 
				</th> 
				<th width="100"    orderField="BIANYAXINGHAO" >
						整流变压器型号 
				</th> 
				<th width="100"    orderField="ISIMPORT" >
						是否重点客户 
				</th> 
				<th width="100"    orderField="STARTDATE" >
						计划开工时间 
				</th> 
				<th width="100"    orderField="ENDDATE" >
						计划结束时间 
				</th> 
				<th width="100"    orderField="CURRENTSTATE" >
						当前状态 
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
						<s:property value="orderNo" />
					</td> 
					<td>
						<s:property value="customerNo" />
					</td> 
					<td>
						<s:property value="gongLv" />
					</td> 
					<td>
						<s:property value="dianYa" />
					</td> 
					<td>
						<s:property value="shiDai" />
					</td> 
					<td>
						<s:property value="bianyaChangjia" />
					</td> 
					<td>
						<s:property value="bianyaXinghao" />
					</td> 
					<td>
						<s:property value="isImport" />
					</td> 
					<td>
						<s:property value="startDate" />
					</td> 
					<td>
						<s:property value="endDate" />
					</td> 
					<td>
						<s:property value="currentState" />
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


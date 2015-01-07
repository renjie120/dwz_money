
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/insuredunit!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/insuredunit!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						编号</td><td>
								<input name="unitCode" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						投保单位 </td><td>
								<input name="unitName" class="textInput" size="30" type="text"   />
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
						<a class="button" href="/money/insuredunit!beforeQuery.do"
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
				<a class="add" href="/money/insuredunit!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/insuredunit!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/insuredunit!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/insuredunit!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/insuredunit!initImport.do" target="dialog"><span>从EXCEL导入</span> </a>
			</li> 

		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="UNITCODE" >
						编号 
				</th> 
				<th width="100"    orderField="UNITNAME" >
						投保单位  
				</th> 
				<th width="100"    orderField="CONTACTNAME" >
						联系人 
				</th> 
				<th width="100"    orderField="CONTACTMOBILE" >
						手机 
				</th> 
				<th width="100"    orderField="CONTACTEMAIL" >
						邮箱 
				</th> 
				<th width=""    orderField="UNITPARENTID" >
						上级单位 
				</th> 
				<th width="100"    orderField="UNITSTATE" >
						状态 
				</th> 
				<th width="100"    orderField="UNITADDRESS" >
						地址 
				</th> 
				<th width="100"    orderField="UNITREMARK" >
						备注 
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
						<s:property value="unitCode" />
					</td> 
					<td>
						<s:property value="unitName" />
					</td> 
					<td>
						<s:property value="contactName" />
					</td> 
					<td>
						<s:property value="contactMobile" />
					</td> 
					<td>
						<s:property value="contactEmail" />
					</td> 
					<td>
						<s:property value="unitParentId" />
					</td> 
					<td>
						<s:property value="unitState" />
					</td> 
					<td>
						<s:property value="unitAddress" />
					</td> 
					<td>
						<s:property value="unitRemark" />
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


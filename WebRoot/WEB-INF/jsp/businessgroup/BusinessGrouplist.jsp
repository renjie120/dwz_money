
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/businessgroup!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/businessgroup!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						集团编号</td><td>
							<input name="groupSno"   class="textInput " type="text"  value="<s:property value="vo.groupSno"/>" />
					</td> 
					<td> 
						集团名称 </td><td>
							<input name="groupName"   class="textInput " type="text"  value="<s:property value="vo.groupName"/>" />
					</td> 
					<td> 
						状态</td><td>
							<my:newselect tagName="groupStatus"  paraType="shopman_status" width="100" allSelected="true" />
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
				<a class="add" href="/money/businessgroup!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/businessgroup!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/businessgroup!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/businessgroup!export.do" target="dwzExport"
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
				<th width="100"    orderField="GROUPSNO" >
						集团编号 
				</th> 
				<th width="100"    orderField="GROUPNAME" >
						集团名称  
				</th> 
				<th width="100"    orderField="GROUPEMAIL" >
						邮箱 
				</th> 
				<th width="100"    orderField="GROUPCONTACT" >
						联系人名称 
				</th> 
				<th width="100"    orderField="GROUPCONTACTPHONE" >
						联系电话 
				</th> 
				<th width="100"    orderField="GROUPCONTACTMOBILE" >
						联系人手机 
				</th> 
				<th width="100"    orderField="GROUPSTATUS" >
						状态 
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
						<s:property value="groupSno" />
					</td> 
					<td style="text-align:center;">
						<s:property value="groupName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="groupEmail" />
					</td> 
					<td style="text-align:center;">
						<s:property value="groupContact" />
					</td> 
					<td style="text-align:center;">
						<s:property value="groupContactPhone" />
					</td> 
					<td style="text-align:center;">
						<s:property value="groupContactMobile" />
					</td> 
					<td style="text-align:center;">
						<s:property value="groupStatus" />
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


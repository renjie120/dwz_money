
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/myuser!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/myuser!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						用户流水号
								<input name="useId" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						用户名
								<input name="userName" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						登陆号
								<input name="loginId" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						组织机构
								<input name="orgId" class="textInput" size="30" type="text"   />
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
						<a class="button" href="/money/myuser!beforeQuery.do"
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
				<a class="add" href="/money/myuser!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/myuser!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/myuser!beforeUpdate.do?useId={useId}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="edit" href="/money/myuser!initPass.do" postType="string"
					target="selectedTodo" rel="ids"  title="密码初始化"><span>密码初始化</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/myuser!export.do" target="dwzExport"
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
				<th width="100"    orderField="USEID" >
						用户流水号 
				</th> 
				<th width="100"    orderField="USERNAME" >
						用户名 
				</th> 
				<th width="100"    orderField="LOGINID" >
						登陆号 
				</th> 
				<th width="100"    orderField="ORGID" >
						组织机构 
				</th> 
				<th width="50"    orderField="EMAIL" >
						邮件 
				</th> 
				<th width="50"    orderField="PHONE" >
						座机 
				</th> 
				<th width="50"    orderField="MOBILE" >
						手机 
				</th> 
				<th width="50"    orderField="USERTYPE" >
						用户类型 
				</th> 
				<th width="50"    orderField="ADDRESS" >
						地址 
				</th> 
				<th width="50"    orderField="ORDERID" >
						排序号 
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="useId" rel="<s:property value="useId" />">
					<td>
						<input name="ids" value="<s:property value="useId" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="useId" />
					</td> 
					<td>
						<s:property value="userName" />
					</td> 
					<td>
						<s:property value="loginId" />
					</td> 
					<td>
						<s:property value="orgName" />
					</td> 
					<td>
						<s:property value="email" />
					</td> 
					<td>
						<s:property value="phone" />
					</td> 
					<td>
						<s:property value="mobile" />
					</td> 
					<td> 
						<s:property value="userTypeName" />
					</td> 
					<td>
						<s:property value="address" />
					</td> 
					<td>
						<s:property value="orderId" />
					</td> 
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
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


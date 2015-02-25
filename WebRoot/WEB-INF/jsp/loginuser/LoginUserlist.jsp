
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/loginuser!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/loginuser!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						用户姓名</td><td>
							<input name="userName"   class="textInput " type="text"  value="<s:property value="vo.userName"/>" />
					</td> 
					<td> 
						登录名称 </td><td>
							<input name="userId"   class="textInput " type="text"  value="<s:property value="vo.userId"/>" />
					</td> 
					<td> 
						所属类别 </td><td>
							<my:newselect tagName="userType"  paraType="aiduyonghu" width="100" allSelected="true" />
					</td> 
					<td> 
						所属单位 </td><td>
							<input name="userUnit"   class="textInput " type="text"  value="<s:property value="vo.userUnit"/>" />
					</td> 
					<td> 
						用户状态 </td><td>
							<my:newselect tagName="userStatus"  paraType="yesorno_status" width="100" allSelected="true" />
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
				<a class="add" href="/money/loginuser!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/loginuser!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/loginuser!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="edit" href="/money/loginuser!initPass.do" postType="string"
					target="selectedTodo" rel="ids"  title="密码初始化"><span>密码初始化</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/loginuser!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/loginuser!initImport.do" target="dialog"><span>导入</span> </a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="USERNAME" >
						用户姓名 
				</th> 
				<th width="100"    orderField="USERID" >
						登录名称  
				</th> 
				<th width="100"    orderField="USERTYPE" >
						所属类别  
				</th> 
				<th width="100"    orderField="USERUNIT" >
						所属单位  
				</th> 
				<th width="100"    orderField="USERSTATUS" >
						用户状态  
				</th> 
				<th width="100"    orderField="USERPHONE" >
						联系电话 
				</th> 
				<th width="100"    orderField="USEREMAIL" >
						邮箱 
				</th> 
				<th width="100"    orderField="USERADDRESS" >
						地址 
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
						<s:property value="userName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="userId" />
					</td> 
					<td style="text-align:center;">
						<s:property value="userType" />
					</td> 
					<td style="text-align:center;">
						<s:property value="userUnit" />
					</td> 
					<td style="text-align:center;">
						<s:property value="userStatus" />
					</td> 
					<td style="text-align:center;">
						<s:property value="userPhone" />
					</td> 
					<td style="text-align:center;">
						<s:property value="userEmail" />
					</td> 
					<td style="text-align:center;">
						<s:property value="userAddress" />
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


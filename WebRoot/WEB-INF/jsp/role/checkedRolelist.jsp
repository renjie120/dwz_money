<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/org.js" type="text/javascript" />
<script type="text/javascript">/**
 * 保存角色授权的结果.
 */
function saveRole(){
	 
}
function navTabAjaxDone(json) {
 DWZ.ajaxDone(json);
 //刷新一个tab页里面的某一个div
 navTabPageBreak({}, 'myUserRoleDiv');
 //navTab.reload(json.forwardUrl); //或者直接写url
}
function testConfirmMsg(url, data){
	alertMsg.confirm("确定删除该权限么?", {
		okCall: function(){
			$.post(url, data, navTabAjaxDone, "json");
		}
	});
}
</script>
<div id="myUserRoleDiv">
	<form id="pagerForm" method="post"
		action="/money/role!queryRoleByUserId.do">
		<input type="hidden" name="userId" id="myUserRoleDiv_userId"
			value="${userId}" /> <input type="hidden" name="pageNum"
			value="${pageNum}" /> <input type="hidden" name="numPerPage"
			value="${numPerPage}" /> <input type="hidden" name="orderField"
			value="${param.orderField}" /> <input type="hidden"
			name="orderDirection" value="${param.orderDirection}" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);"
			action="/money/role!query.do" method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>角色名称</td>
						<td><input name="roleName" class="textInput" size="30"
							type="text" />
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">检索</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent" id='tbtb2' style="overflow:hidden;">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="/money/role!beforeRoleInUser.do" rel="myUserRoleDiv"
					target="dialog" mask="true" title="添加"><span>添加</span> </a>
				</li>
			</ul>
		</div>
		<table class="table" layoutH="10" id="tableArea2" setHeight='true' modifyHeight="100"  >
			<thead>
				<tr>
					<th width="100"  >角色描述</th>
					<th width="100"  >角色名称</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="list" status="stu">
					<tr target="roleId" rel="<s:property value="roleId" />">
						<td><s:property value="roleDesc" />
						</td>
						<td><s:property value="roleName" />
						</td>
						<td><a class="myAddbutton" 
						    href="javascript:;" onclick="testConfirmMsg('/money/role!deleteRoleInUser.do?ids=<s:property value="roleId" />')"
							><span>删除</span> </a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value},'myUserRoleDiv')">
					<option value="20"
						<%if ((request.getAttribute("numPerPage") + "").equals("20")) {%>
						selected <%}%>>20</option>
					<option value="50"
						<%if ((request.getAttribute("numPerPage") + "").equals("50")) {%>
						selected <%}%>>50</option>
					<option value="100"
						<%if ((request.getAttribute("numPerPage") + "").equals("100")) {%>
						selected <%}%>>100</option>
					<option value="200"
						<%if ((request.getAttribute("numPerPage") + "").equals("200")) {%>
						selected <%}%>>200</option>
				</select> <span>条，总共${totalCount}条记录</span>
			</div>
			<div class="pagination" targetType="navTab" rel="myUserRoleDiv"
				totalCount="${totalCount}" numPerPage="${numPerPage}"
				pageNumShown="20" currentPage="${pageNum}"></div>
		</div>
	</div>
</div>


<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/org.js" type="text/javascript" />
<script type="text/javascript">
<!--
function lastLayout(){   
	//$('div[myLayoutH]').parent().height(($('div.pageContent:first').height()-50)+'px'); 
}

/**
 * 保存角色授权的结果.
 */
function saveRole(){
	var ans = [];
	$('#treeDemo2').find('span.checkbox_true_full').each(function(){
		ans.push($(this).next('a').attr('id').replace('treeDemo_','').replace('_a',''));
	});
	var selectId = ans.join(',');
	var checkRoleId =$('#checkRoleId').val(); 
	 $.ajax({
		type:'POST', url:'/money/role!saveMenuWithRole.do', 
		dataType:'json',
		data: {'ids':selectId,'roleId':checkRoleId},
		success: DWZ.ajaxDone,
		error: DWZ.ajaxError
	}); 
}
//-->
</script>
<form id="pagerForm" method="post" action="/money/role!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="orderField" value="${param.orderField}" /> <input
		type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/role!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>角色名称</td>
					<td><input name="roleName" class="textInput" size="30" id='roleName'
						type="text" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="/money/role!beforeQuery.do"
						target="dialog" mask="true" title="查询框"><span>高级检索</span> </a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div>
		<div style="width:60%;border:1px #BAD1D7 solid;float:left;">
			<div class="panelBar">
				<ul class="toolBar">
					<li><a class="add" href="/money/role!beforeAdd.do"
						target="dialog" mask="true" title="添加"><span>添加</span> </a></li>
					<li><a class="delete" href="/money/role!doDelete.do"
						postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
					</a></li>
					<li><a class="edit"
						href="/money/role!beforeUpdate.do?roleId={roleId}" mask="true"
						target="dialog" title="修改"><span>修改</span> </a></li>
					<li><a class="icon" href="/money/role!export.do"
						target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span>
					</a></li>

				</ul>
			</div>
			<table class="table" layoutH="-138" width="400px;">
				<thead>
					<tr>
						<th width="30"><input type="checkbox" group="ids"
							class="checkboxCtrl"></th>
						<th width="100" orderField="ROLEDESC">角色描述</th>
						<th width="100" orderField="ROLENAME">角色名称</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="list" status="stu">
						<tr target="roleId" rel="<s:property value="roleId" />">
							<td><input name="ids" value="<s:property value="roleId" />"
								type="checkbox"></td>
							<td><s:property value="roleDesc" /></td>
							<td><s:property value="roleName" /></td>
							<td> <a
								class="myAddbutton"
								href="/money/role!updateMenuWithRole.do?roleId=<s:property value="roleId" />"
								target="ajax" mask="true" rel="treeDemo2"><span>菜单授权</span>
							</a> 
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div class="panelBar">
				<div class="pages">
					<span>显示</span> <select class="combox" name="numPerPage"
						onchange="navTabPageBreak({numPerPage:this.value,arglist:'roleName',pageNum:1})">
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
				<div class="pagination" targetType="navTab"
					totalCount="${totalCount}" numPerPage="${numPerPage}"
					pageNumShown="20" currentPage="${pageNum}"></div>
			</div>
		</div>
		<div
			style="width:40%;overflow:auto;border:1px #BAD1D7 solid;">
			<div  style="height:300px;" >
				<ul id='treeDemo2' />
			</div>
			<div class="formBar" >
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button onclick="saveRole()">保存</button>
							</div>
						</div>
					</li> 
				</ul>
			</div>
		</div>
	</div>
</div>


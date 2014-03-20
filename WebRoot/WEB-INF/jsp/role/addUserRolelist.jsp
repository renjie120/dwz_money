<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/org.js" type="text/javascript" />
<script type="text/javascript"> 
 function refreshSelf(obj) {
	$form = $(obj);
	//$("#jbsxBox").reload($form.attr('action'), {data: $form.serializeArray()},function(){});
	$("#addUserRoleDiv").loadUrl($form.attr('action'), {
		 
	}, function() {
	});
	return false;
	}
	
	function mydone(json){
		 DWZ.ajaxDone(json);
		 $.pdialog.closeCurrent();  
		 navTabPageBreak({}, 'myUserRoleDiv');
	}
	
	//进行选择角色之后授权
	function checkThis(){
	 var ans = [];
	 var $conetnt = $(".dialogContent", $.pdialog._current);
		 $conetnt.find('div.gridTbody').find('input[type=checkbox]:checked').each(function(){
		  ans.push($(this).val());
		 });
		 var selectId = ans.join(','); 
		 var userId =$('#myUserRoleDiv_userId').val();
		  $.ajax({
		  type:'POST', url:'/money/role!saveUserWithRole.do',
		  dataType:'json',
		  data: {'ids':selectId,'userId':userId},
		  success: mydone,
		  error: DWZ.ajaxError
		 }); 
	}
	
	function cancelThis(){
		  $.pdialog.closeCurrent();
	} 
</script>
<div id='addUserRoleDiv'>
	<form id="pagerForm" method="post"
		action="/money/role!beforeRoleInUser.do">
		<input type="hidden" name="pageNum" value="${pageNum}" /> <input
			type="hidden" name="numPerPage" value="${numPerPage}" /> <input
			type="hidden" name="orderField" value="${param.orderField}" /> <input
			type="hidden" name="orderDirection" value="${param.orderDirection}" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return refreshSelf(this);"
			action="/money/role!beforeRoleInUser.do" method="post">
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
						<li><a class="button" href="javascript:return false;" onclick="checkThis()"><span>选择并授权</span>
						</a>
						</li>
						<li><a class="button" href="javascript:return false;" onclick="cancelThis();"><span>取消</span>
						</a>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" layoutH="150" id="tableid" width="100%"
			setHeight="true" configHeight="150">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids"
						class="checkboxCtrl">
					</th>
					<th width="80">角色描述</th>
					<th width="150">角色名称</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="list" status="stu">
					<tr target="roleId" rel="<s:property value="roleId" />">
						<td><input name="ids" value="<s:property value="roleId" />"
							type="checkbox">
						</td>
						<td><s:property value="roleDesc" />
						</td>
						<td><s:property value="roleName" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage"  
					onchange="dialogPageBreak({numPerPage:this.value,pageNum:1},'addUserRoleDiv')">
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
			<div class="pagination" targetType="dialog"
				totalCount="${totalCount}" rel="addUserRoleDiv"
				numPerPage="${numPerPage}" pageNumShown="20"
				currentPage="${pageNum}"></div>
		</div>
	</div>
</div>


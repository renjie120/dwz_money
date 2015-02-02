<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript"> 
 /**
  * 新建一个保险公司用户.
  */
 function addUser(url,obj){ 
	var unitName = $('#userUnit').val();
 	var options = {mask:true};
	$.pdialog.open(url+"?userUnit="+encodeURIComponent(unitName), '', "投保单位用户管理", options); 
	 
 }
 
 function refreshThisDialog(json){  
 		//$form = $(obj); 
 		//alert($("#companyUserLists").size());
		//$("#jbsxBox").reload($form.attr('action'), {data: $form.serializeArray()},function(){});
		/*$("#companyUserLists").loadUrl($form.attr('action'), {
			 
		}, function() {
		}); */ 
 }
 
  /**
   * 删除一个保险公司用户之后，弹出提示信息，并刷新界面.
   */
  function myDialogAjaxDone(json){  
 		DWZ.ajaxDone(json); 
		if (json.statusCode == DWZ.statusCode.ok) {
			var url = "/money/loginuser!getUnitUser.do";
			var unitName = $('#userUnit').val();
		 	var options = {mask:true};
			$.pdialog.open(url+"?userUnit="+encodeURIComponent(unitName), '', "投保单位用户管理", options); 
		}
  }
 
  /**
   * 删除投保单位用户.
   */
  function deleteUser(url,obj){   
   var checkedItem =  $('input[type=checkbox][name=companyUsers]:checked');
 	if(checkedItem.size()==1){ 
	  $.ajax({
		  type:'POST', 
		  url:'/money/loginuser!doDelete.do',
		  dataType:'json',
		  data: {'ids':checkedItem.val()},
		  success:myDialogAjaxDone,
		  error: DWZ.ajaxError
		 });	  
 	}else{
 		alertMsg.error("必须选择一个且最多一个用户！");
 		return false;
 	}
 }
</script>
<div id='companyUserLists'>
<form id="pagerForm" method="post" action="/money/loginuser!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form> 
<div class="pageHeader">
	<form onsubmit="return dialogSearch(this,'companyUserLists');"  
		action="/money/loginuser!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						用户姓名</td><td>
							<input name="userName"   class="textInput " type="text"  value="<s:property value="vo.userName"/>" />
					</td> 
					<td> 
						用户状态 </td><td>
							<my:newselect tagName="userStatus"  paraType="yesorno_status" width="100" allSelected="true" />
					</td>  
					<input name="userType"    type="hidden"  value="${userType}" />
					<input name="userUnit"  id="userUnit"  type="hidden" value="${userUnit}" />  
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
				<a class="add" href="javascript:;" 
					onclick="addUser('/money/loginuser!beforeAddUnitUser.do',this)"  mask="true"  
					 ><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="javascript:;" 
					onclick="deleteUser('/money/loginuser!deleteUnitUser.do',this)"     
					 > <span>删除</span>
				</a>
			</li>  
		</ul>
	</div>
	<table class="table" >
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="companyUsers" class="checkboxCtrl">
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
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td style="text-align:center;">
						<input name="companyUsers" value="<s:property value="sno" />"
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
</div>

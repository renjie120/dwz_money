
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="common.util.DateUtil"%>
<script type="text/javascript">  
 function addShopm(url,title){ 
 	if(checkedVal!=''){ 
 		var checkedItem = $('input[name=group_ids][value='+checkedVal+']');
 		var unitName = checkedItem.parent().parent().parent().find('[name=cname]').val(); 
	 	var options = {mask:true}; 
		$.pdialog.open(url+"?groupSno="+checkedVal, "", "添加关联商家", options); 
 	}else{
 		alertMsg.error("必须选择一个且最多一个商家集团！");
 		return false;
 	}
 }
 
 var checkedVal = '';
 function showShopms(sno){
 	checkedVal = sno;
 	var $this = $('#jbsxBox3');  
	$this.loadUrl(
		"/money/businessman!getShopmList.do?groupSno="+sno  ,null,function(){ 
		}); 
 }
</script>
<form id="pagerForm" method="post"
	action="/money/businessgroup!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="orderField" value="${param.orderField}" /> <input
		type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/businessgroup!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>集团编号</td>
					<td><input name="groupSno" class="textInput " type="text"
						value="<s:property value="vo.groupSno"/>" /></td>
					<td>集团名称</td>
					<td><input name="groupName" class="textInput " type="text"
						value="<s:property value="vo.groupName"/>" /></td>
					<td>状态</td>
					<td><my:newselect tagName="groupStatus"
							paraType="shopman_status" width="100" allSelected="true" /></td>
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
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent" >
	<div class="panelBar">
		<ul class="toolBar">  
			<li><a class="add" href="javascript:;"
				onclick="addShopm('/money/businessman!tinyShopmList.do',this)"
				mask="true"><span>新增关联商家</span> </a></li> 
		</ul>
	</div>
	<table class="table"   myStyle="height:100px;overflow-y:auto">
		<thead>
			<tr>
				<th width="30"> </th>
				<th width="100" orderField="GROUPSNO">集团编号</th>
				<th width="100" orderField="GROUPNAME">集团名称</th>
				<th width="100" orderField="GROUPEMAIL">邮箱</th>
				<th width="100" orderField="GROUPCONTACT">联系人名称</th>
				<th width="100" orderField="GROUPCONTACTPHONE">联系电话</th>
				<th width="100" orderField="GROUPCONTACTMOBILE">联系人手机</th>
				<th width="100" orderField="GROUPSTATUS">状态</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td style="text-align:center;"><input name="group_ids" onclick='showShopms(<s:property value="sno" />)'
						value="<s:property value="sno" />" checked="" type="radio"></td>
					<td style="text-align:center;"><s:property value="groupSno" />
					</td>
					<td style="text-align:center;"><s:property value="groupName" />
						<input name="cname" value="<s:property value="groupName" />"
						type="hidden"></td>
					<td style="text-align:center;"><s:property value="groupEmail" />
					</td>
					<td style="text-align:center;"><s:property
							value="groupContact" /></td>
					<td style="text-align:center;"><s:property
							value="groupContactPhone" /></td>
					<td style="text-align:center;"><s:property
							value="groupContactMobile" /></td>
					<td style="text-align:center;"><s:property value="groupStatus" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value,pageNum:1})">
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
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
	<div id="jbsxBox3" class="unitBox"
		style="height:700px;border:1px #BAD1D7 solid;overflow:hidden;">
	</div>
</div>



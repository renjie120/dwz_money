
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">  
 function deleteFromGroup(url,title){
 	var checkedItem =  $('input[type=checkbox][name=mids2]:checked'); 
 	if(checkedItem.size()>0){
 		var ans = '';
 		checkedItem.each(function(){
 			ans+=this.value+",";
 		});
 		 $.ajax({
		  type:'POST', 
		  url:url,
		  dataType:'json',
		  data: {'mids':ans,'groupSno':$('#groupSno').val()},
		  success:afterUnbind,
		  error: DWZ.ajaxError
		 });   
 	}else{
 		alertMsg.error("必须至少选择一个商家！");
 		return false;
 	}
 }
 
 function afterUnbind(json) { 
	DWZ.ajaxDone(json); 
 }
 
</script>

<form id="pagerForm" method="post" action="/money/businessman!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="groupSno" id="groupSno" value="${groupSno}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="javascript:;" 
					onclick="deleteFromGroup('/money/businessman!deleteShopmToGroup.do',this)"  mask="true"  
					 ><span>解除绑定</span> </a>  
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="mids2" class="checkboxCtrl">
				</th>
				<th width="140"   >
						商家编号 
				</th> 
				<th width="140"    >
						商家名称  
				</th>   
				<th width="140"   >
						联系人名称 
				</th> 
				<th width="150"    >
						联系人手机 
				</th>   
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td style="text-align:center;">
						<input name="mids2" value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td style="text-align:center;">
						<s:property value="shopmSno" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopmName" />
					</td>  
					<td style="text-align:center;">
						<s:property value="shopmContactName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopmConPhone" />
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


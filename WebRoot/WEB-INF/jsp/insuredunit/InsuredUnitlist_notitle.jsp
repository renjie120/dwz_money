
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
function refreshInsuredUnit(txt) {
		// 提示返回结果.
		if (txt.responseText)
			alertMsg.info(txt.responseText);
		else
			alertMsg.info(txt);
		// 关闭当前页面
		$.pdialog.closeCurrent();
		//location.reload();
		navTabPageBreak({}, 'notitle_insuredUnitlist');
		 
	} 
	
	 function addUnitUser(url,obj){
 	var checkedItem =  $('input[type=checkbox][name=unitIds]:checked');
 	if(checkedItem.size()==1){
 		var sno = checkedItem.val(); 
 		var unitName = checkedItem.parent().parent().parent().find('[name=unitName]').val(); 
 		
	 	var options = {mask:true};
		$.pdialog.open(url+"?companySno="+sno+"&userUnit="+encodeURIComponent(unitName), '', "投保单位用户管理", options); 
 	}else{
 		alertMsg.error("必须选择一个且最多一个投保单位！");
 		return false;
 	}
 }
</script>
<div id="notitle_insuredUnitlist">
<form id="pagerForm" method="post" action="/money/insuredunit!queryByParent.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="unitParentId" value="${unitParentId}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader"  >
	<form onsubmit="return navTabSearch(this);" rel="notitle_insuredUnits"
		action="/money/insuredunit!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						编号</td><td>
							<input name="unitCode"   class="textInput " type="text"  value="<s:property value="vo.unitCode"/>" />
					</td> 
					<td> 
						投保单位 </td><td>
							<input name="unitName"   class="textInput " type="text"  value="<s:property value="vo.unitName"/>" />
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
<div class="pageContent" id="notitle_insuredUnits"> 
<div class="panelBar">
		<ul class="toolBar"> 
			<li>
				<a class="add" href="javascript:;" 
					onclick="addUnitUser('/money/loginuser!getUnitUser.do',this)"  mask="true"  
					 ><span>用户管理</span> </a> 
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138" id="tbtb3">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="unitIds" class="checkboxCtrl">
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
						是否显示 
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
					<td style="text-align:center;">
						<input name="unitIds"  tag='insuredUnit' value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td style="text-align:center;">
						<s:property value="unitCode" />
					</td> 
					<td style="text-align:center;">
						<s:property value="unitName" />
						<input name="unitName" value="<s:property value="unitName" />"
							type="hidden">
					</td> 
					<td style="text-align:center;">
						<s:property value="contactName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="contactMobile" />
					</td> 
					<td style="text-align:center;">
						<s:property value="contactEmail" />
					</td> 
					<td style="text-align:center;">
						<s:property value="unitParentName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="unitState" />
					</td> 
					<td style="text-align:center;">
						<s:property value="unitAddress" />
					</td> 
					<td style="text-align:center;">
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
</div>


<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript"> 
 function kaitong(url,title){
 	var checkedItem =  $('input[type=checkbox][name=insuredUserIds]:checked');
 	if(checkedItem.size()==1){
 		var sno = checkedItem.val();
 		var unitName = checkedItem.parent().parent().parent().find('[name=cname]').val(); 
	 	var options = {mask:true};
		$.pdialog.open(url+"?userId="+sno+"&userName="+encodeURIComponent(unitName), '', title, options); 
 	}else{
 		alertMsg.error("必须选择一个且最多一个用户！");
 		return false;
 	}
 }
</script>
<form id="pagerForm" method="post" action="/money/insureduser!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/insureduser!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						用户号</td><td>
							<input name="iuserNo"   class="textInput " type="text"  value="<s:property value="vo.iuserNo"/>" />
					</td> 
					<td> 
						所属投保单位 </td><td>
							<input name="unitId"   class="textInput " type="text"  value="<s:property value="vo.unitId"/>" />
					</td> 
					<td> 
						保险公司 </td><td>
							<input name="comId"   class="textInput " type="text"  value="<s:property value="vo.comId"/>" />
					</td> 
					<td> 
						状态 </td><td>
							<my:newselect tagName="iuserStatus"  paraType="toubaouser_status" width="100" allSelected="true" />
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
				<a class="add" href="/money/insureduser!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/insureduser!doDelete.do" postType="string"
					target="selectedTodo" rel="insuredUserIds" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/insureduser!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="add" href="javascript:;"  
					onclick="kaitong('/money/userupdatelogger!beforeKaitong.do','开通用户')" mask="true"
					  title="修改"><span>开通业务</span> </a>
			</li>
			<li>
			<a class="add" href="javascript:;"  
					onclick="kaitong('/money/userupdatelogger!beforeDongjie.do','冻结用户')" mask="true"
					 title="修改"><span>冻结</span> </a>
			</li>
			<li>
			<a class="add" href="javascript:;"  
					onclick="kaitong('/money/userupdatelogger!beforeZhuxiao.do','注销用户')" mask="true"
					  title="修改"><span>注销</span> </a>
			</li>
			<li>
				<a class="edit" href="/money/insureduser!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>充值</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/insureduser!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/insureduser!initImport.do" target="dialog"><span>从EXCEL导入</span> </a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="insuredUserIds" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="IUSERNO" >
						用户号 
				</th> 
				<th width="100"    orderField="IUSERNAME" >
						姓名 
				</th> 
				<th width="40"    orderField="IUSERISMAN" >
						性别 
				</th> 
				<th width="120"    orderField="IUSERCARDNO" >
						证件号 
				</th> 
				<th width="100"    orderField="IUSERPHONE" >
						手机号 
				</th> 
				<th width="100"    orderField="UNITID" >
						所属投保单位  
				</th> 
				<th width="100"    orderField="COMID" >
						保险公司  
				</th> 
				<th width="100"    orderField="IUSERSTATUS" >
						状态  
				</th> 
				<th width="100"    orderField="LEFTMONEY" >
						余额 
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td style="text-align:center;">
						<input name="insuredUserIds" value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td style="text-align:center;">
						<s:property value="iuserNo" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserName" />
						<input name="cname" value="<s:property value="iuserName" />"
							type="hidden">
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserIsman" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserCardno" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserPhone" />
					</td> 
					<td style="text-align:center;">
						<s:property value="unitId" />
					</td> 
					<td style="text-align:center;">
						<s:property value="comId" />
					</td> 
					<td style="text-align:center;">
						<s:property value="iuserStatus" />
					</td> 
					<td style="text-align:center;">
						<s:property value="leftMoney" />
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


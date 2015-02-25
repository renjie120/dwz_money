
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/consumeinfo!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/consumeinfo!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						投保用户号</td><td>
							<input name="iuserId" size="20"  class="textInput " type="text"  value="<s:property value="vo.iuserId"/>" />
					</td> 
					<td> 
						所属商家 </td><td>
							<input name="shopmId" size="20"  class="textInput " type="text"  value="<s:property value="vo.shopmId"/>" />
					</td> 
					<td> 
						所属商铺 </td><td>
							<input name="shopId" size="20"  class="textInput " type="text"  value="<s:property value="vo.shopId"/>" />
					</td> 
					<td> 
						所属分公司 </td><td>
							<input name="ownerCom" size="20"  class="textInput " type="text"  value="<s:property value="vo.ownerCom"/>" />
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
				<a class="add" href="/money/consumeinfo!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/consumeinfo!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/consumeinfo!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/consumeinfo!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="IUSERID" >
						投保用户号 
				</th> 
				<th width="100"    orderField="COMID" >
						投保公司 
				</th> 
				<th width="100"    orderField="SHOPMID" >
						所属商家  
				</th> 
				<th width="100"    orderField="SHOPID" >
						所属商铺  
				</th> 
				<th width="100"    orderField="OWNERCOM" >
						所属分公司  
				</th> 
				<th width="100"    orderField="CONSUMESTATUS" >
						支付状态 
				</th> 
				<th width="100"    orderField="CONSUMEMONEY" >
						消费金额 
				</th> 
				<th width="100"    orderField="CARDMONEY" >
						刷卡消费 
				</th> 
				<th width="100"    orderField="CASHMONEY" >
						现金支付 
				</th> 
				<th width="100"    orderField="CONSUMETIME" >
						消费时间 
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
						<div style='width:100px'><s:property value="iuserId" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="comId" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="shopmId" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="shopId" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="ownerCom" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="consumeStatus" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="consumeMoney" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="cardMoney" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="cashMoney" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="consumeTime" /></div>
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



<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/businessshop!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/businessshop!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						商家编号</td><td>
							<input name="shopmId"   class="textInput " type="text"  value="<s:property value="vo.shopmId"/>" />
					</td> 
					<td> 
						商铺名称 </td><td>
							<input name="shopName"   class="textInput " type="text"  value="<s:property value="vo.shopName"/>" />
					</td> 
					<td> 
						商铺编号 </td><td>
							<input name="shopSno"   class="textInput " type="text"  value="<s:property value="vo.shopSno"/>" />
					</td> 
					<td> 
						商铺状态 </td><td>
							<my:newselect tagName="shopStatus"  paraType="shopstatus" width="100" allSelected="true" />
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
				<a class="add" href="/money/businessshop!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/businessshop!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="icon" href="/money/businessshop!initImport.do" target="dialog"><span>从EXCEL导入</span> </a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th> 
				<th width="100"    orderField="SHOPSNO" >
						商铺编号  
				</th> 
				<th width="100"    orderField="SHOPNAME" >
						商铺名称  
				</th> 
				<th width="100"    orderField="SHOPSTATUS" >
						商铺状态  
				</th> 
				<th width="100"    orderField="SHOPCONTACTNAME" >
						联系人  
				</th> 
				<th width="100"    orderField="SHOPCONPHONE" >
					        手机 
				</th>  
				<th width="100"    orderField="SHOPDATE" >
						签约日期 
				</th>  
				<th width="100"    orderField="SHOPPROVINCE" >
						省份 
				</th> 
				<th width="100"    orderField="SHOPCITY" >
						市 
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
						<s:property value="shopSno" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopName" />
					</td>  
					<td style="text-align:center;">
						<s:property value="shopStatus" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopContactName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopConPhone" />
					</td>  
					<td style="text-align:center;">
						<s:property value="shopDate" />
					</td>  
					<td style="text-align:center;">
						<s:property value="shopProvince" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopCity" />
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


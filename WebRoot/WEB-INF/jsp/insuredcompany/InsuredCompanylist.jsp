
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/insuredcompany!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/insuredcompany!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						保险公司名称</td><td>
							<input name="comName"   class="textInput " type="text"  value="<s:property value="vo.comName"/>" />
					</td> 
					<td> 
						保险公司编号 </td><td>
							<input name="comNo"   class="textInput " type="text"  value="<s:property value="vo.comNo"/>" />
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
					<li>
						<a class="button" href="/money/insuredcompany!beforeQuery.do"
							target="dialog" mask="true" title="查询框"><span>高级检索</span> </a>
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
				<a class="add" href="/money/insuredcompany!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/insuredcompany!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/insuredcompany!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/insuredcompany!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="COMNAME" >
						保险公司名称 
				</th> 
				<th width="100"    orderField="COMNO" >
						保险公司编号  
				</th> 
				<th width="100"    orderField="COMSHORTNAME" >
						简称 
				</th> 
				<th width="100"    orderField="COMPHONE" >
						电话 
				</th> 
				<th width="100"    orderField="COMCONTACTNAME" >
						联系人名称 
				</th> 
				<th width=""    orderField="COMCONTACTPHONE" >
						联系人手机 
				</th> 
				<th width="100"    orderField="OWNERCOMPANY" >
						所属保险公司 
				</th> 
				<th width="100"    orderField="COMEMAIL" >
						邮箱 
				</th> 
				<th width="100"    orderField="COMADDRESS" >
						地址 
				</th> 
				<th width="100"    orderField="COMREMARK" >
						备注 
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td>
						<input name="ids" value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="comName" />
					</td> 
					<td>
						<s:property value="comNo" />
					</td> 
					<td>
						<s:property value="comShortName" />
					</td> 
					<td>
						<s:property value="comPhone" />
					</td> 
					<td>
						<s:property value="comContactName" />
					</td> 
					<td>
						<s:property value="comContactPhone" />
					</td> 
					<td>
						<s:property value="ownerCompany" />
					</td> 
					<td>
						<s:property value="comEmail" />
					</td> 
					<td>
						<s:property value="comAddress" />
					</td> 
					<td>
						<s:property value="comRemark" />
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


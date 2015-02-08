
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/businessman!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/businessman!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						商家编号</td><td>
							<input name="shopmSno"   class="textInput " type="text"  value="<s:property value="vo.shopmSno"/>" />
					</td> 
					<td> 
						商家名称 </td><td>
							<input name="shopmName"   class="textInput " type="text"  value="<s:property value="vo.shopmName"/>" />
					</td> 
					<td> 
						商家简称 </td><td>
							<input name="shopmShortName"   class="textInput " type="text"  value="<s:property value="vo.shopmShortName"/>" />
					</td> 
					<td> 
						商家类型 </td><td>
							<my:newselect tagName="shopmType"  paraType="shopman_status" width="100" allSelected="true" />
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
				<a class="add" href="/money/businessman!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/businessman!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/businessman!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"   >
						商家编号 
				</th> 
				<th width="100"    >
						商家名称  
				</th> 
				<th width="100"    >
						商家简称  
				</th> 
				<th width="100"    orderField="SHOPMTYPE" >
						商家状态  
				</th>   
				<th width="100"   >
						联系人名称 
				</th> 
				<th width="100"    >
						联系人手机 
				</th>  
				<th width="100"    >
						邮箱
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
						<s:property value="shopmSno" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopmName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopmShortName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopmType" />
					</td>  
					<td style="text-align:center;">
						<s:property value="shopmContactName" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopmConPhone" />
					</td> 
					<td style="text-align:center;">
						<s:property value="shopmEmail" />
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


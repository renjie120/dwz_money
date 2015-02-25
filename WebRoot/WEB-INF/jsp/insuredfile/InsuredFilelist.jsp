
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/insuredfile!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/insuredfile!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						投保单号 </td><td>
							<input name="insuredFileId" size="20"  class="textInput " type="text"  value="<s:property value="vo.insuredFileId"/>" />
					</td> 
					<td> 
						保单名称</td><td>
							<input name="insuredFileName" size="20"  class="textInput " type="text"  value="<s:property value="vo.insuredFileName"/>" />
					</td> 
					<td> 
						投保单位</td><td>
							<input name="insuredFileUnit" size="20"  class="textInput " type="text"  value="<s:property value="vo.insuredFileUnit"/>" />
					</td> 
					<td> 
						保险公司</td><td>
							<input name="insuredFileCompany" size="20"  class="textInput " type="text"  value="<s:property value="vo.insuredFileCompany"/>" />
					</td> 
					<td> 
						状态</td><td>
							<my:newselect tagName="insuredFileStatus"  paraType="insurefile_state" width="100" allSelected="true" />
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
				<a class="add" href="/money/insuredfile!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/insuredfile!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/insuredfile!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/money/insuredfile!initImport.do" target="dialog"><span>导入</span> </a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="INSUREDFILEID" >
						投保单号  
				</th> 
				<th width="100"    orderField="INSUREDFILENAME" >
						保单名称 
				</th> 
				<th width="100"    orderField="INSUREDFILEUNIT" >
						投保单位 
				</th> 
				<th width="100"    orderField="INSUREDFILECOMPANY" >
						保险公司 
				</th> 
				<th width="100"    orderField="INSUREDFILECONTACT" >
						联系人 
				</th> 
				<th width="100"    orderField="INSUREDFILECONTEL" >
						联系电话 
				</th> 
				<th width="100"    orderField="INSUREDFILECONMOBILE" >
						联系人手机 
				</th> 
				<th width="100"    orderField="INSUREDFILEBEGIN" >
						投保日期 
				</th> 
				<th width="100"    orderField="INSUREDFILEEND" >
						到期日期 
				</th> 
				<th width="100"    orderField="INSUREDFILESTATUS" >
						状态 
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
						<div style='width:100px'><s:property value="insuredFileId" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileName" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileUnit" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileCompany" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileContact" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileConTel" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileConMobile" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileBegin" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileEnd" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:100px'><s:property value="insuredFileStatus" /></div>
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


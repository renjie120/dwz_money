
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript"> 
 /**
  * 上传一个保险公司的文件
  */
 function addFile(url,obj){ 
	var unitName = $('#companyId').val();
 	var options = {mask:true};
	$.pdialog.open(url+"?companyId="+encodeURIComponent(unitName), '', "文件上传", options); 
	 
 }
 </script>
<form id="pagerForm" method="post" action="/money/filemanager!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="text" name="companyId" value="${companyId}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/filemanager!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent" >
				<tr>
					<td>
						文件名
					</td>
					<td>
						<input name="fileName" class="textInput" size="30" type="text" />
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
				<a class="add"  href="javascript:;"   
				onclick="addFile('/upload/test!initCompanyFile.do',this)" 
				  mask="true" title="添加"><span>上传</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/filemanager!doDelete.do"
					postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="50">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th> 
				<th width="150" orderField="FILENAME">
					文件名
				</th>
				<th width="150" orderField="FILELEN">
					文件长度
				</th>
				<th width="100">
					文件下载
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
						<s:property value="fileName" />
					</td>
					<td>
						<s:property value="fileLen" />
					</td>
					<td>
						<a href='/upload/test!getFile.do?fileId=<s:property value="fileId" />'>下载</a>
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
					<%if ((request.getAttribute("numPerPage") + "").equals("20")) {%>
					selected <%}%>>
					20
				</option>
				<option value="50"
					<%if ((request.getAttribute("numPerPage") + "").equals("50")) {%>
					selected <%}%>>
					50
				</option>
				<option value="100"
					<%if ((request.getAttribute("numPerPage") + "").equals("100")) {%>
					selected <%}%>>
					100
				</option>
				<option value="200"
					<%if ((request.getAttribute("numPerPage") + "").equals("200")) {%>
					selected <%}%>>
					200
				</option>
			</select>
			<span>条，总共${totalCount}条记录</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>


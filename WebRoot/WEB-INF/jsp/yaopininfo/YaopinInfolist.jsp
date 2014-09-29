
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
<!--
function see(obj){
	var $this = $(obj); 
	var w =  500;
	var id = $this.parent().parent().parent().find('input[type=checkbox]').attr('value');
	var h = 400;
	var iTop = (window.screen.availHeight-30-h)/2; //获得窗口的垂直位置; 
	var iLeft = (window.screen.availWidth-10-w)/2; //获得窗口的水平位置; 
	var url = unescape('/money/map!initMarvinjs2.do?id='+id);
	window.open (url, 'newwindow', 'height='+h+', width='+w+', top='+iTop+', left='+iLeft+', toolbar=no,'
			+' menubar=no, scrollbars=no, resizable=no,location=no, status=no') ;
	return false;
}
//-->
</script>
<form id="pagerForm" method="post" action="/money/yaopininfo!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="orderField" value="${param.orderField}" /> <input
		type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/money/yaopininfo!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>业务实体</td>
					<td><input name="companyName" class="textInput" size="20"
						type="text" />
					</td>
					<td>物料名称（英文）</td>
					<td><input name="engName" class="textInput" size="20"
						type="text" />
					</td>
					<td>物料名称（中文）</td>
					<td><input name="chnName" class="textInput" size="20"
						type="text" />
					</td>
					<td>结构式</td>
					<td><input name="chemStruct" class="textInput" size="20"
						type="text" />
					</td>
				</tr>
				<tr>
					<td>生产厂家</td>
					<td><input name="productName" class="textInput" size="20"
						type="text" />
					</td>
					<td>销售时间</td>
					<td><input type="text" name="saleTime" class="date" size="20"
						readOnly /></td>
					<td>CAS</td>
					<td><input name="cas" class="textInput" size="20" type="text" />
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
					<li><a class="button" href="/money/yaopininfo!beforeQuery.do"
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
			<li><a class="add" href="/money/yaopininfo!beforeAdd.do"
				target="dialog" mask="true" title="添加"><span>添加</span> </a>
			</li>
			<li><a class="delete" href="/money/yaopininfo!doDelete.do"
				postType="string" target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
			</a>
			</li>
			<li><a class="edit"
				href="/money/yaopininfo!beforeUpdate.do?sno={sno}" mask="true"
				target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<!--li><a class="edit"
				href="/money/map!initMarvinjs2.do" mask="true"
				target="myDialog" title="修改"><span>设置公式</span> </a>
			</li-->
			<li><a class="icon" href="/money/yaopininfo!export.do"
				target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span>
			</a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" group="ids"
					class="checkboxCtrl">
				</th>
				<th width="100" orderField="COMPANYNAME">业务实体</th>
				<th width="150" orderField="ENGNAME">物料名称（英文）</th>
				<th width="150" orderField="CHNNAME">物料名称（中文）</th>
				<th width="150" orderField="CHEMSTRUCT">结构式</th>
				<th width="100" orderField="PRODUCTNAME">生产厂家</th>
				<th width="100" orderField="CUSTOMER">厂家销售客户</th>
				<th width="50" orderField="NUM">数量</th>
				<th width="50" orderField="PRICE">销售价格</th>
				<th width="100" orderField="CHUNDU">纯度</th>
				<th width="100" orderField="SALETIME">销售时间</th>
				<th width="50" orderField="CAS">CAS</th>
				<th width="100" orderField="CONNECT">厂家联系人</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td><input name="ids" value="<s:property value="sno" />"
						type="checkbox">
					</td>
					<td><s:property value="companyName" />
					</td>
					<td><s:property value="engName" />
					</td>
					<td><s:property value="chnName" />
					</td>
					<td><s:property value="chemStruct" />
						<button style='align:right' onclick='see(this)'>编辑公式</button>
					</td>
					<td><s:property value="productName" />
					</td>
					<td><s:property value="customer" />
					</td>
					<td><s:property value="num" />
					</td>
					<td><s:property value="price" />
					</td>
					<td><s:property value="chundu" />
					</td>
					<td><s:property value="saleTime" />
					</td>
					<td><s:property value="cas" />
					</td>
					<td><s:property value="connect" />
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
					<%if((request.getAttribute("numPerPage")+"").equals("20")){%>
					selected <%} %>>20</option>
				<option value="50"
					<%if((request.getAttribute("numPerPage")+"").equals("50")){%>
					selected <%} %>>50</option>
				<option value="100"
					<%if((request.getAttribute("numPerPage")+"").equals("100")){%>
					selected <%} %>>100</option>
				<option value="200"
					<%if((request.getAttribute("numPerPage")+"").equals("200")){%>
					selected <%} %>>200</option>
			</select> <span>条，总共${totalCount}条记录</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>


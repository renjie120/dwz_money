
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript"> 
 /**
  * 上传一个保险公司的文件
  */
 function addFile(url,obj){ 
	var unitName = $('#businessId').val();
 	var options = {mask:true};
	$.pdialog.open(url+"?businessId="+encodeURIComponent(unitName), '', "文件上传", options); 
	 
 }
 
   /**
   * 删除一个保险公司用户之后，弹出提示信息，并刷新界面.
   */
  function myDialogAjaxDone(json){  
 		DWZ.ajaxDone(json); 
		if (json.statusCode == DWZ.statusCode.ok) {
			var url = "/money/uploadfile!queryCompanyList.do";
			var businessId = $('#businessId').val();
		 	var options = {mask:true};
			$.pdialog.open(url+"?businessId="+businessId, '', "文件上传", options); 
		}
  }
  
   /**
   * 删除保险公司用户.
   */
  function deleteFile(url,obj){   
   var checkedItem =  $('input[type=checkbox][name=files]:checked');
 	if(checkedItem.size()>0){ 
	  $.ajax({
		  type:'POST', 
		  url:url,
		  dataType:'json',
		  data: {'ids':checkedItem.val()},
		  success:myDialogAjaxDone,
		  error: DWZ.ajaxError
		 });	  
 	}else{
 		alertMsg.error("至少选择一个文件！");
 		return false;
 	}
 }
 
 
 </script>
<form id="pagerForm" method="post" action="/money/uploadfile!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="businessId" id="businessId"  value="${businessId}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form> 
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add"  href="javascript:;"   
				onclick="addFile('/money/uploadfile!initCompanyFile.do',this)" 
				  mask="true" title="添加"><span>上传</span> </a>
			</li> 
			<li>
				<a class="delete" href="javascript:;"  
				onclick="deleteFile('/money/uploadfile!doDelete.do',this)"  
			    ><span>删除</span>
				</a>
			</li> 
		</ul>
	</div>
	<table class="table" layoutH="50">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="files" class="checkboxCtrl">
				</th> 
				<th width="180"    >
						文件名 
				</th>  
				<th width="80"    >
						文件大小 
				</th>  
				<th width="80"    >
						上传人
				</th>  
				<th width="150"     >
						上传时间 
				</th>  
				<th width="80">
					文件下载
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td style="text-align:center;">
						<input name="files" value="<s:property value="sno" />"
							type="checkbox">
					</td> 
					<td style="text-align:center;">
						 <s:property value="fileName" /> 
					</td>  
					<td style="text-align:center;">
						<div style='width:80px'><s:property value="fileSize" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:80px'><s:property value="createUserName" /></div>
					</td> 
					<td style="text-align:center;">
						<div style='width:130px'><s:property value="createTime" /></div>
					</td> 
					<td>
						<a href='/upload/test!getFile.do?fileId=<s:property value="sno" />'>下载</a>
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


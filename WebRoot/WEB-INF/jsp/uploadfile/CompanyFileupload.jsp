
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript"> 
function submitFile(txt){ 
	 $('#fileForm').ajaxSubmit(function(txt){ 
    //提示返回结果.
    alertMsg.info(txt);  
    cancelthis();
  });
}

 
function cancelthis(){
	var url = "/money/uploadfile!queryCompanyList.do";
	var unitName = $('#businessId').val();
 	var options = {mask:true};
	$.pdialog.open(url+"?businessId="+unitName, '', "文件上传", options); 
	
}
 
</script>
<div class="pageContent">
	<form method="post" id='fileForm' action="/upload/test!saveFile.do" enctype="multipart/form-data"
	>
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							 选择文件:
						</label>
							<input type="file" name="upload"/>
					</div>
						<input name="newFileName" class="textInput " size="30" type="hidden"   />
						<input name="businessId"   id="businessId" type="hidden"  value="${businessId }" />
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick='submitFile()'>
								提交
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button"   onclick="cancelthis()">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
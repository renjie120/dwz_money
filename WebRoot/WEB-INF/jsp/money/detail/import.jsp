<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
<!-- 
function test(){  
	 $('#formImportmoney').ajaxSubmit(function(txt){ 
	 	//提示返回结果.
	 	 alertMsg.info(txt); 
	 	 //关闭当前页面
	 	 $.pdialog.closeCurrent();
	 	 //刷新当前tab页.
	 	 navTab.reload();
	 });
}
//-->
</script>
<form enctype="multipart/form-data" method="post" id='formImportmoney'
	action="/money/newmoney!importExcel.do">
	<div class="pageFormContent" width='300px'>
		<div class="unit">
			<label>
				模板下载：
			</label>
			<label>
				<a href='/money/newmoney!model.do'>点击下载</a> 
			</label>
		</div>
		<div class="unit">
			<label>
				上传文件：
			</label>
			<label>
				<input type="file" name="upload" id="upload"/>
			</label>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button onclick='test()' type="button">
							保存
						</button>
					</div>
				</div>
			</li>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">
							取消
						</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</form>
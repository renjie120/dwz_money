<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>  
<div class="pageContent">
	<form method="post" action="/money/question!importExcel.do" id="questionInfo"
		class="pageForm required-validate" onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent"  width='300px' layoutH="57"> 
		  <div class="unit">
				<label>
					上传文件:
				</label>
				<input type="file" id="image" name="image" class="btnSearch"   value="浏览"
                   					  maxlength="50"  style="margin: 0px 1px 4px 1px;" /> 
			</div>
			<div class="unit">
				<label>
					<a href="/money/question!initImport.do">模版下载</a>
				</label> 
			</div>  
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" >
								导入
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
</div>

 
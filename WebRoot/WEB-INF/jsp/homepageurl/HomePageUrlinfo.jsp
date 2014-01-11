
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="/money/homepageurl!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57"> 
			<div class="unit">
				<label> 链接描述 : </label> <input name="urlDesc"
					class="textInput required" size="30" type="text" />
			</div>
			<div class="unit">
				<label> 链接: </label> <input name="url" class="textInput required"
					size="30" type="text" />
			</div>
			<div class="unit">
				<label> 排序号: </label> <input name="orderId" class="textInput "
					size="30" type="text" />
			</div>
			<div class="unit">
				<label> 类型: </label> <input name="typeId" class="textInput "
					size="30" type="text" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
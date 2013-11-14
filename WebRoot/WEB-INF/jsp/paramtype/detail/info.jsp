<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/paramType!doAdd.do" id="paratypeInfo"
		class="pageForm required-validate" onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label>
					参数描述：
				</label>
				<input name="parameterTypeName" type="text" class="textInput" />
			</div>
			<div class="unit">
				<label>
					参数代码：
				</label>
				<input name="code" type="text" class="textInput" />
			</div>
			<div class="unit">
				<label>
					排序列：
				</label>
				<input type="text" name="orderId" class="number" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" >
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
</div>

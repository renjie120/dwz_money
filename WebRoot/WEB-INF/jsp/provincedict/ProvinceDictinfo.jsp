
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/provincedict!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							省份:
						</label>
							<input name="provName"  class="number "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							类型:
						</label>
							<my:newselect tagName="provType"  paraType="prov_type" width="100"  />
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="provState"  paraType="yesorno_status" width="100"  />
					</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
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
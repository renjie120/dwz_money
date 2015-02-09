
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/addmoneydetail!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							投保用户号: 
						</label>
							<input name="iuserId" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							充值字段: 
						</label>
							<my:newselect tagName="addType"  paraType="addmoneytype" width="100"  />
					</div>
					 <div class="unit">
						<label>
							充值金额 : 
						</label>
							<input name="addMoney"  class="number " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							投保单号 : 
						</label>
							<input name="insuredFileId" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							充值时间: 
						</label>
							<input type="text" name="addTime" class="date "  size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
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
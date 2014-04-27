
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/money/stockmanager!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							股票号码:
						</label>
									<input name="stockNo" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							股票名称 :
						</label>
									<input name="stockName" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							交易时间:
						</label>
							<input type="text" name="dealDate" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							交易价格:
						</label>
									<input name="price" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							交易份额:
						</label>
									<input name="dealNumber" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							交易费率:
						</label>
									<input name="fee" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							交易类型:
						</label>
									<my:newselect tagName="dealType"  paraType="dealType" width="100" allSelected="true" />
					</div>
					 <div class="unit">
						<label>
							交易分组:
						</label>
									<input name="dealGroup" class="textInput " size="30" type="text"   />
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
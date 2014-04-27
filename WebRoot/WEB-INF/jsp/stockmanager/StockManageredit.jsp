
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.stockmanage.StockManager"%>
<% 
	StockManager vo = (StockManager) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/stockmanager!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							股票号码:
						</label>
									<input name="stockNo" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.stockNo"/>" />
					</div>
					 <div class="unit">
						<label>
							股票名称 :
						</label>
									<input name="stockName" class="textInput  "  size="30" type="text"  value="<s:property value="vo.stockName"/>" />
					</div>
					 <div class="unit">
						<label>
							交易时间:
						</label>
							<input type="text" name="dealDate" class="date " size="30" readOnly="true"  value="<s:property value="vo.dealDate"/>" />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							交易价格:
						</label>
									<input name="price" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.price"/>" />
					</div>
					 <div class="unit">
						<label>
							交易份额:
						</label>
									<input name="dealNumber" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.dealNumber"/>" />
					</div>
					 <div class="unit">
						<label>
							交易费率:
						</label>
									<input name="fee" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.fee"/>" />
					</div>
					 <div class="unit">
						<label>
							交易类型:
						</label>
									<my:newselect tagName="dealType"  paraType="dealType" width="100" allSelected="true" selectedValue="<%=vo.getDealType() %>"/>									
					</div>
					 <div class="unit">
						<label>
							交易分组:
						</label>
									<input name="dealGroup" class="textInput  "  size="30" type="text"  value="<s:property value="vo.dealGroup"/>" />
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


<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.moneytype.MoneyType"%>
<%
			Collection list1 = (Collection) ActionContext.getContext().get(
			"allQuestionSort");
	MoneyType vo = (MoneyType) request.getAttribute("questionVo");
	String moneyType = vo.getMoneyType() + "";
%>
<div class="pageContent">
	<form method="post" action="/money/moneyType!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="moenyTypeSno"
			value="<s:property value="moneyTypeVo.moenyTypeSno"/>">
		<div class="pageFormContent" layoutH="55">
			<div class="unit">
				<label>
					类型描述:
				</label>
				<input name="moneyTypeDesc" class="textInput  required " size="30"
					type="text" value="<s:property value="moneyTypeVo.moneyTypeDesc"/>"
					maxLength="10" />
			</div>
			<div class="unit">
				<label>
					收支类型:
				</label>
				<input type="text" name="moneyType" class="date  required "
					size="30" value="<s:property value="moneyTypeVo.moneyType"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					上级编码:
				</label>
				<input type="text" name="parentCode" class="date  " size="30"
					value="<s:property value="moneyTypeVo.parentCode"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					类型编码:
				</label>
				<input name="typeCode" class="textInput  " size="30" type="text"
					value="<s:property value="moneyTypeVo.typeCode"/>" />
			</div>
			<div class="unit">
				<label>
					排序列:
				</label>
				<input name="orderId" class="textInput  required " size="30"
					type="text" value="<s:property value="moneyTypeVo.orderId"/>" />
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


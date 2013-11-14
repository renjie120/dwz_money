<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%
	Collection list1 = (Collection) ActionContext.getContext().get("allQuestionSort");
%>

<div class="pageContent">
	<form method="post" action="/money/moneyType!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="55">
			<div class="unit">
				<label>
					类型描述:
				</label>
				<input name="moneyTypeDesc" class="textInput  required " size="30"
					type="text" maxLength="10" />
			</div>
			<div class="unit">
				<label>
					收支类型:
				</label>
				<input type="text" name="moneyType" class="date  required "
					size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					上级编码:
				</label>
				<input type="text" name="parentCode" class="date  " size="30" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					类型编码:
				</label>
				<input name="typeCode" class="textInput  " size="30" type="text" />
			</div>
			<div class="unit">
				<label>
					排序列:
				</label>
				<input name="orderId" class="textInput  required " size="30"
					type="text" />
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


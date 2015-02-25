
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.ConsumeInfo.ConsumeInfo"%>
<% 
	ConsumeInfo vo = (ConsumeInfo) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/consumeinfo!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							投保用户号:
						</label>
							<input name="iuserId" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.iuserId"/>" />
					</div>
					 <div class="unit">
						<label>
							投保公司:
						</label>
							<input name="comId" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.comId"/>" />
					</div>
					 <div class="unit">
						<label>
							所属商家 :
						</label>
							<input name="shopmId" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.shopmId"/>" />
					</div>
					 <div class="unit">
						<label>
							所属商铺 :
						</label>
							<input name="shopId" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.shopId"/>" />
					</div>
					 <div class="unit">
						<label>
							所属分公司 :
						</label>
							<input name="ownerCom" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.ownerCom"/>" />
					</div>
					 <div class="unit">
						<label>
							支付状态:
						</label>
							<my:newselect tagName="consumeStatus"  paraType="paytype" width="100"  selectedValue="<%=vo.getConsumeStatus() %>"/>									
					</div>
					 <div class="unit">
						<label>
							消费金额:
						</label>
							<input name="consumeMoney" size="30"  class="number  "    type="text"  value="<s:property value="vo.consumeMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							刷卡消费:
						</label>
							<input name="cardMoney" size="30"  class="number  "    type="text"  value="<s:property value="vo.cardMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							现金支付:
						</label>
							<input name="cashMoney" size="30"  class="number  "    type="text"  value="<s:property value="vo.cashMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							消费时间:
						</label>
							<input name="consumeTime" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.consumeTime"/>" />
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
							<input name="createUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createUserName"/>" />
					</div>
					 <div class="unit">
						<label>
							创建时间:
						</label>
							<input name="createTime" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createTime"/>" />
					</div>
					 <div class="unit">
						<label>
							更新用户:
						</label>
							<input name="updateUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateUserName"/>" />
					</div>
					 <div class="unit">
						<label>
							更新时间:
						</label>
							<input name="updateTime" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateTime"/>" />
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


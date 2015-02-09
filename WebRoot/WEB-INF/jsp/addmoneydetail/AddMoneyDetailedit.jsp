
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="ido.addmoneydetail.AddMoneyDetail"%>
<% 
	AddMoneyDetail vo = (AddMoneyDetail) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/addmoneydetail!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							投保用户号(卡号):
						</label>
							<input name="iuserId" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.iuserId"/>" />
					</div>
					 <div class="unit">
						<label>
							充值字段:
						</label>
							<my:newselect tagName="addType"  paraType="addmoneytype" width="100"  selectedValue="<%=vo.getAddType() %>"/>									
					</div>
					 <div class="unit">
						<label>
							充值金额 :
						</label>
							<input name="addMoney" size="30"  class="number  "    type="text"  value="<s:property value="vo.addMoney"/>" />
					</div>
					 <div class="unit">
						<label>
							投保单号 :
						</label>
							<input name="insuredFileId" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.insuredFileId"/>" />
					</div>
					 <div class="unit">
						<label>
							充值时间:
						</label>
							<input name="addTime" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.addTime"/>" />
					</div>
					 <div class="unit">
						<label>
							创建用户:
						</label>
							<input name="createUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createUser"/>" />
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
							<input name="updateUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.updateUser"/>" />
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


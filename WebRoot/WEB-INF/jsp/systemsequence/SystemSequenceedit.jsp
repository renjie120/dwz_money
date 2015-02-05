
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.sequence.SystemSequence"%>
<% 
	SystemSequence vo = (SystemSequence) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/systemsequence!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							序列代码 :
						</label>
							<input name="sequenceCode" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.sequenceCode"/>" />
					</div>
					 <div class="unit">
						<label>
							序列名称:
						</label>
							<input name="sequenceName" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.sequenceName"/>" />
					</div>
					 <div class="unit">
						<label>
							序列值:
						</label>
							<input name="sequenceContent" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.sequenceContent"/>" />
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


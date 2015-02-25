
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.bugList.BugList"%>
<% 
	BugList vo = (BugList) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/buglist!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							问题类型:
						</label>
							<my:newselect tagName="bugType"  paraType="bugtype" width="100"  selectedValue="<%=vo.getBugType() %>"/>									
					</div>
					 <div class="unit">
						<label>
							问题描述:
						</label>
							<input name="bugDesc" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.bugDesc"/>" />
					</div>
					 <div class="unit">
						<label>
							问题发现人:
						</label>
							<input name="createUser" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createUser"/>" />
					</div>
					 <div class="unit">
						<label>
							发现时间:
						</label>
							<input name="createTime" size="30"  class="textInput  " readonly='true'   type="text"  value="<s:property value="vo.createTime"/>" />
					</div>
					 <div class="unit">
						<label>
							待解决时间:
						</label>
							<input name="needTime" size="30"  class="textInput  "    type="text"  value="<s:property value="vo.needTime"/>" />
					</div>
					 <div class="unit">
						<label>
							解决人:
						</label>
							<input name="consolePeople" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.consolePeople"/>" />
					</div>
					 <div class="unit">
						<label>
							解决时间:
						</label>
							<input name="consoleTile" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.consoleTile"/>" />
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<input name="remark" size="30"  class="textInput  required"    type="text"  value="<s:property value="vo.remark"/>" />
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



<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.homepage.HomePageUrl"%>
<% 
	HomePageUrl vo = (HomePageUrl) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/homepageurl!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="urlId"
			value="<s:property value="vo.urlId"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							链接描述 :
						</label>
									<input name="urlDesc" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.urlDesc"/>" />
					</div>
					 <div class="unit">
						<label>
							链接:
						</label>
									<input name="url" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.url"/>" />
					</div>
					 <div class="unit">
						<label>
							排序号:
						</label>
									<input name="orderId" class="textInput  "  size="30" type="text"  value="<s:property value="vo.orderId"/>" />
					</div>
					 <div class="unit">
						<label>
							类型:
						</label>
									<input name="typeId" class="textInput  "  size="30" type="text"  value="<s:property value="vo.typeId"/>" />
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




<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="money.menu.Menu"%>
<% 
	Menu vo = (Menu) request.getAttribute("menuVo");
	String level = vo.getLevel() + "";
	String target = vo.getTarget() + "";
%>
<div class="pageContent">
	<form method="post" action="/money/menu!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="menuId"
			value="<s:property value="menuVo.menuId"/>">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label>
					菜单名:
				</label>
				<input name="menuName" class="textInput  required " size="30"
					type="text" value="<s:property value="menuVo.menuName"/>" />
			</div>
			<div class="unit">
				<label>
					链接:
				</label>
				<input name="url" class="textInput  " size="30" type="text"
					value="<s:property value="menuVo.url"/>" />
			</div>
			<!--  
			<div class="unit">
				<label>
					菜单指向:
				</label>
				<my:newselect tagName="target"  paraType="menutarget" defaultValue="<%=target%>"  />   
			</div>-->
			<div class="unit">
				<label>
					父级菜单:
				</label>
				<input name="parentId" class="textInput  " size="30" type="text"
					value="<s:property value="menuVo.parentId"/>" />
			</div>
			<div class="unit">
				<label>
					菜单级别:
				</label>
				<my:newselect tagName="level"  paraType="menulevel" 
					selectedValue="<%=level%>" />
			</div> 
			<div class="unit">
				<label>
					菜单页编码:
				</label>
				<input name="relId" class="textInput  " size="30" type="text"
					value="<s:property value="menuVo.relId"/>" />
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


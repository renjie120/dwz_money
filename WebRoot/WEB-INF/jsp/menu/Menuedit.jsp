
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script src="/js/treeCombox.js" type="text/javascript" />
<script type="text/javascript">
	/**
	 * 在弹出框里面点击关闭按钮，拖放按钮触发本事件.
	 */
	function myOperation() {
		$('#parentName').hideMenu();//隐藏弹出来的树形下拉菜单.
	}
	$(document).ready(function() {
		var content = {
			action : '/money/tree!getMenuTree.do',
			nameInput : 'parentName',
			height : '200px',
			idInput : 'parentId',
			treeId:"menuTree"
		};
		$('#parentName').treeCombox(content);
	});
</script>
<%@ page import="money.menu.Menu"%>
<% 
	Menu vo = (Menu) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/money/menu!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="menuId"
			value="<s:property value="vo.menuId"/>">
		<div class="pageFormContent" layoutH="57">  
					 <div class="unit">
						<label>
							菜单名称:
						</label>
						<input name="menuName" class="textInput  "  size="30" type="text"  value="<s:property value="vo.menuName"/>" />
					</div>
					 <div class="unit">
						<label>
							上级菜单:
						</label>
						<input name="parentId" id="parentId" type="hidden" value="<s:property value="vo.parentId"/>"/> 
						<input name="parentName" size="30" id="parentName" type="text" readonly="true"
							onclick="showMenu(); return false;" value="<s:property value="vo.parentName"/>"/> 
					</div>
					 <div class="unit">
						<label>
							排序号:
						</label>
									<input name="orderId" class="textInput  "  size="30" type="text"  value="<s:property value="vo.orderId"/>" />
					</div>
					 <div class="unit">
						<label>
							连接:
						</label>
									<input name="url" class="textInput  "  size="30" type="text"  value="<s:property value="vo.url"/>" />
					</div>
					 <div class="unit">
						<label>
							菜单级别:<s:property value='vo.level'/>
						</label>
									<my:newselect tagName="level"  paraType="menulevel" width="100" allSelected="true" selectedValue="<%=vo.getLevel() %>"/>									
					</div>
					 <div class="unit">
						<label>
							菜单编码:
						</label>
									<input name="relId" class="textInput  "  size="30" type="text"  value="<s:property value="vo.relId"/>" />
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


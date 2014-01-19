<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/treeCombox.js" type="text/javascript" />
<script type="text/javascript">
	/**
	 * 在弹出框里面点击关闭按钮，拖放按钮触发本事件.
	 */
	function myOperation() {
		$('#parentMenuName').hideMenu();//隐藏弹出来的树形下拉菜单.
	}
	$(document).ready(function() {
		var content = {
			action : '/money/tree!getMenuTree.do',
			nameInput : 'parentMenuName',
			height : '200px',
			idInput : 'parentId',
			treeId:"menuTree"
		};
		$('#parentMenuName').treeCombox(content);
	});
</script>
<div class="pageContent">
	<form method="post" action="/money/menu!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label> 菜单名称: </label> <input name="menuName"
					class="textInput required" size="30" type="text" />
			</div>
			<div class="unit">
				<label> 上级菜单: </label> <input name="parentId" id="parentId"
					type="hidden" /> <input name="parentName" size="30"
					id="parentMenuName" type="text" readonly="true" class="required" />
			</div>
			<div class="unit">
				<label> 排序号: </label> <input name="orderId" class="textInput "
					size="30" type="text" />
			</div>
			<div class="unit">
				<label> 连接: </label> <input name="url" class="textInput required"
					size="30" type="text" />
			</div>
			<div class="unit">
				<label> 菜单级别: </label>
				<my:newselect tagName="level" paraType="menulevel" width="100"
					allSelected="true" />
			</div>
			<div class="unit">
				<label> 菜单编码: </label> <input name="relId"
					class="textInput required" size="30" type="text" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
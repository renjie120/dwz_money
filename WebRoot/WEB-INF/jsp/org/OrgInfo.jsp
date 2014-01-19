<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/treeCombox.js" type="text/javascript" />
<script type="text/javascript">
	/**
	 * 在弹出框里面点击关闭按钮，拖放按钮触发本事件.
	 */
	function myOperation() {
		$('#parentOrgName').hideMenu();//隐藏弹出来的树形下拉菜单.
	}
	$(document).ready(function() {  
		var content = {
			action : '/money/tree!getOrgTree.do',
			nameInput : 'parentOrgName',
			height:'200px',
			idInput : 'parentOrg' ,
			treeId:"orgTree"
		}; 
		$('#parentOrgName').treeCombox(content);
	});
</script>
<div class="pageContent">
	<form method="post" action="/money/org!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="55">
			<div class="unit">
				<label> 组织名称: </label> <input name="orgName"
					class="textInput  required " size="30" type="text" />
			</div>
			<div class="unit">
				<label> 组织代码: </label> <input name="orderCode" class="textInput  "
					size="30" type="text" />
			</div>
			<div class="unit">
				<label> 父组织: </label> <input name="parentOrg" id="parentOrg"
					type="hidden" /> <input name="parentName" size="30"
					id="parentOrgName" type="text" readonly="true" class="required" />
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


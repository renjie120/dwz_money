<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/menu.js" type="text/javascript" />
<div class="pageContent">
	<form method="post" action="/money/menu!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label> 菜单名称: </label> <input name="menuName" class="textInput required"
					size="30" type="text" />
			</div>
			<div class="unit">
				<label> 上级菜单: </label>
			 <input name="parentId" id="parentId" type="hidden"  /> 
			 <input name="parentName" size="30" id="parentName" type="text" readonly="true"  class="required"
					onclick="showMenu(); return false;" />
			</div>
			<div class="unit">
				<label> 排序号: </label> <input name="orderId" class="textInput "
					size="30" type="text" />
			</div>
			<div class="unit">
				<label> 连接: </label> <input name="url" class="textInput required" size="30"
					type="text" />
			</div>
			<div class="unit">
				<label> 菜单级别: </label>
				<my:newselect tagName="level" paraType="menulevel" width="100"
					allSelected="true" />
			</div>
			<div class="unit">
				<label> 菜单编码: </label> <input name="relId" class="textInput required"
					size="30" type="text" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

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
			action : '/money/tree!getInsuredTree.do',
			nameInput : 'unitParentName',
			height : '200px',
			idInput : 'unitParentId',
			treeId : "menuTree"
		};
		$('#unitParentName').treeCombox(content);
	});
</script>
<%@ page import="ido.InsuredUnit.InsuredUnit"%>
<%
	InsuredUnit vo = (InsuredUnit) request.getAttribute("vo");
%>
<div class="pageContent">
	<form method="post" action="/money/insuredunit!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, refreshInsuredUnit);">
		<input type='hidden' name="sno" value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label> 编号: </label> <input name="unitCode"
					class="textInput  required" type="text"  size="30"
					value="<s:property value="vo.unitCode"/>" />
			</div>
			<div class="unit">
				<label> 投保单位 : </label> <input name="unitName" class="textInput  "  size="30"
					type="text" value="<s:property value="vo.unitName"/>" />
			</div>
			<div class="unit">
				<label> 联系人: </label> <input name="contactName" class="textInput  "  size="30"
					type="text" value="<s:property value="vo.contactName"/>" />
			</div>
			<div class="unit">
				<label> 手机: </label> <input name="contactMobile" class="digits  "  size="30"
					type="text" value="<s:property value="vo.contactMobile"/>" />
			</div>
			<div class="unit">
				<label> 邮箱: </label> <input name="contactEmail" class="email  "  size="30"
					type="text" value="<s:property value="vo.contactEmail"/>" />
			</div>
			<div class="unit">
				<label> 上级单位: </label> <input name="unitParentId" id="unitParentId"
					value="<s:property value="vo.unitParentId"/>" type="hidden" /> <input
					name="unitParentName" size="30" id="unitParentName" type="text" value="<s:property value="vo.unitParentName"/>"
					readonly="true" class="required"/> 
			</div>
			<div class="unit">
				<label> 是否显示: </label>
				<my:newselect tagName="unitState" paraType="yesorno" width="100"
					selectedValue="<%=vo.getUnitState() %>" />
			</div>
			<div class="unit">
				<label> 地址: </label>
				<textarea class="" name="unitAddress" cols="30" rows="2"><s:property value="vo.unitAddress" /></textarea>
			</div>
			<div class="unit">
				<label> 备注: </label>
				<textarea class="" name="unitRemark" cols="30" rows="2"><s:property value="vo.unitRemark" /></textarea>
			</div>
			<div class="unit">
				<label> 创建用户: </label> <input name="createUser" class="textInput  "
					readonly='true' type="text"
					value="<s:property value="vo.createUser"/>" />
			</div>
			<div class="unit">
				<label> 创建时间: </label> <input name="createTime" class="textInput  "
					readonly='true' type="text"
					value="<s:property value="vo.createTime"/>" />
			</div>
			<div class="unit">
				<label> 更新用户: </label> <input name="updateUser" class="textInput  "
					readonly='true' type="text"
					value="<s:property value="vo.updateUser"/>" />
			</div>
			<div class="unit">
				<label> 更新时间: </label> <input name="updateTime" class="textInput  "
					readonly='true' type="text"
					value="<s:property value="vo.updateTime"/>" />
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


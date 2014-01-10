<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%
	String roleId = (String) request.getAttribute("roleId");
%>
<script type="text/javascript">
<!--
	function onClick(event, treeId, treeNode, clickFlag) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var node = treeObj.getNodeByTId(treeNode.tId);
		//注意下面的node.id不等于treeNode.id!!
		$.bringBack({
			'moneyType' : node.id,
			'moneyTypeName' : treeNode.name
		})
	}
//-->
</script>
<div class="pageContent">
<div class="pageFormContent" layoutH="57" style="overflow:hidden;padding:0px 5px; "> 
	<div class="zTreeDemoBackground left"  style="overflow:hidden">
		<ul id="treeDemo" checkable="true" class="ztree"  style="overflow-y:hidden" noScroll="true"
			url='/money/role!getRoleMenuTree.do?roleId=<%=roleId%>'></ul>
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
</div>
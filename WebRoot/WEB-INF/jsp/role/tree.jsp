<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%String roleId =(String) request.getAttribute("roleId"); %>
<script type="text/javascript">
<!--
function onClick(event, treeId, treeNode, clickFlag) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var node = treeObj.getNodeByTId(treeNode.tId); 
	//注意下面的node.id不等于treeNode.id!!
	$.bringBack({'moneyType':node.id, 'moneyTypeName':treeNode.name})
}	 
//-->
</script>
<div class="zTreeDemoBackground left">
	 	<ul id="treeDemo" checkable="true" class="ztree" height='500' url='/money/role!getRoleMenuTree.do?roleId=<%=roleId%>'></ul> 
</div>
 
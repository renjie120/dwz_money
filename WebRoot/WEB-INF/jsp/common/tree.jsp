<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%String treeType =(String) request.getAttribute("treeType"); %>
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
<%if("moneyType".equals(treeType)){ //金额类型树%>
<ul id="treeDemo" class="ztree" height='300'
	url='/money/tree!getMoneyTypeTree.do'></ul>
<%} 
	else if("menu".equals(treeType)) //菜单树
	{%>
<ul id="treeDemo" class="ztree expand" height='300'
	url='/money/tree!getMenuTree.do'></ul>
<%} %>
else if("org".equals(treeType)) //组织机构树 {%>
<ul id="treeDemo" class="ztree expand" height='300'
	url='/money/tree!getOrgTree.do'></ul>
<%} %>

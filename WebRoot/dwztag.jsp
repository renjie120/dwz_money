<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="common.taglib.TreeNode"%>
<%
	//根节点必须id=父亲节点。。。同时在树形的标签里面设置rootId=根节点id
	TreeNode root = new TreeNode("root", "nm", "整个世界", "root");
	TreeNode node1 = new TreeNode("1", "nm", "太阳系", "root");
	TreeNode node2 = new TreeNode("2", "nm", "地球", "1");
	TreeNode node3 = new TreeNode("3", "nm", "火星", "1");
	TreeNode node4 = new TreeNode("4", "nm", "亚洲", "2");
	TreeNode node5 = new TreeNode("5", "nm", "非洲", "2");
	TreeNode node6 = new TreeNode("6", "nm", "51号星球", "9");
	List test = new ArrayList();
	test.add(root);
	test.add(node1);
	test.add(node2);
	test.add(node3);
	test.add(node4);
	test.add(node5);
	test.add(node6);
%>
<body>
	<my:tree rootId="root" treeName="test" treeList="<%=test%>"
		title="我的第一个树形测试界面" checkbox="true"></my:tree>

	<br>
	<my:table list="<%=test%>" id="testTable" layoutH="500">
		<my:column header="主键" value="id" width="100"></my:column>
		<my:column header="name" value="id" width="200"></my:column>
		<my:column header="描述" value="des" width="200"></my:column>
		<my:column header="父亲节点" value="parent"></my:column>
		<my:pagebar totalCount="100"></my:pagebar>
	</my:table>
</body>
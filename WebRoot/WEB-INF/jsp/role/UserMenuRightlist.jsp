
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
<script type="text/javascript">
	var setting = {
		async : {
			enable : true,
			url : "/money/tree!getOrgWithPeopleTree.do",
			autoParam : [ "id", "name" ],
			dataFilter : filter
		},
		callback : {
			onClick : onClick
		}
	};

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
				.getSelectedNodes(), v = "", v2 = "";
		for ( var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
			v2 += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		if (v2.length > 0)
			v2 = v2.substring(0, v2.length - 1);
		$this = $('#jbsxBox');
		var wdt = $this.parent().width() - $('#treeDemo').width() - 28;
		$this.height($('#' + $this.attr('relHeight')).height()).width(wdt)
				.loadUrl("/money/menu!queryByUser.do?userId=" + v2, {},
						function() {

						});
	}

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	function initMyUI(){   
		//专门针对用户权限控制的样式控制. 
		if($('div[autoHeight]').size()>0){ 
			 $("#container .tabsPageContent").height($("div.layout").height()-$("div.tabsHeader").height()-5);
			 var _height = $("#container .tabsPageContent").height() - $('#container div.tabsHeaderContent').height();
			// $('div[autoHeight].tabsContent').height(_height-15);
			 $('div.zTreeDemoBackground[autoHeight]').height(_height-30);
		 }
	 
		$this = $('#jbsxBox');
		var wdt = $this.parent().width() - $('#treeDemo').width() - 28;
		$this.height($('#' + $this.attr('relHeight')).height()).width(wdt);
	}
	
	//在子页面里面刷新子页面自己.
	function refreshSelf(obj) {
		$form = $(obj);
		//$("#jbsxBox").reload($form.attr('action'), {data: $form.serializeArray()},function(){});
		$("#jbsxBox").loadUrl($form.attr('action'), {
			"menuName" : $('input[name=menuName]').val()
		}, function() { 
		});
		return false;
	}
//-->
</script>
<div class="pageContent" style="padding:5px" >
	<div class="tabs" id="layout">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>用户菜单权限</span> </a>
					</li>
					<li><a href="javascript:;"><span>用户角色分配</span> </a>
					</li>
					<li><a href="javascript:;"><span>角色菜单权限</span> </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="tabsContent"
			style="border:1px #BAD1D7 solid;overflow-x:hidden;" autoHeight>
			<div>
				<div class="zTreeDemoBackground left" id='userMenuRight'
					style="float:left; display:block;overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff"
					autoHeight>
					<ul id="treeDemo" class="ztree" lazy="true"></ul>
				</div>

				<div id="jbsxBox" class="unitBox" relHeight='userMenuRight'
					style="height:0px;margin-left:246px;border:1px #BAD1D7 solid;">
					<!--#include virtual="list1.html" -->
				</div>
			</div>

			<div>用户角色分配（暂时是空白）</div>

			<div>角色菜单权限（暂时是空白）</div>

		</div>
	</div>
</div>





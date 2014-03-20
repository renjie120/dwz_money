
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
	function onClick(e, treeId, treeNode) { 
		var zTree = $.fn.zTree.getZTreeObj(treeId), nodes = zTree
				.getSelectedNodes(), v = "", v2 = "";
 
		if (nodes.length > 0 && !nodes[0].isParent) {
			for ( var i = 0, l = nodes.length; i < l; i++) {
				v += nodes[i].name + ",";
				v2 += nodes[i].id + ",";
			}
			if (v.length > 0)
				v = v.substring(0, v.length - 1);
			if (v2.length > 0)
				v2 = v2.substring(0, v2.length - 1); 
			//用户菜单权限的列表界面 
			if(treeId=='userMenuRight_menutree'){ 
				var $this = $('#jbsxBox');
				var wdt = $this.parent().width() - $('#'+treeId).width() - 15;
				$this.height($('#' + $this.attr('relHeight')).height()).width(wdt).loadUrl(
					"/money/menu!queryByUser.do?userId=" + v2,
					{},
					function() {
						var _b = $('#tableArea');
						var _pgContaint = _b.parents(
								"div.pageContent:first").parents(
								"div:first");
						var __int = _pgContaint.height()
								- _pgContaint.find(
										'div.pageHeader:first')
										.height()
								- _b.attr('modifyHeight');
						_b.height(__int);
					});
			}else{ 
			   //查询显示右边的用户拥有的角色列表
				var $this = $('#jbsxBox2');
				$this.find('#myUserRoleDiv_userId').val(v2);
				var wdt = $this.parent().width() - $('#'+treeId).width() - 15; 
				$this.height($('#' + $this.attr('relHeight')).height()).width(wdt).loadUrl(
					"/money/role!queryRoleByUserId.do?userId="+v2,
					{},
					function() {
						var _b = $('#tableArea2');
						var _pgContaint = _b.parents(
								"div.pageContent:first").parents(
								"div:first");
						var __int = _pgContaint.height()
								- _pgContaint.find(
										'div.pageHeader:first')
										.height()
								- _b.attr('modifyHeight');
						_b.height(300);
					});
			}
		} else { 
			zTree.expandNode(nodes[0]); 
		}
	}

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	function resizeGrid() {
		$this = $('#jbsxBox');
		var wdt = $this.parents().width() - $('#userMenuRight_menutree').width() - 15;
		$this.width(wdt);
		$('#tbtb').width(wdt); 
		$this.find('div.gridScroller').wdt(wdt + "px");
	}
	function initMyUI() {
		//专门针对用户权限控制的样式控制. 
		if ($('div[autoHeight]').size() > 0) {
			$("#container .tabsPageContent")
					.height(
							$("div.layout").height()
									- $("div.tabsHeader").height() - 5);
			var _height = $("#container .tabsPageContent").height()
					- $('#container div.tabsHeaderContent').height();
			$('div.zTreeDemoBackground[autoHeight]').height(_height - 30);
		}
		if($('#jbsxBox:visible').size()>0)
			changeTable('jbsxBox','tbtb','userMenuRight_menutree');
		if($('#jbsxBox2:visible').size()>0)
			changeTable('jbsxBox2','tbtb2','userMenuRight_menutree2');
	}

	function changeTable(boxId,tableId,treeId){ 
		$this = $('#'+boxId);
		var wdt = $this.parents().width() - $('#'+treeId).width() - 15;
		var hgt = $('#' + $this.attr('relHeight')).height();
		$this.height(hgt).width(wdt); 
		$('#'+tableId).height(hgt - 55); 
		$this.find('div.panelBar').width(wdt);

		//找到嵌入的子页面里面的table，没有对应的table ID的！！因为生成表格的时候，会自动把全部属性重新组装一遍！
		var _b = $this.find('.gridScroller');
		var _pgContaint = _b.parents("div.pageContent:first").parents(
				"div:first");
		var __int = _pgContaint.height()
				- _pgContaint.find('div.pageHeader:first').height()
				- _b.attr('modifyHeight'); 
		_b.height(__int).width(wdt);
	}
	
	//在子页面里面刷新子页面自己.
	function refreshSelf(obj) {
		$form = $(obj);
		//$("#jbsxBox").reload($form.attr('action'), {data: $form.serializeArray()},function(){});
		$("#jbsxBox").loadUrl($form.attr('action'), {
			"menuName" : escape($('input[name=menuName]').val())
		}, function() {
		});
		return false;
	}
 	//角色管理主界面.
//-->
</script>
<div class="pageContent" style="padding:5px">
	<div>
				<div class="zTreeDemoBackground left" id='userMenuRight2'
					style="float:left; display:block;overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff"
					autoHeight>  
					 <ul id="userMenuRight_menutree2" class="ztree" lazy="true" url="/money/tree!getOrgWithPeopleTree.do" autoParam="[ 'id', 'name']"></ul>
				</div>
				<div id="jbsxBox2" class="unitBox"  relHeight='userMenuRight2'
					style="height:0px;margin-left:246px;border:1px #BAD1D7 solid;overflow:hidden;">
					 
				</div>
			</div> 
</div>
<!--   li><a href="javascript:;"><span>用户菜单权限</span> </a></li-->
					<!--  div>
						<div class="zTreeDemoBackground left" id='userMenuRight'
							style="float:left; display:block;overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff"
							autoHeight> 
							<ul id="userMenuRight_menutree" class="ztree" lazy="true" url="/money/tree!getOrgWithPeopleTree.do" autoParam="[ 'id', 'name']"></ul>
						</div>
		
						<div id="jbsxBox" class="unitBox" relHeight='userMenuRight'
							style="height:0px;margin-left:246px;border:1px #BAD1D7 solid;overflow:hidden;"> 
						</div>
					</div>-->




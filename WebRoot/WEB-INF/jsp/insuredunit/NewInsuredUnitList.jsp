
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/money/insuredunit!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form> 
<script type="text/javascript"> 
	function onClick(e, treeId, treeNode) {  
		var zTree = $.fn.zTree.getZTreeObj(treeId), nodes = zTree
				.getSelectedNodes(), v = "", v2 = "";
		//得到选中的节点id和名字的集合.
 		for ( var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
			v2 += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		if (v2.length > 0)
			v2 = v2.substring(0, v2.length - 1); 
				
		if (nodes.length > 0 && !nodes[0].isParent) { 
		   zTree.expandNode(nodes[0]); 
		} 
		
		if(treeId=='insuredUnit_tree'){ 
			var $this = $('#jbsxBox3');
			var wdt = $this.parent().width() - $('#'+treeId).width() - 15; 
			$('#selectedUnitId').val(v2)
			$this.height(700).width(wdt).loadUrl(
				"/money/insuredunit!queryByParent.do?unitParentId=" + v2 );
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
		$this = $('#jbsxBox3');
		var wdt = $this.parents().width() - $('#userMenuRight_menutree').width() - 15;
		$this.width(wdt);
		$('#tbtb3').width(wdt); 
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
		if($('#jbsxBox3:visible').size()>0)
			changeTable('jbsxBox3','tbtb3','insuredUnit_tree');
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
	
	function refreshInsuredUnit(txt) {
		// 提示返回结果.
		if (txt.responseText)
			alertMsg.info(txt.responseText);
		else
			alertMsg.info(txt);
		// 关闭当前页面
		$.pdialog.closeCurrent();
		 
	} 
	
	function addUnitParent(){ 
		return "unitParentId="+$('#selectedUnitId').val();
	}
 	//角色管理主界面.
//-->
</script> 
<input type="hidden" id="selectedUnitId" value="0"/>
<div class="pageContent"> 
	<div class="panelBar">
		<ul class="toolBar">
			<li> 
				<a class="add" addToUrl="addUnitParent" href="/money/insuredunit!beforeAdd.do" target="dialog" mask="true"  
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/money/insuredunit!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/money/insuredunit!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<!-- li>
				<a class="icon" href="/money/insuredunit!initImport.do" target="dialog"><span>从EXCEL导入</span> </a>
			</li -->
		</ul>
	</div>
	<div id='insuredUnit_Div'>
		<div class="zTreeDemoBackground left" id='newInsuredUnit'
			style="float:left; display:block;overflow:hidden; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff"
			autoHeight>  
			 <ul id="insuredUnit_tree" class="ztree"  url="/money/tree!getInsuredTree.do" autoParam="[ 'id', 'name']"></ul>
		</div>
		<div id="jbsxBox3" class="unitBox"   
			style="height:700px;margin-left:246px;border:1px #BAD1D7 solid;overflow:hidden;"> 
		</div>
	</div>  
	</div>
</div>


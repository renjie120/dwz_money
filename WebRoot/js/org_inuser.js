var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};

function myOperation() {
	hideMenu();
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "", v2 = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
		v2 += nodes[i].id + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	if (v2.length > 0)
		v2 = v2.substring(0, v2.length - 1);
	$("#orgName").attr("value", v);
	$("#orgId").attr("value", v2);
	hideMenu();
}

function showOrgMenu() {
	$.get("/money/tree!getOrgTree.do", function(data) {
		eval("var json=" + data);
		$.fn.zTree.init($("#treeDemo"), setting, json);
		var cityObj = $("#orgName");
		var cityOffset = cityObj.offset();
		$("#menuContent").css({
			left : cityOffset.left + "px",
			top : cityOffset.top + cityObj.outerHeight() + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	});
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}

$(document).ready(
function() {
	$("body").append("<div id='menuContent' class='menuContent myinnerTree' style='display:none; position: absolute;'> "
	+"<ul id='treeDemo' class='ztree' style='margin-top:0; width:250px;height:300px;overflow:auto;'></ul> </div>");
});
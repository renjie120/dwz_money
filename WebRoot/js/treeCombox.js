(function($) {
	$.fn.treeCombox = function(p) {
		return this.each(function() {
			if (!docloaded) {
				$(this).hide();
				var t = this;
				$(document).ready(function() {
					$.addTreeCombox(t, p);
				});

			} else {
				$.addTreeCombox(this, p);
			}

		});
	}
	$.addTreeCombox = function(t, c) {
		if (t.gt) {
			return false;
		}
		
		c = $.extend({
			height : '300px',
			width : '250px',
			action : null,
			nameInput : null,
			idInput : null,
			treeId : null,
			checkbox:false
		}, c);

		var dbs = {
			_click : function(e, treeId, treeNode) {
				if(!c.checkbox){
				var zTree = $.fn.zTree.getZTreeObj(c.treeId), nodes = zTree
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
				$("#" + c.nameInput).attr("value", v);
				$("#" + c.idInput).attr("value", v2);
				dbs.hideMenu();
				}
			},
			hideMenu : function() {
				$("#" + c.contentId).fadeOut("fast");
				$("body").unbind("mousedown", dbs.onBodyDown);
			},
			onBodyDown : function(event) {
				if (!(event.target.id == "menuBtn"
						|| event.target.id == c.contentId || $(event.target)
						.parents("#" + c.contentId).length > 0)) {
					dbs.hideMenu();
				}
			},
			showTree : function() {
				var $this = dbs.obj;
				$.get(c.action, function(data) {
					eval("var json=" + data);
					if(c.checkbox){
						setting.check.enable = true;
					}
					$.fn.zTree.init($("#" + c.treeId), setting, json);
					var cityObj = $("#" + c.nameInput);
					var cityOffset = cityObj.offset();
					$("#" + c.contentId).css({
						left : cityOffset.left + "px",
						top : cityOffset.top + cityObj.outerHeight() + "px"
					}).slideDown("fast");
					$("body").bind("mousedown", dbs.onBodyDown);
				});
				return false;
			}
		};
		var setting = {
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			check: {
				enable: false
			},
			callback : {
				onClick : dbs._click
			}
		};
		c.contentId = "_content_" + c.treeId;
		$("body")
				.append(
						"<div id='"
								+ c.contentId
								+ "' class='menuContent myinnerTree' style='display:none; position: absolute;'><a href='javascript:_makesure()'>确定</a>  <a href='javascript:_clear()'>清除</a> <a href='javascript:_cancel()'>取消</a>"
								+ "<ul id='" + c.treeId
								+ "' class='ztree' style='margin-top:0;width:"
								+ c.width + ";height:" + c.height
								+ ";overflow:auto;'></ul> </div>");

		dbs.obj = $(t);
		dbs.obj.bind('click', dbs.showTree);
		t.gt = dbs;
	};

	var docloaded = false;
	$(document).ready(function() {
		docloaded = true;
	});

	$.fn.hideMenu = function() {
		return this.each(function() {
			if (this.gt)
				this.gt.hideMenu();
		});
	};
})(jQuery);
function _cancel(){
	
}
function _clear(){
}
function _makesure(){
	
}
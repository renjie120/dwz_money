function myOperation() {
	$('#moneyTypeName').hideMenu();// 隐藏弹出来的树形下拉菜单.
}
$(document).ready(function() {
	var content = {
		action : '/money/tree!getMoneyTypeTree.do',
		nameInput : 'moneyTypeName2',
		height : '200px',
		 checkbox:true,
		idInput : 'moneyType2',
		treeId : "ttt"
	};
	$('#moneyTypeName2').treeCombox(content);

});
function _cancel() {
	$('#moneyTypeName2').hideMenu();
}
function _clear() {
	$('#moneyTypeName2').val('');
	$('#moneyType2').val('');
	$('#moneyTypeName2').hideMenu();
}
function _makesure() {
	var ans = [];
	var names = [];
	var zzz = $.fn.zTree.getZTreeObj("ttt"); 
	var nodes = zzz.getCheckedNodes(true),v='',v2='';
	if (nodes.length > 0 ) {
		for ( var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
			v2 += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		if (v2.length > 0)
			v2 = v2.substring(0, v2.length - 1); 
	}   
	$("#moneyTypeName2").attr("value", v);
	$("#moneyType2").attr("value", v2);
	$('#moneyTypeName2').hideMenu();
}
function myOperation() {
	$('#moneyTypeName').hideMenu();// 隐藏弹出来的树形下拉菜单.
}
$(document).ready(function() {
	var content = {
		action : '/money/tree!getMoneyTypeTree.do',
		nameInput : 'moneyTypeName2',
		height : '200px',
		// checkbox:true,
		idInput : 'moneyType2',
		treeId : "moneyTree3"
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
	$('#moneyTree3').find('span.checkbox_true_full').each(
			function() {
				ans.push($(this).next('a').attr('id')
						.replace('moneyTree3_', '').replace('_a', ''));
				names.push($(this).next('a').attr('title'));
			});
	$("#moneyTypeName2").attr("value", names.join(','));
	$("#moneyType2").attr("value", ans.join(','));
	$('#moneyTypeName2').hideMenu();
}
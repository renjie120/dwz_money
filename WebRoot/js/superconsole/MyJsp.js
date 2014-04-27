function test() {
	$('#exesql').attr('action', '/money/superconsole!exeSqlAction.do')
			.ajaxSubmit(function(txt) {
				// 提示返回结果.
				alertMsg.info(txt);
			});
}

function importt() {
	$('#exesql').attr('action', '/money/superconsole!importMoney.do')
			.ajaxSubmit(function(txt) {
				alert(txt);
				$('#moneyStr').val('');
			});
}

function import4() {
	$('#exesql').attr('action', '/money/superconsole!importStockDeals.do')
			.ajaxSubmit(function(txt) {
				alert(txt);
				$('#moneyStr').val('');
			});
} 
function importt2() {
	$('#exesql').attr('action', '/money/superconsole!importDiary.do')
			.ajaxSubmit(function(txt) {
				alert(txt);
				$('#moneyStr').val('');
			});
}
function importt3() {
	$('#exesql').attr('action', '/money/superconsole!importGongguo.do')
			.ajaxSubmit(function(txt) {
				alert(txt);
				$('#moneyStr').val('');
			});
} 
function query() {
	$('#exesql').attr('action', '/money/superconsole!querySqlAction.do')
			.ajaxSubmit(function(txt) {
				var json = (new Function("return " + txt))();
				var rowlen = json.length;
				var ans = [];
				if (rowlen > 0) {
					var colLen = json[0].length;
					for ( var i = 0; i < rowlen; i++) {
						var onerow = json[i];
						ans.push("<tr>");
						for ( var j = 0; j < colLen; j++) {
							ans.push("<td>");
							ans.push(onerow[j]);
							ans.push("</td>");
						}
						ans.push("</tr>");
					}
				}
				$('#anstable').html('');
				$('#anstable').append(ans.join(''));
			});
}
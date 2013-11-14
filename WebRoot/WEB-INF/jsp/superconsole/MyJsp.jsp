<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
<!--  
function test(){    
	 $('#exesql').attr('action','/money/superconsole!exeSqlAction.do').ajaxSubmit(function(txt){  
	 	 //提示返回结果.
	 	 alertMsg.info(txt);   
	 });
}

function query(){    
	 $('#exesql').attr('action','/money/superconsole!querySqlAction.do').ajaxSubmit(function(txt){
	    var json = (new Function("return " + txt))();
	    var rowlen = json.length;
	    var ans = [];  
	    if(rowlen>0){ 
		    var colLen = json[0].length;
		    for(var i=0;i<rowlen;i++){
		    	var onerow = json[i];
		    	ans.push("<tr>");
		    	for(var j=0;j<colLen;j++){
		    		ans.push("<td>");ans.push(onerow[j]);ans.push("</td>");
		    	}
		    	ans.push("</tr>");
		    }
	 	} 
	 	$('#anstable').html('');
	 	$('#anstable').append(ans.join(''));
	 });
}
//-->
</script>
<div class="pageContent">
	<form method="post" action="/money/superconsole!exeSqlAction.do"
		id="exesql" class="pageForm required-validate">
		<div class="pageFormContent" width='300px' layoutH="-57">
			<div class="unit">
				<button onclick="test()" type="button">
					执行
				</button>
				<button onclick="query()" type="button">
					查询
				</button>
			</div>
			<div class="unit">
				<label>
					输入sql语句:
				</label>
				<textarea class="editor" name="sql" rows="5" cols="80"></textarea>
			</div> 
			<div class="unit">
				<label>
					查询结果:
				</label>
				<table id="anstable"></table>
			</div> 
		</div>
	</form>
</div> 
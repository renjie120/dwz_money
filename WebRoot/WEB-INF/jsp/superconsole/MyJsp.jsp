<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script src="/js/superconsole/MyJsp.js" type="text/javascript"></script>
<form method="post" action="#" id="exesql">
	<div class="pageFormContent" width='300px' layoutH="-57">
		<div class="unit">
			<label> 人工导入数据: </label>
			<textarea class="editor" name="moneyStr" id="moneyStr" rows="5"
				cols="80"></textarea>
		</div>
		<div class="unit">
			<button onClick="importt()" type="button">导入金额</button>
			<button onClick="importt2()" type="button">导入日记</button>
			<button onClick="importt3()" type="button">导入功过</button>
			<button onClick="import4()" type="button">导入交易</button>
			<button onclick="test()" type="button">执行</button>
			<button onclick="query()" type="button">查询</button>
		</div>
		<div class="unit">
			<label> 输入sql语句: </label>
			<textarea class="editor" name="sql" rows="5" cols="80"></textarea>
		</div>
		<div class="unit">
			<label> 查询结果: </label>
			<table id="anstable"></table>
		</div>
	</div>
</form>

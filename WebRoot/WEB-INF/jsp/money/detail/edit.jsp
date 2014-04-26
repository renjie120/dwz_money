<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>  
<script src="/js/treeCombox.js" type="text/javascript" />
<script type="text/javascript">
<!--
	function test() {
		$('#moneyEditInfo').ajaxSubmit(function(txt) {
			//提示返回结果.
			alertMsg.info(txt);
			//关闭当前页面
			setTimeout(function() {
				navTab.closeCurrentTab();
			}, 100);
			//刷新当前tab页.
			navTab.reloadFlag("moneylist");
		});
	}

	function myOperation() {
		$('#moneyTypeName').hideMenu();//隐藏弹出来的树形下拉菜单.
	}
	$(document).ready(function() {
		var content = {
			action : '/money/tree!getMoneyTypeTree.do',
			nameInput : 'moneyTypeName',
			height : '200px',
			idInput : 'moneyType',
			treeId : "moneyTree"
		};
		$('#moneyTypeName').treeCombox(content);
	});
//-->
</script>
<div class="pageContent">
	<form method="post" action="/money/newmoney!doUpdate.do"
		class="pageForm required-validate" id="moneyEditInfo" onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layouth='57'>
			<input type='hidden' name="moneySno"
				value="<s:property value="moneyVo.moneySno"/>"> <input
				type='hidden' name="bookType"
				value="<s:property value="moneyVo.bookType"/>">
			<div class="unit">
				<label> 时间： </label> <input type="text" name="moneyTime"
					class="date" size="30" readOnly="true"
					value="<s:property value="moneyVo.moneyTime"/>" /> <a
					class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label> 金额： </label> <input name="money" class="number required"
					type="text" size="30" alt="请输入数字"
					value="<s:property value="moneyVo.money"/>" />
			</div>
			<div class="unit">
				<label> 类别： </label> <input class="required" name="moneyTypeName" id="moneyTypeName"
					readOnly="true" type="text"
					value="<s:property value="moneyVo.moneyTypeName"/>" /> <input
					name="moneyType" 	id="moneyType"  type="hidden"
					value="<s:property value="moneyVo.moneyType"/>" /> </a>
			</div> 
			 <s:if test="%{moneyVo.splitSno!=null}"> 
			 <div class="unit">
				<label> 原金额： </label><label>  <s:property value="moneyVo.realMoney"/> </label>
			 </div>
			 </s:if>
			  <s:if test="%{moneyVo.splitSno==null}"> 
				<div class="unit">
				<label> 拆分： </label> <select name="splitMonth"><option value="1">不拆分</option><option value="2">2月</option>
					<option value="3">3月</option>
					<option value="6">6月</option>
					<option value="8">8月</option>
					<option value="12">12月</option>
				</select>
			 </div>
			 </s:if> 
			<div class="unit">
				<label> 描述： </label>
				<textarea class="editor" name="moneyDesc" rows="5" cols="30"><s:property value="moneyVo.moneyDesc" /></textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

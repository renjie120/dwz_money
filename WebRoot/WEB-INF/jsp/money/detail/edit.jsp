<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
<!-- 
function test(){  
	 $('#moneyEditInfo').ajaxSubmit(function(txt){ 
	 	 //提示返回结果.
	 	 alertMsg.info(txt); 
	 	 //关闭当前页面
	 	 setTimeout(function(){navTab.closeCurrentTab();}, 100);
	 	 //刷新当前tab页.
	 	 navTab.reloadFlag("moneylist");
	 });
}
//-->
</script>
<div class="pageContent">
	<form method="post" action="/money/newmoney!doUpdate.do"
		class="pageForm required-validate" id="moneyEditInfo">
		<div class="pageFormContent" layouth='-50'>
			<input type='hidden' name="moneySno"
				value="<s:property value="moneyVo.moneySno"/>">
			<input type='hidden' name="bookType"
				value="<s:property value="moneyVo.bookType"/>">
			<div class="unit">
				<label>
					时间：
				</label>
				<input type="text" name="moneyTime" class="date" size="30" readOnly="true"
					value="<s:property value="moneyVo.moneyTime"/>" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					金额：
				</label>
				<input name="money" class="number required" type="text" size="30"
					alt="请输入数字" value="<s:property value="moneyVo.money"/>" />
			</div>
			<div class="unit">
				<label>
					类别：
				</label>
				<input class="required" name="moneyTypeName" readOnly="true" type="text" value="<s:property value="moneyVo.moneyTypeName"/>"/> 
				<input  name="moneyType" type="hidden" value="<s:property value="moneyVo.moneyType"/>"/> 
				<a class="btnLook" href="/money/tree!moneyTypeTree.do" lookupGroup="obj"  
					lookupToPks="true" lookupPk="moneyType" title='收支类别树'  width='300'></a>  
			</div>
			<div class="unit">
				<label>
					描述：
				</label>
				<textarea class="editor" name="moneyDesc" rows="15" 
				cols="80"><s:property value="moneyVo.moneyDesc" /></textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="test()">
								保存
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

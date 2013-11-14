<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
<!-- 
function test(){  
	 $('#moneyAddInfo').ajaxSubmit(function(txt){ 
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
	<form method="post" action="/money/newmoney!doAdd.do" id="moneyAddInfo"
		class="pageForm required-validate" >
		<div class="pageFormContent"  layouth='-50'>
			<div class="unit">
				<label>
					时间：
				</label>
				<input type="text" name="moneyTime" class="date" size="30" readOnly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					金额：
				</label>
				<input name="money" class="number required" type="text" size="30" alt="请输入数字" />
			</div>
			<div class="unit">
				<label>
					类别：
				</label> 
				<input class="required" name="moneyTypeName" type="text" readOnly="true"/> 
				<input  name="moneyType" type="hidden"/> 
				<a class="btnLook" href="/money/tree!moneyTypeTree.do"
				 lookupGroup="obj"  lookupToPks="true" 
				 lookupPk="moneyType" title='收支类别树'  width='300'></a> 
			</div>
			<div class="unit">
				<label>
					描述：
				</label>
				<textarea class="editor" name="moneyDesc" rows="15" cols="80"></textarea>
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


<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript">
$(function(){
	$('#iuserId').blur(function(){
	if(this.value!='')
	   $.ajax({
		  type:'POST', 
		  url:'/money/insuredfile!isExistedInsuredUserNo.do',
		  dataType:'json',
		  data: {'insuredFileId':encodeURIComponent(this.value)},
		  success: afterJudge,
		  error: DWZ.ajaxError
		 }); 
	});
	
	$('#shopId').blur(function(){
	if(this.value!='')
	   $.ajax({
		  type:'POST', 
		  url:'/money/insuredfile!isExistedShopCode.do',
		  dataType:'json',
		  data: {'insuredFileId':encodeURIComponent(this.value)},
		  success: afterJudge2,
		  error: DWZ.ajaxError
		 }); 
	});
	
})

function afterJudge2(json) {  
	 if((json+"")=='false'){
		  DWZ.ajaxDone('不存在该商铺编号"'+$('#shopId').val()+'"，请重新输入');
		  $('#shopId').val('');
	 }
	}
	
function afterJudge(json) {  
	 if((json+"")=='false'){
		  DWZ.ajaxDone('不存在该用户号"'+$('#iuserId').val()+'"，请重新输入');
		  $('#iuserId').val('');
	 }
	}
	
</script>	
<div class="pageContent">
	<form method="post" action="/money/consumeinfo!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							投保用户号: 
						</label>
							<input name="iuserId"  id="iuserId" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							所属商铺编号 : 
						</label>
							<input name="shopId" id="shopId"  class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							支付状态: 
						</label>
							<my:newselect tagName="consumeStatus"  paraType="paytype" width="100"  />
					</div>
					 <div class="unit">
						<label>
							消费金额: 
						</label>
							<input name="consumeMoney"  class="number " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							刷卡消费: 
						</label>
							<input name="cardMoney"  class="number " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							现金支付: 
						</label>
							<input name="cashMoney"  class="number " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							消费时间: 
						</label>
							<input type="text" name="consumeTime" class="date "  size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
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
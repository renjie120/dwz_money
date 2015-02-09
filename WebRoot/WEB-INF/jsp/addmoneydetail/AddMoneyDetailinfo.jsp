
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript"> 
$(function(){

$('#insuredFileId').blur(function(){
	if(this.value!='')
	   $.ajax({
		  type:'POST', 
		  url:'/money/insuredfile!isExistedFileId.do',
		  dataType:'json',
		  data: {'insuredFileId':encodeURIComponent(this.value)},
		  success: afterJudge2,
		  error: DWZ.ajaxError
		 }); 
	});
	
	
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
	
	
});

function afterJudge(json) {  
	 if((json+"")=='false'){
		  DWZ.ajaxDone('不存在该投保用户卡号"'+$('#iuserId').val()+'"!');
		  $('#iuserId').val('');
	 }
	}
	
function afterJudge2(json) {  
	 if((json+"")=='false'){
		  DWZ.ajaxDone('不存在该投保单号"'+$('#insuredFileId').val()+'"!');
		  $('#insuredFileId').val('');
	 }
	}
</script>

<div class="pageContent">
	<form method="post" action="/money/addmoneydetail!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							投保用户号(卡号): 
						</label>
							<input name="iuserId" id="iuserId" value="00001" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							充值字段: 
						</label>
							<my:newselect tagName="addType"  paraType="addmoneytype" width="100"  />
					</div>
					 <div class="unit">
						<label>
							充值金额 : 
						</label>
							<input name="addMoney "  class="number required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							投保单号 : 
						</label>
							<input name="insuredFileId" value="20150001" id="insuredFileId" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							充值时间: 
						</label>
							<input type="text" name="addTime" class="date  "  size="30" readOnly="true"   />
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
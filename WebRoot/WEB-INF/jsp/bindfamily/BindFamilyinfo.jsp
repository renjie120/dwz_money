
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript">
$(function(){
	$('#iuserNo').blur(function(){
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
})

function afterJudge(json) {  
	 if((json+"")=='false'){
		  DWZ.ajaxDone('不存在该用户号"'+$('#iuserNo').val()+'"，请重新输入');
		  $('#iuserNo').val('');
	 }
	}
	</script>
<div class="pageContent">
	<form method="post" action="/money/bindfamily!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							主用户号: 
						</label>
							<input name="iuserNo" id="iuserNo" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							绑定人: 
						</label>
							<input name="bindName" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							关系: 
						</label>
							<my:newselect tagName="relation"  paraType="bindusertype" width="100"  />
					</div>
					 <div class="unit">
						<label>
							身份证: 
						</label>
							<input name="cardNo" class="textInput required" size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							手机号: 
						</label>
							<input name="phone" class="textInput required" size="30" type="text"   />
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
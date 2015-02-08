
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript"> 
$(function(){
	$('#groupSno').blur(function(){
	if(this.value!='')
	   $.ajax({
		  type:'POST', 
		  url:'/money/insuredfile!isExistedGroupCode.do',
		  dataType:'json',
		  data: {'insuredFileId':encodeURIComponent(this.value)},
		  success: afterJudge,
		  error: DWZ.ajaxError
		 }); 
	});
})

function afterJudge(json) {  
	 if((json+"")=='true'){
		  DWZ.ajaxDone('已经存在该集团编号"'+$('#groupSno').val()+'"，请重新输入');
		  $('#groupSno').val('');
	 }
	}
</script>
<div class="pageContent">
	<form method="post" action="/money/businessgroup!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							集团编号:
						</label>
							<input name="groupSno"  id="groupSno" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							集团名称 :
						</label>
							<input name="groupName" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="groupEmail"  class="email required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<input name="groupContact" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系电话:
						</label>
							<input name="groupContactPhone" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<input name="groupContactMobile"  class="digits required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							状态:
						</label>
							<my:newselect tagName="groupStatus"  paraType="shopman_status" width="100"  />
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
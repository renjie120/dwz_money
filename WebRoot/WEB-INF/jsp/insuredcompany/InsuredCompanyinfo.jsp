
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript"> 
$(function(){
	$('#comName').blur(function(){
	if(this.value!='')
	   $.ajax({
		  type:'POST', 
		  url:'/money/insuredfile!isExistedCompanyCode.do',
		  dataType:'json',
		  data: {'insuredFileId':encodeURIComponent(this.value)},
		  success: afterJudge,
		  error: DWZ.ajaxError
		 }); 
	});
})

function afterJudge(json) {  
	 if((json+"")=='true'){
		  DWZ.ajaxDone('已经存在该公司名称"'+$('#comName').val()+'"，请重新输入');
		  $('#comName').val('');
	 }
	}
</script>
<div class="pageContent">
	<form method="post" action="/money/insuredcompany!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							保险公司名称:
						</label>
							<input name="comName" id="comName" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							保险公司编号 :
						</label>
							<input name="comNo" readOnly="true" class="textInput "  type="text"  value="${comNo}" />
					</div>
					 <div class="unit">
						<label>
							状态 :
						</label>
							<my:newselect tagName="comStatus" id="test" paraType="yesorno_status" width="100"  />
					</div>
					 <div class="unit">
						<label>
							简称:
						</label>
							<input name="comShortName" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							电话:
						</label>
							<input name="comPhone"  class="digits "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人名称:
						</label>
							<input name="comContactName" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							联系人手机:
						</label>
							<input name="comContactPhone" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							所属保险公司:
						</label>
							<my:newselect tagName="ownerCompany"  tableName="dict_Insured_company" nameColumn="com_name" idColumn ="id" width="100"  />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="comEmail"  class="email "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea class="" name="comAddress"  rows="4" cols="40"></textarea>
					</div>
					 <div class="unit">
						<label>
							备注:
						</label>
							<textarea class="" name="comRemark"  rows="4" cols="40"></textarea>
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
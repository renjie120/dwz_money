
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript"> 
function refreshUnitUser(txt){
	// 提示返回结果.
	if (txt.responseText)
		alertMsg.info(txt.responseText);
	else 
		alertMsg.info(txt);
	cancelthis();
}

/**
 * 重新打开查询保险公司用户界面
 */
function cancelthis(){
	var url = "/money/loginuser!getUnitUser.do";
	var unitName = $('#userUnit').val();
 	var options = {mask:true};
	$.pdialog.open(url+"?userUnit="+encodeURIComponent(unitName), '', "投保单位用户管理", options); 
	
}

</script>
<div class="pageContent">
	<form method="post" action="/money/loginuser!doAdd.do"  
		class="pageForm required-validate"
		onsubmit="return myCallback(this, refreshUnitUser);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							用户姓名:
						</label>
							<input name="userName" class="textInput required"  type="text"   />
					</div>
					 <div class="unit">
						<label>
							登录名称 :
						</label>
							<input name="userId" class="textInput required"  type="text"   />
							
					</div> 
					<div class="unit">
						<label>
							所属类别 :
						</label>
						<input name="userTypeName" value="${userTypeName}"   type="text"  readOnly="true"  /> 
						<input name="userType" value="${userType}" type="hidden"   /> 
					</div>
					 <div class="unit">
						<label>
							投保单位 :
						</label>
							<input name="userUnit" id="userUnit" class="textInput required" readOnly="true" value="${userUnit}" type="text"   />
					</div>
					 <div class="unit">
						<label>
							用户密码:
						</label>
							<input name="userPass" class="textInput required" id="w_userPass" type="password"  minlength="6" maxlength="20"  />
					</div>
					<div class="unit">
						<label>
							确认密码:
						</label>
							<input name="userPass2" class="textInput " equalto="#w_userPass" type="password"   />
					</div> 
					 <div class="unit">
						<label>
							用户状态 :
						</label>
							<my:newselect tagName="userStatus"  paraType="yesorno_status" width="100"  />
					</div>
					 <div class="unit">
						<label>
							联系电话:
						</label>
							<input name="userPhone" class="textInput "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							邮箱:
						</label>
							<input name="userEmail"  class="email "  type="text"   />
					</div>
					 <div class="unit">
						<label>
							地址:
						</label>
							<textarea class="" name="userAddress"  rows="3" cols="40"></textarea>
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
							<button type="button" onclick="cancelthis()" >
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
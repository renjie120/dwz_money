
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
<!-- 
function setRoles(){  
	  var roleKeyV = $('#roleKey').val(); 
	   var roleTypeV = $('#roleType').val();
		var	 url="/money/role!beforeRoleInUser2.do";
	   var options = {mask:true}; 
	  $.pdialog.open(url+"?roleType="+roleTypeV+"&roleKey="+roleKeyV, '', "角色授权", options);
}
//-->
</script>
<div class="pageContent">
	<form method="post" action="/money/addrole!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label>
					角色类型:
				</label>
				<my:newselect tagName="roleType" paraType="roletype" width="100" />
			</div>
			<div class="unit">
				<label>
					授权对象 :
				</label> 	 
				<my:newselect tagName="roleKey" paraType="aiduyonghu" width="100" /> 
			</div>
			<div class="unit">
				<label>
					角色 :
				</label>
				<input  type="hidden" id="roleIds" name="roleIds" /> 
				<textarea class="" name="roleNames" id="roleNames" size="30" rows="4" cols="40" readOnly="true"></textarea> 
				<div class="buttonActive">
					<div class="buttonContent">
						<button type="button" onclick="setRoles()">
							选择角色
						</button>
					</div>
				</div>
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
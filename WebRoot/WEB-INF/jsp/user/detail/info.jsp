<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<script type="text/javascript">
<!-- 
function test(){  
	 $('#userForm').ajaxSubmit(function(txt){ 
	 	 //提示返回结果.
	 	 alertMsg.info(txt); 
	 	 //关闭当前页面
	 	 $.pdialog.closeCurrent();
	 	 //刷新当前tab页.
	 	 navTab.reload();
	 });
}
//-->
</script>
<div class="pageContent">
	<form method="post" action="/money/myuser!doAdd.do"
		class="pageForm required-validate" id='userForm'
		onsubmit="return validateCallback(this, navTabAjaxDone);" >
		<div class="pageFormContent" layoutH="56" width='300px'>
			<div class="unit">
				<label>
					用户名：
				</label>
				<input name="userName" type="text" size="30" class="textInput required" />
			</div>
			<div class="unit">
				<label>
					密码：
				</label>
				<input name="pass" id="pass" type="password" size="30"  class="required alphanumeric" minlength="6" 
					maxlength="20" />
			</div>
			<div class="unit">
				<label>
					确认密码：
				</label>
				<input type="password" name="repass" size="30" class="required" equalto="#pass"/> 
			</div>
			<div class="unit">
				<label>
					登陆名：
				</label>
				<input name="loginId" type="text" size="30" class="required"  class="textInput" />
			</div>
			<div class="unit">
				<label>
					组织机构：
				</label>
				<input name="orgId" type="text" size="30" class="textInput" />
			</div>
			<div class="unit">
				<label>
					邮件：
				</label>
				<input name="email" type="text" size="30" class="email" />
			</div>
			<div class="unit">
				<label>
					电话：
				</label>
				<input name="phone" type="text" size="30" class="phone" />
			</div>
			<div class="unit">
				<label>
					手机：
				</label>
				<input type="text" name="mobile" size="30" class="number" maxlength="11"/>
			</div>
			<div class="unit">
				<label>
					地址：
				</label>
				<input type="text" name="address" size="30"  />
			</div>
			<div class="unit">
				<label>
					排序id：
				</label>
				<input type="text" name="orderId" size="30" class="number" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button  type="button" onclick="test()">
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

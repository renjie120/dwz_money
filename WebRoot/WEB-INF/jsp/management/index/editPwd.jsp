<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
  <script type="text/javascript">
  <!--
	 DWZ.ajaxError = function(xhr, ajaxOptions, thrownError){
		 alertMsg.correct(xhr.responseText) ;
		 //关闭当前dialog页面.
		 $.pdialog.closeCurrent();
	}

  //-->
  </script>
<div class="pageContent">
<form method="post" action="/money/myuser!changePwd.do" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="pageFormContent" layouth="57">
		<p>
			<label>
				<s:text name="form.pwd"/>
			</label><input type="password" name="oldPassword" class="required"  />
		</p>
		<p>
			<label>
				<s:text name="form.newPwd"/>
			</label><input type="password" name="newPassword" id="newPassword" minlength="6" maxlength="16" class="required" value=""/>
		</p>
		<p>
			<label>
				<s:text name="form.confirmPwd"/>
			</label><input type="password" name="rPassword" class="required" minlength="6" maxlength="16" equalTo="#newPassword" value=""/>
		</p>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close" >关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
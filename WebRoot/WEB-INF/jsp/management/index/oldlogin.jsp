<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><s:text name="ui.title" />
		</title>
		<link href="/styles/management/themes/default/style.css"
			rel="stylesheet" type="text/css" />
		<link href="/styles/management/themes/css/core.css" rel="stylesheet"
			type="text/css" />
		<link href="/styles/management/themes/css/login.css" rel="stylesheet"
			type="text/css" />
		<script src="/styles/management/js/jquery-1.4.4.min.js"
			type="text/javascript"></script> 
		<script type="text/javascript"> 
function toggleBox(boxId){
	var $box = $("#"+boxId);
	if ($box.is(":visible")) $box.slideUp();
	else $box.slideDown();
}

</script>
	</head>

	<body>
		<div id="login">
			<div id="login_header">

				<div class="login_headerContent">
					<div class="navList">
						<ul>
							<li>
								<a href="http://weibo.com/lsq1987" target="_blank">微博</a>
							</li>
						</ul>
					</div>
					<h2 class="login_title">
						请你登录
					</h2>
				</div>
			</div>
			<div id="login_content">
				<div class="loginForm">
					<form method="post" action="/passport!login.do">
						<c:if test="${!empty message}">
							<p style="color: red">
								${message}
							</p>
						</c:if>
						<p>
							<label>
								用户名：
							</label>
							<input type="text" name="app_username" size="20"
								class="login_input" value="test" />
						</p>
						<p>
							<label>
								密码：
							</label>
							<input type="password" name="app_password" size="20"
								class="login_input" value="1" />
						</p>

						<!--<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p>-->

						<div class="login_bar">
							<input class="sub" type="submit" value="" />
						</div>
					</form>
				</div>
				<div class="login_main">
					<!-- 
				<ul class="helpList">
					<li><a href="javascript:toggleBox('forgotPwd')">忘记密码?</a></li>
					<li id="forgotPwd" style="background: none; display: none">
					<form method="post" action="/myaccount/member.do?method=forgetPassword" onsubmit="return validateCallback(this);">
						<p>
							<label>用户名：</label>
							<input type="text" name="userName" class="textInput required" size="20"/>
							<span class="info">请输入你的用户名以获取密码.</span>
						</p>
						<p><input type="submit" value="Submit"/></p>
					</form>
					</li>
				</ul>
 -->
					<div class="login_inner">
						<p>
							测试用户: test
						</p>
						<p>
							密码: 1
						</p>
					</div>
				</div>
			</div>
			<div id="login_footer">
				Copyright &copy; 2012 renjie120.com 沪ICP备12002847
				<script language="javascript" type="text/javascript"
					src="http://js.users.51.la/6717157.js"></script>
				<noscript>
					<a href="http://www.51.la/?6717157" target="_blank"><img
							alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"
							src="http://img.users.51.la/6717157.asp" style="border: none" />
					</a>
				</noscript>
			</div>
		</div>
	</body>
</html>

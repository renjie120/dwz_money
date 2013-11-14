<%@ page pageEncoding="UTF-8"%>
<html>
	<meta charset="utf-8">
	<title>人杰技术网</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="/styles/management/js/jquery-1.4.4.min.js"
		type="text/javascript"></script>
	<style type="text/css">
body {
	margin: 0px;
	background-color: #d6e3f7;
}
</style>
	<script type="text/javascript">  
</script>
	</head>
	<body>
		<div style='height: 80%'>
			<div class="span4">
				<h3>
					demo系统展示
				</h3>
				<p>
				<form method="post" action="/passport!login.do">
					<c:if test="${!empty message}">
						<p style="color: red">
							${message}
						</p>
					</c:if>
					<p>
						<label>
							用户名(默认test)
						</label>
						<input type="text" name="app_username" size="20"
							class="login_input" value="test" />
					</p>
					<p>
						<label>
							密码(默认为1)
						</label>
						<input type="password" name="app_password" size="20"
							class="login_input" value="1" />
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value="登陆" />
					</div>
				</form>
				</p>
				<div>
					我的目标：实现这样一个系统
					<br>
					1.漂亮的样式
					<br>
					2.实用的权限控制
					<br>
					3.动态表单增删改查
					<br>
					4.报表展示
					<br>
					5.简易工作流程
					<br>
					6.开发框架沉淀
					<br>
					7.快捷部署,二次开发
				</div>
			</div>
		</div>

		<hr>
		<footer>
		<jsp:include page="/WEB-INF/jsp/management/index/stockFoot.jsp"></jsp:include>
		<p>
			©2012 人杰技术网版权所有 沪ICP备12002847号
			<script language="javascript" type="text/javascript"
				src="http://js.users.51.la/6717157.js"></script>
		<noscript>
			<a href="http://www.51.la/?6717157" target="_blank"><img
					alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"
					src="http://img.users.51.la/6717157.asp" style="border: none" /> </a>
		</noscript>
		</p>
		</footer>
		</div>
		<script src="/bootstrap/js/jquery.min.js"></script>
		<script src="/bootstrap/js/bootstrap.min.js"></script>
</html>
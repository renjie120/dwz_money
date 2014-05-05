<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<script src="/styles/management/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>
<%
	if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
		response.setHeader("Pragma", "no-cache");
	} else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
		response.setHeader("Cache-Control", "no-cache");
	}
	response.setDateHeader("Expires", 0);
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>演示系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE3 {
	color: #528311;
	font-size: 12px;
}

.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style>
<base href="/">
<script src="/styles/management/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>
<script src="/img/images/login.js" type="text/javascript"></script>
</head>
<body>
	<form action="/passport!login.do" id="form1" method="post">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td bgcolor="#e5f6cf">&nbsp;</td>
			</tr>
			<tr>
				<td height="608"
					background="/img/images/login_03.gif"><table
						width="862" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="266"
								background="/img/images/login_0503.png">&nbsp;</td>
						</tr>
						<tr>
							<td height="95"><table width="100%" border="0"
									cellspacing="0" cellpadding="0">
									<tr>
										<td width="424" height="95"
											background="/img/images/login_06.gif">&nbsp;</td>
										<td width="183"
											background="/img/images/login_07.gif"><table
												width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="21%" height="30"><div align="center">
															<span class="STYLE3">用户</span>
														</div>
													</td>
													<td width="79%" height="30"><input name="app_username" 
														value="test2" id="username"
														style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
												</tr>
												<tr>
													<td height="30"><div align="center">
															<span class="STYLE3">密码</span>
														</div>
													</td>
													<td height="30"><input name="app_password" value="1"
														type="password" id="password"
														style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
													</td>
												</tr>
												<tr>
													<td height="30">&nbsp;</td>
													<td height="30"><img
														src="/img/images/dl.gif" width="81"
														height="22" border="0" usemap="#Map">
													</td>
												</tr>
											</table>
										</td>
										<td width="255"
											background="/img/images/login_08.gif">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="247" valign="top"
								background="/img/images/login_09.gif"><table
									width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="22%" height="30">&nbsp;</td>
										<td width="56%">&nbsp;</td>
										<td width="22%">&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td height="30"><table width="100%" border="0"
												cellspacing="0" cellpadding="0">
												<tr>
													<td width="44%" height="20">&nbsp;</td>
													<td width="56%" class="STYLE4">版本 2011.08.27</td>
												</tr>
											</table>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#a2d962">&nbsp;</td>
			</tr>
		</table>
	</form>
	<map name="Map">
		<area shape="rect" coords="3,3,36,19"   onclick="login()">
		<area shape="rect" coords="40,3,78,18"   onclick="reset()">
	</map>
</body>
<script>
	
</script>
</html>

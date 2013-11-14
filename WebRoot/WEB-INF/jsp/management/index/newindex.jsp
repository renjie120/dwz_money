<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><s:text name="ui.title" /></title>
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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<title><s:text name="ui.title" /></title>
	<%
		String path = request.getContextPath();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%> 
	<script type="text/javascript"> 
		var appPath = "<%=path%>"; 
		</script>
	<script type="text/javascript">
            $(function(){  
                    $("#reset").click(
                        function()
                        {
                            $("#accordion").effect("shake", 219);
                        }
                        );
                    $("#login").click(
                        function(){
                        $("#loginconfirm").dialog({
                            position:[200, 200],
                            height: 480,
                            width: 640,
                            resizable: false,
                            height:140,
                            modal: true,
                            buttons: {
                                "确定": function() {
                                $( this ).dialog( "close" );
                                },
                                "取消": function() {
                                $( this ).dialog( "close" );
                                }
                            }

                        });
                        }
                        );

                    });
                </script>
	<style type="text/css">
/*demo page css*/
body {
	font: 68.5% "Trebuchet MS";
	margin: 0px;
	background-color: #A5E3AA
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

input {
	display: block;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 10px;
}
</style>
	<body>
		<div id="login">
			<div class="demo">
				<div id="accordion"
					style="width: 480px; margin-left: 200px; margin-top: 40px;">
					<div>
						<form>
							<fieldset>
								<label for="name">
									用户名
								</label>
								<input type="text" name="name" id="name"
									class="text ui-widget-content ui-corner-all" />
								<label for="password">
									密码
								</label>
								<input type="password" name="password" id="password" value=""
									class="text ui-widget-content ui-corner-all" />
								</fieldse>
								<div id="login">
									登录
								</div>
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
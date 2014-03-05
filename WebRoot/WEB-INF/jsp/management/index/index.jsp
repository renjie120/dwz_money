<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="dwz.framework.constants.Constants"%>
<%@ page import="common.tree.Tree"%>
<html>
<head> 
<!-- s:text name="ui.title" /-->
<title>流程系统新平台
</title>
<%
	String path = request.getContextPath();
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	Object obj = session.getAttribute(Constants.AUTHENTICATION_KEY);
	System.out.println("登陆信息为：" + obj);
	if (obj == null)
		response.sendRedirect("/management/index!login.do");
%>
<script type="text/javascript"> 
		var appPath = "<%=path%>
	";
</script>

<link href="/styles/management/themes/default/style.css"
	rel="stylesheet" type="text/css" />
<link href="/styles/management/themes/css/core.css" rel="stylesheet"
	type="text/css" />
<link href="/styles/uploadify/css/uploadify.css" rel="stylesheet"
	type="text/css" />
<link href="/gridTree/GridTree.css" rel="stylesheet" type="text/css" />
<script src="/styles/management/js/speedup.js" type="text/javascript"></script>
<script src="/styles/management/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>
<script src="/styles/management/js/jquery.cookie.js"
	type="text/javascript"></script>
<script src="/styles/management/js/jquery.validate.js"
	type="text/javascript"></script>
<script src="/styles/management/js/jquery.bgiframe.js"
	type="text/javascript"></script>
<script src="/styles/xheditor/xheditor-1.1.9-zh-cn.min.js"
	type="text/javascript"></script>
<script src="/styles/uploadify/scripts/swfobject.js"
	type="text/javascript"></script>
<script src="/styles/uploadify/scripts/jquery.uploadify.v2.1.0.js"
	type="text/javascript"></script>

<link rel="stylesheet" type="text/css"
	href="/styles/flexigrid_my/css/flexigrid_blue.css" />
<script type="text/javascript" src="/styles/flexigrid_my/flexigrid.js"></script>


<!-- 下面是jquery的form插件. -->
<script src="/styles/jquery.form.js" type="text/javascript"></script>

<!--  下面引入表格树 -->
<script src="/gridTree/hashMap.js" type="text/javascript"></script>
<script src="/gridTree/GridTree.All.js" type="text/javascript"></script>

<script src="/styles/management/js/src/dwz.core.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.util.date.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.validate.method.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.regional.zh.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.barDrag.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.drag.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.tree.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.accordion.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.ui.js" type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.theme.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.switchEnv.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.alertMsg.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.contextmenu.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.navTab.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.tab.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.resize.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.dialog.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.dialogDrag.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.cssTable.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.stable.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.taskBar.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.ajax.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.pagination.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.database.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.datepicker.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.effects.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.panel.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.checkbox.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.history.js"
	type="text/javascript"></script>
<script src="/styles/management/js/src/dwz.combox.js"
	type="text/javascript"></script>
<!-- 
<script src="/styles/management/js/dwz.min.js" type="text/javascript"></script>
<script src="/styles/management/js/dwz.regional.zh.js" type="text/javascript"></script> -->



<!-- 下面引入ZTree -->
<link rel="stylesheet" href="/ztree2/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
	<script type="text/javascript"
		src="/ztree2/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="/ztree2/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript"> 
		$(function() {
			DWZ.init("/styles/management/dwz.frag.xml", {
				loginUrl : "/management/index!login.do",
				loginTitle : "Login", // 弹出登录对话框 
				debug : false, // 调试模式 【true|false】
				callback : function() {
					initEnv();
					$("#themeList").theme({
						themeBase : "/styles/management/themes"
					});
				}
			});
		});
		//清理浏览器内存,只对IE起效,FF不需要
		if ($.browser.msie) {
			window.setInterval("CollectGarbage();", 10000);
		}
		
		function logout(){
		  $.ajax({
			  type:'POST', url:'/passport!logout.do',  
			  success: function(){ 
			  	location.href="/management/index!login.do";
			  },
			  error: DWZ.ajaxError
		 });
 		}
	</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="/management/index!index.do">coLogo</a> <a
					target="_blank"
					href="http://wpa.qq.com/msgrd?v=3&uin=1246910068&site=qq&menu=yes">
					<img border="0"
					src="http://wpa.qq.com/pa?p=2:1246910068:41 &r=0.901225046595993"
					alt="点击这里给我发消息" title="点击这里给我发消息">
				</a>
				<ul class="nav">
					<li><a href="/money/myuser!myContact.do" target="dialog"
						mask="true">我的资料</a></li>
					<li><a href="/management/index!editPwd.do" target="dialog"
						mask="true">修改密码</a></li>
					<li><a href="#" onclick="logout()">退出登录</a></li>
				</ul>
			</div>
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>系统演示</h2>
					<div>收缩</div>
				</div>
				<div class="accordion" fillSpace="sideBar">${request.allMenu}
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:void(0)"><span><span
										class="home_icon">My Home</span> </span> </a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:void(0)">首页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent">
					<div>
						<div class="accountInfo">
							<div class="right">
								<p>
									<fmt:formatDate value="${model.now}"
										pattern="dd MMMM yyyy, EEEE" />
								</p>
							</div>
							<p>
								<span>欢迎使用本系统 ${contextUser.userName}</span>
							</p>
						</div>

						<div class="pageFormContent" layoutH="-80">
							<h1>收藏文件</h1>
							<s:iterator value="#request.allHomepage" var="haha" status="stu">
								<div class="unit">
									<a href="<s:property value="url" />" target="navTab"
										rel="relId<s:property value="urlId" />"><s:property
											value="urlDesc" /> </a>
								</div>
							</s:iterator>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="footer">
		www.thinksafari.com
		<!--联系方式: <font style="color: red">(qq)1246910068</font>&nbsp;&nbsp;&nbsp;&nbsp;(email)lishuiqing110@163.com&nbsp;&nbsp;&nbsp;&nbsp;
		人杰工作室(LSQ,SGX)-->
	</div>
</body>
</html>
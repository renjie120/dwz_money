<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
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

		<link href="/styles/management/themes/default/style.css"
			rel="stylesheet" type="text/css" />
		<link href="/styles/management/themes/css/core.css" rel="stylesheet"
			type="text/css" />
		<link href="/styles/uploadify/css/uploadify.css" rel="stylesheet"
			type="text/css" />
		<link href="/styles/gridtree/demo/gridtree.css" rel="stylesheet"
			type="text/css" />
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
		<script src="/styles/gridtree/demo/hashMap.js" type="text/javascript"></script>
		<script src="/styles/gridtree/demo/GridTree.d.js"
			type="text/javascript"></script> 

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
		<script src="/styles/management/js/src/dwz.ui.js"
			type="text/javascript"></script>
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
		<link rel="stylesheet" href="/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css">
		<script type="text/javascript"
			src="/ztree/js/jquery.ztree.core-3.0.js"></script>

		<script type="text/javascript"> 
$(function(){	
	DWZ.init("/styles/management/dwz.frag.xml", {
		loginUrl:"/management/index!login.do", loginTitle:"Login",	// 弹出登录对话框 
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/styles/management/themes"}); 
		}
	});
});
//清理浏览器内存,只对IE起效,FF不需要
if ($.browser.msie) {
	window.setInterval("CollectGarbage();", 10000);
}
</script>
	</head> 
	<body scroll="no">
		<div id="layout">
			<div id="header">
				<div class="headerNav">
					<a class="logo" href="/management/index!index.do">coLogo</a>
					<a target="_blank"
						href="http://wpa.qq.com/msgrd?v=3&uin=1246910068&site=qq&menu=yes">
						<img border="0"
							src="http://wpa.qq.com/pa?p=2:1246910068:41 &r=0.901225046595993"
							alt="点击这里给我发消息" title="点击这里给我发消息">
					</a>
					<ul class="nav">
						<li>
							<a href="/management/user!editContext.do" target="dialog"
								mask="true">我的资料</a>
						</li>
						<li>
							<a href="/management/index!editPwd.do" target="dialog"
								mask="true">修改密码</a>
						</li>
						<li>
							<a href="/passport!logout.do">退出登录</a>
						</li>
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
						<h2>
							系统演示
						</h2>
						<div>
							收缩
						</div>
					</div>
					<div class="accordion" fillSpace="sideBar">
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>业务系统
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder expand">
								<li>
									<a href="#">GridTree2.0展示</a>
									<ul>
										<li> 
											<a href="/gridtree/demo!demo.do" target="navTab"
												rel="flexiGridNav">DWZ表格树</a>
												<ul>
												<li>
												<a href='123'>aaaa</a>
												</li>
												<li>
												<a href='123'>aaaa</a>
												</li>
												<li>
												<a href='123'>aaaa</a>
												</li>
												</ul>
										</li>
										<li>
											<a href="/commonGridTreeIndex.jsp" target="a"
												rel="flexiGridNav2">普通表格树(推荐)</a>
										</li> 
										<li>
											<a href="/gridTree/myApi.html" target="b"
												rel="flexiGridNav2">配置说明</a>
										</li> 
										<li>
											<a href="/gridTree/myMethod.html" target="c"
												rel="flexiGridNav2">API文档</a>
										</li> 
									</ul>
								</li>
								<li>
									<a href="#">基本信息列表</a>
									<ul>
										<li>
											<a href="/money/newmoney!query.do" target="navTab"
												rel="moneylist">收支列表</a>
										</li>
										<li>
											<a href="/money/question!query.do" target="navTab"
												rel="questionlist">问题列表</a>
										</li>
										<li>
											<a href="/money/plan!query.do" target="navTab"
												rel="questionlist">计划与执行</a>
										</li>
									</ul>
								</li>
								<li>
									<a href="#">报表展示</a>
									<ul >
										<li>
											<a href="/money/newmoney!report.do" target="navTab"
												rel="moneyReport" external="true">收支相关报表</a>
										</li>
										<li>
											<a href="/money/question!report.do" target="navTab"
												rel="paramstypelist" external="true">问题相关报表</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>系统维护
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder"> 
								<li>
									<a href="/money/paramtype!query.do" target="navTab"
										rel="paramstypelist2">新参数类型列表</a>
								</li> 
								<li>
									<a href="/money/param!query.do" target="navTab"
										rel="paramslist2">新参数列表</a>
								</li>
								<li>
									<a href="/money/menu!query.do" target="navTab" rel="menulist">菜单列表</a>
								</li>
								<li>
									<a href="/money/org!query.do" target="navTab" rel="orglist">组织机构列表</a>
								</li>
								<li>
									<a href="/money/diary!query.do" target="navTab" rel="orglist">日志列表</a>
								</li>
								<li>
									<a href="/money/cache!query.do" target="navTab"
										rel="paramstypelist">缓存列表</a>
								</li>
								<li>
									<a href="/money/moneyType!query.do" target="navTab"
										rel="userlist">金额类型列表</a>
								</li>
								<li>
									<a href="/money/myuser!query.do" target="navTab" rel="userlist">用户列表</a>
								</li>
							</ul>
						</div>
						<%
						if (isAdmin) {
						%>
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>演示系统
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li>
									<a href="http://docs.fusioncharts.com/free/" target="new">fusionChartFree文档</a>
								</li> 
								<li>
									<a href="/money/superconsole!init.do" target="navTab"
										rel="superconsole">超级控制台</a>
								</li> 
								<li>
									<a href="/demoDwz/dwz-ria/index.html" target="navTab32"
										rel="flexiGridNav3">DWZ演示</a>
								</li>
								<li>
									<a href="http://j-ui.com" target="navTab31"
										rel="flexiGridNav3">DWZ官网</a>
								</li>
								<li>
									<a href="http://58asp.com/" target="new">控制台</a>
								</li>
								<li>
									<a href="/source/DwzTreeUtil!seeJava.do" target="navTab"
										rel="flexiGridNav">java代码</a>
								</li>
								<li>
									<a href="/source/editPwd!seeJsp.do" target="navTab"
										rel="flexiGridNav">jsp代码</a>
								</li>
								<li>
									<a href="/typechangedemo/saveuser!initUser.do" target="navTab"
										rel="flexiGridNav">类型转换</a>
								</li>
								<li>
									<a href="/typechangedemo/saveuser2!initUser.do" target="navTab"
										rel="flexiGridNav">类型转换2</a>
								</li>
								<li>
									<a href="/upload/test!init.do" target="navTab"
										rel="flexiGridNav">文件上传</a>
								</li>
								<li>
									<a href="/upload/test2!init.do" target="navTab"
										rel="flexiGridNav">文件上传2</a>
								</li>
								<li>
									<a href="/upload/test2!testOgnl.do" target="navTab"
										rel="flexiGridNav">测试OGNL表达式</a>
								</li>
							</ul>
						</div>
						<%
						}
						%>
					</div>

				</div>
			</div>
			<div id="container">
				<div id="navTab" class="tabsPage">
					<div class="tabsPageHeader">
						<div class="tabsPageHeaderContent">
							<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
							<ul class="navTab-tab">
								<li tabid="main" class="main">
									<a href="javascript:void(0)"><span><span
											class="home_icon">My Home</span> </span> </a>
								</li>
							</ul>
						</div>
						<div class="tabsLeft">
							left
						</div>
						<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
						<div class="tabsRight">
							right
						</div>
						<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
						<div class="tabsMore">
							more
						</div>
					</div>
					<ul class="tabsMoreList">
						<li>
							<a href="javascript:void(0)">首页</a>
						</li>
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
								<p>
									<label>
										用户名:
									</label>
									<span class="unit">${contextUser.userName}</span>
								</p>
								<p>
									<label>
										电话:
									</label>
									<span class="unit">${contextUser.phone}</span>
								</p>
								<p>
									<label>
										邮件:
									</label>
									<span class="unit">${contextUser.email}</span>
								</p>
								<div class="divider"></div>
								<h1>
									网站地图
								</h1>
								<div class="unit">
                	<a href="/money/question!query.do" target="navTab"
												rel="questionlist">常规增删改查页面</a> 
								</div>
							<div class="unit">
									<a href="/money/newmoney!report.do" target="navTab"
												rel="moneyReport" external="true">报表展示功能</a> 
								</div><div class="unit">
									<a href="/money/myuser!query.do" target="navTab" rel="userlist">用户管理</a> 
								</div><div class="unit">
									<a href="/money/menu!query.do" target="navTab" rel="menulist">权限菜单控制</a> 
								</div><div class="unit">
									<a href="/money/newmoney!technology.do"  target="dialog" >使用技术</a> 
								</div><div class="unit">
									<a href="/money/newmoney!about.do"  target="dialog" >系统目标</a> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

		<div id="footer">
			联系方式:
			<font style="color: red">(qq)1246910068</font>&nbsp;&nbsp;&nbsp;&nbsp;(email)lishuiqing110@163.com&nbsp;&nbsp;&nbsp;&nbsp;<!--人杰工作室(LSQ,SGX)-->
		</div>
	</body>
</html>
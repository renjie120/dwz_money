<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/include.inc.jsp"%> 
<script type="text/javascript">
  var appPath = "<%=basePath%>"; 
</script>
<div class="pageHeader"  >
	<form onsubmit="return navTabSearch(this);"
		action="/money/myuser!queryGridTree.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						用户流水号
								<input name="useId" class="textInput" size="30" type="text"   />
					</td> 
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									检索
								</button>
							</div>
						</div>
					</li> 
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent" style="overflow:hidden"> 
	 <div id="newtableTree"></div>
</div>
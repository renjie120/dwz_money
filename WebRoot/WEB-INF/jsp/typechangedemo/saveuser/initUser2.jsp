<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>  
<form method="post" action="/typechangedemo/saveuser2!saveUser.do">
	<table width="300px">
		<tr><td>请输入第一个：用户名和密码</td><td><input type="text" name="user"></td></tr>
		<tr><td>请输入第二个：用户名和密码</td><td><input type="text" name="user"></td></tr> 
		<tr><td>请输入生日</td><td><input name="birth"></td></tr>
		<tr><td colspan="2"><input type="submit" value="提交"></td></tr>
	</table>
</form> 
</div>

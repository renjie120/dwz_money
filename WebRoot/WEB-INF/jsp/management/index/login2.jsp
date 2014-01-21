<%@ page import="dwz.framework.constants.Constants"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	request.getSession().removeAttribute("user");
	request.getSession().removeAttribute("menuMap");
	request.getSession().invalidate();
	session.removeAttribute(Constants.AUTHENTICATION_KEY);
	
	
	System.out.println(System.currentTimeMillis()+"-----------");
	//response.sendRedirect("/management/index!login.do");
%>
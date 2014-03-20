<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/sitemesh-decorator.tld" prefix="decorator"%>
<%@ taglib uri="/WEB-INF/tlds/sitemesh-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tlds/my-dwz-taglib.tld" prefix="my"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri='/WEB-INF/tlds/core-server.tld' prefix='core'%>
<%@ taglib uri='/WEB-INF/tlds/core-pager.tld' prefix='pager'%>
<%@ taglib uri='/WEB-INF/tlds/core-elutil.tld' prefix='elutil'%>
<%@ taglib uri='/WEB-INF/tlds/core-info.tld' prefix='info'%>
<%@ page import="java.util.*,org.apache.struts2.views.jsp.TextTag"%>
<%@ page import="com.opensymphony.xwork2.util.*"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="dwz.framework.constants.Constants"%>
<%@ page import="dwz.framework.constants.user.*"%>
<%@ page import="dwz.framework.user.User"%> 
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	User user = (User) session
			.getAttribute(Constants.AUTHENTICATION_KEY);
	boolean isAdmin = false;
	if (user != null) { 
		if (user.getUserType().equals(UserType.ADMIN)||user.getUserType().equals(UserType.SUPER))
			isAdmin = true;
	}
%>
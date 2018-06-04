<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mafia</title>
</head>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="mafiaStyle.css" />
<%@ page import="com.objects.Game" %>
<%@ page import="com.objects.Player" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<% 
String myName = (String)session.getAttribute("myName");
pageContext.setAttribute("myName", myName);
String message = (String)session.getAttribute("message");
pageContext.setAttribute("message", message);
%>
<c:out value="${myName} ${message}"/>
	<br>
	<br>
	<form name="Reveal" action="RevealRoleToPlayerServlet" method="POST">
		<input type="hidden" name="myName" value="${myName}"/>
		<input type="submit" value="What is my Role?" class="btn btn-danger"/>
	</form>
	<br>
	<br>
	<form name="Reveal" action="PlayerGetsListServlet" method="POST">
		<input type="hidden" name="myName" value="${myName}"/>
		<input type="submit" value="Perform Action" class="btn btn-danger"/>
	</form>
	<br>
	<br>
	<form name="Reveal" action="index.jsp" method="POST">
		<input type="submit" value="Logout" class="btn btn-danger"/>
	</form>
</body>
</html>
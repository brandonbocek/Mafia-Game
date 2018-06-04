<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Play Mafia</title>
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
String theRole  = (String)session.getAttribute("theRole");
pageContext.setAttribute("theRole", theRole);
String othersRole  = (String)session.getAttribute("othersRole");
pageContext.setAttribute("othersRole", othersRole);
String myName = (String)session.getAttribute("myName");
pageContext.setAttribute("myName", myName);
String chosenPlayer = (String)session.getAttribute("chosenPlayer");
pageContext.setAttribute("chosenPlayer", chosenPlayer);
%>
<c:out value="${chosenPlayer}"/> is a <c:out value="${othersRole}"/>
<form name="Reveal" action="playerDayView.jsp" method="POST">
		<input type="hidden" name="myName" value="${myName}"/>
		<input type="submit" value="Okay" class="btn btn-danger"/>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Menu</title>
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
Game theGame  = (Game)session.getAttribute("theGame");
pageContext.setAttribute("theGame", theGame);
%>
<c:out value="${theGame.getWinner()}"/><br><br>
<div class="container" style="margin-left: 20%">
	  <c:forEach var="i" begin="0" end="${theGame.getNumOfPlayersPlaying()-1}">
	   	<c:choose>
			<c:when test="${theGame.getPlayerLife(i)}">
			<c:out value="${theGame.getPlayerName(i)} is alive"/><br><br>
			</c:when>
		</c:choose>
	  </c:forEach>
</div>
<br>
<div class="container" style="margin-left: 20%">
	  <c:forEach var="i" begin="0" end="${theGame.getNumOfPlayersPlaying()-1}">
	   	<c:choose>
			<c:when test="${!theGame.getPlayerLife(i)}">
			<c:out value="${theGame.getPlayerName(i)} is dead"/><br><br>
			</c:when>
		</c:choose>
	  </c:forEach>
</div>
<div id="mainAdminButtons">
	<div id="signIn">
		<form name="Reveal" action="GetWhoDiedOvernightServlet" method="POST">
			<input type="hidden" name="theGame" value="${theGame}"/>
			<input type="submit" value="Show Night Results" class="btn btn-danger"/>
		</form>
	</div>
	<br>
	<br>
	<div id="signUp">
		<form name="Reveal" action="AllowActionsServlet" method="POST">
			
			<input type="submit" value="Allow Actions" class="btn btn-danger"/>
		</form>
	</div>
	<br>
	<br>
	<div id="signUp">
		<form name="Reveal" action="TownKillsServlet" method="POST">
			<input type="hidden" name="theGame" value="${theGame}"/>
			<input type="submit" value="Kill Someone" class="btn btn-danger"/>
		</form>
	</div>
</div>
</body>
</html>
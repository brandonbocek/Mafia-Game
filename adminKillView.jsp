<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Town Kills</title>
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

<form name="Reveal" action="AdminKillsServlet" method="POST">
<c:forEach var="i" begin="0" end="${theGame.getNumOfPlayersPlaying()-1}">
	<c:choose>
		<c:when test="${theGame.getPlayerLife(i)}">
			<label for="playerAdminWants${i}"><c:out value="${theGame.getPlayerName(i)}"/></label>
			<input type="radio" id="playerAdminWants${i}" name="playerAdminWants" value="${theGame.getPlayerName(i)}"><br><br>
		</c:when>
	</c:choose>

</c:forEach>
<input type="hidden" name="theGame" value="${theGame}"/>
<input type="submit" value="Submit" class="btn btn-danger"/>
</form>
</body>
</html>
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
String myName = (String)session.getAttribute("myName");
pageContext.setAttribute("myName", myName);
String myRole  = (String)session.getAttribute("myRole");
pageContext.setAttribute("myRole", myRole);
ArrayList<Player> others = (ArrayList<Player>)session.getAttribute("others");
pageContext.setAttribute("others", others);
%>
<c:choose>
	<c:when test="${theRole.equals(\"Mafia\")}">
		<c:out value="${myName}"/>, you are a <c:out value="${myRole}"/><br> so choose a player to kill<br><br>
	</c:when>
	<c:when test="${theRole.equals(\"Cop\")}">
		<c:out value="${myName}"/>, you are a <c:out value="${myRole}"/><br> so choose a player to investigate<br><br>
	</c:when>
	<c:when test="${theRole.equals(\"Nurse\")}">
		<c:out value="${myName}"/>, you are a <c:out value="${myRole}"/><br> so choose a player to save from death<br><br>
	</c:when>
	<c:when test="${theRole.equals(\"Lawyer\")}">
		<c:out value="${myName}"/>, you are a <c:out value="${myRole}"/><br> so choose a player to investigate<br><br>
	</c:when>
	<c:when test="${theRole.equals(\"Undercover Cop\")}">
		<c:out value="${myName}"/>, you are an undercover cop so wake up with the mafia but your vote is meaningless<br><br>
	</c:when>
	<c:when test="${theRole.equals(\"Civilian\")}">
		<c:out value="${myName}"/>, you are a <c:out value="${myRole}"/><br> so you don't have any actions to take<br><br>
	</c:when>
	<c:when test="${theRole.equals(\"Grandma with a knife\")}">
		<c:out value="${myName}"/>, you are a <c:out value="${myRole}"/><br> 
		so you don't have any actions to take<br> 
		and you won't die at night<br><br>
	</c:when>
</c:choose>
<form name="Reveal" action="NightUpdateServlet" method="POST">
<span id="playersToChooseFrom">
	<c:forEach var="i" begin="0" end="${others.size()-1}">
	   
		<label for="chosenPlayer${i}">	
			<c:out value="-> ${others.get(i).getFirstName()}"/>
		</label>
		<input type="radio" id="chosenPlayer${i}" name="chosenPlayer" value="${others.get(i).getFirstName()}"><br><br>
	<br>
	</c:forEach>
</span>
<input type="hidden" name="myName" value="${myName}"/>
<input type="hidden" name="myRole" value="${myRole}"/>
<input type="submit" value="Submit" class="btn btn-danger"/>
</form>

</body>
</html>
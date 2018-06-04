<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

<!-- show the word for now -->
<div id="nameTitle">

</div>
<br>
<div id="signIn">
	<b>Sign Up</b>
	<form name="Reveal" action="SignInServlet" method="POST">
		<input name="myName" type="text"/>
		<input type="submit" value="Submit" class="btn btn-danger"/>
	</form>
</div>
<div id="signUp">
	
	<b>Sign in</b>
	<form name="Reveal" action="SignInServlet" method="POST">
		<input name="myName" type="text"/>
		<input type="submit" value="Submit" class="btn btn-danger"/>
	</form>
	
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="welcome">Fill Out Options for this Mafia Game</div>
<br>


<form action="AssignRolesServlet" method="post">

How many mafia?

<select name="numOfMafia">
	<option value="1" selected>1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
</select>
	<br>
	<br>
	Is a Cop Playing?
	<span id="radioPoints">
		<input type="radio" id="yesCop" name="copIsPlay" value="yes">
		<label for="yesCop">Yes</label>
			 
		<input type="radio" id="noCop" name="copIsPlay" checked="checked" value="no">
		<label for="noCop">No </label>
	</span>
	<br>
	<br>
	Is a Nurse Playing?
	<span id="radioPoints">
		<input type="radio" id="yesNurse" name="nurseIsPlay" value="yes">
		<label for="yesNurse">Yes</label>
			 
		<input type="radio" id="noNurse" name="nurseIsPlay" checked="checked" value="no">
		<label for="noNurse">No </label>
	</span>
	<br>
	<br>
	Is a Lawyer Playing?
	<span id="radioPoints">
		<input type="radio" id="yesLawyer" name="lawyerIsPlay" value="yes">
		<label for="yesLawyer">Yes</label>
			 
		<input type="radio" id="noLawyer" name="lawyerIsPlay" checked="checked" value="no">
		<label for="noLawyer">No </label>
	</span>
	<br>
	<br>
	Is a Grandma with a Knife Playing?
	<span id="radioPoints">
		<input type="radio" id="yesGma" name="gmaIsPlay" value="yes">
		<label for="yesGma">Yes</label>
			 
		<input type="radio" id="noGma" name="gmaIsPlay" checked="checked" value="no">
		<label for="noGma">No </label>
	</span>
	<br>
	<br>
	Is an Undercover Cop Playing?
	<span id="radioPoints">
		<input type="radio" id="yesUnderCop" name="underCopIsPlay" value="yes">
		<label for="yesUnderCop">Yes</label>
			 
		<input type="radio" id="noUnderCop" name="underCopIsPlay" checked="checked" value="no">
		<label for="noUnderCop">No </label>
	</span>
	<br>
	<br>
	<div id="start"><input type="submit" value="Submit"></div>
</form>
</body>
</html>
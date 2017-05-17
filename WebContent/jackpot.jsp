<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
    <title>Jackpot - No Place Like Home</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Begin Header -->
<%@include file="header.jspf" %>
<!-- End Header -->

<main>
	<article>
		<h3>Winnaar jackpot</h3>
		<p id="jackpotText">Proficiat! Je wint de jackpot van <%=request.getParameter("casino")%> euro!</p>
	</article>	
</main>

<!-- Begin Footer -->
<%@include file="footer.jspf" %>
<!-- End Footer -->

</body>
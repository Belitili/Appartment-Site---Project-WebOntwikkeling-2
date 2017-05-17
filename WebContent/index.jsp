<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.db.ApartmentDB"%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Home - No Place Like Home</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Begin Header -->
<%@include file="header.jspf" %>
<!-- End Header -->

<main>
	<article>
		<h3>Welkom</h3>
		<p>Welkom! Ga mee op zoek naar een stekje in Leuven.</p>
		<% 
				ApartmentDB db = ApartmentDB.getDB();
		%>
		<p id="calculatedValue">De gemiddelde prijs is momenteel <%=db.getAvaragePrice()%> euro.</p>
		<p id="link"><a href="Controller?action=read">Naar het overzicht</a></p>
	</article>	
</main>

<!-- Begin Footer -->
<div id="absoluteFooter">
	<%@include file="footer.jspf" %>
</div>
<!-- End Footer -->

</body>
</html>
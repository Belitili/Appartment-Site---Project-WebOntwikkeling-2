<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
    <title>Bevestiging - No Place Like Home</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Begin Header -->
<%@include file="header.jspf" %>
<!-- End Header -->

<main>
	<article>
		<h3>Bevestiging</h3>
		<p>Volgend apartement toegevoegd:</p>
		<p>Huurprijs: <%=request.getParameter("huurprijs") %></p>
		<p>Aantal slaapkamers: <%=request.getParameter("aantalSlaapkamers") %></p>
		<p>Adres: <%=request.getParameter("adres") %></p>
		<p>Casino: <%=request.getParameter("casino")%></p>
		<p><a href="Controller?action=read">Overzicht</a></p>
	</article>	
</main>

<!-- Begin Footer -->
<%@include file="footer.jspf" %>
<!-- End Footer -->

</body>
</html>
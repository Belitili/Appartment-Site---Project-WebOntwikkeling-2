<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.model.Apartment" %>
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
    <title>Delete bevestiging - No Place Like Home</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Begin Header -->
<%@include file="header.jspf" %>
<!-- End Header -->

<main>
	<article>
		<% Apartment ap = (Apartment) request.getAttribute("ap"); %>
		<h3>Delete Bevestiging</h3>
		<p>Volgend apartement verwijderen?</p>
		<p>Huurprijs: <%= ap.getPrice() %></p>
		<p>Aantal slaapkamers: <%= ap.getRooms() %></p>
		<p>Adres: <%= ap.getAddress() %></p>
		<form method="POST" action="Controller?action=deleteConfirm&id=<%=request.getParameter("id")%>">
			<input type="submit" id="delete" value="Verwijder" />
		</form>
	</article>	
</main>

<!-- Begin Footer -->
<%@include file="footer.jspf" %>
<!-- End Footer -->

</body>
</html>
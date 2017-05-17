<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.model.Apartment"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
	<title>Overzicht - No Place Like Home</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Begin Header -->
<%@include file="header.jspf" %>
<!-- End Header -->

<main>
	<article>
		<h3>Overzicht</h3>
		<table>
			<tr>
				<th>Huurprijs</th>
				<th>Slaapkamers</th>
				<th>Adres</th>
				<th>Link</th>
			</tr>
			<% 
				ArrayList<Apartment> apartments = (ArrayList<Apartment>) request.getAttribute("apartments");
				String totCasino = (String) request.getAttribute("totCasino");
				for (Apartment ap: apartments) {
			%>
			<tr>
				<td><%=ap.getPrice()%></td>
				<td><%=ap.getRooms()%></td>
				<td><%=ap.getAddress()%></td>
				<td><a href="<%=ap.getLink()%>">link</a></td>
				<td><a href="Controller?action=update&id=<%=ap.getId()%>">Update</a></td>
				<td><a href="Controller?action=delete&id=<%=ap.getId()%>">Delete</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<p id="totCasino">De totale waarde van alle casinobedragen kleiner dan 1000 bedraagt <%=totCasino%> euro</p>
		<p id="link"><a href="form.jsp">Toevoegen</a></p>
	</article>
	<p id="linkHome"><a href="index.jsp">Home</a></p>
</main>

<!-- Begin Footer -->
<%@include file="footer.jspf" %>
<!-- End Footer -->

</body>
</html>
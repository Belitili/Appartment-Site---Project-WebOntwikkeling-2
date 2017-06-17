<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.db.ApartmentDB" %>
<%@ page import="domain.model.Apartment" %>
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Zoeken - No Place Like Home</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Begin Header -->
<%@include file="header.jspf" %>
<!-- End Header -->

<main>
	<article>
		<h3>Zoeken</h3>
		<form method="POST" action="Controller?action=search">
			<label for="linkAppt">Link</label>
			<input type="url" id="linkAppt" name="linkAppt" required/>
			<input type="submit" value="Zoeken"/>
		</form>
		<% 	if(request.getAttribute("foundApp")!=null) { 
			Apartment ap = (Apartment) request.getAttribute("foundApp"); %>
			<div id=result>
				<p>Volgend apartement gevonden:</p>
				<p>Huurprijs: <%=ap.getPrice()%></p>
				<p>Aantal slaapkamers: <%=ap.getRooms() %></p>
				<p>Adres: <%= ap.getAddress() %></p>
				<p>Casino: <%= ap.getCasino() %></p>
			</div>
		<%	} 
			if(request.getAttribute("error")!=null) {%>
			<div>
				<p><%= request.getAttribute("error") %></p>
			</div>
		<%	} %>
	</article>	
	
	
</main>

<!-- Begin Footer -->
<div id="absoluteFooter">
	<%@include file="footer.jspf" %>
</div>
<!-- End Footer -->

</body>
</html>
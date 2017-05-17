<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.model.Apartment"%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Update - No Place Like Home</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Begin Header -->
<%@include file="header.jspf" %>
<!-- End Header -->

<main>
	<article>
		<h3>Toevoegen</h3>
		<%@include file="errorMessages.jspf" %>
		<%Apartment ap = (Apartment) request.getAttribute("apartmentToUpdate");%>
		<form name="addApartmentForm" method="POST" action="Controller?action=updateValuesApartment">
			<label for="huurprijs">Huurprijs*</label>
			<input type="number" id="huurprijs" name="huurprijs" value="<%=ap.getPrice()%>" required/>
			<label for="aantalSlaapkamers">Aantal slaapkamers*</label>
			<input type="number" id="aantalSlaapkamers" name="aantalSlaapkamers" min="1" value="<%=ap.getRooms()%>" required/>
			<label for="adres">Adres*</label>
			<input type="text" id="adres" name="adres" value="<%=ap.getAddress()%>" required/>
			<label for="linkAppt">Link*</label>
			<input type="url" id="linkAppt" name="linkAppt" value="<%=ap.getLink()%>" readonly/>
			<label for="casino">Casino</label>
			<input type="number" id="casino" name="casino" value="<%=ap.getCasino()%>">
			<input type="submit" value="Update"/>
		</form>
		<p id="reference">*Verplichte velden</p>
	</article>
	<p id="linkHome"><a href="index.jsp">Home</a></p>
</main>

<!-- Begin Footer -->
<%@include file="footer.jspf" %>
<!-- End Footer -->

</body>
</html>
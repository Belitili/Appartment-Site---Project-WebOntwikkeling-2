<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Toevoegen - No Place Like Home</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- Begin Header -->
<%@include file="header.jspf" %>
<!-- End Header -->

<main>
	<article>
		<%@include file="errorMessages.jspf" %>
		<h3>Toevoegen</h3>
		<form name="addApartmentForm" method="POST" action="Controller?action=create">
			<label for="huurprijs">Huurprijs*</label>
			<input type="number" id="huurprijs" name="huurprijs" required/>
			<label for="aantalSlaapkamers">Aantal slaapkamers*</label>
			<input type="number" id="aantalSlaapkamers" name="aantalSlaapkamers" min="1" value="2" required/>
			<label for="adres">Adres*</label>
			<input type="text" id="adres" name="adres" required/>
			<label for="linkAppt">Link*</label>
			<input type="url" id="linkAppt" name="linkAppt" required/>
			<label for="casino">Casino</label>
			<input type="number" id="casino" name="casino">
			<input type="submit" value="Toevoegen"/>
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
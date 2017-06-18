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
		<h3>Update</h3>
		<%@include file="errorMessages.jspf" %>
		<%Apartment ap = (Apartment) request.getAttribute("apartmentToUpdate");%>
		<form name="addApartmentForm" method="POST" action="Controller?action=updateValuesApartment" novalidate>
			<%@include file="aptForm.jspf" %>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.model.Apartment" %>
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
		<form name="addApartmentForm" method="POST" action="Controller?action=create" novalidate>
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
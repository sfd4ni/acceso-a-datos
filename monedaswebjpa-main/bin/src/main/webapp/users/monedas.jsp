<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Monedas</title>
</head>
<body>
	<div>
	<h1>Monedas</h1>
	<a href = "../logout">Logout</a>
	<ol>
		<c:forEach items="${listaMonedas}" var="moneda">
    		<li><a href="../gestormonedas?id=${moneda.getIdmoneda()}">${moneda.getNombre()}</a></li>
		</c:forEach>
	</ol>
	
		
	</div>
</body>
</html>
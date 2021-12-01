<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order</title>
</head>
<body>
	<h1>Crear nuevo pedido.</h1>
	<span>Lista productos:</span>
	<c:forEach items="${carritoList}" var="product">
			<span>${product}</span><br/>
	</c:forEach>
	<br/>
	<c:forEach items="${productsList}" var="product">
		<form method="POST" action="gestororders">
			<input type="hidden" name="productStr" value="${product.getProductName()}" />
			<span>${product.getProductName()}</span>
			<input type="submit" name="add" value="Add"/>
		</form>
		<br/>
	</c:forEach>
</body>
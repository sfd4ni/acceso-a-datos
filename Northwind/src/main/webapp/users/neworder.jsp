<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/order.css">
<title>Order</title>
</head>
<body>
	<h1>Crear nuevo pedido.</h1>
	<span>Lista productos:</span>
	<table>
	<tr>
		<th>Product</th>
		<th>Unit Price</th>
		<th>Quantity</th>
	</tr>
	<c:set var="priceTotal" value="${0}" />
	<c:forEach items="${carritoList}" var="orderDetail">
		<tr>
			<td>${orderDetail.getProduct().getProductName()}</td>
			<td>${orderDetail.getUnitPrice()}</td>
			<td>${orderDetail.getQuantity()}</td>
			
		</tr>
		<c:set var="priceTotal" value="${priceTotal + orderDetail.getUnitPrice() * orderDetail.getQuantity()}" />
	</c:forEach>
	</table>
	<span>Total price: ${priceTotal}</span>
		<form method="POST" action="gestororders">
			<input type="submit" name="back" value="Back"/>
			<input type="submit" name="save" value="Save"/>
		</form>
	<c:forEach items="${productsList}" var="product">
		<form method="POST" action="gestororders">
			<input type="hidden" name="productId" value="${product.getProductID()}" />
			<input type="hidden" name="productStr" value="${product.getProductName()}" />		
			<span>Producto: ${product.getProductName()} </span>
			<span>Precio: ${product.getUnitPrice()}$ </span>
			<span>Unidades en stock: ${product.getUnitsInStock()} </span>
			<input name="productQuantity" placeholder="1"/>
			<input type="submit" name="add" value="Add"/>
		</form>
		<br/>
	</c:forEach>
</body>
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
	<table>
	<tr>
		<th>Product</th>
		<th>Unit Price</th>
		<th>Quantity</th>
	</tr>
	<c:forEach items="${carritoList}" var="orderDetail">
		<tr>
			<td><span>${orderDetail.getProduct().getProductName()}</span><td/>
			<td><span>${orderDetail.getUnitPrice()}</span><td/>
			<td><span>${orderDetail.getQuantity()}</span><td/>
		</tr>
	</c:forEach>
	
	</table>
		<form method="POST" action="gestororders">
			<input type="submit" name="save" value="Save"/>
		</form>
	<c:forEach items="${productsList}" var="product">
		<form method="POST" action="gestororders">
			<input type="hidden" name="productId" value="${product.getProductID()}" />
			<input type="hidden" name="productStr" value="${product.getProductName()}" />		
			<span>Producto: ${product.getProductName()} </span>
			<span>Precio: ${product.getUnitPrice()} </span>
			<input name="productQuantity" />
			<input type="submit" name="add" value="Add"/>
		</form>
		<br/>
	</c:forEach>
</body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/order.css">
<title>Order</title>
</head>
<body>
<div>
	<span>ID: ${order.getOrderID()}</span><br/>
	<span>Customer: ${order.getCustomer().getCustomerID()}</span><br/>
	<span>Employee: ${order.getEmployee().getFirstName()} ${order.getEmployee().getLastName()}</span><br/>
	<span>Shipper: ${order.getShipper().getCompanyName()}</span><br/>
	<span>Freight: ${order.getFreight()}</span><br/>
	<span>Order date: ${order.getOrderDate()}</span><br/>
	<span>Required date:  ${order.getRequiredDate()}</span><br/>
	<span>Ship address: ${order.getShipAddress()}</span><br/>
	<span>Ship city: ${order.getShipCity()}</span><br/>
	<span>Ship country: ${order.getShipCountry()}</span><br/>
	<span>Ship name: ${order.getShipName()}</span><br/>
	<span>Shipped date: ${order.getShippedDate()}</span><br/>
	<span>Ship postal code: ${order.getShipPostalCode()}</span><br/>
	<span>Ship region: ${order.getShipRegion()}</span><br/>
	<span>Order details:</span><br/>
	<table>
	<tr>
		<th>Product</th>
		<th>Unit Price</th>
		<th>Quantity</th>
	</tr>
	<c:forEach items="${order.getOrderDetails()}" var="orderDetail">
		<tr>
			<td>${orderDetail.getProduct().getProductName()}</td>
			<td>${orderDetail.getUnitPrice()}</td>
			<td>${orderDetail.getQuantity()}</td>
		</tr>
	</c:forEach>
	</table>
	
</div>

<form action="" method="POST">
	<input style="display:none;" type="text" name="idorder" value="${order.getOrderID()}"/>
	<input type="submit" name="borrar" value="Borrar"/>
	<input type="submit" name="modificar" value="Modificar"/>
</form>
</body>
</html>
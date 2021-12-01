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
	<h1>${customer.getContactName()}</h1>
	<span>ID: ${customer.getCustomerID()}</span><br/>
	<span>Adress: ${customer.getAddress()}</span><br/>
	<span>City: ${customer.getCity()}</span><br/>
	<span>Company Name: ${customer.getCompanyName()}</span><br/>
	<span>Contact Title: ${customer.getContactTitle()}</span><br/>
	<span>Country: ${customer.getCountry()}</span><br/>
	<span>Fax: ${customer.getFax()}</span><br/>
	<span>Phone: ${customer.getPhone()}</span><br/>
	<span>Postal Code: ${customer.getPostalCode()}</span><br/>
	<span>Region: ${customer.getRegion()}</span><br/>
	<span>Lista pedidos:</span>
	<c:forEach items="${customer.getOrders()}" var="order">
   		<li><a href="gestororders?id=${order.getOrderID()}">${order.getRequiredDate()}</a></li>
	</c:forEach>
	<a href="gestororders?customerid="${customer.getCustomerID()}>Crear nuevo pedido</a>
</body>

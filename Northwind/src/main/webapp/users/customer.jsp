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
<form action="gestorcustomers" method="POST">
	<input style="display:none;" type="text" name="idorder" value="${customer.getCustomerID()}"/>
	<input type="text" name="freight" value="${customer.getContactName()}"/>
	<input type="submit" name="borrar" value="Borrar"/>
	<input type="submit" name="modificar" value="Modificar"/>
</form>
</body>

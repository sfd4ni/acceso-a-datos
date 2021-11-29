<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customers</title>
</head>
<body>
	<div>
	<h1>Customers</h1>
	<a href = "../logout">Logout</a>
	<ol>
		<c:forEach items="${listaCustomers}" var="customer">
    		<li><a href="../gestorcustomers?id=${customer.getCustomerID()}">${customer.getContactName()}</a></li>
		</c:forEach>
	</ol>
	</div>
</body>
</html>
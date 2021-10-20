<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenido</title>
</head>
<body>
el dato recibido es:

<c:forEach items="${mensajes}" var="mensaje">
	<c:out value="${mensaje}"/><br/>
</c:forEach>
</body>
</html>
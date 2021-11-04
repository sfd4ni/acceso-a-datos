<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Búsqueda</title>
    </head>
    <body>
        <form action="principal" method="POST">
	  <label for="marca">Introduce el nombre de la marca de lapiz:</label><br>
	  <input type="text" id="marca" name="marca"><br>
	   <input type="submit" value="Submit">
	</form>
	<div>
		<c:forEach items="${lapicesList}" var="lapiz">
			<c:out value="${lapiz.imprimirLapiz()}"/>
			<br />
		</c:forEach>
	</div>
    </body>
</html>

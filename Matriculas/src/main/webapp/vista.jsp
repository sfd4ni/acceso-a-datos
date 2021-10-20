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
		Foro:
		
		<c:forEach items="${listaMensajes}" var="mensaje">
			<span><c:out value="${mensaje.getTexto()}"/>: <c:out value="${mensaje.getNombreUsuario()}"/></span><br/>
		</c:forEach>
		<form action="principal" method="POST"> 
		
			<label for="numero">Introduzca un mensaje:</label>
			<br>
			<label>Usuario: </label>
			<input type="text" name="usuario" id="usuario"  />
			<label> Mensaje: </label>
			<input type="text" name="texto" id="texto"  />
			<br>
			<input type="submit" value="Enviar" />
		                          
		</form>	
	</body>
</html>
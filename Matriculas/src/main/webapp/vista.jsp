<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="vista.css">
		<title>Bienvenido</title>
		</head>
	<body>
		<br/>
		<div class="contenedor">
		<h1>Foro:</h1>
			<div class="elemento areaTexto">
			<textarea class="mensajes" name="w3review" rows="40" cols="200" readonly>
				<c:forEach items="${listaMensajes}" var="mensaje">
					<c:out value="${mensaje.getNombreUsuario()}"/>: <c:out value="${mensaje.getTexto()}"/>
				</c:forEach>
			</textarea>
				
			</div>
			<div class="elemento formulario">
				<form action="principal" method="POST"> 
			
					<span>Introduzca un mensaje:</span>
					<br>
					<label for="usuario">Usuario: </label>
					<input type="text" name="usuario" id="usuario"  />
					<label for="texto"> Mensaje: </label>
					<input type="text" name="texto" id="texto"/>
					<br>
					<input type="submit" value="Enviar" />
				                          
				</form>	
			</div>
		</div>
		
		
	</body>
</html>
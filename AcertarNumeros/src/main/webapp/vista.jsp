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
		<h1>Apuesta por un número:</h1>
			<div class="elemento formulario">
				<form action="principal" method="POST"> 
			
					<span>Introduzca una apuesta:</span>
					<br>
					<label for="apuesta">Adivina: </label>
					<input type="text" name="apuesta" id="usuario"  />
					<br>
					<input type="submit" value="Enviar" />
				                          
				</form>
			</div>
			<div class="elemento areaTexto">
				<c:forEach items="${listaApuestas}" var="mensaje">
					<c:out value="${apuesta}"/>"/>
				</c:forEach>
			</div>
		</div>
		
		
	</body>
</html>
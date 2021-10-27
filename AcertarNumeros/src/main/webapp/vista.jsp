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
			<c:if test="${ganador != null }">
				El último ganador fue: <c:out value="${ganador}"/>,
				a la hora <c:out value="${horaGanador}"/>,
				acertó el número <c:out value="${secretoGanador}"/>
			</c:if>
			<div class="elemento areaTexto">
				<c:forEach items="${mapaApuestas}" var="apuesta">
					<c:choose>
					    <c:when test="${secreto > apuesta.value}">
					        secreto >
					    </c:when>    
					    <c:otherwise>
					        secreto <
					    </c:otherwise>
					</c:choose>
					<c:out value="${apuesta.value}"/>
					<br />
				</c:forEach>
			</div>
		</div>
		
		
	</body>
</html>